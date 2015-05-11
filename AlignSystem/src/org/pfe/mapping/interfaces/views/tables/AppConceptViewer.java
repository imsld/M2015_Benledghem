package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.pfe.ontologie.ConceptInformation;

public class AppConceptViewer extends TableViewer{
	 public AppConceptViewer(Composite parent, int style) 
	    {
	        super(parent, style);
	        Table table = getTable();
	        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	        table.setLayoutData(gridData);
	        createColumns();
	        table.setHeaderVisible(true);
	        table.setLinesVisible(true);
	        setContentProvider(new AppConceptContentProvider());
	    }

	    private void createColumns()
	    {
	        String[] titles = { "Concept", "IRI", "Label"};
	        int[] bounds = { 200, 200, 350};

	        TableViewerColumn column = createTableViewerColumn(titles[0], bounds[0], 0);
	        column.setLabelProvider(new ColumnLabelProvider(){
	            public String getText(Object element) {
	                if(element instanceof ConceptInformation)
	                    return ((ConceptInformation)element).getConcept();
	                return super.getText(element);
	            }
	        });

	        column = createTableViewerColumn(titles[1], bounds[1], 1);
	        column.setLabelProvider(new ColumnLabelProvider(){
	            public String getText(Object element) {
	                if(element instanceof ConceptInformation)
	                    return ((ConceptInformation)element).getIRI();
	                return super.getText(element);
	            }
	        });

	        column = createTableViewerColumn(titles[2], bounds[2], 2);
	        column.setLabelProvider(new ColumnLabelProvider(){
	            public String getText(Object element) {
	                if(element instanceof ConceptInformation)
	                    return ((ConceptInformation)element).getLabel();
	                return super.getText(element);
	            }
	        });	        
	    }

	    private TableViewerColumn createTableViewerColumn(String header, int width, int idx) 
	    {
	        TableViewerColumn column = new TableViewerColumn(this, SWT.LEFT, idx);
	        column.getColumn().setText(header);
	        column.getColumn().setWidth(width);
	        column.getColumn().setResizable(true);
	        column.getColumn().setMoveable(true);

	        return column;
	    }
}
