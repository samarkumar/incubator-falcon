/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.falcon.designer.ui.transformation;

import java.util.logging.Logger;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class TransformationDialogBox extends DialogBox {

  private final static Logger logger = Logger.getLogger("FalconDesignerUi");
  
  private TransformationWidget actionWidget;

  public TransformationDialogBox(
      final @SuppressWarnings("rawtypes") TransformationWidget actionWidget) {
    this.actionWidget = actionWidget;

    getElement().getStyle().setZIndex(1);
    setGlassEnabled(true);
    setModal(true);
    setAnimationEnabled(true);
    setTitle("Waiting");

    Label okButton = new Label("OK");
    Label clearButton = new Label("Clear");

    okButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        logger.info(actionWidget.getCurrentActionVO().toString());
        hide();

      }
    });
    
    
    clearButton.addClickHandler(new ClickHandler() {

      @Override
      public void onClick(ClickEvent event) {
        actionWidget.clear();
       

      }
    });

    HorizontalPanel buttons = new HorizontalPanel();
    buttons.add(okButton);
    buttons.add(clearButton);
    VerticalPanel vPanel = new VerticalPanel();
    vPanel.add(actionWidget);
    vPanel.add(buttons);
    add(vPanel);

  }

  public TransformationWidget getActionWidget() {
    return actionWidget;
  }

}
