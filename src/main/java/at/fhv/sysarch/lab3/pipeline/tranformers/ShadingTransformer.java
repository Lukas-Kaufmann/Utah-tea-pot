package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import com.hackoeur.jglm.Vec3;
import com.hackoeur.jglm.Vec4;
import javafx.scene.paint.Color;

public class ShadingTransformer implements ITransformer<ColoredFace, ColoredFace> {

    private Vec3 lightPos;

    public ShadingTransformer(Vec3 lightPos) {
        this.lightPos = lightPos;
    }

    @Override
    public ColoredFace transform(ColoredFace coloredFace) {
        Color baseColor = coloredFace.getColor();
        //TODO better center for face
        Vec3 faceToLight = this.lightPos.subtract(coloredFace.getV1().toVec3());
        double factor = faceToLight.getUnitVector().dot(coloredFace.getN3().toVec3());
        double factorWithBaseBrightness = 0.2 + 0.8 * factor;
        Color shadedColor = baseColor.deriveColor(0, 1, factorWithBaseBrightness, 1);
        coloredFace.setColor(shadedColor);
        return coloredFace;
    }
}
