package org.pfe.mapping.interfaces.views;

import java.io.File;
import java.io.FileFilter;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.GridData;
import org.pfe.mapping.alignement.Alignement;
import org.pfe.mapping.alignement.semantics.SemanticSimilarityWords;
import org.pfe.mapping.interfaces.Acceuil;


public class ViewAlignement extends ViewPart {

	public ViewAlignement() {
	}

	public static final String ID = "org.pfe.mapping.interafces.views.ViewAlignement"; //$NON-NLS-1$
	public CheckboxTableViewer ctv1;
	public String name="";
	
	@PostConstruct
	public void createPartControl(Composite parent) {
		FillLayout fillLayout = (FillLayout) parent.getLayout();
		fillLayout.marginHeight = 1;
		fillLayout.marginWidth = 1;
		fillLayout.spacing = 1;

		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Group group = new Group(composite_1, SWT.NONE);
		group.setLayout(new GridLayout(1, false));

		Composite composite_3 = new Composite(group, SWT.BORDER);
		composite_3.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		composite_3.setLayout(new GridLayout(3, false));
		
		ctv1 = CheckboxTableViewer.newCheckList(group, SWT.BORDER);
		ctv1.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		ctv1.setContentProvider(new BackupFilesContentProvider());
	    ctv1.setLabelProvider(new BackupFilesLabelProvider());

		Composite panel0 = new Composite(group, SWT.BORDER);
		panel0.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		panel0.setLayout(new GridLayout(1, false));

		// Create the controls

		Button aligner0 = new Button(panel0, SWT.PUSH);
		aligner0.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		aligner0.setText("Lancer alignement");

		Button button = new Button(composite_3, SWT.NONE);
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false,
				1, 1));
		button.setText("Charger entrep\u00F4t");

		Button button_1 = new Button(composite_3, SWT.NONE);
		button_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		button_1.setText("Tout s\u00E9lectionner");

		Button button_2 = new Button(composite_3, SWT.NONE);
		button_2.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		button_2.setText("Tout d\u00E9s\u00E9lectionner");

		Composite composite_2 = new Composite(parent, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		Group group_1 = new Group(composite_2, SWT.NONE);

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		Group group_2 = new Group(composite, SWT.NONE);
		group_2.setLayout(new GridLayout(1, false));
		
		ExpandBar expandBar = new ExpandBar(group_2, SWT.NONE);
		expandBar.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		
		ExpandItem xpndtmNewExpanditem = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setExpanded(true);
		xpndtmNewExpanditem.setText("New ExpandItem");
		
		Composite composite_4 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem.setControl(composite_4);
		xpndtmNewExpanditem.setHeight(xpndtmNewExpanditem.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		composite_4.setLayout(new FillLayout(SWT.VERTICAL));
		
		Button btnRadioButton = new Button(composite_4, SWT.RADIO);
		btnRadioButton.setText("Radio Button");
		
		Button btnRadioButton_1 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_1.setText("Radio Button");
		
		Button btnRadioButton_2 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_2.setText("Radio Button");
		
		Button btnRadioButton_3 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_3.setText("Radio Button");
		
		Button btnRadioButton_4 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_4.setText("Radio Button");
		
		Button btnRadioButton_5 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_5.setText("Radio Button");
		
		Button btnRadioButton_6 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_6.setText("Radio Button");
		
		Button btnRadioButton_7 = new Button(composite_4, SWT.RADIO);
		btnRadioButton_7.setText("Radio Button");
		
		ExpandItem xpndtmNewExpanditem_1 = new ExpandItem(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setExpanded(true);
		xpndtmNewExpanditem_1.setText("New ExpandItem");
		
		Composite composite_5 = new Composite(expandBar, SWT.NONE);
		xpndtmNewExpanditem_1.setControl(composite_5);
		xpndtmNewExpanditem_1.setHeight(xpndtmNewExpanditem_1.getControl().computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		
		Button btnRadioButton_8 = new Button(composite_5, SWT.RADIO);
		btnRadioButton_8.setBounds(0, 0, 90, 16);
		btnRadioButton_8.setText("Radio Button");
		
		Button btnRadioButton_9 = new Button(composite_5, SWT.RADIO);
		btnRadioButton_9.setBounds(0, 0, 90, 16);
		btnRadioButton_9.setText("Radio Button");
		
		Button btnRadioButton_10 = new Button(composite_5, SWT.RADIO);
		btnRadioButton_10.setBounds(0, 0, 90, 16);
		btnRadioButton_10.setText("Radio Button");
		
		aligner0.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent event) {
		    	  String O1 = "/"+Acceuil.path+"/OntoBe/bfo.owl";
		    	  String O2 = "/"+Acceuil.path+"/OntoBe/caro.owl";
		    	  String method = null;
		    	  
		    	  if (btnRadioButton.getSelection())
		    		  method = SemanticSimilarityWords.HirstStOnge;
		    	  
		    	  
		       Alignement  a= new Alignement(O1, O2, method);
		       a.startAlignement();
		      }
		    });

	}

	@Override
	public void setFocus() {
		// Set the focus
	}
	
	class BackupFilesContentProvider implements IStructuredContentProvider {
		  private final Object[] EMPTY = new Object[] {};

		  /**
		   * Gets the files in the specified directory
		   * 
		   * @param arg0
		   *            a String containing the directory
		   */
		  public Object[] getElements(Object arg0) {
		    File file = new File((String) arg0);
		    if (file.isDirectory()) {
		      return file.listFiles(new FileFilter() {
		        public boolean accept(File pathName) {
		          // Ignore directories; return only files
		          return pathName.isFile();
		        }
		      });
		    }
		    return EMPTY;
		  }

		  /**
		   * Disposes any created resources
		   */
		  public void dispose() {
		    // Nothing to dispose
		  }

		  /**
		   * Called when the input changes
		   */
		  public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		    // Nothing to do
		  }
		}

	class BackupFilesLabelProvider implements ILabelProvider {
		  /**
		   * Returns the image
		   * 
		   * @param arg0
		   *            the file
		   * @return Image
		   */
		  public Image getImage(Object arg0) {
		    return null;
		  }

		  /**
		   * Returns the name of the file
		   * 
		   * @param arg0
		   *            the name of the file
		   * @return String
		   */
		  public String getText(Object arg0) {
		    return ((File) arg0).getName();
		  }

		  /**
		   * Adds a listener
		   * 
		   * @param arg0
		   *            the listener
		   */
		  public void addListener(ILabelProviderListener arg0) {
		    // Throw it away
		  }

		  /**
		   * Disposes any created resources
		   */
		  public void dispose() {
		    // Nothing to dispose
		  }

		  /**
		   * Returns whether changing this property for this element affects the label
		   * 
		   * @param arg0
		   *            the element
		   * @param arg1
		   *            the property
		   */
		  public boolean isLabelProperty(Object arg0, String arg1) {
		    return false;
		  }

		  /**
		   * Removes a listener
		   * 
		   * @param arg0
		   *            the listener
		   */
		  public void removeListener(ILabelProviderListener arg0) {
		    // Ignore
		  }
		}
}
