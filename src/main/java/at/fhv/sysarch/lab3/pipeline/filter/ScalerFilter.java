package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import at.fhv.sysarch.lab3.pipeline.Pipe;

public class ScalerFilter<I extends Face> implements ITransformer<I, Face> {

    private Pipe<Face> pipeSuccessor;

    private float factor;

    public ScalerFilter(float factor) {
        this.factor = factor;
    }

    @Override
    public Face transform(I input) {
        Face newFace = new Face(input.getV1().multiply(this.factor), input.getV2().multiply(this.factor), input.getV3().multiply(this.factor), input);
        return newFace;
    }
}
