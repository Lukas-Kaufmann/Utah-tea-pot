package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Vec4;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class DepthSorter implements IFilter<Face, Face> {

    private Pipe<Face> predecessor;
    private Pipe<Face> successor;

    private Iterator<Face> faceIterator;
    private boolean firstRead = true;

    public DepthSorter() {
    }

    @Override
    public Face read() {
        if (firstRead) {
            PriorityQueue<Face> faceQueue = new PriorityQueue<>(this::compare);
            boolean looping = true;
            while (looping) {
                Face prevFace = this.predecessor.read();
                if (prevFace != null) {
                    if (prevFace.getN1().equals(Vec4.VEC4_ZERO)) { //TODO better terminator
                        looping = false;
                    }
                    faceQueue.add(prevFace);
                }
            }
            this.faceIterator = faceQueue.iterator();
            firstRead = false;
        }

        Face face = faceIterator.next();
        if (face.getN1().equals(Vec4.VEC4_ZERO)) { //TODO better terminator
            firstRead = true;
        }
        return face;
    }

    @Override
    public void write(Face input) {
        //TODO
    }

    public int compare(Face o1, Face o2) {
        float averageZ1 = (o1.getV1().getZ() + o1.getV2().getZ() + o1.getV3().getZ()) / 3;
        float averageZ2 = (o2.getV1().getZ() + o2.getV2().getZ() + o2.getV3().getZ()) / 3;

//        return new Random().nextInt(2) - 1;
        return (int) ((averageZ1 - averageZ2) * 100);
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
