package at.fhv.sysarch.lab3.obj;

import javafx.scene.paint.Color;

public class ColoredFace extends Face {

    private Color color;

    public ColoredFace(Face other) {
        this(other, Color.ORANGE);
    }

    public ColoredFace(Face other, Color color) {
        super(other);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
