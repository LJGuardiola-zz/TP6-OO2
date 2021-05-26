package c.view.controllers;

import c.model.Receipt;
import c.view.util.AlertsUtil;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.StackPane;
import javafx.util.converter.IntegerStringConverter;
import c.model.Fuel;
import c.model.Fuels;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

public class FuelingController extends BaseController implements Initializable {

    private ToggleGroup group;

    @FXML
    public TextField emailField;

    @FXML
    private TextField litersField;

    @FXML
    private RadioButton normalButton;

    @FXML
    private RadioButton superButton;

    @FXML
    private StackPane loadingPanel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        group = new ToggleGroup();

        normalButton.setToggleGroup(group);
        normalButton.setUserData(Fuels.COMUN);

        superButton.setToggleGroup(group);
        superButton.setUserData(Fuels.SUPER);

        litersField.setTextFormatter(
                new TextFormatter<>(
                        new IntegerStringConverter()
                )
        );

    }

    @FXML
    void calculate() {
        Task<Double> task = new Task<Double>() {
            @Override
            protected Double call() {
                return fuelStation.getFinalCost(
                        (Fuel) group.getSelectedToggle().getUserData(),
                        (Integer) litersField.getTextFormatter().getValue()
                );
            }
        };
        configureTask(task,
                t -> AlertsUtil.showInfoMessage(
                        "Costo final", "El costo final es de " + t.getValue()
                )
        );
        exec(task);
    }

    @FXML
    void pay() {
            Task<String> task = new Task<String>() {
                @Override
                protected String call() {
                    Receipt receipt = fuelStation.pay(
                            (Fuel) group.getSelectedToggle().getUserData(),
                            (Integer) litersField.getTextFormatter().getValue(),
                            emailField.getText()
                    );
                    return receipt.getEmail();
                }
            };
            configureTask(task,
                    t -> AlertsUtil.showInfoMessage(
                            "Éxito", "El pago fue procesado con éxito. Se envió el recibo a " + t.getValue()
                    )
            );
            exec(task);
    }

    private void configureTask(Task<?> task, Consumer<Task<?>> consumer) {
        task.setOnRunning(
                event -> loadingPanel.setVisible(true)
        );
        task.setOnFailed(event -> {
            loadingPanel.setVisible(false);
            AlertsUtil.showErrorMessage("Error", task.getException().getMessage());
        });
        task.setOnSucceeded(event -> {
            loadingPanel.setVisible(false);
            consumer.accept(task);
        });
    }

    private void exec(Task<?> task) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(task);
        executorService.shutdown();
    }

}
