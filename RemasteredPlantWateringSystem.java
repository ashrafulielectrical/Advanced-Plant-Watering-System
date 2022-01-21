package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortEvent;
import com.fazecast.jSerialComm.SerialPortMessageListenerWithExceptions;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import sample.SerialPortService;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.nio.ByteBuffer;

public class Main extends Application {
    private final static int MAX_MOISTURE_VALUE = 1 << 10;
    private int value;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        var controller = new DataController();
        var serialPort = SerialPortService.getSerialPort("COM5");
        var outputStream = serialPort.getOutputStream();
        serialPort.addDataListener(controller);

        var pane = new BorderPane();

        var slider = new Slider();
        slider.setMin(0.0);
        slider.setMax(100.0);

        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {

            //Button to activate pump + slider OLED values.

            try {
                outputStream.write(newValue.byteValue());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        var label = new Label();
        var button = new Button("Pump");
        button.setOnMousePressed(value -> {
            try {
                outputStream.write(500);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        button.setOnMouseReleased(value -> {
            try {
                outputStream.write(0);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        slider.valueProperty().addListener((observableValue, oldValue, newValue) -> {
            label.setText(String.valueOf(newValue.intValue()));
        });

        pane.setTop(button);
        pane.setCenter(slider);
        pane.setRight(label);
        pane.setPadding(new Insets(0, 20, 0, 20));

        // Graph of moisture sensor values.

        Stage stage = new Stage();
        stage.setTitle("Live Moisture Sensor Values");

        var now = System.currentTimeMillis();

        var xAxis = new NumberAxis("Time", now, now + 50000, 10000); // creates the x-axis (which automatically updates)
        var yAxis = new NumberAxis("Moisture Sensor Values", 0, MAX_MOISTURE_VALUE, 10); // creates the y-axis

        var series = new XYChart.Series<>(controller.getDataPoints()); // creates the series (all the data)
        series.setName("Moisture");

        var lineChart = new LineChart<>(xAxis, yAxis, FXCollections.singletonObservableList(series)); // creates the chart
        lineChart.setTitle("Live Moisture Sensor Values");


        pane.setBottom(lineChart);
        var scene = new Scene(pane, 1200, 600);


        primaryStage.setScene(scene);
        primaryStage.show();

    }
}