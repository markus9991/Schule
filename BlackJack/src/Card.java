
public class Card {

	private int color;
	private int value;

	public Card(int color, int value) {
		this.color = color;
		this.value = value;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String toString() {
		return "Farbe: " + color + "| Wert: " + value + "\n";
	}
}