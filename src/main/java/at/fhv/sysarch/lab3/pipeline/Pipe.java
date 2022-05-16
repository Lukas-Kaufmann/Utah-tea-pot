package at.fhv.sysarch.lab3.pipeline;

import at.fhv.sysarch.lab3.obj.Face;
import com.hackoeur.jglm.Vec4;

// TODO: how can pipes be used for different data types?
public class Pipe<O> {

    private IFilter successor;

    public void setSuccessor(IFilter sink) {
        this.successor = sink;
    }

    public void write(O o) {
        this.successor.write(o);
    }

}
