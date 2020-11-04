package thegit;

import java.nio.file.Path;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;


/*
 * Project: RCS - Rail Control System

import java.nio.file.Path;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import ch.sbb.lta.client.stammdaten.zellen.RefreshableView;
import ch.sbb.lta.client.stammdaten.zellen.StammdatenActivator;
import ch.sbb.lta.client.stammdaten.zellen.job.UpdateZelle;

/**
 * (Re)Generiert Zelleninformationen
 */
public class UpdateZelleHandler extends AbstractHandler implements ISelectionChangedListener {

  private final Stammdaten view;
  private Path currentSelection;

  public UpdateZelleHandler(final Stammdaten view) {
    this.view = view;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
  @Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		return null;
	}

  @Override
  public void selectionChanged(final SelectionChangedEvent event) {
    final Object firstElement = ((StructuredSelection) event.getSelection()).getFirstElement();
    if (firstElement instanceof IResource) {
      currentSelection = ((IResource) firstElement).getLocation().toFile().toPath();
    }
    else {
      currentSelection = null;
    }
  }
}