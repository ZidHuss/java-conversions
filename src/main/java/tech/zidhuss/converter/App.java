package tech.zidhuss.converter;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class App extends Application {

    Button button;
    Scene scene;
    TextField textFieldCelcius;
    TextField textFieldFahrenheit;
    TextField textFieldKelvin;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Conversion Program");
        scene = new Scene(addGridPane(), 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private GridPane addGridPane() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(15));
        textFieldCelcius = new TextField("0");
        textFieldFahrenheit = new TextField("32");
        textFieldKelvin = new TextField("-273.15");
        grid.add(new Label("Celcius"), 0, 0);
        grid.add(new Label("Fahrenheit"), 0, 1);
        grid.add(new Label("Kelvin"), 0, 2);
        grid.add(textFieldCelcius, 1, 0);
        grid.add(textFieldFahrenheit, 1, 1);
        grid.add(textFieldKelvin, 1, 2);
        grid.setAlignment(Pos.CENTER);

        textFieldCelcius.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textFieldCelcius.isFocused())
                if (!newValue.matches("^-?\\d*\\.?\\d*"))
                    textFieldCelcius.setText(oldValue);
                else {
                    double parsed = Double.parseDouble(newValue);
                    textFieldFahrenheit.setText(String.format("%.2f", Conversions.cToF(parsed)));
                    textFieldKelvin.setText(String.format("%.2f", Conversions.cToK(parsed)));
                }
        });
        textFieldFahrenheit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textFieldFahrenheit.isFocused())
                if (!newValue.matches("^-?\\d*\\.?\\d*"))
                    textFieldFahrenheit.setText(oldValue);
                else {
                    double parsed = Double.parseDouble(newValue);
                    textFieldCelcius.setText(String.format("%.2f", Conversions.fToC(parsed)));
                    textFieldKelvin.setText(String.format("%.2f", Conversions.fToK(parsed)));
                }
        });
        textFieldKelvin.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textFieldKelvin.isFocused())
                if (!newValue.matches("^-?\\d*\\.?\\d*"))
                    textFieldKelvin.setText(oldValue);
                else {
                    double parsed = Double.parseDouble(newValue);
                    textFieldCelcius.setText(String.format("%.2f", Conversions.kToC(parsed)));
                    textFieldFahrenheit.setText(String.format("%.2f", Conversions.kToF(parsed)));
                }
        });
        return grid;
    }
}
