package minesweeper;
import java.util.ArrayList;
import java.util.Random;

public class Board {
    
    private Tile[][] board = new Tile[8][8];
    private int numberOfBombs = 15;
    public boolean isGameWon;
    public boolean isGameLost;

    public Board(String difficulty) {
        if (difficulty == "Easy") {
            this.numberOfBombs = 15;
        }
        else if (difficulty == "Intermediate") {
            this.numberOfBombs = 20;
        }
        else if (difficulty == "Hard") {
            this.numberOfBombs = 25;
        }
        else {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < 8; i++) {
            for (int l = 0; l < 8; l++) {
                Tile tile  = new Tile(i, l);
                board[i][l] = tile;
            }
        }
    }

    public Board() { // Tomt brett
        this.numberOfBombs = 0;
        for (int i = 0; i < 8; i++) {
            for (int l = 0; l < 8; l++) {
                board[i][l].setX(0);
                board[i][l].setY(0);
                board[i][l].setValue(0);
                board[i][l].setFlag(false);

            }
        }
    }

    public void setTile(Tile tile, int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            board[x][y] = tile;
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public void setDifficulty(String difficulty) {
        if (difficulty == "Easy") {
            this.numberOfBombs = 15;
        }
        else if (difficulty == "Intermediate") {
            this.numberOfBombs = 20;
        }
        else if (difficulty == "Hard") {
            this.numberOfBombs = 25;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    public void placeBombs() {

        Random randomX = new Random();
        Random randomY = new Random();
        
        for (int i = 0; i < numberOfBombs; i++) {
            
            int x = randomX.nextInt(8);
            int y = randomY.nextInt(8);

            if (!getTile(x, y).hasBomb()) {
                getTile(x, y).setBomb();
            }
            else {
                i = i - 1;
            }
        } 
    }

    public void isGameLost() {
        for (int i = 0; i < 8; i++) {
            for (int l = 0; l < 8; l++) {
                if (getTile(i, l).isClicked() && getTile(i, l).hasBomb()) {
                    isGameLost = true;
                }
            }
        }
        if (isGameLost) {
            for (int i = 0; i < 8; i++) {
                for (int l = 0; l < 8; l++) {
                    getTile(i, l).click();
                }
            }
        }
    }

    public void isGameWon() {
        int numberOfTilesClicked = 0;

        for (int i = 0; i < 8; i++) {
            for (int l = 0; l < 8; l++) {
                if (getTile(i, l).isClicked()) {
                    numberOfTilesClicked += 1;
                }
            }
        }
        if (getNumberOfBombs() == 15) {
            if (numberOfTilesClicked == 64 - 15) {
                this.isGameWon = true;
            }
        }
        else if (getNumberOfBombs() == 20) {
            if (numberOfTilesClicked == 64 - 20) {
                this.isGameWon = true;
            }
        }
        else if (getNumberOfBombs() == 25) {
            if (numberOfTilesClicked == 64 - 25) {
                this.isGameWon = true;
            }
        }
    }

    public void setGameWon() {
        isGameWon = true;
    }
    
    public Tile getTile(int x, int y) {
        if (board[x][y] != null ) {
            return board[x][y];
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getNumberOfBombs() {
        return numberOfBombs;
    }

    public ArrayList<Tile> returnAdjacent(Tile tile) {
        ArrayList<Tile> list = new ArrayList<Tile>();
        
        if (tile.getX() == 0 && tile.getY() == 0) {
            list.add(getTile(0, 1));
            list.add(getTile(1, 0));
            list.add(getTile(1, 1));
        }

        else if (tile.getX() == 7 && tile.getY() == 0) {
            list.add(getTile(6, 0));
            list.add(getTile(6, 1));
            list.add(getTile(7, 1));
        }

        else if (tile.getX() == 0 && tile.getY() == 7) {
            list.add(getTile(0, 6));
            list.add(getTile(1, 7));
            list.add(getTile(1, 6));
        }
        else if (tile.getX() == 7 && tile.getY() == 7) {
            list.add(getTile(7, 6));
            list.add(getTile(6, 7));
            list.add(getTile(6, 6));
        }


        else if (tile.getY() == 0) {
            list.add(getTile(tile.getX() - 1, 1)); // Venstre ned
            list.add(getTile(tile.getX(), 1)); // Under
            list.add(getTile(tile.getX() + 1, 1)); // Høyre ned
            list.add(getTile(tile.getX() - 1, 0)); // Venstre
            list.add(getTile(tile.getX() + 1, 0)); // Høyre
        } 

        else if (tile.getX() == 0) {
            list.add(getTile(1, tile.getY() - 1));
            list.add(getTile(1, tile.getY()));
            list.add(getTile(1, tile.getY() + 1));
            list.add(getTile(0, tile.getY() - 1));
            list.add(getTile(0, tile.getY() + 1));
        }
        
        else if (tile.getX() == 7) {
            list.add(getTile(6, tile.getY() - 1));
            list.add(getTile(6, tile.getY()));
            list.add(getTile(6, tile.getY() + 1));
            list.add(getTile(7, tile.getY() - 1));
            list.add(getTile(7, tile.getY() + 1));
        }

        else if (tile.getY() == 7) {
            list.add(getTile(tile.getX() - 1, 6));
            list.add(getTile(tile.getX(), 6));
            list.add(getTile(tile.getX() + 1, 6));
            list.add(getTile(tile.getX() - 1, 7));
            list.add(getTile(tile.getX() + 1, 7));
        }

        else {
            list.add(getTile(tile.getX(), tile.getY() + 1)); // Top
            list.add(getTile(tile.getX() + 1, tile.getY() + 1)); // Top right
            list.add(getTile(tile.getX() + 1, tile.getY())); // Right
            list.add(getTile(tile.getX() + 1, tile.getY() - 1)); // Bottom right
            list.add(getTile(tile.getX(), tile.getY() - 1)); // Bottom
            list.add(getTile(tile.getX() - 1, tile.getY() - 1)); // Bottom left
            list.add(getTile(tile.getX() - 1, tile.getY())); // Left
            list.add(getTile(tile.getX() - 1, tile.getY() + 1)); // Top left
        }

        return list;
    }

    public void giveValues(Tile tile) {

        tile.setValue(0);

        if (tile.getX() == 0 && tile.getY() == 0) {
            if (getTile(0, 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, 0).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, 1).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getX() == 7 && tile.getY() == 0) {
            if (getTile(6, 0).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(6, 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(7, 1).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getX() == 0 && tile.getY() == 7) {
            if (getTile(0, 6).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, 7).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, 6).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getX() == 7 && tile.getY() == 7) {
            if (getTile(7,6).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(6, 7).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(6, 6).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getY() == 0) {
            if (getTile(tile.getX() - 1, 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX(), 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() - 1, 0).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, 0).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getX() == 0) {
            if (getTile(1, tile.getY() - 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, tile.getY()).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(1, tile.getY() + 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(0, tile.getY() + 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(0, tile.getY() - 1).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getX() == 7) {
            if (getTile(6, tile.getY() - 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(6, tile.getY()).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(6, tile.getY() + 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(7, tile.getY() - 1).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(7, tile.getY() + 1).hasBomb()) {
                tile.increaseValue();
            }
        }

        else if (tile.getY() == 7) {
            if (getTile(tile.getX() - 1, 6).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX(), 6).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, 6).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() - 1, 7).hasBomb()) {
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, 7).hasBomb()) {
                tile.increaseValue();
            }
        }
   
        else {

            if (getTile(tile.getX(), tile.getY() + 1).hasBomb()) { // Top
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, tile.getY() + 1).hasBomb()) { // Top right
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, tile.getY()).hasBomb()) { // Right
                tile.increaseValue();
            }
            if (getTile(tile.getX() + 1, tile.getY() - 1).hasBomb()) { // Bottom right
                tile.increaseValue();
            }
            if (getTile(tile.getX(), tile.getY() - 1).hasBomb()) { // Bottom
                tile.increaseValue();
            }
            if (getTile(tile.getX() - 1, tile.getY() - 1).hasBomb()) { // Bottom left
                tile.increaseValue();
            }
            if (getTile(tile.getX() - 1, tile.getY()).hasBomb()) { // Left
                tile.increaseValue();
            }
            if (getTile(tile.getX() -1, tile.getY() + 1).hasBomb()) { // Top left
                tile.increaseValue();
            }
            if (getTile(tile.getX(), tile.getY()).hasBomb()) {
                tile.setValue(9);
            }
        }

        if (getTile(tile.getX(), tile.getY()).hasBomb()) {
            tile.setValue(9);
        }
    }
}
