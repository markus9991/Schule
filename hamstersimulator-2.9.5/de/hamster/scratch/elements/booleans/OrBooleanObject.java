package de.hamster.scratch.elements.booleans;

import java.awt.Graphics;

import de.hamster.scratch.BoundingBox;
import de.hamster.scratch.DockingBox;
import de.hamster.scratch.Renderable;
import de.hamster.scratch.ScratchProgram;
import de.hamster.scratch.ScratchUtils;
import de.hamster.scratch.elements.BooleanObject;
import de.hamster.scratch.elements.voids.FunctionResultException;
import de.hamster.workbench.Utils;

public class OrBooleanObject extends BooleanObject {
	public static int START_WIDTH = 8;
	public static int TEXT_SPACING = 6;
	public static int MIN_WIDTH = 25;
	public static int MIN_HEIGHT = 13;
	public static String TEXT = Utils.getOr();

	private int textWidth;

	public OrBooleanObject() {
		super(false);

		this.textWidth = ScratchUtils.getTextWidth(OrBooleanObject.TEXT,
				BooleanObject.TEXT_FONT);

		this.maxChilds = 2;
		DockingBox leftBool = new DockingBox(OrBooleanObject.START_WIDTH, 2,
				OrBooleanObject.MIN_WIDTH, OrBooleanObject.MIN_HEIGHT,
				OrBooleanObject.START_WIDTH, 2, RType.BOOLEAN);
		this.dockings.put(0, leftBool);
		DockingBox rightBool = new DockingBox(0, 0, 0, 0, 0, 0, RType.BOOLEAN);
		this.dockings.put(1, rightBool);

		this.updateBounds();
	}

	@Override
	public String getName() {
		return "oder";
	}

	@Override
	public void render(Graphics g) {
		super.render(g);

		g.setColor(BooleanObject.COLOR_TEXT);
		g.setFont(BooleanObject.TEXT_FONT);
		int textHeight = 7;
		g.drawString(OrBooleanObject.TEXT, this.bounding.getX()
				+ OrBooleanObject.START_WIDTH + this.dockings.get(0).getWidth()
				+ OrBooleanObject.TEXT_SPACING, this.bounding.getY()
				+ textHeight + (this.bounding.getHeight() - textHeight) / 2);
	}

	@Override
	public void updateBounds() {
		int w = 2
				* (OrBooleanObject.START_WIDTH + OrBooleanObject.TEXT_SPACING)
				+ this.textWidth;
		int h = OrBooleanObject.MIN_HEIGHT;

		// Update breiten und H�hen der DockingBoxen
		for (int i = 0; i < this.maxChilds; i++) {
			if (this.childs.get(i) != null) {
				BoundingBox temp = this.childs.get(i).getGlobalBounding();
				w += temp.getWidth();
				h = h < temp.getHeight() + 4 ? temp.getHeight() + 4 : h;
				this.dockings.get(i).setSize(temp.getWidth(), temp.getHeight());
			} else {
				w += this.dockings.get(i).getType().getWidth();
				h = h < this.dockings.get(i).getType().getHeight() + 4 ? this.dockings
						.get(i).getType().getHeight() + 4
						: h;
				this.dockings.get(i).setSize(
						this.dockings.get(i).getType().getWidth(),
						this.dockings.get(i).getType().getHeight());
			}
		}

		// Position der rechten DockBox
		this.dockings.get(1).setPosition(
				OrBooleanObject.START_WIDTH + OrBooleanObject.TEXT_SPACING * 2
						+ this.textWidth + this.dockings.get(0).getWidth(), 0);
		this.dockings.get(1).setDockX(
				OrBooleanObject.START_WIDTH + OrBooleanObject.TEXT_SPACING * 2
						+ this.textWidth + this.dockings.get(0).getWidth());

		// Verticale Ausrichtung der Dockings anpassen
		for (int i = 0; i < this.maxChilds; i++) {
			this.dockings.get(i).setPosition(this.dockings.get(i).getX(),
					(h - this.dockings.get(i).getHeight()) / 2);
			this.dockings.get(i).setDockY(
					(h - this.dockings.get(i).getHeight()) / 2);
		}

		this.bounding.setSize(w, h);

		if (this.parent != null) {
			this.parent.updateBounds();
		}
	}

	@Override
	public Renderable clone() {
		OrBooleanObject temp = new OrBooleanObject();
		return temp;
	}

	@Override
	public Object performImplementation(ScratchProgram program)
			throws FunctionResultException {
		// Linkes Boolean auswerten
		boolean leftBool = true;
		if (this.childs.get(0) != null) {
			leftBool = (Boolean) this.childs.get(0).perform(program);
		}
		
		if (leftBool) { // u.U. fr�hzeitiger Ausstieg
			return true;
		}

		// Rechtes Boolean auswerten
		boolean rightBool = true;
		if (this.childs.get(1) != null) {
			rightBool = (Boolean) this.childs.get(1).perform(program);
		}

		// Gesamte Auswertung
		return leftBool || rightBool;
	}
	
	@Override
	public void writeSourceCode(StringBuffer buffer, int layer, boolean comment, boolean needsReturn) throws FunctionResultException {
		startLine(buffer, layer, comment);
		buffer.append("(");
		
		if (childs.get(0) == null)
			buffer.append("true");
		else
			childs.get(0).writeSourceCode(buffer, 0, false, false);
		
		buffer.append(" || ");
		
		if (childs.get(1) == null)
			buffer.append("true");
		else
			childs.get(1).writeSourceCode(buffer, 0, false, false);
		
		buffer.append(")");
	}
}
