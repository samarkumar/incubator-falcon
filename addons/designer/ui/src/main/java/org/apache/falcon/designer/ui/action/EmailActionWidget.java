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

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class EmailActionWidget extends ActionWidget<EmailActionVO> {

  private String toEmailLabel = "TO";
  private String ccEmailLabel = "CC";
  private String subjectLabel = "SUBJECT";
  private String bodyLabel = "BODY";

  private TextBox toEmailTextBox = new TextBox();
  private TextBox ccEmailTextBox = new TextBox();
  private TextBox subjectTextBox = new TextBox();
  private TextBox bodyTextBox = new TextBox();

  public EmailActionWidget() {
    super("EMAIL");
    VerticalPanel mainPanel = new VerticalPanel();

    FlexTable schemaFlexTable = new FlexTable();
    int rowNumber = 0;
    schemaFlexTable.setText(rowNumber, 0, this.getType());
    
    rowNumber++;
    schemaFlexTable.setText(rowNumber, 0, toEmailLabel);
    schemaFlexTable.setWidget(rowNumber, 1, toEmailTextBox);

    rowNumber++;
    schemaFlexTable.setText(rowNumber, 0, ccEmailLabel);
    schemaFlexTable.setWidget(rowNumber, 1, ccEmailTextBox);

    rowNumber++;
    schemaFlexTable.setText(rowNumber, 0, subjectLabel);
    schemaFlexTable.setWidget(rowNumber, 1, subjectTextBox);

    rowNumber++;
    schemaFlexTable.setText(rowNumber, 0, bodyLabel);
    schemaFlexTable.setWidget(rowNumber, 1, bodyTextBox);

    mainPanel.add(schemaFlexTable);
    initWidget(mainPanel);

  }

  @Override
  EmailActionVO getCurrentActionVO() {
    EmailActionVO returnVO = new EmailActionVO();
    returnVO.setToEmails(toEmailTextBox.getText());
    returnVO.setCcEmails(ccEmailTextBox.getText());
    returnVO.setSubjects(subjectTextBox.getText());
    returnVO.setBody(bodyTextBox.getText());
    return returnVO;
  }

}
