package org.pfe.mapping.interfaces.views.tables;

import java.io.File;

import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;

public class AppRepositoriesViewLabelProvider  extends StyledCellLabelProvider {
	
	private Image image;
	
	
	public AppRepositoriesViewLabelProvider(Image image) {
		super();
		this.image = image;
	}

	@Override
	public void update(ViewerCell cell) {
		Object element = cell.getElement();
		StyledString text = new StyledString();
		File file = (File) element;
		if (file.isDirectory()) {
			text.append(getFileName(file));
			cell.setImage(image);
			String[] files = file.list();
			if (files != null) {
				text.append(" (" + files.length + ") ",
						StyledString.COUNTER_STYLER);
			}
		} else {
			text.append(getFileName(file));
		}
		cell.setText(text.toString());
		cell.setStyleRanges(text.getStyleRanges());
		super.update(cell);

	}

	private String getFileName(File file) {
		String name = file.getName();
		return name.isEmpty() ? file.getPath() : name;
	}
}
