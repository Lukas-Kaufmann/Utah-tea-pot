package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;

public class ColorTransformer implements ITransformer<Face, ColoredFace> {

    private javafx.scene.paint.Color baseColor;

    public ColorTransformer(javafx.scene.paint.Color baseColor) {
        this.baseColor = baseColor;
    }


    @Override
    public ColoredFace transform(Face face) {
        return new ColoredFace(face, baseColor);
    }
}
