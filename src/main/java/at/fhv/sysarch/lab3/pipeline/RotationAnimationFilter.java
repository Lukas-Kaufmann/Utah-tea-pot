package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class RotationAnimationFilter<I extends Face> implements IFilter<Face> {

    private Mat4 rotationMatrix;
    private Pipe pipeSuccessor;

    public void setRotationMatrix(Mat4 mat) {
        rotationMatrix = mat;
    }

    public void setPipeSuccessor(Pipe pipe) {
        this.pipeSuccessor = pipe;
    }

    @Override
    public void write(Face input) {
        Face rotated = input.multiply(this.rotationMatrix);
        this.pipeSuccessor.write(rotated);
    }
}
