package com.example.wordle;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class Animations {

    public static void animateLetter(final Label letterBox, final String color) {
        ScaleTransition showLetterBack = new ScaleTransition(Duration.millis(250), letterBox);
        showLetterBack.setFromY(1);
        showLetterBack.setToY(0);

        ScaleTransition showLetterFront = new ScaleTransition(Duration.millis(250), letterBox);
        showLetterFront.setFromY(0);
        showLetterFront.setToY(1);

        showLetterBack.play();
        showLetterBack.setOnFinished(actionEvent -> {
            letterBox.setStyle("-fx-background-color: " + color);
            showLetterFront.play();
        });
    }

    public static void invalidWordAnimation(final Label[][] twoDArray, final int rowIndex) {
        for (int i = 0; i < Word.LETTERS_PER_WORD; i++) {
            bounceAnimation(twoDArray[rowIndex][i]);
        }
    }

    public static void bounceAnimation(final Label label) {
        Duration wiggleDuration             = Duration.millis(100);
        TranslateTransition wiggleLetterBox = new TranslateTransition(wiggleDuration, label);

        wiggleLetterBox.setToX(20);
        wiggleLetterBox.setAutoReverse(true);
        wiggleLetterBox.setCycleCount(4);

        wiggleLetterBox.play();
    }
}
