package org.apache.falcon.designer.ui.util;

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
