package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.pfe.ontologie.ConceptDataModel;
import org.pfe.ontologie.SimiliratyConceptDataModel;
import org.pfe.ontologie.SimiliratyConceptInformation;

public class AppSimiliratyConceptProvider implements IStructuredContentProvider {
	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof SimiliratyConceptDataModel)
			return ((SimiliratyConceptDataModel) inputElement).getData()
					.toArray();
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}