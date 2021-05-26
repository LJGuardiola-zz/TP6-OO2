package c.view;

import c.model.SendEmailFuelStationObserver;
import c.service.MailtrapService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import c.model.FuelStation;
import c.model.ReceiptsRepository;
import c.view.controllers.HomeController;

public abstract class Launcher extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/c/view/home.fxml")
        );

        stage.setTitle("YPZW");
        stage.setScene(
                new Scene(loader.load())
        );

        HomeController controller = loader.getController();
        controller.setFuelStation(
                new FuelStation(getRepository(),
                        new SendEmailFuelStationObserver(
                                new MailtrapService()
                        )
                )
        );

        stage.show();

    }

    public abstract ReceiptsRepository getRepository();

}
