package org.pfe.mapping.interfaces.views.tables;

import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.pfe.mapping.interfaces.views.ViewAlignementResult;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.SimiliratyConceptDataModel;

public class ViewAlignementResultOnglet {

	ViewAlignementResult view;
	List<List<String>> list;

	public ViewAlignementResultOnglet(ViewAlignementResult view, List<List<String>> list) {
		this.view = view;
		this.list = list;
	}

	public void updateOngletResultatInterface() {
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);
		
		AppSimiliratyConceptViewer table_concept_score = new AppSimiliratyConceptViewer(
				composite_1, SWT.BORDER | SWT.V_SCROLL | SWT.LAST_LINE_SELECTION | SWT.FULL_SELECTION);
		
		
		SimiliratyConceptDataModel model = new SimiliratyConceptDataModel(list);
		
		table_concept_score.setInput(model);
		

		
		view.tbtmResult.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();
	}

	public void updateOngletAlignementInterface() {

	}

}
