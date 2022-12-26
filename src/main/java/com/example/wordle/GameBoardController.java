package com.example.wordle;

import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.util.Duration;
import java.util.Arrays;

public class GameBoardController {

    private static final int    LETTERS_PER_WORD          = 5;
    private static final int    TURNS_PER_GAME            = 6;
    private static final String CONTAINS_VALID_CHARACTERS = "^[a-zA-Z]*$";

    // First letter row
    @FXML private Label box11;
    @FXML private Label box12;
    @FXML private Label box13;
    @FXML private Label box14;
    @FXML private Label box15;

    // Second letter row
    @FXML private Label box21;
    @FXML private Label box22;
    @FXML private Label box23;
    @FXML private Label box24;
    @FXML private Label box25;

    // Second letter row
    @FXML private Label box31;
    @FXML private Label box32;
    @FXML private Label box33;
    @FXML private Label box34;
    @FXML private Label box35;

    // Second letter row
    @FXML private Label box41;
    @FXML private Label box42;
    @FXML private Label box43;
    @FXML private Label box44;
    @FXML private Label box45;

    // Second letter row
    @FXML private Label box51;
    @FXML private Label box52;
    @FXML private Label box53;
    @FXML private Label box54;
    @FXML private Label box55;

    // Second letter row
    @FXML private Label box61;
    @FXML private Label box62;
    @FXML private Label box63;
    @FXML private Label box64;
    @FXML private Label box65;

    private Label[][] letterBoxes;
    private String[]  userWordLetters;
    private int       letterIndex;
    private int       rowIndex;
    private int       playerTurn;
    private long      lastBounceAnimation;

    /**
     * Creates an object of type GameBoard.
     */
    public GameBoardController() {
        letterIndex     = 0;
        rowIndex        = 0;
        playerTurn      = 0;
        userWordLetters = new String[LETTERS_PER_WORD];
    }

    public void initializeLetterBoxes() {
        letterBoxes = new Label[][] {
                {box11, box12, box13, box14, box15},
                {box21, box22, box23, box24, box25},
                {box31, box32, box33, box34, box35},
                {box41, box42, box43, box44, box45},
                {box51, box52, box53, box54, box55},
                {box61, box62, box63, box64, box65}
        };
    }

    protected void enterKeyPushed() {
        if (userWordLetters.length == LETTERS_PER_WORD) {
            String userWord = String.join("", userWordLetters);

            if (validateUserGuess(userWord)) {
                playerTurn++;
                rowIndex++;
                letterIndex = 0;

            } else {
                bounceAnimationForInvalidWord();
            }
        }
    }

    private void bounceAnimationForInvalidWord() {
        if (System.currentTimeMillis() - lastBounceAnimation > 500) {
            for (int i = 0; i < LETTERS_PER_WORD; i++) {
                bounceAnimation(letterBoxes[rowIndex][i]);
                lastBounceAnimation = System.currentTimeMillis();
            }
        }
    }

    private void bounceAnimation(final Label label) {
        Duration wiggleDuration = Duration.millis(100);
        TranslateTransition wiggleLetterBox = new TranslateTransition(wiggleDuration, label);

        wiggleLetterBox.setToX(20);
        wiggleLetterBox.setAutoReverse(true);
        wiggleLetterBox.setCycleCount(4);
        wiggleLetterBox.play();
    }

    protected void backspaceKeyPushed() {
        if (letterIndex > 0) {
            letterIndex--;
            letterBoxes[rowIndex][letterIndex].setText("");
            userWordLetters[letterIndex] = "";
        }
    }

    protected void letterKeyPushed(final String letter) {
        addLetterToBox(letter);
    }

    private void addLetterToUserWord(final String letter) {
        userWordLetters[letterIndex] = letter;
    }


    private void addLetterToBox(final String letter) {
        if (letterIndex < 5) {
            letterBoxes[rowIndex][letterIndex].setText(letter);
            addLetterToUserWord(letter);
            letterIndex++;
        }
    }

    /*
     * Checks that a word contains specified letters per word.
     */
    private boolean validLength(final String word) {
        return word.length() == LETTERS_PER_WORD;
    }

    /*
     * Checks that a word contains valid characters.
     */
    private boolean validCharacters(final String word) {
        return word.matches(CONTAINS_VALID_CHARACTERS);
    }

    /*
     * Checks if the userWord is in the word list.
     */
    private boolean validWord(final String word) {
        return Arrays.asList(WordList.WORDS).contains(word);
    }

    /**
     * Checks that word contains exactly five letters and is a valid English word.
     * @param word the word (String) to be validated.
     * @return true if the word is a real five-letter word.
     */
    private boolean validateUserGuess(final String word) {
        return validLength(word) && validCharacters(word) && validWord(word);
    }
}
