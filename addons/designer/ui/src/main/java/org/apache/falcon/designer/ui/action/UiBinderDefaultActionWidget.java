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

package org.apache.falcon.designer.ui.action;

import org.apache.falcon.designer.ui.vo.DefaultActionVO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UiBinderDefaultActionWidget extends ActionWidget<DefaultActionVO> {

  private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

  @UiTemplate("UiBinderDefaultActionWidget.ui.xml")
  interface LoginUiBinder extends UiBinder<Widget, UiBinderDefaultActionWidget> {
  }

  @UiField
  TextBox scriptTextBox;

  public UiBinderDefaultActionWidget() {
    super(null);
    initWidget(uiBinder.createAndBindUi(this));

  }

  @Override
  public DefaultActionVO getCurrentActionVO() {
    DefaultActionVO returnVO = new DefaultActionVO();

    returnVO.setScript(scriptTextBox.getText());
    return returnVO;
  }
  
  @Override
  public void clear() {
    scriptTextBox.setText("");
    
  }

}