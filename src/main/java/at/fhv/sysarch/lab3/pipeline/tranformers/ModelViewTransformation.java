package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.ITransformer;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Mat4;

public class ModelViewTransformation implements ITransformer<Face, Face> {

    private Mat4 modelTranslation;
    private Mat4 viewTransformation;
    private Mat4 rotationMatrix;

    public ModelViewTransformation(Mat4 modelTranslation, Mat4 viewTransformation) {
        this.modelTranslation = modelTranslation;
        this.viewTransformation = viewTransformation;
    }

    public void setRotationMatrix(Mat4 rotationMatrix) {
        this.rotationMatrix = rotationMatrix;
    }

    @Override
    public Face transform(Face face) {
        Mat4 combinedMat = this.viewTransformation.multiply(this.modelTranslation).multiply(this.rotationMatrix);
        Face output = face.multiply(combinedMat);
        return output;
    }
}
