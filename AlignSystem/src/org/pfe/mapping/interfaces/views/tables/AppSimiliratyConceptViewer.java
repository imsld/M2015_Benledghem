package org.pfe.mapping.interfaces.views.tables;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.pfe.ontologie.SimiliratyConceptInformation;

public class AppSimiliratyConceptViewer extends TableViewer {
	private TableEditor editor;
	private Button button;
	TableItem item;

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
				"Similarité linguistique", "Similarité hiérarchique",
				"Similarité Sémantique" };
		int[] bounds = { 200, 200, 200, 200, 200 };

		TableViewerColumn column = createTableViewerColumn(titles[0],
				bounds[0], 0);

		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element)
							.getConcept_o1();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[1], bounds[1], 1);
		column.setLabelProvider(new ColumnLabelProvider() {
			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element)
							.getConcept_o2();
				return super.getText(element);
			}
		});

		column = createTableViewerColumn(titles[2], bounds[2], 2);
		column.setLabelProvider(new ColumnLabelProvider() {
			// make sure you dispose these buttons when viewer input changes
			Map<Object, Button> buttons = new HashMap<Object, Button>();

			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element)
							.getSocre_linguistic();
				return super.getText(element);
			}

			@Override
			public void update(ViewerCell cell) {

				item = (TableItem) cell.getItem();

				if (buttons.containsKey(cell.getElement())) {
					button = buttons.get(cell.getElement());

				} else {
					button = new Button((Composite) cell.getViewerRow()
							.getControl(), SWT.CHECK);
					Object element = cell.getElement();
					button.setText(getText(element));
					button.setSelection(((SimiliratyConceptInformation) element).IsMaxLinguistiqueScore());
					buttons.put(element, button);
					

					button.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							if (((SimiliratyConceptInformation) element)
									.IsMaxLinguistiqueScore()) {
							((SimiliratyConceptInformation) element).setMaxLinguistiqueScore(false);
							}
							else{
								((SimiliratyConceptInformation) element).setMaxLinguistiqueScore(true);
								String cpt1 = ((SimiliratyConceptInformation) element)
										.getConcept_o1();
								String cpt2 = ((SimiliratyConceptInformation) element)
										.getConcept_o2();
								
								TableItem[] selection = getTable().getItems();
								for (int i = 0; i < selection.length; i++) {
									SimiliratyConceptInformation elt = (SimiliratyConceptInformation) selection[i]
											.getData();
									if ((elt.getConcept_o1().equals(cpt1))
											&& !(elt.getConcept_o2()
													.equals(cpt2))
											&& (elt.IsMaxLinguistiqueScore())) {
										elt.setMaxLinguistiqueScore(false);
										(getTable().getItems())[i]
												.setData(elt);
										Button b = ((Button) (getTable().getChildren())[(i*3)]);
										b.setSelection(false);
										
										break;
									}
								}
							}
						}
					});

					editor = new TableEditor(item.getParent());
					editor.grabHorizontal = true;
					editor.grabVertical = true;
					editor.setEditor(button, item, cell.getColumnIndex());
					editor.layout();
				}
			}
		});

		column = createTableViewerColumn(titles[3], bounds[3], 3);

		column.setLabelProvider(new ColumnLabelProvider() {
			// make sure you dispose these buttons when viewer input changes
			Map<Object, Button> buttons = new HashMap<Object, Button>();

			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element)
							.getSocre_hierarchic();
				return super.getText(element);
			}

			@Override
			public void update(ViewerCell cell) {

				item = (TableItem) cell.getItem();

				if (buttons.containsKey(cell.getElement())) {
					button = buttons.get(cell.getElement());

				} else {
					button = new Button((Composite) cell.getViewerRow()
							.getControl(), SWT.CHECK);
					Object element = cell.getElement();
					button.setText(getText(element));
					button.setSelection(((SimiliratyConceptInformation) element).IsMaxHierarchiqueScore());
					buttons.put(element, button);
					

					button.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							if (((SimiliratyConceptInformation) element)
									.IsMaxHierarchiqueScore()) {
							((SimiliratyConceptInformation) element).setMaxHierarchiqueScore(false);
							}
							else{
								((SimiliratyConceptInformation) element).setMaxHierarchiqueScore(true);
								String cpt1 = ((SimiliratyConceptInformation) element)
										.getConcept_o1();
								String cpt2 = ((SimiliratyConceptInformation) element)
										.getConcept_o2();
								
								TableItem[] selection = getTable().getItems();
								for (int i = 0; i < selection.length; i++) {
									SimiliratyConceptInformation elt = (SimiliratyConceptInformation) selection[i]
											.getData();
									if ((elt.getConcept_o1().equals(cpt1))
											&& !(elt.getConcept_o2()
													.equals(cpt2))
											&& (elt.IsMaxHierarchiqueScore())) {
										elt.setMaxHierarchiqueScore(false);
										(getTable().getItems())[i]
												.setData(elt);
										Button b = ((Button) (getTable().getChildren())[(i*3)+1]);
										b.setSelection(false);
										
										break;
									}
								}
							}
						}
					});

					editor = new TableEditor(item.getParent());
					editor.grabHorizontal = true;
					editor.grabVertical = true;
					editor.setEditor(button, item, cell.getColumnIndex());
					editor.layout();
				}

			}
		});

		column = createTableViewerColumn(titles[4], bounds[4], 4);
		column.setLabelProvider(new ColumnLabelProvider() {
			// make sure you dispose these buttons when viewer input changes
			Map<Object, Button> buttons = new HashMap<Object, Button>();

			public String getText(Object element) {
				if (element instanceof SimiliratyConceptInformation)
					return ((SimiliratyConceptInformation) element)
							.getSocre_semantic();
				return super.getText(element);
			}

			@Override
			public void update(ViewerCell cell) {

				item = (TableItem) cell.getItem();

				if (buttons.containsKey(cell.getElement())) {
					button = buttons.get(cell.getElement());

				} else {
					button = new Button((Composite) cell.getViewerRow()
							.getControl(), SWT.CHECK);
					Object element = cell.getElement();
					button.setText(getText(element));
					button.setSelection(((SimiliratyConceptInformation) element).IsMaxSemantiqueScore());
					buttons.put(element, button);
					

					button.addSelectionListener(new SelectionAdapter() {
						public void widgetSelected(SelectionEvent event) {
							if (((SimiliratyConceptInformation) element)
									.IsMaxSemantiqueScore()) {
							((SimiliratyConceptInformation) element).setMaxSemantiqueScore(false);
							}
							else{
								((SimiliratyConceptInformation) element).setMaxSemantiqueScore(true);
								String cpt1 = ((SimiliratyConceptInformation) element)
										.getConcept_o1();
								String cpt2 = ((SimiliratyConceptInformation) element)
										.getConcept_o2();
								
								TableItem[] selection = getTable().getItems();
								for (int i = 0; i < selection.length; i++) {
									SimiliratyConceptInformation elt = (SimiliratyConceptInformation) selection[i]
											.getData();
									if ((elt.getConcept_o1().equals(cpt1))
											&& !(elt.getConcept_o2()
													.equals(cpt2))
											&& (elt.IsMaxSemantiqueScore())) {
										elt.setMaxSemantiqueScore(false);
										(getTable().getItems())[i]
												.setData(elt);
										Button b = ((Button) (getTable().getChildren())[(i*3)+2]);
										b.setSelection(false);
										
										break;
									}
								}
							}
						}
					});

					editor = new TableEditor(item.getParent());
					editor.grabHorizontal = true;
					editor.grabVertical = true;
					editor.setEditor(button, item, cell.getColumnIndex());
					editor.layout();
				}

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
