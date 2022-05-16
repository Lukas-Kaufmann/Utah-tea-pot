package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Mat4;

public class ModelViewTransformation implements IFilter<Face, Face> {

    private Pipe<Face> successor;

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
    public void setPipeSuccessor(Pipe<Face> pipe) {
        this.successor = pipe;
    }

    @Override
    public void write(Face input) {
        Mat4 combinedMat = this.viewTransformation.multiply(this.modelTranslation.multiply(this.rotationMatrix));
        //TODO why does this not work? maybe some camera stuff missing
        Face output = input.multiply(combinedMat);
        this.successor.write(output);
    }
}
