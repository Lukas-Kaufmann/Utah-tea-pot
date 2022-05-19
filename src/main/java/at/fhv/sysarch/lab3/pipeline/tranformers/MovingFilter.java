package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Vec4;

//TODO delete
public class MovingFilter<I extends Face> implements ITransformer<I, Face> {

    private Vec4 step;
    private Pipe successor;

    public MovingFilter(Vec4 step) {
        this.step = step;
    }

    @Override
    public Face transform(I input) {
        Face newFace = new Face(
                input.getV1().add(step),
                input.getV2().add(step),
                input.getV3().add(step),
                input.getN1(),
                input.getN2(),
                input.getN3()
        );
        return newFace;
    }
}
