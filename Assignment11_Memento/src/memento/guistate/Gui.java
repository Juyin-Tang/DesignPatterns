package memento.guistate;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Gui extends Application {

    private Controller controller;
    private ColorBox colorBox1;
    private ColorBox colorBox2;
    private ColorBox colorBox3;
    private CheckBox checkBox;

    @Override
    public void start(Stage stage) {
        controller = new Controller(this);

        Insets insets = new Insets(10, 10, 10, 10);

        colorBox1 = new ColorBox(1, controller);
        colorBox2 = new ColorBox(2, controller);
        colorBox3 = new ColorBox(3, controller);

        checkBox = new CheckBox("Click me!");
        checkBox.setPadding(insets);

        HBox hBox = new HBox(colorBox1.getRectangle(), colorBox2.getRectangle(), colorBox3.getRectangle());
        hBox.setSpacing(10);
        hBox.setMargin(colorBox1.getRectangle(), insets);
        hBox.setMargin(colorBox2.getRectangle(), insets);
        hBox.setMargin(colorBox3.getRectangle(), insets);

        Label label = new Label("Press Ctrl-Z to undo, Ctrl-Y to redo.");
        label.setPadding(insets);

        Button historyButton = new Button("Show History");
        historyButton.setOnAction(e -> showHistoryWindow());

        VBox vBox = new VBox(hBox, checkBox, label, historyButton);
        vBox.setSpacing(10);

        checkBox.setOnAction(event -> {
            controller.setIsSelected(checkBox.isSelected());
        });

        Scene scene = new Scene(vBox);
        scene.setOnKeyPressed(event -> {
            if (event.isControlDown() && event.getCode() == KeyCode.Z) {
                System.out.println("Ctrl-Z pressed");
                controller.undo();
            } else if (event.isControlDown() && event.getCode() == KeyCode.Y) {
                System.out.println("Ctrl-Y pressed");
                controller.redo();
            }
        });

        stage.setScene(scene);
        stage.setTitle("Memento Pattern Example with Redo & History");
        stage.show();
    }

    public void updateGui() {
        colorBox1.setColor(controller.getOption(1));
        colorBox2.setColor(controller.getOption(2));
        colorBox3.setColor(controller.getOption(3));
        checkBox.setSelected(controller.getIsSelected());
    }

    private void showHistoryWindow() {
        Stage historyStage = new Stage();
        historyStage.setTitle("History");

        ListView<IMemento> listView = new ListView<>();
        listView.getItems().setAll(controller.getHistory());

        listView.setCellFactory(param -> new ListCell<IMemento>() {
            @Override
            protected void updateItem(IMemento item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item.getDescription());
                }
            }
        });

        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
                IMemento selected = listView.getSelectionModel().getSelectedItem();
                if (selected != null) {
                    controller.restoreFromHistory(selected);
                    listView.getItems().setAll(controller.getHistory());
                }
            }
        });

        Scene scene = new Scene(listView, 400, 300);
        historyStage.setScene(scene);
        historyStage.show();
    }
}