package org.pfe.mapping.interfaces.views.tables;

import java.util.List;

import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.pfe.mapping.interfaces.views.ViewAlignement;
import org.pfe.mapping.interfaces.views.ViewAlignementResult;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.SimiliratyConceptDataModel;
import org.pfe.ontologie.SimiliratyConceptDataModelAlignement;
import org.pfe.ontologie.SimiliratyConceptInformation;

public class ViewAlignementResultOnglet {

	ViewAlignementResult view;
	ViewAlignement viewLocal;
	List<List<String>> list;

	public ViewAlignementResultOnglet(ViewAlignement viewLocal,
			ViewAlignementResult view, List<List<String>> list) {
		this.view = view;
		this.list = list;
		this.viewLocal = viewLocal;
	}

	public void updateOngletResultatInterface() {
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);

		AppSimiliratyConceptViewer table_concept_score = new AppSimiliratyConceptViewer(list,
				composite_1, SWT.BORDER | SWT.V_SCROLL
						| SWT.LAST_LINE_SELECTION | SWT.FULL_SELECTION);

		SimiliratyConceptDataModel model = new SimiliratyConceptDataModel(list);

		table_concept_score.setInput(model);

		view.tbtmResult.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();

		table_concept_score
				.addSelectionChangedListener(new ISelectionChangedListener() {
					@Override
					public void selectionChanged(SelectionChangedEvent event) {
						IStructuredSelection selection = (IStructuredSelection) table_concept_score
								.getSelection();
						SimiliratyConceptInformation element = (SimiliratyConceptInformation) selection
								.getFirstElement();
					}
				});

	}

	public void updateOngletAlignementInterface(List<List<String>> list2) {
		Composite composite_1 = new Composite(view.tabFolder, SWT.NONE);

		AppSimiliratyConceptViewerAlignement table_concept_score = new AppSimiliratyConceptViewerAlignement(
				composite_1, SWT.BORDER | SWT.V_SCROLL
						| SWT.LAST_LINE_SELECTION | SWT.FULL_SELECTION);

		SimiliratyConceptDataModelAlignement model = new SimiliratyConceptDataModelAlignement(
				list2, viewLocal.btnRadioLig.getSelection(),
				viewLocal.btnRadioHier.getSelection(),
				viewLocal.btnRadioSem.getSelection());

		table_concept_score.setInput(model);

		view.tbtmAlignment.setControl(composite_1);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite_1.setLayout(layout);
		composite_1.layout();

	}

}
