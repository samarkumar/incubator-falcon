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
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class DefaultActionWidget extends ActionWidget<DefaultActionVO> {

  private String scriptLabel = "SCRIPT";

  private TextBox scriptTextBox = new TextBox();

  public DefaultActionWidget(String type) {
    super(type);
    VerticalPanel mainPanel = new VerticalPanel();

    FlexTable schemaFlexTable = new FlexTable();
    int rowNumber = 0;
    schemaFlexTable.setText(rowNumber, 0, this.getType());

    rowNumber++;
    schemaFlexTable.setText(rowNumber, 0, scriptLabel);
    schemaFlexTable.setWidget(rowNumber, 1, scriptTextBox);

    mainPanel.add(schemaFlexTable);
    initWidget(mainPanel);

  }

  @Override
  DefaultActionVO getCurrentActionVO() {
    DefaultActionVO returnVO = new DefaultActionVO();

    returnVO.setScript(scriptTextBox.getText());
    return returnVO;
  }

}
