package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;

import java.util.*;

public class DepthSorter implements IFilter<Face, Face> {

    private Pipe<Face> predecessor;
    private Pipe<Face> successor;
    private PriorityQueue<Face> faceQueue = new PriorityQueue<>(this::compare);
    private boolean firstRead = true;

    @Override
    public Face read() {
        if (firstRead) {
            this.faceQueue.clear();
            boolean looping = true;
            while (looping) {
                Face prevFace = this.predecessor.read();
                if (prevFace != null) {
                    if (Face.isTerminatingFace(prevFace)) {
                        looping = false;
                    }
                    faceQueue.add(prevFace);
                }
            }
            firstRead = false;
        }

        Face face = faceQueue.poll();
        if (Face.isTerminatingFace(face)) {
            firstRead = true;
        }
        return face;
    }

    @Override
    public void write(Face input) {
        if (!Face.isTerminatingFace(input)) {
            this.faceQueue.add(input);
        } else {
            while (!this.faceQueue.isEmpty()) {
                this.successor.write(this.faceQueue.poll());
            }
        }
    }

    public int compare(Face o1, Face o2) {
        float averageZ1 = (o1.getV1().getZ() + o1.getV2().getZ() + o1.getV3().getZ()) / 3;
        float averageZ2 = (o2.getV1().getZ() + o2.getV2().getZ() + o2.getV3().getZ()) / 3;

        return Float.compare(averageZ1, averageZ2);
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {
        this.successor = successor;
    }

    @Override
    public void setPredecessor(Pipe<Face> predecessor) {
        this.predecessor = predecessor;
    }
}
