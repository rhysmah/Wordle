package com.example.wordle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Arrays;
import java.util.Random;

public class GameBoardController {

    private static final int    TURNS_PER_GAME = 6;
    private static final Random RANDOM         = new Random();

    private final String[]  userWordLetters;
    private final boolean[] winCondition;

    @FXML private Button quitGame;
    @FXML private Button startGame;

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

    // Third letter row
    @FXML private Label box31;
    @FXML private Label box32;
    @FXML private Label box33;
    @FXML private Label box34;
    @FXML private Label box35;

    // Fourth letter row
    @FXML private Label box41;
    @FXML private Label box42;
    @FXML private Label box43;
    @FXML private Label box44;
    @FXML private Label box45;

    // Fifth letter row
    @FXML private Label box51;
    @FXML private Label box52;
    @FXML private Label box53;
    @FXML private Label box54;
    @FXML private Label box55;

    // Sixth letter row
    @FXML private Label box61;
    @FXML private Label box62;
    @FXML private Label box63;
    @FXML private Label box64;
    @FXML private Label box65;

    private Label[][] letterBoxes;
    private int       letterIndex;
    private int       rowIndex;
    private int       playerTurn;
    private String    gameWord;

    /**
     * Creates an object of type GameBoard.
     */
    public GameBoardController() {
        letterIndex     = 0;
        rowIndex        = 0;
        playerTurn      = 0;
        winCondition    = new boolean[] { false, false, false, false, false} ;
        userWordLetters = new String[Word.LETTERS_PER_WORD];
        gameWord        = WordList.WORDS[RANDOM.nextInt(WordList.WORDS.length - 1)];
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

    public void initializeButtons() {
        startGame.setOnAction(actionEvent -> startGameButtonClick());
        quitGame.setOnAction(actionEvent  -> quitGameButtonClick());
    }

    public void startGameButtonClick() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                letterBoxes[i][j].setText("");
                letterBoxes[i][j].setStyle("-fx-background-color: #DCDCDC;");
            }
        }
        letterIndex = 0;
        rowIndex    = 0;
        playerTurn  = 0;
        gameWord    = WordList.WORDS[RANDOM.nextInt(WordList.WORDS.length - 1)];

        resetUserWord();
    }

    public void quitGameButtonClick() {
        Platform.exit();
    }

    protected void enterKeyPushed() {

        // Enter key stops working when user wins game.
        if (userWordLetters.length == Word.LETTERS_PER_WORD && !winConditionMet()) {
            String userWord = String.join("", userWordLetters);

            if (Word.validateUserGuess(userWord)) {
                printLetter();

                playerTurn++;
                rowIndex++;
                letterIndex = 0;

            } else {
                Animations.invalidWordAnimation(letterBoxes, rowIndex);
            }
        }
        if (winConditionMet()) {
            PopUpWindow.display("You won!", "The word was indeed " + gameWord);
        }
        if (playerHasNoTurnsRemaining()) {
            PopUpWindow.display("You lost!", "The word was " + gameWord);
        }
    }

    protected void backspaceKeyPushed() {
        if (letterIndex > 0) {
            letterIndex--;
            letterBoxes[rowIndex][letterIndex].setText("");
            userWordLetters[letterIndex] = "";
        }
    }

    protected void letterKeyPushed(final String letter) {
        if (!winConditionMet()) {
            addLetterToGameBoardLetterBox(letter);
        }
    }

    private void printLetter() {

        letterIndex = 0;
        String[] gameWordLetters  = gameWord.split("");

        while (letterIndex < Word.LETTERS_PER_WORD) {
            String playerWordLetter = userWordLetters[letterIndex];
            Label  gameWordLetter   = letterBoxes[rowIndex][letterIndex];

            if (playerWordLetter.equals(gameWordLetters[letterIndex])) {
                Animations.animateLetter(gameWordLetter, "#3CB371;"); // Green
                gameWordLetters[letterIndex] = "";
                userWordLetters[letterIndex] = "";
                winCondition[letterIndex] = true;

            } else if (Arrays.asList(gameWordLetters).contains(playerWordLetter)) {
                Animations.animateLetter(gameWordLetter, "#FFD700;"); // Yellow
                gameWordLetters[letterIndex] = "";
                userWordLetters[letterIndex] = "";

            } else {
                Animations.animateLetter(gameWordLetter, "#DCDCDC;"); // Grey
            }
            letterIndex++;
        }
    }

    private boolean playerHasNoTurnsRemaining() {
        return playerTurn == TURNS_PER_GAME;
    }

    private void resetUserWord() {
        Arrays.fill(userWordLetters, "");
    }

    private boolean winConditionMet() {
        for (boolean booleanValue : winCondition) {
            if (!booleanValue) {
                return false;
            }
        }
        return true;
    }

    private void addLetterToUserWord(final String letter) {
        userWordLetters[letterIndex] = letter;
    }

    private void addLetterToGameBoardLetterBox(final String letter) {
        if (letterIndex < 5) {
            letterBoxes[rowIndex][letterIndex].setText(letter);
            addLetterToUserWord(letter);
            letterIndex++;
        }
    }
}
