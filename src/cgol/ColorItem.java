package cgol;

import java.awt.Color;

public class ColorItem {
	private Color color;
    private String label;

    public ColorItem(Color color, String label) {
        this.color = color;
        this.label = label;
    }

    public Color getColor() {
        return this.color;
    }

    public String toString() {
        return label;
    }
}