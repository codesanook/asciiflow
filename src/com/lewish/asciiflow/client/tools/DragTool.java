//Copyright Lewis Hemens 2011
package com.lewish.asciiflow.client.tools;

import com.lewish.asciiflow.client.Canvas;
import com.lewish.asciiflow.client.Cell;
import com.lewish.asciiflow.client.Drag;
import com.lewish.asciiflow.client.HistoryManager;
import com.lewish.asciiflow.client.Tool;
import com.lewish.asciiflow.client.resources.AsciiflowClientBundle;

public abstract class DragTool extends Tool {

	public DragTool(Canvas canvas, HistoryManager historyManager, AsciiflowClientBundle clientBundle) {
		super(canvas, historyManager, clientBundle);
	}

	protected Drag currentBox;

	@Override
	public void mouseOver(Cell cell) {
		if (currentBox != null) {
			currentBox.setFinish(cell);
			draw(currentBox);
			refreshDraw();
		}
	}

	@Override
	public void mouseDown(Cell cell) {
		if (currentBox == null) {
			currentBox = new Drag(cell);
			draw(currentBox);
			refreshDraw();
		}
	}

	@Override
	public void mouseUp(Cell cell) {
		if (currentBox != null) {
			currentBox.setFinish(cell);
			currentBox = null;
			commitDraw();
		}
	}

	@Override
	public void cleanup() {
		if (currentBox != null) {
			canvas.clearDraw();
			currentBox = null;
		}
	}

	protected abstract void draw(Drag box);
}