package at.fhv.sysarch.lab3.pipeline;

public interface ITransformer<I, O> {

    O transform(I i);

}
