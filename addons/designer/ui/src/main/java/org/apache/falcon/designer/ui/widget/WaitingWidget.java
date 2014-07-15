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

import org.apache.falcon.designer.ui.util.Constants;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Image;

public class WaitingWidget {

  private static final DialogBox dialogWaiting = new DialogBox();

  private static final String loadingURL = GWT.getModuleBaseForStaticFiles()
      + Constants.WAITING_IMAGE;

  static {

    dialogWaiting.getElement().getStyle().setZIndex(1);
    dialogWaiting.setGlassEnabled(true);
    dialogWaiting.setModal(true);
    dialogWaiting.setAnimationEnabled(true);
    dialogWaiting.setTitle("Waiting");
    dialogWaiting.add(new Image(loadingURL));
  }

  public static void show() {
    dialogWaiting.center();
  }

  public static void hide() {
    dialogWaiting.hide();
  }

}
