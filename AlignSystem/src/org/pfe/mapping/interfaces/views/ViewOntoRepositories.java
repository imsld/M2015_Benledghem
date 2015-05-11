package org.pfe.mapping.interfaces.views;

import java.io.File;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.ViewPart;
import org.pfe.mapping.interfaces.Acceuil;
import org.pfe.mapping.interfaces.views.tables.AppRepositoriesContentProvider;
import org.pfe.mapping.interfaces.views.tables.AppRepositoriesViewLabelProvider;
import org.pfe.mapping.interfaces.views.tables.ViewOntoPropertiesOnglet;

public class ViewOntoRepositories extends ViewPart {
	public ViewOntoRepositories() {
	}

	public static final String ID = "org.pfe.mapping.interafces.views.ViewOntoRepositories"; //$NON-NLS-1$
	private Image image;
	private TreeViewer treeViewer;

	@PostConstruct
	public void createPartControl(Composite parent) {

		
		createImage();
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		treeViewer.setContentProvider(new AppRepositoriesContentProvider());
		treeViewer
				.setLabelProvider(new AppRepositoriesViewLabelProvider(image));

		File folder = new File(Acceuil.path);
		treeViewer.setInput(folder.listFiles());
		Tree tree = (Tree) treeViewer.getControl();


		IWorkbenchPage page = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getActivePage();
		
		tree.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				String fileNameOnto = item.getText();
				
				if(!fileNameOnto.contains(".owl")){
					IViewPart view1 = page.findView(ViewAlignement.ID);
					String s = item.getText();
					s = s.substring(0,s.indexOf(" ("));
					s = Acceuil.path + "/" + s;
					((ViewAlignement) view1).ctv1.setInput(s);

				}
				
				if (fileNameOnto.contains(".owl")) {


					IViewPart view = page.findView(ViewOntoProperties.ID);
					

					if (view != null && view instanceof ViewOntoProperties) {

						String repositorieName = item.getParentItem().getText();
						repositorieName = repositorieName.substring(0,
								repositorieName.indexOf(" ("));
						String ontologyPath = Acceuil.path + "/" + repositorieName;
						ontologyPath = ontologyPath + "/" + fileNameOnto;
						
						ViewOntoPropertiesOnglet ropertiesOnglet = new ViewOntoPropertiesOnglet(
								(ViewOntoProperties) view, ontologyPath);
						ropertiesOnglet.updateOngletInformationInterface();
						ropertiesOnglet.updateOngletClassesInterface();
						ropertiesOnglet.updateOngletRelationInterface();
						ropertiesOnglet.updateOngletProprieteInterface();
						ropertiesOnglet.updateOngletIndividualInterface();
					}
				} 
			}
		});
	}

	private void createImage() {
		ImageDescriptor imageDcr = PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
		this.image = imageDcr.createImage();
	}

	@Override
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}

	@PreDestroy
	public void dispose() {
		image.dispose();
	}

}