package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Mat4;

public class ViewPortTransformation implements IFilter<Face, Face> {

    private Pipe<Face> successor;
    private Mat4 viewPortMatrix;

    public ViewPortTransformation(Mat4 viewPortMatrix) {
        this.viewPortMatrix = viewPortMatrix;
    }

    @Override
    public void setPipeSuccessor(Pipe<Face> pipe) {
        this.successor = pipe;
    }

    @Override
    public void write(Face input) {
        //TODO
        Face output = input;
        this.successor.write(output);
    }
}
