package at.fhv.sysarch.lab3.pipeline;

// TODO: how can pipes be used for different data types?
public class Pipe<O> {

    private IFilter<?, O> predecessor;
    private IFilter<O, ?> successor;

    public void setPredecessor(Filter<?, O> predecessor) {
        this.predecessor = predecessor;
    }

    public void setSuccessor(IFilter<O, ?> successor) {
        this.successor = successor;
    }

    public void write(O o) {
        this.successor.write(o);
    }

    public O read() {
        return this.predecessor.read();
    }

}
