package at.fhv.sysarch.lab3.pipeline;

public class Filter<I, O> implements IFilter<I, O> {

    private Pipe<O> successor;
    private Pipe<I> predecessor;
    private ITransformer<I, O> transformer;

    public static <A, B> Filter<A, B> ofTransformer(ITransformer<A, B> t) {
        return new Filter<>(t);
    }

    public Filter(ITransformer<I, O> transformer) {
        this.transformer = transformer;
    }

    public O read() {
        return this.transformer.transform(this.predecessor.read());
    }

    public void write(I i) {
        O result = this.transformer.transform(i);
        if (result != null) {
            this.successor.write(result);
        }
    }


    public void setSuccessor(Pipe<O> successor) {
        this.successor = successor;
    }

    public void setPredecessor(Pipe<I> predecessor) {
        this.predecessor = predecessor;
    }
}
