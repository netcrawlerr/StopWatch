package com.example.stopwatch;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Stopwatch extends Application {
    Scene scene;
    VBox vBox;
    HBox hBox;
    Button sButton, rButton;
    Text text;
    Timeline timeline;
    int mins = 0, secs = 0, millis = 0;
    boolean sos = true;

    public static void main(String[] args) {
        launch(args);
    }

    void change(Text text) {
        if (millis == 1000) {
            secs++;
            millis = 0;
        }
        if (secs == 60) {
            mins++;
            secs = 0;
        }
        text.setText((((mins / 10) == 0) ? "0" : "") + mins + ":"
                + (((secs / 10) == 0) ? "0" : "") + secs + ":"
                + (((millis / 10) == 0) ? "00" : (((millis / 100) == 0) ? "0" : "")) + millis++);
    }

    @Override
    public void start(Stage stage) {
        text = new Text("00:00:000");
        text.setStyle("-fx-font-size: 48px; -fx-fill: white; -fx-font-weight: bold;");
        timeline = new Timeline(new KeyFrame(Duration.millis(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(text);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(false);
        sButton = new Button("Start");
        sButton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 14px; -fx-font-weight: bold;");
        sButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (sos) {
                    timeline.play();
                    sos = false;
                    sButton.setText("Stop");
                } else {
                    timeline.pause();
                    sos = true;
                    sButton.setText("Start");
                }
            }
        });
        rButton = new Button("Reset");
        rButton.setStyle("-fx-background-color: lightcoral; -fx-font-size: 14px; -fx-font-weight: bold;");
        rButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mins = 0;
                secs = 0;
                millis = 0;
                timeline.pause();
                text.setText("00:00:000");
                if (!sos) {
                    sos = true;
                    sButton.setText("Start");
                }
            }
        });
        hBox = new HBox(30);
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(sButton, rButton);
        vBox = new VBox(30);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #4E3D28;"); // Darker brown background color
        vBox.getChildren().addAll(text, hBox);
        scene = new Scene(vBox, 200, 150);
        stage.setScene(scene);


        stage.setMinWidth(420);
        stage.setMinHeight(620);

        stage.setTitle("Stopwatch");
        stage.show();

    }
}
