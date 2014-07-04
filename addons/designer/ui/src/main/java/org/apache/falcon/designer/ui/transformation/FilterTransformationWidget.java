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

import java.util.Map;

import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FilterTransformationWidget extends TransformationWidget {

  TextBox toEmailTextBox = new TextBox();
 
  private Map<String, String> schema  ;

  public FilterTransformationWidget(Map<String, String> schema) {
    super("EMAIL");
    this.schema = schema;
    VerticalPanel panel = new VerticalPanel();
    FlexTable schemaFlexTable = new FlexTable();
    int index = -1;
    for(String eachColumn:schema.keySet()){
      schemaFlexTable.setWidget(++index, 0, new Label(eachColumn));
      schemaFlexTable.setWidget(index, 1, new  TextBox());
    }
    panel.add(schemaFlexTable);
    initWidget(panel);

  }

  @Override
  public String getCurrentActionVO() {
    return schema.toString();
  }

  @Override
  public void clear() {
    toEmailTextBox.setText("");

  }

}
