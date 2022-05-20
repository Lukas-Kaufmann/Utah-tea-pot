package at.fhv.sysarch.lab3.pipeline;

public class Pipe<O> {

    private IFilter<?, O> predecessor;
    private IFilter<O, ?> successor;

    public void setPredecessor(IFilter<?, O> predecessor) {
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
