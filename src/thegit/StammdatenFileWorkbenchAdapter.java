package thegit;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.IWorkbenchAdapter;


public class StammdatenFileWorkbenchAdapter implements IWorkbenchAdapter {

	  private final IResource resource;

	  public StammdatenFileWorkbenchAdapter(final IResource resource) {
	    this.resource = resource;
	  }

	  @Override
	  public Object[] getChildren(final Object o) {
	    if (resource instanceof IContainer) {
	      try {
	        return ((IContainer) resource).members();
	      }
	      catch (final CoreException e) {
	       e.printStackTrace();
	      }
	    }
	    return null;
	  }

	  @Override
	  public ImageDescriptor getImageDescriptor(final Object object) {
	    if (object instanceof IFile) {
	      return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FILE);
	    }
	    if (object instanceof IFolder) {
	      return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
	    }
	    if (object instanceof IProject) {
	      return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_OBJ_FOLDER);
	    }
	    return null;
	  }

	  @Override
	  public String getLabel(final Object o) {
	    if (o instanceof IResource) {
	      return ((IResource) o).getName();
	    }
	    return null;
	  }

	  @Override
	  public Object getParent(final Object o) {
	    if (o instanceof IResource) {
	      return ((IResource) o).getParent();
	    }
	    return null;
	  }
}