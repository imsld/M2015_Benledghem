package org.pfe.mapping.interfaces;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class AcceuilWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public AcceuilWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new AcceuilActionBarAdvisor(configurer);
	}

	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 600));
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(false);
		
		configurer.setTitle("PFE - Syetème d'alignement d'ontologies");//$NON-NLS-1$
	}
}
