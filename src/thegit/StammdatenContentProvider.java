package thegit;


import java.util.Arrays;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;


public class StammdatenContentProvider extends CommonContentProvider {

  @Override
  public Object[] getElements(final Object inputElement) {
    if (content != null) {
      try {
        final IProject project = content.getProject();
        if (project == null) {
          return null;
        }
        return Arrays.asList(project.members()).stream()
            .toArray();
      }
      catch (final CoreException e) {
        return null;
      }
    }
    else {
      return null;
    }
  }
}