package thegit;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class EgitActivator extends AbstractUIPlugin {
	public static EgitActivator plugin;
	@Override
	public void start(BundleContext context) throws Exception {
		
		super.start(context);
		plugin = this;
		
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
