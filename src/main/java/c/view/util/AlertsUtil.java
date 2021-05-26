package c.view.util;

import javafx.scene.control.Alert;

public class AlertsUtil {

    public static void showInfoMessage(String title, String message) {
        showAlert(Alert.AlertType.INFORMATION, title, message);
    }

    public static void showErrorMessage(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }

    private static void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
