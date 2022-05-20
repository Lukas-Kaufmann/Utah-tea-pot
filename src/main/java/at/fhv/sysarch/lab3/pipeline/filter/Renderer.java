package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.ColoredFace;
import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.scene.canvas.GraphicsContext;

public class Renderer implements IFilter<ColoredFace, Face> {
    private final GraphicsContext context;
    private final RenderingMode rm;

    private Pipe<ColoredFace> predecessor;

    public Renderer(GraphicsContext context, RenderingMode rm) {
        this.context = context;
        this.rm = rm;
    }

    @Override
    public Face read() {
        boolean looping = true;
        while (looping) {
            ColoredFace face = this.predecessor.read();
            if (face != null) {
                this.paint(face);
                if (Face.isTerminatingFace(face)) {
                    looping = false;
                }
            }
        }
        return null;
    }

    public void write(ColoredFace face) {
        this.paint(face);
    }

    private void paint(ColoredFace face) {
        context.setFill(face.getColor());
        context.setStroke(face.getColor());

        if(rm == RenderingMode.POINT) {
            context.fillOval(face.getV1().getX(), face.getV1().getY(), 5,5);
        } else {
            final double[] xPoints = new double[] {face.getV1().getX(), face.getV2().getX(), face.getV3().getX()};
            final double[] yPoints = new double[] {face.getV1().getY(), face.getV2().getY(), face.getV3().getY()};
            context.strokePolygon(xPoints, yPoints, 3);
            if (rm == RenderingMode.FILLED) {
                context.fillPolygon(xPoints, yPoints, 3);
            }
        }
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setPredecessor(Pipe<ColoredFace> predecessor) {
        this.predecessor = predecessor;
    }
}
