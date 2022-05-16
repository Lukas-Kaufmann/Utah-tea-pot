package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;

public class ScalerFilter<I extends Face> implements IFilter<I, Face> {

    private Pipe pipeSuccessor;

    public void setPipeSuccessor(Pipe pipe) {
        this.pipeSuccessor = pipe;
    }

    public void write(I face) {
        Face newFace = new Face(face.getV1().multiply(100), face.getV2().multiply(100), face.getV3().multiply(100), face);
        this.pipeSuccessor.write(newFace);
    }
}
