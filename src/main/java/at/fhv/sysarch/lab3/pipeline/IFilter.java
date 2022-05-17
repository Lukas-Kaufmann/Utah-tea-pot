package at.fhv.sysarch.lab3.pipeline;

// TODO: Think about how generics can be applied in this context
// TODO: The current solution is JUST an illustration and not sufficient for the example. It only shows how generics may be used.
// TODO: Can you use one interface for both implementations (push and pull)? Or do they require specific interfaces?
public interface IFilter<I, O> {

    public static <A, B> Filter<A, B> ofTransformer(ITransformer<A, B> t) {
        return new Filter<>(t);
    }
    O read();

    void write(I input);

    void setSuccessor(Pipe<O> successor);

    void setPredecessor(Pipe<I> predecessor);
}
