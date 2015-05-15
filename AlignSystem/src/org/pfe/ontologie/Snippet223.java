package org.pfe.ontologie;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

public class Snippet223 {

public static void main (String [] args) {
	Display display = new Display ();
	Shell shell = new Shell (display);
	shell.setLayout(new FillLayout());
	shell.setText("ExpandBar Example");
	ExpandBar bar = new ExpandBar (shell, SWT.V_SCROLL);
	Image image = display.getSystemImage(SWT.ICON_QUESTION);
	
	// First item
	Composite composite = new Composite (bar, SWT.NONE);
	GridLayout layout = new GridLayout ();
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
	layout.verticalSpacing = 10;
	composite.setLayout(layout);
	Button button = new Button (composite, SWT.PUSH);
	button.setText("SWT.PUSH");
	button = new Button (composite, SWT.RADIO);
	button.setText("SWT.RADIO");
	button = new Button (composite, SWT.CHECK);
	button.setText("SWT.CHECK");
	button = new Button (composite, SWT.TOGGLE);
	button.setText("SWT.TOGGLE");
	
	ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
	item0.setText("What is your favorite button");
	item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	item0.setControl(composite);
	item0.setImage(image);
	ExpandItem item1 = new ExpandItem (bar, SWT.NONE, 1);
	item1.setText("What is your favorite icon");
	item1.setImage(image);
	
	// Third item
	composite = new Composite (bar, SWT.NONE);
	layout = new GridLayout (2, true);
	layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
	layout.verticalSpacing = 10;
	composite.setLayout(layout);
	new Scale (composite, SWT.NONE);
	new Spinner (composite, SWT.BORDER);
	new Slider (composite, SWT.NONE);
	ExpandItem item2 = new ExpandItem (bar, SWT.NONE, 2);
	item2.setText("What is your favorite range widget");
	item2.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
	item2.setControl(composite);
	item2.setImage(image);
	
	item1.setExpanded(true);
	bar.setSpacing(8);
	shell.setSize(400, 350);
	shell.open();
	while (!shell.isDisposed ()) {
		if (!display.readAndDispatch ()) {
			display.sleep ();
		}
	}
	image.dispose();
	display.dispose();
}

}