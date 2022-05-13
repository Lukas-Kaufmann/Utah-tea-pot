package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Vec4;

public class MovingFilter<I extends Face> implements IFilter<I> {

    private Vec4 step;
    private Pipe successor;

    public MovingFilter(Vec4 step) {
        this.step = step;
    }

    @Override
    public void setPipeSuccessor(Pipe pipe) {
        this.successor = pipe;
    }

    @Override
    public void write(I input) {
        Face newFace = new Face(
                input.getV1().add(step),
                input.getV2().add(step),
                input.getV3().add(step),
                input.getN1(),
                input.getN2(),
                input.getN3()
        );
        this.successor.write(newFace);
    }
}
