package at.fhv.sysarch.lab3.pipeline.tranformers;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;

public class ModelSource implements IFilter<Model, Face> {
    //TODO generator class
    private Pipe<Face> successor;

    public void write(Model model) {
        for(Face face : model.getFaces()){
            // TODO: write face to next filter
            successor.write(face);
        }
        successor.write(null);
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
    public void setPredecessor(Pipe<Model> predecessor) {

    }
}
