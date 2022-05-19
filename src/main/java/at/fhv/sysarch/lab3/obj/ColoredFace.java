package at.fhv.sysarch.lab3.obj;

import com.hackoeur.jglm.Mat4;
import com.hackoeur.jglm.Vec4;
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

    public ColoredFace(Vec4 v1, Vec4 v2, Vec4 v3, Face otherNormals, Color color) {
        super(v1, v2, v3, otherNormals);
        this.color = color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
