package thegit;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;



public abstract class CommonContentProvider implements ITreeContentProvider {

  protected StammdatenNavigatorInput content = null;

  @Override
  public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
    if (newInput instanceof StammdatenNavigatorInput) {
      content = (StammdatenNavigatorInput) newInput;
    }
    viewer.refresh();
  }

  @Override
  public void dispose() {
    // do nothing
  }

  @Override
  public Object[] getChildren(final Object parentElement) {
    if (parentElement instanceof IContainer) {
      try {
        return ((IContainer) parentElement).members();
      }
      catch (final CoreException e) {
        return null;
      }
    }
    else {
      return null;
    }
  }

  @Override
  public Object getParent(final Object element) {
    if (element instanceof IResource) {
      return ((IResource) element).getParent();
    }
    return null;
  }

  @Override
  public boolean hasChildren(final Object element) {
    if (element instanceof IContainer) {
      try {
        final IResource[] members = ((IContainer) element).members();
        return members != null && members.length > 0;
      }
      catch (final CoreException e) {
        return false;
      }
    }
    else {
      return false;
    }
  }
}