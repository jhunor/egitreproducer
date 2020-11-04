package thegit;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.model.IWorkbenchAdapter;

public class StammdatenFileAdapter implements IAdapterFactory {

  @Override
  public Object getAdapter(final Object adaptableObject, @SuppressWarnings("rawtypes") final Class adapterType) {
    if (adaptableObject instanceof IResource && adapterType.equals(IWorkbenchAdapter.class)) {
      return new StammdatenFileWorkbenchAdapter((IResource) adaptableObject);
    }
    return null;
  }

  @SuppressWarnings("rawtypes")
  @Override
  public Class[] getAdapterList() {
    return new Class[] { IWorkbenchAdapter.class };
  }

}