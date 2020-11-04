package thegit;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchCommandConstants;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.navigator.CommonNavigator;
import org.eclipse.ui.navigator.CommonViewer;

public class Stammdaten extends CommonNavigator {
	public static final String ID = "ch.sbb.lta.client.stammdaten.zellen.view.Stammdaten"; //$NON-NLS-1$
	private UpdateZelleHandler updateZelleHandler;
	public Stammdaten() {
		super();
//		generateZellenHandler = new GenerateZellenHandler(this);
//		generateTzdeExpanderHandler = new GenerateTzdeExpanderFahrstrassenHandler(this);
		updateZelleHandler = new UpdateZelleHandler(this);
	}
	 @Override
	  public void createPartControl(final Composite aParent) {
	    super.createPartControl(aParent);
	    //addSelectionChangedListeners(getCommonViewer());
	    loadData();
	    activateHandlers(getSite().getService(IHandlerService.class));
//	    activateContext(getSite().getService(IContextService.class));
	    hookDoubleClickCommand();
	    addResourceChangeListener();
	  }
	 
	 private void addResourceChangeListener() {
		    ResourcesPlugin.getWorkspace().getRoot().getWorkspace().addResourceChangeListener(event -> resourceChanged(),
		        IResourceChangeEvent.POST_CHANGE);
		  }
	 private void resourceChanged() {
		    this.getCommonViewer().getControl().getDisplay().asyncExec(() -> this.getCommonViewer().refresh());
		  }

	  private void doubleClick(final DoubleClickEvent event) {
	    final IHandlerService handlerService = getSite().getService(IHandlerService.class);
	    try {
	      final TreeSelection selection = (TreeSelection) event.getSelection();
	      final IResource file = (IResource) selection.getFirstElement();
	      if (!(file instanceof IFile)) {
	        return;
	      }
	      handlerService.executeCommand("ch.sbb.lta.client.stammdaten.zellen.command.openEditor", null);
	    }
	    catch (final Exception ex) {
	      System.out.println(ex.getStackTrace());
	    }
	  }
	  private void hookDoubleClickCommand() {
		    getCommonViewer().addDoubleClickListener(this::doubleClick);
		  }

	 public void refresh() {
	    final IProject project = getProject();
	    try {
	      final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
	      root.refreshLocal(IResource.DEPTH_INFINITE, null);
	      if (project != null && project.exists() && project.isOpen() && project.isAccessible()) {
	        project.refreshLocal(IResource.DEPTH_INFINITE, null);
	      }
	    }
	    catch (final CoreException e) {
	      System.out.println(e.getStackTrace());
	    }
	    final StammdatenNavigatorInput content = new StammdatenNavigatorInput(project);
	    final CommonViewer viewer = this.getCommonViewer();
	    viewer.getContentProvider().inputChanged(viewer, null, content);
	    viewer.refresh();
	  }
	 private void loadData() {
		    final StammdatenNavigatorInput content = new StammdatenNavigatorInput(getProject());
		    getCommonViewer().setInput(content);
		  }
	 protected void activateHandlers(final IHandlerService handlerService) {
		    handlerService.activateHandler(IWorkbenchCommandConstants.FILE_REFRESH, new AbstractHandler() {
		    	@Override
		    	public Object execute(ExecutionEvent event) throws ExecutionException {
		    		refresh();
		    		return null;
		    	}
		    }
		    );
//		 	handlerService.activateHandler("ch.sbb.lta.client.stammdaten.zellen.generateZellen", generateZellenHandler);
		    handlerService.activateHandler("ch.sbb.lta.client.stammdaten.zellen.updateZelle", updateZelleHandler);
//		    handlerService.activateHandler("ch.sbb.lta.client.stammdaten.zellen.generateTzdeExpanderFahrstrassen", generateTzdeExpanderHandler);
		  }
	 
	 public IProject getProject() {
		    final IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		    final IProject project = root.getProject("stammdaten");
		    try {
		      if (!project.exists()) {
		        return null;
		      }
		      if (!project.isOpen()) {
		        project.open(null);
		      }
		     
		    }
		    catch (final CoreException e) {
		      System.out.println(e.getStackTrace());
		    }
		    return project;
		  }


}