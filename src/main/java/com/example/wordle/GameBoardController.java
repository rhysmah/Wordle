package com.example.wordle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class GameBoardController {

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
    private int       letterIndex;
    private int       rowIndex;

    /**
     * Creates an object of type GameBoard.
     */
    public GameBoardController() {
        letterIndex = 0;
        rowIndex    = 0;
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
}
