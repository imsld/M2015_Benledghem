package org.pfe.mapping.interfaces.views;

import javax.annotation.PostConstruct;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.graphics.Font;

public class ViewOntoProperties extends ViewPart {
	public ViewOntoProperties() {
	}

	public static final String ID = "org.pfe.mapping.interafces.views.ViewOntoProperties"; //$NON-NLS-1$

	public Label label_File_Path;
	public Label label_IRI_Affiche;
	public Label label_Nombre_Concept;
	public Label label_Nombre_Relation;
	public Label label_Nombre_Propriete;
	public Label label_Nombre_Individu;

	public CTabFolder tabFolder;
	public CTabItem tbtmClasse;
	public CTabItem tbtmRelation;
	public CTabItem tbtmPropriete; 
	public CTabItem tbtmIndividu;
	
	public String test="";

	@PostConstruct
	public void createPartControl(Composite parent) {

		tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setTabPosition(SWT.BOTTOM);
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmInformation = new CTabItem(tabFolder, SWT.NONE);
		tbtmInformation.setText("Information");

		tabFolder.setSelection(tbtmInformation);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		tbtmInformation.setControl(composite);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);

		Composite banner = new Composite(composite, SWT.NONE);
		banner.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_FILL,
				GridData.VERTICAL_ALIGN_BEGINNING, true, false));
		layout = new GridLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 10;
		layout.numColumns = 2;
		banner.setLayout(layout);

		Font boldFont = JFaceResources.getFontRegistry().getBold(
				JFaceResources.DEFAULT_FONT);

		Label l = new Label(banner, SWT.NONE);
		l.setText("Chemin de l'ontologie :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));

		label_File_Path = new Label(banner, SWT.WRAP);
		label_File_Path.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		l = new Label(banner, SWT.NONE);
		l.setText("IRI de l'ontologie :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));

		label_IRI_Affiche = new Label(banner, SWT.WRAP);
		label_IRI_Affiche.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		l = new Label(banner, SWT.NONE);
		l.setText("Nombre de concepts :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));

		label_Nombre_Concept = new Label(banner, SWT.WRAP);
		label_Nombre_Concept.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		l = new Label(banner, SWT.NONE);
		l.setText("Nombre de relations :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));

		label_Nombre_Relation = new Label(banner, SWT.WRAP);
		label_Nombre_Relation.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		l = new Label(banner, SWT.NONE);
		l.setText("Nombre de propriétés :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));

		label_Nombre_Propriete = new Label(banner, SWT.WRAP);
		label_Nombre_Propriete.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		l = new Label(banner, SWT.NONE);
		l.setText("Nombre d'indiviu :");
		l.setFont(boldFont);
		l.setLayoutData(new GridData(SWT.RIGHT, SWT.BEGINNING, false, false));
		label_Nombre_Individu = new Label(banner, SWT.WRAP);
		label_Nombre_Individu.setLayoutData(new GridData(SWT.BEGINNING,
				SWT.BEGINNING, true, false));

		tbtmClasse = new CTabItem(tabFolder, SWT.NONE);
		tbtmClasse.setText("Classes");

		tbtmRelation = new CTabItem(tabFolder, SWT.NONE);
		tbtmRelation.setText("Relations");

		tbtmPropriete = new CTabItem(tabFolder, SWT.NONE);
		tbtmPropriete.setText("Propriétés");

		tbtmIndividu = new CTabItem(tabFolder, SWT.NONE);
		tbtmIndividu.setText("Individus");
	}

	@Override
	public void setFocus() {
		// Set the focus
	}
}
