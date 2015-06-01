package org.pfe.mapping.interfaces.views;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;

public class ViewAlignementResult extends ViewPart {
	public ViewAlignementResult() {
	}
	
	public static final String ID = "org.pfe.mapping.interafces.views.ViewAlignementResult"; //$NON-NLS-1$

	public CTabFolder tabFolder;
	public CTabItem tbtmResult;
	public CTabItem tbtmAlignment;

	
	@Override
	public void createPartControl(Composite parent) { 
		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setTabPosition(SWT.BOTTOM);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		Font boldFont = JFaceResources.getFontRegistry().getBold(
				JFaceResources.DEFAULT_FONT);

		tbtmResult = new CTabItem(tabFolder, SWT.NONE);
		tbtmResult.setText("R\u00E9sultats");

		tabFolder.setSelection(tbtmResult);

		tbtmAlignment = new CTabItem(tabFolder, SWT.NONE);
		tbtmAlignment.setText("Alignement");

	}

	@Override
	public void setFocus() {
		// Set the focus
	}

}
