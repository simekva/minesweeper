package minesweeper;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public class minesweeperController {

    @FXML GridPane grid;
    @FXML Button buttonStartGame;
    @FXML RadioButton radioButton1;
    @FXML RadioButton radioButton2;
    @FXML RadioButton radioButton3;
    @FXML Text minesweeperText;
    @FXML ToggleGroup togglegroup;
    @FXML Board board = new Board("Easy");
    @FXML Text textOnTop;
    @FXML Button buttonSave;
    @FXML Button buttonLoad;
    FileHandler fileHandler = new FileHandler();
    
    @FXML
    public void handleStartGame(ActionEvent event) {
        handleRadioButtons(event);
        grid.setDisable(false);
        if (buttonStartGame.getText().contains("Start game") ) {
            grid.setVisible(true); // Vis brettet når trykker på Start game.
            buttonStartGame.setText("Back to Menu"); // Endre tekst på knappen når man er i en runde.
            radioButton1.setVisible(false); // Fjerner radioknappene ettersom de ikke brukes i spill.
            radioButton2.setVisible(false);
            radioButton3.setVisible(false);
            minesweeperText.setVisible(false); // Fjerner logo-tekst i spill.
            buttonLoad.setVisible(false);
            buttonSave.setVisible(true);
            board.placeBombs();
            
            for (int i = 0; i < 8; i++) {
                for (int l = 0; l < 8; l++) {
                    Tile tile = board.getTile(i, l);
                    tile.setFocusTraversable(false);
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {
                        

                        @Override
                        public void handle(MouseEvent event) {
                            if (event.getButton() == MouseButton.PRIMARY) {
                                if (!tile.hasFlag()) {
                                    tile.click();
                                    board.giveValues(tile);
                                    if (tile.getValue() != 0 && tile.getValue() != 9) {
                                        tile.setText(String.valueOf(tile.getValue()));
                                        tile.setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                        tile.setOpacity(1);
                                    }
                                    else if (tile.getValue() == 0) {
                                        ArrayList<Tile> list = board.returnAdjacent(tile);
                                        for (int s = 0; s < list.size(); s++) {
                                            if (!list.get(s).hasFlag()) {
                                                board.giveValues(list.get(s));
                                                list.get(s).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                list.get(s).setOpacity(1);
                                                list.get(s).setStyle("-fx-color: white");
                                                list.get(s).click();
                                            }
                                        }
                                    }
                                    else if (tile.getValue() == 9) {
                                        tile.click();
                                    }

                                    for (int i = 0; i < 8; i++) {
                                        for (int l = 0; l < 8; l++) {
                                            Tile tile = board.getTile(i, l);
                                            if (tile.getValue() == 0 && tile.isClicked()) {
                                                ArrayList<Tile> list = board.returnAdjacent(tile);
                                                for (int s = 0; s < list.size(); s++) {
                                                    board.giveValues(list.get(s));
                                                    list.get(s).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                    list.get(s).setOpacity(1);
                                                    list.get(s).setStyle("-fx-color: white");
                                                    list.get(s).click();
                                                    if (list.get(s).getValue() == 0) {
                                                        ArrayList<Tile> list2 = board.returnAdjacent(list.get(s));
                                                        for (int r = 0; r < list2.size(); r++) {
                                                            board.giveValues(list2.get(r));
                                                            list2.get(r).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                            list2.get(r).setOpacity(1);
                                                            list2.get(r).setStyle("-fx-color: white");
                                                            list2.get(r).click();
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    board.isGameWon();
                                    board.isGameLost();
                                    if (board.isGameWon) {
                                        textOnTop.setText("Game Won!");
                                        textOnTop.setVisible(true);
                                        buttonSave.setVisible(false);
                                        grid.setDisable(true);
                                    }
                                    if (board.isGameLost) {
                                        textOnTop.setText("Game over ):");
                                        textOnTop.setVisible(true);
                                        buttonSave.setVisible(false);
                                        grid.setDisable(true);
                                    }
                                }
                            }
                            else if (event.getButton() == MouseButton.SECONDARY) {
                                if (!tile.isClicked() && !tile.hasFlag()) {
                                    tile.setFlag(true);
                                }
                                else if (!tile.isClicked() && tile.hasFlag()) {
                                    tile.setFlag(false);
                                }
                            }
                        }
                    });
                grid.add(board.getTile(i, l), i, l);
                }
            }
        }
        else if (buttonStartGame.getText().contains("Back to Menu")) {
            grid.setVisible(false);
            buttonStartGame.setText("Start game");
            radioButton1.setVisible(true);
            radioButton2.setVisible(true);
            radioButton3.setVisible(true);
            minesweeperText.setVisible(true);
            grid.getChildren().clear();
            board = new Board("Easy");
            textOnTop.setVisible(false);
            buttonSave.setVisible(false);
            buttonLoad.setVisible(true);
        }
    }

    @FXML
    public void handleRadioButtons(ActionEvent event) {
        if (togglegroup.getSelectedToggle().toString().contains("Easy")) {
            board.setDifficulty("Easy");
        }
        else if (togglegroup.getSelectedToggle().toString().contains("Intermediate")) {
            board.setDifficulty("Intermediate");
        }
        else if (togglegroup.getSelectedToggle().toString().contains("Hard")) {
            board.setDifficulty("Hard");
        }
    }

    public void handleSave() {
        fileHandler.save("saveFile", board);
    }

    public void handleLoad() {
        board = fileHandler.load("saveFile.MINESWEEPER");
        grid.setVisible(true); // Vis brettet når trykker på Start game.
        buttonStartGame.setText("Back to Menu"); // Endre tekst på knappen når man er i en runde.
        radioButton1.setVisible(false); // Fjerner radioknappene ettersom de ikke brukes i spill.
        radioButton2.setVisible(false);
        radioButton3.setVisible(false);
        minesweeperText.setVisible(false); // Fjerner logo-tekst i spill.
        buttonLoad.setVisible(false);
        buttonSave.setVisible(true);
        for (int i = 0; i < 8; i++) {
            for (int l = 0; l < 8; l++) {

                Tile tile = board.getTile(i, l);
                tile.setFocusTraversable(false);
                if (!tile.isClicked()) {
                    tile.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent event) {


                        
                                if (event.getButton() == MouseButton.PRIMARY) {

                                    if (!tile.hasFlag()) {
                                        tile.click();
                                        board.giveValues(tile);
                                        if (tile.getValue() != 0 && tile.getValue() != 9) {
                                            tile.setText(String.valueOf(tile.getValue()));
                                            tile.setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                            tile.setOpacity(1);
                                        }
                                        else if (tile.getValue() == 0) {
                                            ArrayList<Tile> list = board.returnAdjacent(tile);
                                            for (int s = 0; s < list.size(); s++) {
                                                if (!list.get(s).hasFlag()) {
                                                    board.giveValues(list.get(s));
                                                    list.get(s).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                    list.get(s).setOpacity(1);
                                                    list.get(s).setStyle("-fx-color: white");
                                                    list.get(s).click();
                                                }
                                            }
                                        }
                                        else if (tile.getValue() == 9) {
                                            tile.click();
                                        }
                                        for (int i = 0; i < 8; i++) {
                                            for (int l = 0; l < 8; l++) {
                                                Tile tile = board.getTile(i, l);
                                                if (tile.getValue() == 0 && tile.isClicked()) {
                                                    ArrayList<Tile> list = board.returnAdjacent(tile);
                                                    for (int s = 0; s < list.size(); s++) {
                                                        board.giveValues(list.get(s));
                                                        list.get(s).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                        list.get(s).setOpacity(1);
                                                        list.get(s).setStyle("-fx-color: white");
                                                        list.get(s).click();
                                                        if (list.get(s).getValue() == 0) {
                                                            ArrayList<Tile> list2 = board.returnAdjacent(list.get(s));
                                                            for (int r = 0; r < list2.size(); r++) {
                                                                board.giveValues(list2.get(r));
                                                                list2.get(r).setFont(Font.font("Italic", FontWeight.BOLD, 20));
                                                                list2.get(r).setOpacity(1);
                                                                list2.get(r).setStyle("-fx-color: white");
                                                                list2.get(r).click();
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                        board.isGameWon();
                                        board.isGameLost();
                                        if (board.isGameWon) {
                                            textOnTop.setText("Game Won!");
                                            textOnTop.setVisible(true);
                                            buttonSave.setVisible(false);
                                            grid.setDisable(true);
                                        }
                                        if (board.isGameLost) {
                                            textOnTop.setText("Game over ):");
                                            textOnTop.setVisible(true);
                                            buttonSave.setVisible(false);
                                            grid.setDisable(true);
                                        }
                                    }
                                }
                                else if (event.getButton() == MouseButton.SECONDARY) {
                                    if (!tile.isClicked() && !tile.hasFlag()) {
                                        tile.setFlag(true);
                                    }
                                    else if (!tile.isClicked() && tile.hasFlag()) {
                                        tile.setFlag(false);
                                    }
                                }
                            }
                    });
                }
                else if (tile.isClicked()) {
                    if (tile.getValue() != 0) {
                        tile.setText(String.valueOf(tile.getValue()));
                        tile.setFont(Font.font("Italic", FontWeight.BOLD, 20));
                        tile.setOpacity(1);
                        tile.setStyle("-fx-color: white");
                    }
                    else if (tile.getValue() == 0) {
                        tile.setText("");
                        tile.setStyle("-fx-color: white");
                    }
                 
                }
                grid.add(board.getTile(i, l), i, l);
            }
        }
    }
}
