package org.apache.falcon.designer.ui.util;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ImageWithText extends Composite implements HasClickHandlers {

  private Label caption;
  private Image image;

  public ImageWithText(String text, String url) {

    image = new Image(url);
    image.setSize("100%", "50px");

    caption = new Label();
    caption.setText(text);
    caption.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

    VerticalPanel panel = new VerticalPanel();
    panel.add(image);
    panel.add(caption);

    initWidget(panel);

  }

  void setImageSize(String width, String height) {
    image.setSize(width, height);
  }

  @Override
  public HandlerRegistration addClickHandler(ClickHandler arg0) {
    return addDomHandler(arg0, ClickEvent.getType());
  }

}
