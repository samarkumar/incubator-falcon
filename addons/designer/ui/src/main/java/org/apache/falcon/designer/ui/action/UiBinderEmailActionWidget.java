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

import org.apache.falcon.designer.ui.vo.EmailActionVO;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiTemplate;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class UiBinderEmailActionWidget extends ActionWidget<EmailActionVO> {

  private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

  @UiTemplate("UiBinderEmailActionWidget.ui.xml")
  interface LoginUiBinder extends UiBinder<Widget, UiBinderEmailActionWidget> {
  }

  @UiField
  TextBox toEmailTextBox = new TextBox();
  TextBox ccEmailTextBox = new TextBox();
  TextBox subjectTextBox = new TextBox();
  TextBox bodyTextBox = new TextBox();

  public UiBinderEmailActionWidget() {
    super("EMAIL");
    initWidget(uiBinder.createAndBindUi(this));

  }

  @Override
  public EmailActionVO getCurrentActionVO() {
    EmailActionVO returnVO = new EmailActionVO();
    returnVO.setToEmails(toEmailTextBox.getText());
    returnVO.setCcEmails(ccEmailTextBox.getText());
    returnVO.setSubjects(subjectTextBox.getText());
    returnVO.setBody(bodyTextBox.getText());

    return returnVO;
  }

  @Override
  public void clear() {
    toEmailTextBox.setText("");

  }

}
