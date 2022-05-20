package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import com.hackoeur.jglm.Vec4;

import java.util.*;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class DepthSorter implements IFilter<Face, Face>, Comparator<Face> {

    private Pipe<Face> predecessor;
    private Pipe<Face> successor;

    private Iterator<Face> faceIterator;
    private boolean firstRead = true;

    public DepthSorter() {
    }

    @Override
    public Face read() {
        if (firstRead) {
            SortedSet<Face> faces = new TreeSet<Face>(this::compare);
            boolean looping = true;
            while (looping) {
                Face prevFace = this.predecessor.read();
                if (prevFace != null) {
                    if (prevFace.getN1().equals(Vec4.VEC4_ZERO)) { //TODO better terminator
                        looping = false;
                    }
                    faces.add(prevFace);
                }
            }
            this.faceIterator = faces.iterator();
            firstRead = false;
        }

        Face face = faceIterator.next();
        if (face.getN1().equals(Vec4.VEC4_ZERO)) { //TODO better terminator
            firstRead = true;
        }
        return face;
    }

    @Override
    public void write(Face input) {
        //TODO
    }

    @Override
    public int compare(Face o1, Face o2) {
        return (int) ((o1.getV1().getZ() - o2.getV1().getZ()) * 100 + 0.5);
    }


    @Override
    public Comparator<Face> reversed() {
        return Comparator.super.reversed();
    }

    @Override
    public Comparator<Face> thenComparing(Comparator<? super Face> other) {
        return Comparator.super.thenComparing(other);
    }

    @Override
    public <U> Comparator<Face> thenComparing(Function<? super Face, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        return Comparator.super.thenComparing(keyExtractor, keyComparator);
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<Face> thenComparing(Function<? super Face, ? extends U> keyExtractor) {
        return Comparator.super.thenComparing(keyExtractor);
    }

    @Override
    public Comparator<Face> thenComparingInt(ToIntFunction<? super Face> keyExtractor) {
        return Comparator.super.thenComparingInt(keyExtractor);
    }

    @Override
    public Comparator<Face> thenComparingLong(ToLongFunction<? super Face> keyExtractor) {
        return Comparator.super.thenComparingLong(keyExtractor);
    }

    @Override
    public Comparator<Face> thenComparingDouble(ToDoubleFunction<? super Face> keyExtractor) {
        return Comparator.super.thenComparingDouble(keyExtractor);
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {
        this.successor = successor;
    }

    @Override
    public void setPredecessor(Pipe<Face> predecessor) {
        this.predecessor = predecessor;
    }
}
