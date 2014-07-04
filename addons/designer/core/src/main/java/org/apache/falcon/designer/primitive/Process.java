package org.apache.falcon.designer.primitive;

import org.apache.falcon.designer.configuration.ProcessConfig;

public class Process extends Primitive<Process, ProcessConfig> {

  public Process(ProcessConfig process) {
    this.setConfiguration(process);
  }

  @Override
  protected Process copy() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Iterable<Message> validate() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Code doCompile() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  protected Process doOptimize() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getNamespace() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getEntity() {
    // TODO Auto-generated method stub
    return null;
  }

}
