package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import at.fhv.sysarch.lab3.pipeline.ITransformer;



public class BackfaceCuller implements ITransformer<Face, Face> {

    private Pipe<Face> successor;

    public BackfaceCuller() {
    }

    @Override
    public Face transform(Face face) {
        boolean v1Before = face.getV1().dot(face.getN1()) < 0;
        boolean v2Before = face.getV2().dot(face.getN2()) < 0;
        boolean v3Before = face.getV3().dot(face.getN3()) < 0;

        if (v1Before || v2Before || v3Before) {
            return face;
        }
        return null;
    }
}
