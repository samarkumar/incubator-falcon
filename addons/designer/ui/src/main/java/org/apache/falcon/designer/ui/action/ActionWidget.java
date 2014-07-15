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

import org.apache.falcon.designer.ui.vo.ActionVO;

import com.google.gwt.user.client.ui.Composite;

/*
 * Abstract class representing a action widget
 */
public abstract class ActionWidget<T extends ActionVO> extends Composite {

  private String type;

  public String getType() {
    return type;
  }

  abstract public T getCurrentActionVO();

  public void setType(String type) {
    this.type = type;
  }

  public ActionWidget(final String type) {
    if (type == null) {
      this.type = "DEFAULT";
    } else {
      this.type = type;
    }
  }

  abstract public void clear();
}
