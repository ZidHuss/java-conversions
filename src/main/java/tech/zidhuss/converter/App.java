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

    Stage stage;
    Button button;
    Scene tempScene;
    Scene massScene;
    Scene mainScene;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Conversion Program");
        stage = primaryStage;
        tempScene = new Scene(addTemperaturePane(), 500, 500);
        mainScene = new Scene(mainMenu(), 500, 500);
        massScene = new Scene(addMassPane(), 500, 500);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private GridPane addTemperaturePane() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(15));
        TextField textFieldCelcius = new TextField("0");
        TextField textFieldFahrenheit = new TextField("32");
        TextField textFieldKelvin = new TextField("-273.15");
        grid.add(new Label("Celcius"), 0, 0);
        grid.add(new Label("Fahrenheit"), 0, 1);
        grid.add(new Label("Kelvin"), 0, 2);
        grid.add(textFieldCelcius, 1, 0);
        grid.add(textFieldFahrenheit, 1, 1);
        grid.add(textFieldKelvin, 1, 2);
        grid.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(mainScene);
        });
        grid.add(backButton, 0, 3);

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

    private GridPane addMassPane() {
        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(15));
        TextField textFieldKilo = new TextField("0");
        TextField textFieldPound = new TextField("0");
        grid.add(new Label("Kilo"), 0, 0);
        grid.add(new Label("Pound"), 0, 1);
        grid.add(textFieldKilo, 1, 0);
        grid.add(textFieldPound, 1, 1);
        grid.setAlignment(Pos.CENTER);

        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            stage.setScene(mainScene);
        });
        grid.add(backButton, 0, 3);

        textFieldKilo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textFieldKilo.isFocused())
                if (!newValue.matches("^-?\\d*\\.?\\d*"))
                    textFieldKilo.setText(oldValue);
                else {
                    double parsed = Double.parseDouble(newValue);
                    textFieldPound.setText(String.format("%.2f", Conversions.kgToP(parsed)));
                }
        });
        textFieldPound.textProperty().addListener((observable, oldValue, newValue) -> {
            if (textFieldPound.isFocused())
                if (!newValue.matches("^-?\\d*\\.?\\d*"))
                    textFieldPound.setText(oldValue);
                else {
                    double parsed = Double.parseDouble(newValue);
                    textFieldKilo.setText(String.format("%.2f", Conversions.pToKg(parsed)));
                }
        });
        return grid;
    }
    private GridPane mainMenu() {
        GridPane grid = new GridPane();
        grid.setVgap(20);
        grid.setPadding(new Insets(15));
        grid.setAlignment(Pos.CENTER);
        Button temp = new Button("Temperature");
        temp.setOnAction(e -> {
            stage.setScene(tempScene);
        });
        Button mass = new Button("Mass");
        mass.setOnAction(e -> {
            stage.setScene(massScene);
        });
        grid.add(temp, 0, 0);
        grid.add(mass, 0, 1);
        return grid;
    }
}
