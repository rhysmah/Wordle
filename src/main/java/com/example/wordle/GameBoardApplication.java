package com.example.wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GameBoardApplication extends Application {

    private final String windowTitle          = "Wordle Clone";
    private final int    windowWidthInPixels  = 500;
    private final int    windowHeightInPixels = 650;

    /**
     * Runs the program.
     * @param stage to be shown.
     * @throws IOException if "gameboard.fxml" is not found.
     */
    @Override
    public void start(final Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardApplication.class.getResource("gameboard.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), windowWidthInPixels, windowHeightInPixels);
        stage.setTitle(windowTitle);
        stage.setScene(scene);

        GameBoardController controller = fxmlLoader.getController();
        controller.initializeLetterBoxes();

        stage.show();
    }

    /**
     * Drives the program.
     * @param args to be read.
     */
    public static void main(final String[] args) {
        launch();
    }
}
