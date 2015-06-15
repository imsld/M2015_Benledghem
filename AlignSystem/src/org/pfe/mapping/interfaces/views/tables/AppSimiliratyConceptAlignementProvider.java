package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.pfe.ontologie.SimiliratyConceptDataModelAlignement;

public class AppSimiliratyConceptAlignementProvider implements
		IStructuredContentProvider {

	public Object[] getElements(Object inputElement) {
		if (inputElement instanceof SimiliratyConceptDataModelAlignement)
			return ((SimiliratyConceptDataModelAlignement) inputElement).getData()
					.toArray();
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
	}
}
