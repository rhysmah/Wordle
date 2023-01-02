package com.example.wordle;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import java.io.IOException;

public class GameBoardApplication extends Application {

    private static final String WINDOW_TITLE = "Wordle Clone";
    private static final int    WINDOW_WIDTH_IN_PIXELS = 500;
    private static final int    WINDOW_HEIGHT_IN_PIXELS = 650;

    private long animationDelay;

    /**
     * Runs the program.
     * @param stage to be shown.
     * @throws IOException if "gameboard.fxml" is not found.
     */
    @Override
    public void start(final Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(GameBoardApplication.class.getResource("gameboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WINDOW_WIDTH_IN_PIXELS, WINDOW_HEIGHT_IN_PIXELS);

        GameBoardController controller = fxmlLoader.getController();

        // Sets up key presses from the keyboard.
        scene.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().isLetterKey()) {
                controller.letterKeyPushed(keyEvent.getText().toUpperCase());
            }
            if (keyEvent.getCode() == KeyCode.ENTER && System.currentTimeMillis() - animationDelay > 500) {
                controller.enterKeyPushed();
                animationDelay = System.currentTimeMillis();
            }
            if (keyEvent.getCode() == KeyCode.BACK_SPACE) {
                controller.backspaceKeyPushed();
            }
        });
        controller.initializeLetterBoxes();
        controller.initializeButtons();

        stage.setTitle(WINDOW_TITLE);
        stage.setScene(scene);
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
