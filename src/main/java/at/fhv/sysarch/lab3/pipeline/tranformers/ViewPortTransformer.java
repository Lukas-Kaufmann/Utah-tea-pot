package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;

public class ViewPortTransformer implements ITransformer<ColoredFace, ColoredFace> {

    private Mat4 viewPortTrans;

    public ViewPortTransformer(Mat4 viewPortTrans) {
        this.viewPortTrans = viewPortTrans;
    }

    @Override
    public ColoredFace transform(ColoredFace face) {
        Vec4 v1 = face.getV1().multiply(1.0f / face.getV1().getW());
        Vec4 v2 = face.getV2().multiply(1.0f / face.getV2().getW());
        Vec4 v3 = face.getV3().multiply(1.0f / face.getV3().getW());

        Face dividedFace = new Face(v1, v2, v3, face);
        Face transformedFace = dividedFace.multiply(this.viewPortTrans);
        ColoredFace newFace = new ColoredFace(transformedFace, face.getColor());

        return newFace;
    }
}
