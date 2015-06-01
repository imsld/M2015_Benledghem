package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.pfe.ontologie.SimiliratyConceptInformation;

public class AppSimiliratyConceptViewer extends TableViewer {

	public AppSimiliratyConceptViewer(Composite parent, int style) {
		super(parent, style);
		Table table = getTable();
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		table.setLayoutData(gridData);
		createColumns();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		setContentProvider(new AppSimiliratyConceptProvider());
	}

	private void createColumns() {
		String[] titles = { "Concept Ontologie 1", "Concept Ontologie 2",
				"Similarité linguistique", "Similarité hiérarchique", "Similarité Sémantique" };
		int[] bounds = { 200, 200, 200, 200, 200 };

		TableViewerColumn column = createTableViewerColumn(titles[0],
				bounds[0], 0);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element).getConcept_o1();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[1], bounds[1], 1);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element).getConcept_o2();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[2], bounds[2], 2);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element).getSocre_linguistic();
				return super.getText(element);
			}
		});
		
		column = createTableViewerColumn(titles[3], bounds[3], 3);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element).getSocre_hierarchic();
				return super.getText(element);
			}
		});
		column = createTableViewerColumn(titles[4], bounds[4], 4);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element).getSocre_semantic();
				return super.getText(element);
			}
		});
	}

	private TableViewerColumn createTableViewerColumn(String header, int width,
			int idx) {
		TableViewerColumn column = new TableViewerColumn(this, SWT.LEFT, idx);
		column.getColumn().setText(header);
		column.getColumn().setWidth(width);
		column.getColumn().setResizable(true);
		column.getColumn().setMoveable(true);

		return column;
	}

}
