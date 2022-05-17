package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class RotationAnimationFilter<I extends Face> implements ITransformer<Face, Face> {

    private Mat4 rotationMatrix;
    private Pipe pipeSuccessor;

    public void setRotationMatrix(Mat4 mat) {
        rotationMatrix = mat;
    }

    @Override
    public Face transform(Face face) {
        Face rotated = face.multiply(this.rotationMatrix);
        return rotated;
    }
}
