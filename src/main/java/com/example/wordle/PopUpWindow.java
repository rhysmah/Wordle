package com.example.wordle;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopUpWindow {

    public static void display(final String endGameMessage, final String gameWord) {
        Stage popUpWindow = new Stage();
        popUpWindow.initModality(Modality.APPLICATION_MODAL);

        Text endGameText  = new Text(endGameMessage);
        Text gameWordText = new Text(gameWord);

        Button closeWindow = new Button("Close");

        closeWindow.setOnAction(actionEvent -> popUpWindow.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(endGameText, gameWordText, closeWindow);
        layout.setAlignment(Pos.CENTER);
        Scene popUp = new Scene(layout, 200, 200);
        popUpWindow.setScene(popUp);
        popUpWindow.showAndWait();
    }
}
