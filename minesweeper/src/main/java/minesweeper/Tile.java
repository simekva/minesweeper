package minesweeper;

import javafx.scene.control.Button;

public class Tile extends Button {

    private int value;
    private int x;
    private int y;
    private boolean isBomb;
    private boolean isFlag;
    private boolean clicked;

    public Tile(int x, int y) {
        if (x >= 0 && x <= 7 && y >= 0 && y <= 7) {
            this.setPrefSize(62.5, 62.5);
            this.isBomb = false;
            this.isFlag = false;
            this.x = x;
            this.y = y;
            this.clicked = false;
            this.setStyle("-fx-color: grey");
        }
        else {
            throw new IllegalArgumentException();
        }
    }

    public int getValue() {
        return this.value;
    }

    public void increaseValue() {
        this.value += 1;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        if (x >= 0 && x <= 7) {
            this.x = x;
        }
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        if (y >= 0 && y <= 7) {
            this.y = y;
        }
    }

    public boolean hasBomb() {
        return this.isBomb;
    }

    public void setBomb() {
        this.isBomb = !this.isBomb;
        this.value = 9;
    }

    public boolean hasFlag() {
        return this.isFlag;
    }

    public void setFlag(boolean setFlag) {
        this.isFlag = setFlag;
        if (setFlag) {
            this.setStyle("-fx-color: green");
        }
        if (!setFlag) {
            this.setStyle("-fx-color: grey");
        }
    }
    public void setValue(int value) {
        if (value >= 0 && value <= 9) {
            this.value = value;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
    
    public void click() {
        if (!isFlag) {
            this.setDisable(true);

            if (this.isBomb) {
                this.setStyle("-fx-background-color: red");
                this.setText("");
            }
            else if (value > 0) {
                this.setText(String.valueOf(value));
            }
            else if (isFlag) {
            
            }
        
            else {
                this.setStyle("-fx-color: white");
            }
            this.clicked = true;
        }
    }
    
    public boolean isClicked() {
        return this.clicked;
    }

    public void setClicked() {
        this.clicked = true;
    }

    public String isClickedString() {
        return String.valueOf(this.clicked);
    }
}
