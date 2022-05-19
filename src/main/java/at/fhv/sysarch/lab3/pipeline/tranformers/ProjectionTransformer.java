package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import com.hackoeur.jglm.Mat4;

public class ProjectionTransformer implements ITransformer<ColoredFace, ColoredFace> {

    private Mat4 projMatrix;

    public ProjectionTransformer(Mat4 projMatrix) {
        this.projMatrix = projMatrix;
    }

    @Override
    public ColoredFace transform(ColoredFace coloredFace) {
        Face transformed = coloredFace.multiply(projMatrix);
        return new ColoredFace(transformed, coloredFace.getColor());
    }
}
