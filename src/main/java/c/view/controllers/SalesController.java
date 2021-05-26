package c.view.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import c.model.Receipt;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import static c.view.util.AlertsUtil.showErrorMessage;

public class SalesController extends BaseController implements Initializable {

    @FXML
    private DatePicker startField;

    @FXML
    private DatePicker endField;

    @FXML
    private TableView<Receipt> table;

    @FXML
    private TableColumn<Receipt, String> dateColumn;

    @FXML
    private TableColumn<Receipt, String> litersColumn;

    @FXML
    private TableColumn<Receipt, String> mountColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        dateColumn.setCellValueFactory(
                sale -> new SimpleStringProperty(
                        sale.getValue().getFormattedDate()
                )
        );
        
        litersColumn.setCellValueFactory(
                sale -> new SimpleStringProperty(
                        String.valueOf(
                                sale.getValue().getLiters()
                        )
                )
        );
        
        mountColumn.setCellValueFactory(
                sale -> new SimpleStringProperty(
                        "$" + sale.getValue().getMount()
                )
        );
        
    }

    public void load() {
        try {
            table.getItems().clear();
            table.setItems(
                    fuelStation.getReceipts(
                            startField.getValue(), endField.getValue()
                    ).stream().collect(Collectors.toCollection(FXCollections::observableArrayList))
            );
        } catch (Exception e) {
            showErrorMessage("Error", e.getMessage());
        }
    }

}
