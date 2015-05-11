package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.pfe.mapping.interfaces.views.ViewOntoProperties;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.IndividualDataModel;
import org.pfe.ontologie.Ontologie;
import org.pfe.ontologie.ProprieteDataModel;
import org.pfe.ontologie.RelationDataModel;

public class ViewOntoPropertiesOnglet {
	private ViewOntoProperties view;
	private Ontologie onto;
	private String ontologyPath;

	public ViewOntoPropertiesOnglet(ViewOntoProperties view, String ontologyPath) {
		this.view = view;
		this.ontologyPath = ontologyPath;

		onto = new Ontologie("/" + ontologyPath);
	}
	
	public void updateOngletIndividualInterface(){
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);
		AppIndividualViewer table_individual = new AppIndividualViewer(composite_1,
				SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);

		IndividualDataModel model = new IndividualDataModel(
				onto.getIndividualsDataList());
		
		table_individual.setInput(model);
		view.tbtmIndividu.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();
	}

	public void updateOngletProprieteInterface() {
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);
		AppProprieteViewer table_propriete = new AppProprieteViewer(composite_1,
				SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);

		ProprieteDataModel model = new ProprieteDataModel(
				onto.getProprieteDataList());
		
		table_propriete.setInput(model);
		view.tbtmPropriete.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();
		
	}
	
	public void updateOngletRelationInterface() {
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);

		AppRelationViewer table_relation = new AppRelationViewer(composite_1,
				SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);

		RelationDataModel model = new RelationDataModel(
				onto.getRelationDataList());

		table_relation.setInput(model);

		view.tbtmRelation.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();
	}

	public void updateOngletClassesInterface() {

		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);

		AppConceptViewer table_concept = new AppConceptViewer(composite_1,
				SWT.BORDER | SWT.V_SCROLL | SWT.FULL_SELECTION);

		ConceptDataModel model = new ConceptDataModel(onto.getConceptDataList());

		table_concept.setInput(model);

		view.tbtmClasse.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();

	}

	private void update(Label label, String text) {
		GridData gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		label.setText(text);
		label.setLayoutData(gridData);
		label.getParent().layout();
		label.getParent().getParent().layout();
	}

	public void updateOngletInformationInterface() {
		update(view.label_File_Path, ontologyPath);
		update(view.label_IRI_Affiche, onto.getURI());
		update(view.label_Nombre_Concept,
				Integer.toString(onto.getConceptCout()));
		update(view.label_Nombre_Relation,
				Integer.toString(onto.getRelationCout()));
		update(view.label_Nombre_Propriete,
				Integer.toString(onto.getProprieteCout()));
		update(view.label_Nombre_Individu,
				Integer.toString(onto.getIndividualCout()));
		
	}

}
