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

package org.apache.falcon.designer.ui.widget;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;

public class WaitingWidget {

  private static final DialogBox d = new DialogBox();

  private static final String loadingURL = GWT.getModuleBaseForStaticFiles()
      + "images/715.GIF";

  static {

    d.getElement().getStyle().setZIndex(1);
    d.setGlassEnabled(true);
    d.setModal(true);
    d.setAnimationEnabled(true);
    d.setTitle("Waiting");
    d.add(new Image(loadingURL));
  }

  public static void show() {
    d.center();
  }

  public static void hide() {

    d.hide();
  }

}
