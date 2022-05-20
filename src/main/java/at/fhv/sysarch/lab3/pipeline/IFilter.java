package at.fhv.sysarch.lab3.pipeline;

public interface IFilter<I, O> {

    O read();

    void write(I input);

    void setSuccessor(Pipe<O> successor);

    void setPredecessor(Pipe<I> predecessor);
}
