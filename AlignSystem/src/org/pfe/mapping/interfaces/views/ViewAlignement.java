package org.pfe.mapping.interfaces.views;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.print.DocFlavor.STRING;

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
import org.eclipse.swt.widgets.Display;
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
import org.pfe.mapping.interfaces.views.tables.ViewAlignementResultOnglet;
import org.pfe.mapping.interfaces.views.tables.ViewOntoPropertiesOnglet;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Combo;

public class ViewAlignement extends ViewPart {

	public ViewAlignement() {
	}

	public static final String ID = "org.pfe.mapping.interafces.views.ViewAlignement"; //$NON-NLS-1$
	public CheckboxTableViewer ctv1;
	public String name = "";
	public Text text_1;
	public Text text_2;
	public Text text_3;

	public ProgressBar progressBar;

	@PostConstruct
	public void createPartControl(Composite parent) {

		Composite composite_1 = new Composite(parent, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_2 = new Composite(parent, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite_3 = new Composite(parent, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));

		Group grp_ontologies = new Group(composite_1, SWT.NONE);
		grp_ontologies.setText("S\u00E9lection d'ontologies");
		grp_ontologies.setLayout(new GridLayout(1, false));

		Composite composite_11 = new Composite(grp_ontologies, SWT.BORDER);
		composite_11
				.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		composite_11.setLayout(new GridLayout(3, false));

		ctv1 = CheckboxTableViewer.newCheckList(grp_ontologies, SWT.BORDER);
		ctv1.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		ctv1.setContentProvider(new BackupFilesContentProvider());
		ctv1.setLabelProvider(new BackupFilesLabelProvider());

		Composite composite_12 = new Composite(grp_ontologies, SWT.BORDER);
		composite_12.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
		composite_12.setLayout(new GridLayout(1, false));

		Group grp_operaions = new Group(composite_2, SWT.NONE);
		grp_operaions.setText("D\u00E9roulement d'alignement");
		grp_operaions.setLayout(new GridLayout(1, false));

		Composite composite_21 = new Composite(grp_operaions, SWT.BORDER);
		composite_21
				.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		composite_21.setLayout(new GridLayout(1, false));

		Label lblNewLabel_1 = new Label(composite_21, SWT.NONE);
		lblNewLabel_1.setText("Concept première ontologie  :");

		text_1 = new Text(composite_21, SWT.BORDER);
		text_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1,
				1));

		Label lblNewLabel_2 = new Label(composite_21, SWT.NONE);
		lblNewLabel_2.setText("Concept deuxième ontologie  :");

		text_2 = new Text(composite_21, SWT.BORDER);
		text_2.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1,
				1));

		Label lblNewLabel_3 = new Label(composite_21, SWT.NONE);
		lblNewLabel_3.setText("Score d'alignement  :");

		text_3 = new Text(composite_21, SWT.BORDER);
		text_3.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, true, 1,
				1));

		// tv1 = CheckboxTableViewer.newCheckList(grp_operaions, SWT.BORDER);
		// tv1.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));
		// tv1.setContentProvider(new BackupFilesContentProvider());
		// tv1.setLabelProvider(new BackupFilesLabelProvider());

		Composite composite_22 = new Composite(grp_operaions, SWT.BORDER);
		composite_22
				.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL));
		composite_22.setLayout(new FillLayout(SWT.HORIZONTAL));
		progressBar = new ProgressBar(composite_22, SWT.FILL);

		Group grp_parameters = new Group(composite_3, SWT.NONE);
		grp_parameters.setText("Configuration");
		grp_parameters.setLayout(new FillLayout(SWT.HORIZONTAL));

		// Create the controls

		Button btn_align = new Button(composite_12, SWT.PUSH);
		btn_align.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		btn_align.setText("Lancer alignement");

		Button btn_load = new Button(composite_11, SWT.NONE);
		btn_load.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		btn_load.setText("Charger entrep\u00F4t");

		Button btn_selectAll = new Button(composite_11, SWT.NONE);
		btn_selectAll.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false,
				false, 1, 1));
		btn_selectAll.setText("Tout s\u00E9lectionner");

		Button btn_deselectAll = new Button(composite_11, SWT.NONE);
		btn_deselectAll.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER,
				false, false, 1, 1));
		btn_deselectAll.setText("Tout d\u00E9s\u00E9lectionner");

		ExpandBar bar = new ExpandBar(grp_parameters, SWT.V_SCROLL);

		Composite composite_31 = new Composite(bar, SWT.NONE);
		GridLayout gl_composite_31 = new GridLayout();
		gl_composite_31.numColumns = 2;
		gl_composite_31.marginLeft = gl_composite_31.marginTop = gl_composite_31.marginRight = gl_composite_31.marginBottom = 10;
		gl_composite_31.verticalSpacing = 10;
		composite_31.setLayout(gl_composite_31);
		Button button_0 = new Button(composite_31, SWT.RADIO);
		button_0.setText(/* "Hirst and St-Onge" */"HirstStOnge");
		button_0.setToolTipText("Cette mesure prend en considération toutes les relations dans WordNet. Les liens sont\n"
				+ "classés comme haut (eg. partie-de), bas (eg. sous-classe), horizontal (eg. antonyme).\n"
				+ "La similarité est calculée entre mots par le poids du chemin le plus court qui mène d’un \n"
				+ "terme à un autre. Il est calculé en fonctions de ces classifications qui indiquent les\n"
				+ "changements de direction");
		Button button_02 = new Button(composite_31, SWT.RADIO);
		button_02.setText("Lesk");
		button_02
				.setToolTipText("considère la similarité entre deux sens comme le nombre de mots en commun dans\n"
						+ "leurs définitions.");
		Button button_01 = new Button(composite_31, SWT.RADIO);
		button_01.setText(/* "Leacock-Chodorow" */"LeacockChodorow");
		button_01
				.setToolTipText("L’approche par les liens assimile la distance entre deux termes au nombre de liens\n"
						+ "qui les séparent sur le plus court chemin dans le réseau");
		Button button_06 = new Button(composite_31, SWT.RADIO);
		button_06.setText("Lin");
		button_06
				.setToolTipText("Lin propose également une mesure de similarité très proche, qui revient essentiellement\n"
						+ "à une reformulation sous forme de rapport de la formule de Jiang and Conrath");
		Button button_03 = new Button(composite_31, SWT.RADIO);
		button_03.setSelection(true);
		button_03.setText("WuPalmer");
		button_03
				.setToolTipText("la similarité est définie par rapport à la distance qui sépare deux concepts dans\n"
						+ "la hiérarchie et également par leur position par rapport à la racine");
		Button button_04 = new Button(composite_31, SWT.RADIO);
		button_04.setText("Resnik");
		button_04
				.setToolTipText("Resnik définit la similarité sémantique entre deux concepts par la quantité\n"
						+ "d’information qu’ils partagent");
		Button button_05 = new Button(composite_31, SWT.RADIO);
		button_05.setText("JiangConrath");
		button_05
				.setToolTipText("Jiang Conrath pallie aux limites de la mesure de Resnik en combinant le contenu\n"
						+ "informationnel du plus petit généralisant à ceux des concepts.\n"
						+ "Elle prend en considération aussi le nombre d’arcs");
		Button button_07 = new Button(composite_31, SWT.RADIO);
		button_07.setText("Path");
		button_07.setToolTipText("Path");

		ExpandItem item_word = new ExpandItem(bar, SWT.NONE, 0);
		item_word.setText("Distance sémantique - mot");
		item_word
				.setHeight(composite_31.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item_word.setControl(composite_31);

		Composite composite_32 = new Composite(bar, SWT.NONE);
		GridLayout gl_composite_32 = new GridLayout(2, true);
		gl_composite_32.marginLeft = gl_composite_32.marginTop = gl_composite_32.marginRight = gl_composite_32.marginBottom = 10;
		gl_composite_32.verticalSpacing = 10;
		composite_32.setLayout(gl_composite_32);
		Button button_08 = new Button(composite_32, SWT.RADIO);
		button_08.setSelection(true);
		button_08.setText("FastMethod");
		Button btnCalculateRbfSimilarity = new Button(composite_32, SWT.RADIO);
		btnCalculateRbfSimilarity.setText("Calculate RBF Similarity");
		Button btnNeroneNetwork = new Button(composite_32, SWT.RADIO);
		btnNeroneNetwork.setText("Nerone network");
		ExpandItem item_sentance = new ExpandItem(bar, SWT.NONE, 1);
		item_sentance.setText("Distance sémantique - phrase");
		item_sentance.setHeight(composite_32.computeSize(SWT.DEFAULT,
				SWT.DEFAULT).y);
		item_sentance.setControl(composite_32);
		new Label(composite_32, SWT.NONE);

		item_word.setExpanded(true);
		item_sentance.setExpanded(true);

		ExpandItem item_Ponderation = new ExpandItem(bar, SWT.NONE);
		item_Ponderation.setExpanded(true);
		item_Ponderation.setText("Pond\u00E9ration");

		Composite composite = new Composite(bar, SWT.NONE);
		item_Ponderation.setControl(composite);
		item_Ponderation.setHeight(item_Ponderation.getControl().computeSize(
				SWT.DEFAULT, SWT.DEFAULT).y);
		composite.setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("Linguistique");

		Combo combo_ling = new Combo(composite, SWT.NONE);
		combo_ling.setItems(new String[] { "0", "0.1", "0.2", "0.3", "0.4",
				"0.5", "0.6", "0.7", "0.8", "0.9", "1" });
		combo_ling.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		combo_ling.select(5);

		Label lblNewLabel_4 = new Label(composite, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_4.setText("Hi\u00E9rarchique");

		Combo combo_hier = new Combo(composite, SWT.NONE);
		combo_hier.setItems(new String[] { "0", "0.1", "0.2", "0.3", "0.4",
				"0.5", "0.6", "0.7", "0.8", "0.9", "1" });
		combo_hier.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));
		combo_hier.select(5);

		btn_align.addSelectionListener(new SelectionAdapter() {
			String O1 = "/" + Acceuil.path + "/BachMark/Labo1.owl";
			String O2 = "/" + Acceuil.path + "/BachMark/Labo2.owl";
			String methodWord = null;
			String methodSentence = null;

			List<List<String>> list = new ArrayList<List<String>>();

			public void widgetSelected(SelectionEvent event) {

				if (button_0.getSelection()
						&& (button_0.getText() == SemanticSimilarityWords.HirstStOnge))
					methodWord = SemanticSimilarityWords.HirstStOnge;

				else if (btn_selectAll.getSelection()
						&& (button_01.getText() == SemanticSimilarityWords.LeacockChodorow))
					methodWord = SemanticSimilarityWords.LeacockChodorow;

				else if (button_02.getSelection())
					methodWord = SemanticSimilarityWords.Lesk;

				else if (button_03.getSelection()
						&& (button_03.getText() == SemanticSimilarityWords.WuPalmer))
					methodWord = SemanticSimilarityWords.WuPalmer;

				else if (button_04.getSelection()
						&& (button_04.getText() == SemanticSimilarityWords.Resnik))
					methodWord = SemanticSimilarityWords.Resnik;

				else if (button_05.getSelection()
						&& (button_05.getText() == SemanticSimilarityWords.JiangConrath))
					methodWord = SemanticSimilarityWords.JiangConrath;

				else if (button_06.getSelection()
						&& (button_06.getText() == SemanticSimilarityWords.Lin))
					methodWord = SemanticSimilarityWords.Lin;

				else if (button_07.getSelection()
						&& (button_07.getText() == SemanticSimilarityWords.Path))
					methodWord = SemanticSimilarityWords.Path;

				if (button_08.getSelection()
						&& (button_08.getText() == SemanticSimilaritySentences.FastMethod))
					methodSentence = SemanticSimilaritySentences.FastMethod;

				else if (btnCalculateRbfSimilarity.getSelection()
						&& (btnCalculateRbfSimilarity.getText() == SemanticSimilaritySentences.calculateRBFSimilarity))
					methodSentence = SemanticSimilaritySentences.calculateRBFSimilarity;

				IWorkbenchPage page = PlatformUI.getWorkbench()
						.getActiveWorkbenchWindow().getActivePage();
				IViewPart view = page.findView(ViewAlignementResult.ID);
				IViewPart viewLocal = page.findView(ViewAlignement.ID);

				try {
					page.showView(ViewAlignementResult.ID);
				} catch (PartInitException e) {
					e.printStackTrace();
				}

				list.clear();
				ViewAlignementResultOnglet ropertiesOnglet = new ViewAlignementResultOnglet(
						(ViewAlignementResult) view, list);
				ropertiesOnglet.updateOngletResultatInterface();
				// /

				new Alignement(O1, O2, methodWord, methodSentence,
						(ViewAlignement) viewLocal, getSite().getShell()
								.getDisplay(), progressBar, list, combo_ling
								.getText(), combo_hier.getText(),
						ropertiesOnglet).start();
				
				ropertiesOnglet.updateOngletResultatInterface();

				view.setFocus();
			}
		});
	}

	@Override
	public void setFocus() {
		// Set the focus
	}

	class BackupFilesContentProvider implements IStructuredContentProvider {
		private final Object[] EMPTY = new Object[] {};

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

		public void dispose() {
		}

		public void inputChanged(Viewer arg0, Object arg1, Object arg2) {
		}
	}

	class BackupFilesLabelProvider implements ILabelProvider {
		public Image getImage(Object arg0) {
			return null;
		}

		public String getText(Object arg0) {
			return ((File) arg0).getName();
		}

		public void addListener(ILabelProviderListener arg0) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object arg0, String arg1) {
			return false;
		}

		public void removeListener(ILabelProviderListener arg0) {
		}
	}
}
