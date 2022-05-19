package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import at.fhv.sysarch.lab3.pipeline.ITransformer;



public class BackfaceCuller implements ITransformer<Face, Face> {

    private Pipe<Face> successor;

    public BackfaceCuller() {
    }

    @Override
    public Face transform(Face face) {
        boolean v1Behind = face.getV1().dot(face.getN1()) > 0;
        boolean v2Behind = face.getV2().dot(face.getN2()) > 0;
        boolean v3Behind = face.getV3().dot(face.getN3()) > 0;

        if (!(v1Behind && v2Behind && v3Behind)) {
            return face;
        }
        return null;
    }
}
