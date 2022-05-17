package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import javafx.scene.paint.Color;

public class ShadingTransformer implements ITransformer<Face, ColoredFace> {

    private Color baseColor;

    public ShadingTransformer(Color baseColor) {
        this.baseColor = baseColor;
    }


    @Override
    public ColoredFace transform(Face face) {
        //TODO add shading
        return new ColoredFace(face, baseColor);
    }
}
