package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer<I extends Face> implements IFilter<I, Face> {
    //TODO special consumer class
    private final GraphicsContext context;
    private final RenderingMode rm;
    private final Color color;

    public Renderer(GraphicsContext context, RenderingMode rm, Color color) {
        this.context = context;
        this.rm = rm;
        this.color = color;
    }

    @Override
    public Face read() {
        return null;
    }

    public void write(I face) {
        context.setFill(this.color);
        context.setStroke(this.color);

        if(rm == RenderingMode.POINT) {
            context.fillOval(face.getV1().getX(), face.getV1().getY(), 5,5);
        } else {
            final double[] xPoints = new double[] {face.getV1().getX(), face.getV2().getX(), face.getV3().getX()};
            final double[] yPoints = new double[] {face.getV1().getY(), face.getV2().getY(), face.getV3().getY()};
            if (rm == RenderingMode.FILLED) {
                context.fillPolygon(xPoints, yPoints, 3);
            } else {
                context.strokePolygon(xPoints, yPoints, 3);
            }
        }
    }

    @Override
    public void setSuccessor(Pipe<Face> successor) {

    }

    @Override
    public void setPredecessor(Pipe<I> predecessor) {
        //TODO
    }
}
