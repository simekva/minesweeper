package minesweeper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;

public class minesweeperTest{

    // private Board board;

    // @BeforeAll
    // static void initJfxRuntime() {
    //     Platform.startup(() -> {});
    // }

    // @BeforeEach 
    // public void setup() {
    //     board = new Board("Easy");
    //     board.placeBombs();
    // }
    
    // @Test
    // public void testSetBombs() {
    //     int numberOfBombs = 0;
    //     for (int i = 0; i < 8; i++) {
    //         for (int l = 0; l < 8; l++) {
    //             if (board.getTile(i, l).hasBomb()) {
    //                 numberOfBombs += 1;
    //             }
    //         }
    //     }
    //     Assertions.assertEquals(board.getNumberOfBombs(), numberOfBombs);
    //     Assertions.assertEquals(board.getNumberOfBombs(), 15);
    // }
    
    // @Test
    // public void testIsGameLost() {
    //     for (int i = 0; i < 8; i++) {
    //         for (int l = 0; l < 8; l++) {
    //             board.getTile(i, l).click();
    //         }
    //     }
    //     Assertions.assertEquals(board.isGameLost, false);
    // }

    // @Test
    // public void testTsGameWon() {
    //     for (int i = 0; i < 8; i++) {
    //         for (int l = 0; l < 8; l++) {
    //             if (!board.getTile(i, l).hasBomb()) {
    //                 board.getTile(i, l).click();
    //             }
    //         }
    //     }
    //     Assertions.assertEquals(board.isGameWon, false); 
    // }

    // @Test
    // public void testGiveValues() {
    //     for (int i = 0; i < 8; i++) {
    //         for (int l = 0; l < 8; l++) {
    //             int tileValue = 0;
    //             board.giveValues(board.getTile(i, l));
    //             List<Tile> list = board.returnAdjacent(board.getTile(i, l));
    //             if (!board.getTile(i, l).hasBomb()) {
    //                 for (int r = 0; r < list.size(); r++) {
    //                     if (list.get(r).hasBomb()) {
    //                         tileValue += 1;
    //                     }
    //                 }
    //             }
    //             Assertions.assertEquals(board.getTile(i, l).getValue(), tileValue);
    //         }
    //     }
    // }
    
    // @Test
    // public void testReturnAdjacent() {
        
    //     // Tile in the middle of the board
    //     Tile tile = board.getTile(3, 4);
    //     List<Tile> list = board.returnAdjacent(tile);
    //     List<Tile> adjacent = new ArrayList<Tile>();
    //     adjacent.add(board.getTile(3, 3)); // Above
    //     adjacent.add(board.getTile(4, 3)); // Top right
    //     adjacent.add(board.getTile(4, 4)); // Right
    //     adjacent.add(board.getTile(4, 5)); // Bottom right
    //     adjacent.add(board.getTile(3, 5)); // Bottom
    //     adjacent.add(board.getTile(2, 5)); // Bottom left
    //     adjacent.add(board.getTile(2, 4)); // Left
    //     adjacent.add(board.getTile(2, 3)); // Top left

    //     assertEquals(list.toArray().toString(), adjacent.toArray().toString());

    //     // Tile on the top left
    //     Tile tile2 = board.getTile(0, 0);
    //     List<Tile> list2 = board.returnAdjacent(tile2);
    //     List<Tile> adjacent2 = new ArrayList<Tile>();
    //     adjacent2.add(board.getTile(1, 0)); // Right
    //     adjacent2.add(board.getTile(1, 1)); // Bottom righ
    //     adjacent2.add(board.getTile(0, 1)); // Bottom

    //     Assertions.assertEquals(list2.toArray().toString(), adjacent2.toArray().toString());

    // }

    // @Test
    // public void wrongValues() {
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> new Board("Extreme"));
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> board.setTile(new Tile(3, 4), 100, 500));
    // }
}
