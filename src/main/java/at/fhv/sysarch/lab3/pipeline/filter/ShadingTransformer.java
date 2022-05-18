package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import com.hackoeur.jglm.Vec3;
import com.hackoeur.jglm.Vec4;
import javafx.scene.paint.Color;

public class ShadingTransformer implements ITransformer<ColoredFace, ColoredFace> {

    private Vec3 lightPos;

    public ShadingTransformer(Vec3 lightPos) {
        this.lightPos = lightPos.multiply(-100);
    }

    @Override
    public ColoredFace transform(ColoredFace coloredFace) {
        Color baseColor = coloredFace.getColor();
        //TODO change brightness based on angle
        Color shadedColor = baseColor.deriveColor(0, 1, 1, 1);
        coloredFace.setColor(shadedColor);
        return coloredFace;
    }
}
