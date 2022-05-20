package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.obj.Model;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Vec4;

import java.util.Iterator;

public class ModelSource implements IFilter<Model, Face> {
    private Pipe<Face> successor;

    private Iterator<Face> iterator;

    public ModelSource() {
    }

    public void setModel(Model model) {
        this.iterator = model.getFaces().iterator();
    }

    public void write(Model stepModel) {
        for(Face face : stepModel.getFaces()){
            successor.write(face);
        }
        //remove if deepsorting wont be implemented in push factory
//        new Face(Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO);
    }

    @Override
    public Face read() {
        if (this.iterator.hasNext()) {
            return this.iterator.next();
        }
        return new Face(Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO, Vec4.VEC4_ZERO);
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {
        this.successor = successor;
    }

    @Override
    public void setPredecessor(Pipe<Model> predecessor) {

    }
}
