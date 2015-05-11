package org.pfe.mapping.interfaces.views;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

public class ViewParameters extends ViewPart {
	public ViewParameters() {
	}	

	public static final String ID = "org.pfe.mapping.interfaces.views.ViewParameters"; //$NON-NLS-1$

	@PostConstruct
	public void createPartControl(Composite parent) {
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
