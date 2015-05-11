package org.pfe.mapping.interfaces.views.tables;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.pfe.ontologie.IndividualInformation;

public class AppIndividualViewer extends TableViewer{
	 public AppIndividualViewer(Composite parent, int style) 
	    {
	        super(parent, style);
	        Table table = getTable();
	        GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
	        table.setLayoutData(gridData);
	        createColumns();
	        table.setHeaderVisible(true);
	        table.setLinesVisible(true);
	        setContentProvider(new AppIndividualContentProvider());
	    }

	    private void createColumns()
	    {
	        String[] titles = { "Individu", "Type"};
	        int[] bounds = { 250, 250};

	        TableViewerColumn column = createTableViewerColumn(titles[0], bounds[0], 0);
	        column.setLabelProvider(new ColumnLabelProvider(){
	            public String getText(Object element) {
	                if(element instanceof IndividualInformation)
	                    return ((IndividualInformation)element).getIndividu();
	                return super.getText(element);
	            }
	        });

	        column = createTableViewerColumn(titles[1], bounds[1], 1);
	        column.setLabelProvider(new ColumnLabelProvider(){
	            public String getText(Object element) {
	                if(element instanceof IndividualInformation)
	                    return ((IndividualInformation)element).getType();
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
