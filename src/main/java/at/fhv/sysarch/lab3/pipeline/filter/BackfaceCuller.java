package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;

public class BackfaceCuller implements IFilter<Face, Face> {

    private Pipe<Face> successor;

    public BackfaceCuller() {
    }

    @Override
    public void setPipeSuccessor(Pipe<Face> pipe) {
        this.successor = pipe;
    }

    @Override
    public void write(Face input) {
        boolean v1Before = input.getV1().dot(input.getN1()) > 0;
        boolean v2Before = input.getV2().dot(input.getN2()) > 0;
        boolean v3Before = input.getV3().dot(input.getN3()) > 0;

        if (v1Before || v2Before || v3Before) {
            this.successor.write(input);
        }
    }
}
