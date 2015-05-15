package org.pfe.mapping.interfaces.views;

import java.io.File;
import java.io.FileFilter;

import javax.annotation.PostConstruct;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.pfe.mapping.alignement.Alignement;
import org.pfe.mapping.alignement.semantics.SemanticSimilaritySentences;
import org.pfe.mapping.alignement.semantics.SemanticSimilarityWords;
import org.pfe.mapping.interfaces.Acceuil;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public class ViewAlignement extends ViewPart {

	public ViewAlignement() {
	}	

	public static final String ID = "org.pfe.mapping.interafces.views.ViewAlignement"; //$NON-NLS-1$
	public CheckboxTableViewer ctv1;
	public String name = "";

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
		group_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		ExpandBar bar = new ExpandBar(group_2, SWT.V_SCROLL);

		Composite composite0 = new Composite(bar, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite0.setLayout(layout);
		Button button_0 = new Button(composite0, SWT.RADIO);
		button_0.setSelection(true);
		button_0.setText(/*"Hirst and St-Onge"*/"HirstStOnge");
		button_0.setToolTipText("Cette mesure prend en considération toutes les relations dans WordNet. Les liens sont\n"+
								"classés comme haut (eg. partie-de), bas (eg. sous-classe), horizontal (eg. antonyme).\n"+
								"La similarité est calculée entre mots par le poids du chemin le plus court qui mène d’un \n"+
								"terme à un autre. Il est calculé en fonctions de ces classifications qui indiquent les\n"+ 
								"changements de direction");
		Button button_01 = new Button(composite0, SWT.RADIO);
		button_01.setText(/*"Leacock-Chodorow"*/"LeacockChodorow");
		button_01.setToolTipText("L’approche par les liens assimile la distance entre deux termes au nombre de liens\n"+
								 "qui les séparent sur le plus court chemin dans le réseau");
		Button button_02 = new Button(composite0, SWT.RADIO);
		button_02.setText("Lesk");
		button_02.setToolTipText("considère la similarité entre deux sens comme le nombre de mots en commun dans\n"+
								 "leurs définitions.");
		Button button_03 = new Button(composite0, SWT.RADIO);
		button_03.setText("WuPalmer");
		button_03.setToolTipText("la similarité est définie par rapport à la distance qui sépare deux concepts dans\n"+
		                         "la hiérarchie et également par leur position par rapport à la racine");
		Button button_04 = new Button(composite0, SWT.RADIO);
		button_04.setText("Resnik");
		button_04.setToolTipText("Resnik définit la similarité sémantique entre deux concepts par la quantité\n"+
								 "d’information qu’ils partagent");
		Button button_05 = new Button(composite0, SWT.RADIO);
		button_05.setText("JiangConrath");
		button_05.setToolTipText("Jiang Conrath pallie aux limites de la mesure de Resnik en combinant le contenu\n"+
								 "informationnel du plus petit généralisant à ceux des concepts.\n"+
								 "Elle prend en considération aussi le nombre d’arcs");
		Button button_06 = new Button(composite0, SWT.RADIO);
		button_06.setText("Lin");
		button_06.setToolTipText("Lin propose également une mesure de similarité très proche, qui revient essentiellement\n"+
								 "à une reformulation sous forme de rapport de la formule de Jiang and Conrath");
		Button button_07 = new Button(composite0, SWT.RADIO);
		button_07.setText("Path");
		button_07.setToolTipText("Path");

		ExpandItem item0 = new ExpandItem(bar, SWT.NONE, 0);
		item0.setText("Distance sémantique - mot");
		item0.setHeight(composite0.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item0.setControl(composite0);

		Composite composite00 = new Composite(bar, SWT.NONE);
		GridLayout layout0 = new GridLayout(2, true);
		layout0.marginLeft = layout0.marginTop = layout0.marginRight = layout0.marginBottom = 10;
		layout0.verticalSpacing = 10;
		composite00.setLayout(layout0);
		Button button_08 = new Button(composite00, SWT.RADIO);
		button_08.setSelection(true);
		button_08.setText("FastMethod");
		Button button_09 = new Button(composite00, SWT.RADIO);
		button_09.setText("calculateRBFSimilarity");
		Button button_10 = new Button(composite00, SWT.RADIO);
		button_10.setText("SWT.RADIO");
		ExpandItem item2 = new ExpandItem(bar, SWT.NONE, 1);
		item2.setText("Distance sémantique - phrase");
		item2.setHeight(composite00.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite00);
		new Label(composite00, SWT.NONE);

		item0.setExpanded(true);
		item2.setExpanded(true);

		aligner0.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				String O1 = "/" + Acceuil.path + "/OntoBe/bfo.owl";
				String O2 = "/" + Acceuil.path + "/OntoBe/caro.owl";
				String methodWord = null;
				String methodSentence = null;
				
       if(button_0.getText()== SemanticSimilarityWords.HirstStOnge)
				methodWord = SemanticSimilarityWords.HirstStOnge;
        
       else 
        	 if(button_1.getText()== SemanticSimilarityWords.LeacockChodorow)
			   methodWord = SemanticSimilarityWords.LeacockChodorow;
        
        else if(button_02.getText()== SemanticSimilarityWords.Lesk)
     			methodWord = SemanticSimilarityWords.Lesk;
        
        else if(button_03.getText()== SemanticSimilarityWords.WuPalmer)
 			methodWord = SemanticSimilarityWords.WuPalmer;
        
        else if(button_04.getText()== SemanticSimilarityWords.Resnik)
 			methodWord = SemanticSimilarityWords.Resnik;
        
        else if(button_05.getText()== SemanticSimilarityWords.JiangConrath)
 			methodWord = SemanticSimilarityWords.JiangConrath;
        
        else if(button_06.getText()== SemanticSimilarityWords.Lin)
 			methodWord = SemanticSimilarityWords.Lin;
        
        else if(button_07.getText()== SemanticSimilarityWords.Path)
 			methodWord = SemanticSimilarityWords.Path;
       
       /////
        
        if(button_08.getText()==SemanticSimilaritySentences.FastMethod)
        	methodSentence = SemanticSimilaritySentences.FastMethod;
        
        else if(button_08.getText()==SemanticSimilaritySentences.calculateRBFSimilarity)
        	methodSentence = SemanticSimilaritySentences.calculateRBFSimilarity;
        
				Alignement align = new Alignement(O1, O2, methodWord, methodSentence);
				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IViewPart view = page.findView(ViewAlignementResult.ID);
				try {
					page.showView(ViewAlignementResult.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}

				//((ViewAlignementResult) view).........;
				view.setFocus();
				align.start();
		
				
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
		 * Returns whether changing this property for this element affects the
		 * label
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
