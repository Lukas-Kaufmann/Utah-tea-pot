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
        //TODO
        Vec4 v1 = rotationMatrix.multiply(input.getV1());
        Vec4 v2 = rotationMatrix.multiply(input.getV2());
        Vec4 v3 = rotationMatrix.multiply(input.getV3());
        Vec4 n1 = rotationMatrix.multiply(input.getN1());
        Vec4 n2 = rotationMatrix.multiply(input.getN2());
        Vec4 n3 = rotationMatrix.multiply(input.getN3());

        Face rotated = new Face(v1, v2, v3, n1, n2, n3);
        this.pipeSuccessor.write(rotated);
    }
}
