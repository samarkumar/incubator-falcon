package org.apache.falcon.designer.ui.client;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.falcon.designer.ui.client.server.BackEndService;
import org.apache.falcon.designer.ui.client.server.BackEndServiceAsync;
import org.apache.falcon.designer.ui.util.ArrowImageWithText;
import org.apache.falcon.designer.ui.util.FeedInformation;
import org.apache.falcon.designer.ui.util.ImageWithText;
import org.apache.falcon.designer.ui.util.LogImageWithText;
import org.apache.falcon.designer.ui.util.WaitingWidget;
import org.apache.falcon.designer.ui.vo.FeedVO;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
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

public class FalconDesignerUi implements EntryPoint {

  BackEndServiceAsync backEndService = (BackEndServiceAsync) GWT
      .create(BackEndService.class);

  private Button addStockButton = new Button("Add");

  final static String TRANSFORMATIONLABEL = "Transformation";
  final static String SCHEMALABLE = "Schema";
  final static String ACTIONLABLE = "Actions";
  final static String PIPELINESLABEL = "Piplelines";

  String[] pipelinesData = { "Analytics", "Datawarehousing", "Feedback" };

  private static final int HeaderRowIndex = 0;

  HorizontalPanel applicableClusterPanel;

  private HTML outputHTML;
  private final HorizontalPanel schemaTab = new HorizontalPanel();
  private final TabLayoutPanel outputTab =new TabLayoutPanel(2.5, Unit.EM);;

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

    final HorizontalPanel workPanel = new HorizontalPanel();

    HTML contents = new HTML("some data");
    workPanel.add(contents);
    workPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);

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

        System.out.println("dragged1");
        focusPanel.getElement().getStyle().setBackgroundColor("#99eedf");

      }
    });

    focusPanel.addDropHandler(new DropHandler() {

      @Override
      public void onDrop(DropEvent event) {

        System.out.println("dragged2");
        // prevent the native text drop
        event.preventDefault();

        final String data = event.getData("text");
        String type = event.getData("type");
        System.out.println("passed value" + event.getSource());
        if (TRANSFORMATIONLABEL.equals(type)) {
          ImageWithText log = new ArrowImageWithText(data + "(" + type + ")");
          workPanel.add(log);

        } else {
          ImageWithText log = null;
          if (SCHEMALABLE.equals(type)) {

            log = new LogImageWithText(data);

            log.addClickHandler(new ClickHandler() {

              @Override
              public void onClick(ClickEvent clickEventInst) {
                
                WaitingWidget.show();
                System.out
                    .println("here ------------ ------------ ------------ ------------ ------------ ------------");

                final AsyncCallback<Map<String, String>> callbackPopulateOutPutTab =
                    new AsyncCallback<Map<String, String>>() {
                      public void onSuccess(Map<String, String> result) {
                        outputHTML.setText(result.toString());

                        FlexTable schemaFlexTable = new FlexTable();
                        int rowNumber = 0;
                        for (Entry<String, String> eachEntry : result
                            .entrySet()) {
                          schemaFlexTable.setText(rowNumber, 0, eachEntry.getKey());
                          schemaFlexTable.setText(rowNumber, 1, eachEntry.getValue());
                          rowNumber++;
                        }
                        
                        schemaTab.clear();
                        schemaTab.add(schemaFlexTable);
                        outputTab.add(schemaTab, "Schema");
                        
                        
                        WaitingWidget.hide();

                      }

                      @Override
                      public void onFailure(Throwable caught) {
                        caught.printStackTrace();

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
                        caught.printStackTrace();

                      }
                    };

                backEndService.getFeedDetails(data,
                    callbackPopulateApplicableClusterPanel);

              }
            });

          } else {

            log = new LogImageWithText(data);

          }
          // will show details on clicking on the logs
          workPanel.add(log);
        }
      }
    });

    SplitLayoutPanel rightPanel = new SplitLayoutPanel();

    mainPanel.addWest(leftPanel, 150);
    mainPanel.addEast(rightPanel, 150);
    mainPanel.add(center);

    renderTransformations(rightPanel);

    FlexTable menuflexTable = new FlexTable();
    int rowIndex = 1;
    renderActions(rightPanel);

    // add pipelines

    menuflexTable = new FlexTable();
    menuflexTable.setWidth("100%");
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

    addStockButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        System.out.println(" get event source " + event.getSource()
            + " data is ");
        // addStock();
      }
    });

  }

  private void addColumn(String columnHeading, FlexTable flexTable) {
    int HeaderRowIndex = 0;
    Widget widget = createCellWidget(columnHeading, false, null);
    int cell = flexTable.getCellCount(HeaderRowIndex);

    widget.setWidth("100%");
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
            System.out.println("Dragging");
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
    menuflexTable.setWidth("100%");
    leftPanel.add(menuflexTable);
    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(SCHEMALABLE, menuflexTable);
    menuflexTable.addStyleName("FlexTable");
    final int rowIndex = 1;

    AsyncCallback<List<String>> callbackAAP =
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
            caught.printStackTrace();
          }
        };

    backEndService.getAllFeedNames(callbackAAP);

  }

  private void renderActions(final SplitLayoutPanel p) {

    final VerticalPanel actionVerticalPanel = new VerticalPanel();
    actionVerticalPanel.setWidth("100%");
    p.add(actionVerticalPanel);
    actionVerticalPanel.add(new Image(loadingURL));

    final FlexTable menuflexTable = new FlexTable();
    final int rowIndex = 1;
    menuflexTable.setWidth("100%");

    menuflexTable.insertRow(HeaderRowIndex);
    addColumn(ACTIONLABLE, menuflexTable);
    menuflexTable.addStyleName("FlexTable");

    AsyncCallback<List<String>> callbackGetAllActions =
        new AsyncCallback<List<String>>() {
          public void onSuccess(List<String> result) {
            int localIndex = rowIndex;
            for (String eachValue : result) {
              addRow(eachValue, menuflexTable, localIndex++, SCHEMALABLE);

            }
            actionVerticalPanel.clear();
            actionVerticalPanel.add(menuflexTable);
          }

          @Override
          public void onFailure(Throwable caught) {
            caught.printStackTrace();
          }
        };

    backEndService.getAllActions(callbackGetAllActions);

  }

  private void renderTransformations(final SplitLayoutPanel p) {

    final VerticalPanel transformationVerticalPanel = new VerticalPanel();
    transformationVerticalPanel.setWidth("100%");
    p.addNorth(transformationVerticalPanel, screenHeight / (double) 2);
    transformationVerticalPanel.add(new Image(loadingURL));

    final FlexTable menuflexTable = new FlexTable();
    menuflexTable.setWidth("100%");
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
              addRow(eachValue, menuflexTable, localIndex++, SCHEMALABLE);
            }
            transformationVerticalPanel.clear();
            transformationVerticalPanel.add(menuflexTable);
          }

          @Override
          public void onFailure(Throwable caught) {
            caught.printStackTrace();
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

}
