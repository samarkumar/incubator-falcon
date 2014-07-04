package org.apache.falcon.designer.primitive.action;

import org.apache.falcon.designer.action.configuration.EmailActionConfiguration;
import org.apache.falcon.designer.primitive.Action;
import org.apache.falcon.designer.primitive.Code;
import org.apache.falcon.designer.primitive.Message;

public class EmailAction extends Action<EmailAction, EmailActionConfiguration> {
  //flow grouping .. show come from the flow
  private static final String namespace = "EmailAction";
  
  //flow name .. show come from the flow
  private static final String entity= "1232";
  
  
  public EmailAction(EmailActionConfiguration value){
   this.setConfiguration(value);
  }

  @Override
  public boolean hasOutput() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  protected EmailAction copy() {
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
   getConfiguration().getBody();
    return null;
  }

  @Override
  protected EmailAction doOptimize() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getNamespace() {
    return namespace;
  }

  @Override
  public String getEntity() {
    return entity;
  }
  
  

}
