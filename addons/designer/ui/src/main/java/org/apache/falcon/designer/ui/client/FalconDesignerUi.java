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

package org.apache.falcon.designer.ui.client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;
import java.util.logging.Level;
import org.apache.falcon.designer.ui.action.ActionDialogBox;
import org.apache.falcon.designer.ui.action.ActionWidgetFactory;
import org.apache.falcon.designer.ui.client.server.BackEndService;
import org.apache.falcon.designer.ui.client.server.BackEndServiceAsync;
import org.apache.falcon.designer.ui.vo.FeedVO;
import org.apache.falcon.designer.ui.widget.ActionImageWithText;
import org.apache.falcon.designer.ui.widget.ArrowImageWithText;
import org.apache.falcon.designer.ui.widget.FeedInformation;
import org.apache.falcon.designer.ui.widget.ImageWithText;
import org.apache.falcon.designer.ui.widget.LogImageWithText;
import org.apache.falcon.designer.ui.widget.WaitingWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.Style.Overflow;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DragOverEvent;
import com.google.gwt.event.dom.client.DragOverHandler;
import com.google.gwt.event.dom.client.DragStartEvent;
import com.google.gwt.event.dom.client.DragStartHandler;
import com.google.gwt.event.dom.client.DropEvent;
import com.google.gwt.event.dom.client.DropHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SplitLayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * The main entry poit class respresenting the designer UI
 * 
 */
public class FalconDesignerUi implements EntryPoint {

  private BackEndServiceAsync backEndService = (BackEndServiceAsync) GWT
      .create(BackEndService.class);

  private final static Logger logger = Logger.getLogger("FalconDesignerUi");

  final static String TRANSFORMATIONLABEL = "Transformation";
  final static String SCHEMALABLE = "Schema";
  final static String ACTIONLABLE = "Actions";
  final static String PIPELINESLABEL = "Piplelines";

  private static final String[] pipelinesData = { "Analytics",
  "Datawarehousing", "Feedback" };

  private static final String hundredPercent = "100%";
  private static final int HeaderRowIndex = 0;

  private HorizontalPanel applicableClusterPanel;

  private HTML outputHTML;
  private final HorizontalPanel schemaTab = new HorizontalPanel();
  private final TabLayoutPanel outputTab = new TabLayoutPanel(2.5, Unit.EM);
  final AbsolutePanel workPanel = new AbsolutePanel();

  private final String loadingURL = GWT.getModuleBaseForStaticFiles()
      + "images/715.GIF";

  final int screenHeight = Window.getClientHeight();
  final int screenWidth = Window.getClientWidth();

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {

    SplitLayoutPanel mainPanel = new SplitLayoutPanel();
    mainPanel.setPixelSize(screenWidth, screenHeight);
    mainPanel.setTitle("main panel");
    mainPanel.setStyleName("splitLayoutPanel");

    // center panel topmost
    applicableClusterPanel = new HorizontalPanel();

    SplitLayoutPanel leftPanel = new SplitLayoutPanel();
    SplitLayoutPanel center = new SplitLayoutPanel();
    Style e = workPanel.getElement().getStyle();

    e.setPosition(Position.ABSOLUTE);
    e.setOverflow(Overflow.VISIBLE);

    // workPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

    outputTab.setStyleName("gwt-TabLayoutPanel");

    outputHTML = new HTML("this content");
    outputTab.add(outputHTML, "OutPut");
    outputTab.add(new HTML("that content"), "Errors");
    outputTab.add(new HTML("the other content"), "Metrics");
    outputTab.add(new HTML("the other content"), "Messages");

    final FocusPanel focusPanel = new FocusPanel(workPanel);
    center.addNorth(createMenu(), 50);
    center.addNorth(applicableClusterPanel, 200);
    center.addSouth(outputTab, 200);
    center.add(focusPanel);

    focusPanel.addDragOverHandler(new DragOverHandler() {

      @Override
      public void onDragOver(DragOverEvent dragoverevent) {
        logger.log(Level.INFO, "dragged1");

        focusPanel.getElement().getStyle().setBackgroundColor("#99eedf");

      }
    });

    focusPanel.addDropHandler(new DropHandler() {

      @Override
      public void onDrop(DropEvent event) {

        logger.log(Level.INFO, "dragged2");
        // prevent the native text drop
        event.preventDefault();

        final String data = event.getData("text");
        final int workPanelLeft = workPanel.getAbsoluteLeft();
        final int workPanelTop = workPanel.getAbsoluteTop();
        final int x = event.getNativeEvent().getClientX();
        final int y = event.getNativeEvent().getClientY();
        final int eventX = event.getNativeEvent().getClientX() - workPanelLeft;
        final int eventY = event.getNativeEvent().getClientY() - workPanelTop;
        String type = event.getData("type");
        logger.log(Level.INFO, "passed value" + event.getSource() + "get type "
            + type);
        if (TRANSFORMATIONLABEL.equals(type)) {

          // some mock schema . have to find the best to get schema of the last
          // element
          final Map<String, String> schema = new HashMap<String, String>();
          schema.put("column1", "int");
          schema.put("column2", "varchar");

          ImageWithText log =
              new ArrowImageWithText(data + "(" + type + ")", data, schema);
          workPanel.add(log, eventX, eventY - 20);

        } else if (ACTIONLABLE.equals(type)) {

          ImageWithText log = null;
          log = new ActionImageWithText(data);

          log.addClickHandler(new ClickHandler() {

            ActionDialogBox emailAction = ActionWidgetFactory
                .getActionFactor(data.toUpperCase());

            @Override
            public void onClick(ClickEvent clickEventInst) {
              logger.log(Level.INFO, " ------------ action widget ------------"
                  + emailAction.getActionWidget().getCurrentActionVO());
              emailAction.center();

            }
          });

          workPanel.add(log, eventX, eventY);

        } else if (SCHEMALABLE.equals(type)) {
          ImageWithText log = null;
          log = new LogImageWithText(data);

          log.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent clickEventInst) {

              WaitingWidget.show();
              logger
                  .log(
                      Level.INFO,
                      "here ------------ ------------ ------------ ------------ ------------ ------------");

              final AsyncCallback<Map<String, String>> callbackPopulateOutPutTab =
                  new AsyncCallback<Map<String, String>>() {
                    public void onSuccess(Map<String, String> result) {
                      outputHTML.setText(result.toString());

                      FlexTable schemaFlexTable = new FlexTable();
                      int rowNumber = 0;
                      for (Entry<String, String> eachEntry : result.entrySet()) {
                        schemaFlexTable.setText(rowNumber, 0,
                            eachEntry.getKey());
                        schemaFlexTable.setText(rowNumber, 1,
                            eachEntry.getValue());
                        rowNumber++;
                      }

                      schemaTab.clear();
                      schemaTab.add(schemaFlexTable);
                      outputTab.add(schemaTab, "Schema");

                      WaitingWidget.hide();

                    }

                    @Override
                    public void onFailure(Throwable caught) {
                      logger.severe(caught.getMessage());

                    }
                  };

              final AsyncCallback<FeedVO> callbackPopulateApplicableClusterPanel =
                  new AsyncCallback<FeedVO>() {
                    public void onSuccess(FeedVO result) {

                      FeedInformation feedInformationPanel =
                          new FeedInformation("Applicable Clusters", result
                              .getClusters(), "Frequency:");
                      applicableClusterPanel.clear();
                      applicableClusterPanel.add(feedInformationPanel);
                      outputHTML.setText(result.toString());

                      backEndService.getColumnsForTable(result.getHcatURL(),
                          result.getDatabaseName(), result.getTableName(),
                          callbackPopulateOutPutTab);

                    }

                    @Override
                    public void onFailure(Throwable caught) {
                      logger.severe(caught.getMessage());

                    }
                  };

              backEndService.getFeedDetails(data,
                  callbackPopulateApplicableClusterPanel);

            }
          });

          // will show details on clicking on the logs
          workPanel.add(log);
        } else {
          ImageWithText log = null;
          log = new ActionImageWithText(data);
          workPanel.add(log, x - workPanelLeft, y - workPanelTop);
        }
      }
    });

    SplitLayoutPanel rightPanel = new SplitLayoutPanel();

    mainPanel.addWest(leftPanel, 150);
    mainPanel.addEast(rightPanel, 150);
    mainPanel.add(center);

    renderTransformations(rightPanel);

    renderActions(rightPanel);
    // add pipelines

    FlexTable menuflexTable = new FlexTable();
    int rowIndex = 1;

    menuflexTable.setWidth(hundredPercent);
    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(PIPELINESLABEL, menuflexTable);
    menuflexTable.addStyleName("FlexTable");
    rowIndex = 1;
    Tree treeMenu = showPipelinTree();
    menuflexTable.setWidget(rowIndex, 0, treeMenu);
    leftPanel.addNorth(menuflexTable, screenHeight / (double) 2);

    renderFeeds(leftPanel);

    // Associate the Main panel with the HTML host page.
    RootPanel.get("bodyPanel").add(mainPanel);

  }

  private void addColumn(String columnHeading, FlexTable flexTable) {
    int HeaderRowIndex = 0;
    Widget widget = createCellWidget(columnHeading, false, null);
    int cell = flexTable.getCellCount(HeaderRowIndex);

    widget.setWidth(hundredPercent);
    widget.addStyleName("FlexTable-ColumnLabel");

    flexTable.setWidget(HeaderRowIndex, cell, widget);
    flexTable.getCellFormatter().addStyleName(HeaderRowIndex, cell,
        "FlexTable-ColumnLabelCell");
  }

  private Widget createCellWidget(Object cellObject, boolean draggable,
      final String type) {
    Widget widget = null;
    if (cellObject instanceof Widget)
      widget = (Widget) cellObject;
    else {
      final Label widgetLabel = new Label(cellObject.toString());
      if (draggable) {
        widgetLabel.getElement().setDraggable(
            com.google.gwt.dom.client.Element.DRAGGABLE_TRUE);

        widgetLabel.addDragStartHandler(new DragStartHandler() {

          @Override
          public void onDragStart(DragStartEvent dragstartevent) {
            logger.log(Level.INFO, "Dragging");
            dragstartevent.setData("text", widgetLabel.getText());
            dragstartevent.setData("type", type);

          }
        });
      }
      widget = widgetLabel;

    }
    return widget;
  }

  private void addRow(Object cellObjects, FlexTable flexTable, int rowIndex,
      String type) {
    Widget widget = createCellWidget(cellObjects, true, type);
    widget.setStyleName("FlexTable-Cell");
    flexTable.setWidget(rowIndex, 0, widget);

  }

  private void renderFeeds(SplitLayoutPanel leftPanel) {

    final FlexTable menuflexTable = new FlexTable();
    menuflexTable.setWidth(hundredPercent);
    leftPanel.add(menuflexTable);
    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(SCHEMALABLE, menuflexTable);
    menuflexTable.addStyleName("FlexTable");
    final int rowIndex = 1;

    AsyncCallback<List<String>> callBackFeedNames =
        new AsyncCallback<List<String>>() {
          public void onSuccess(List<String> result) {
            int localIndex = rowIndex;
            if (result != null) {
              for (String eachFeed : result) {
                addRow(eachFeed, menuflexTable, localIndex++, SCHEMALABLE);
              }
            }
          }

          @Override
          public void onFailure(Throwable caught) {
            logger.severe(caught.getMessage());
          }
        };

    backEndService.getAllFeedNames(callBackFeedNames);

  }

  private void renderActions(final SplitLayoutPanel p) {

    final VerticalPanel actionVerticalPanel = new VerticalPanel();
    actionVerticalPanel.setWidth(hundredPercent);
    p.add(actionVerticalPanel);
    actionVerticalPanel.add(new Image(loadingURL));

    final FlexTable menuflexTable = new FlexTable();
    final int rowIndex = 1;
    menuflexTable.setWidth(hundredPercent);

    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(ACTIONLABLE, menuflexTable);
    menuflexTable.addStyleName("FlexTable");

    AsyncCallback<List<String>> callbackGetAllActions =
        new AsyncCallback<List<String>>() {
          public void onSuccess(List<String> result) {
            int localIndex = rowIndex;
            for (String eachValue : result) {
              addRow(eachValue, menuflexTable, localIndex++, ACTIONLABLE);

            }
            actionVerticalPanel.clear();
            actionVerticalPanel.add(menuflexTable);
          }

          @Override
          public void onFailure(Throwable caught) {
            logger.severe(caught.getMessage());
          }
        };

    backEndService.getAllActions(callbackGetAllActions);

  }

  private void renderTransformations(final SplitLayoutPanel p) {

    final VerticalPanel transformationVerticalPanel = new VerticalPanel();
    transformationVerticalPanel.setWidth(hundredPercent);
    p.addNorth(transformationVerticalPanel, screenHeight / (double) 2);
    transformationVerticalPanel.add(new Image(loadingURL));

    final FlexTable menuflexTable = new FlexTable();
    menuflexTable.setWidth(hundredPercent);
    // rightPanel.
    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(TRANSFORMATIONLABEL, menuflexTable);
    menuflexTable.addStyleName("FlexTable");
    final int rowIndex = 1;

    menuflexTable.setWidget(rowIndex, 0, new Button("Create"));
    // ...and set it's column span so that it takes up the whole row.
    menuflexTable.getFlexCellFormatter().setColSpan(rowIndex, 0, 1);

    AsyncCallback<List<String>> callbackGetAllTransformations =
        new AsyncCallback<List<String>>() {
          public void onSuccess(List<String> result) {
            int localIndex = rowIndex;
            for (String eachValue : result) {
              addRow(eachValue, menuflexTable, localIndex++,
                  TRANSFORMATIONLABEL);
            }
            transformationVerticalPanel.clear();
            transformationVerticalPanel.add(menuflexTable);
          }

          @Override
          public void onFailure(Throwable caught) {
            logger.severe(caught.getMessage());
          }
        };

    backEndService.getAllTransformations(callbackGetAllTransformations);

  }

  private Tree showPipelinTree() {

    TreeItem root1 = new TreeItem();
    root1.setText("Analytics");
    root1.addTextItem("DataWaresousing");
    root1.addTextItem("item1");
    root1.addTextItem("item2");

    TreeItem root2 = new TreeItem();
    root2.setText("Feedback");
    for (String eachLable : pipelinesData) {
      root2.addTextItem(eachLable);
    }

    Tree treeMenu = new Tree();
    treeMenu.addItem(root1);
    treeMenu.addItem(root2);
    return treeMenu;

  }

  private MenuBar createMenu() {

    Command cmd = new Command() {
      public void execute() {
        getWidgetTree();
        Window.alert("You selected a menu item!");
      }
    };

    MenuBar topLevelMenu = new MenuBar();
    topLevelMenu.addItem("Save", cmd);
    topLevelMenu.addItem("Edit", cmd);
    topLevelMenu.addItem("Compile", cmd);
    topLevelMenu.addItem("Version", cmd);
    topLevelMenu.addItem("Provision", cmd);
    topLevelMenu.setStyleName("gwt-MenuBar");
    return topLevelMenu;

  }

  private void getWidgetTree() {

    Iterator<Widget> allWidgets = workPanel.iterator();
    while (allWidgets.hasNext()) {
      Widget currentWidget = allWidgets.next();
      logger.info(" Widget " + currentWidget.getClass() + "left"
          + workPanel.getWidgetLeft(currentWidget) + " value "
          + ((ImageWithText) currentWidget).getValue());

    }

  }

}
