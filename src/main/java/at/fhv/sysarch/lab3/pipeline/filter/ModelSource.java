package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;

public class ModelSource<I extends Model> implements IFilter<I, Face> {
    //TODO generator class
    private Pipe<Face> successor;

    public void write(I model) {
        for(Face face : model.getFaces()){
            // TODO: write face to next filter
            successor.write(face);
        }
    }

    @Override
    public Face read() {
        return null;
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {
        this.successor = successor;
    }

    @Override
    public void setPredecessor(Pipe<I> predecessor) {

    }
}
