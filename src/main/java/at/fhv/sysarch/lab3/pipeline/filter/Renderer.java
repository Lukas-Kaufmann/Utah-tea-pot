package at.fhv.sysarch.lab3.pipeline.filter;

import at.fhv.sysarch.lab3.obj.Face;
import at.fhv.sysarch.lab3.pipeline.IFilter;
import at.fhv.sysarch.lab3.pipeline.Pipe;
import at.fhv.sysarch.lab3.rendering.RenderingMode;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Renderer<I extends Face> implements IFilter<I, Face> {
    private GraphicsContext context;
    private RenderingMode rm;

    private Color color;

    public Renderer(GraphicsContext context, RenderingMode rm, Color color) {
        this.context = context;
        this.rm = rm;
        this.color = color;
    }

    @Override
    public void setPipeSuccessor(Pipe pipe) {
        // TODO: think about how you organize your interfaces
    }

    public void write(I face) {
        context.setFill(this.color);
        context.setStroke(this.color);

        if(rm == RenderingMode.POINT) {
            context.fillOval(face.getV1().getX(), face.getV1().getY(), 5,5);
        }
        else if (rm == RenderingMode.FILLED) {
            final double[] xPoints = new double[] {face.getV1().getX(), face.getV2().getX(), face.getV3().getX()};
            final double[] yPoints = new double[] {face.getV1().getY(), face.getV2().getY(), face.getV3().getY()};
            context.fillPolygon(xPoints, yPoints, 3);
        }
        else {
            context.strokeLine(face.getV1().getX(), face.getV1().getY(), face.getV2().getX(), face.getV2().getY());
            context.strokeLine(face.getV1().getX(), face.getV1().getY(), face.getV3().getX(), face.getV3().getY());
            context.strokeLine(face.getV2().getX(), face.getV2().getY(), face.getV3().getX(), face.getV3().getY());
        }
    }
}
