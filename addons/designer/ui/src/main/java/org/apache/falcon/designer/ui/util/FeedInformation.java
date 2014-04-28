package org.apache.falcon.designer.ui.util;

import java.util.List;

import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;

public class FeedInformation extends Composite {

  public FeedInformation(String heading, List<String> clusters, String footer) {

    VerticalPanel panel = new VerticalPanel();

    Label headingLabel = new Label();
    headingLabel.setText(heading);
    panel.add(headingLabel);

    for (String clusterHeading : clusters) {
      CheckBox checkbox = new CheckBox(clusterHeading);
      checkbox.setValue(true);
      panel.add(checkbox);
    }

    Label footerLabel = new Label();
    footerLabel.setText(heading);

    panel.add(footerLabel);

    initWidget(panel);
  };

}
