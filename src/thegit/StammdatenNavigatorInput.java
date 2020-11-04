/*
 * Project: RCS - Rail Control System
 *
 * © Copyright by SBB AG, Alle Rechte vorbehalten
 */
package thegit;

import org.eclipse.core.resources.IProject;

/**
 */
public class StammdatenNavigatorInput {

  private final IProject dir;

  public StammdatenNavigatorInput(final IProject dir) {
    this.dir = dir;
  }

  public IProject getProject() {
    return this.dir;
  }
}