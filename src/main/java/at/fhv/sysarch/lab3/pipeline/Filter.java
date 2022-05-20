package at.fhv.sysarch.lab3.pipeline;

public class Filter<I, O> implements IFilter<I, O> {

    private Pipe<O> successor;
    private Pipe<I> predecessor;
    private ITransformer<I, O> transformer;


    private Filter(ITransformer<I, O> transformer) {
        this.transformer = transformer;
    }

    public static <A, B> IFilter<A, B> ofTransformer(ITransformer<A, B> t) {
        return new Filter<>(t);
    }

    public O read() {
        I prev = this.predecessor.read();
        if (prev == null) {
            return null;
        }
        return this.transformer.transform(prev);
    }

    public void write(I i) {
        if (i != null) {
            O result = this.transformer.transform(i);
            if (result != null) {
                this.successor.write(result);
            }
        }
    }

    public void setSuccessor(Pipe<O> successor) {
        this.successor = successor;
    }

    public void setPredecessor(Pipe<I> predecessor) {
        this.predecessor = predecessor;
    }
}
