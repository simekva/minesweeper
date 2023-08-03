package minesweeper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileHandler implements SaveAndLoadInterface{

    @Override
    public void save(String filename, Board board) {

        String directory = System.getProperty("user.dir");
        String filePath = directory + File.separator + filename;

        File saveFile = new File(filePath + ".MINESWEEPER");
        try {
            PrintWriter printWriter = new PrintWriter(saveFile);
            for (int i = 0; i < 8; i++) {
                for (int l = 0; l < 8; l++) {
                    List<Object> list = new ArrayList<Object>();
                    list.add(board.getTile(i, l).getValue());
                    list.add(board.getTile(i, l).getX());
                    list.add(board.getTile(i, l).getY());
                    list.add(board.getTile(i, l).hasBomb());
                    list.add(board.getTile(i, l).hasFlag());
                    list.add(board.getTile(i, l).isClicked());
                    printWriter.println(list.toString().replace("[", "").replace("]", "").replace(" ", ""));
                }
            }
            System.out.println("Wrote to file: " + filename);
            printWriter.close();
        } catch (IOException e) {
            System.out.println("Could not write to file.");
            e.printStackTrace();
        }
    }

    @Override
    public Board load(String filename) {

        String directory = System.getProperty("user.dir");
        String filePath = directory + File.separator + filename;
        File file = new File(filePath);

        //Stage stage = new Stage();
        //FileChooser fileChooser = new FileChooser();
        //File selectedFile = fileChooser.showOpenDialog(stage);
        Board board = new Board("Easy");

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                for (int i = 0; i < 8; i++) {
                    for (int l = 0; l < 8; l++) {
                        String line = scanner.nextLine();
                        List<Object> list = new ArrayList<Object>(Arrays.asList(line.split(",")));
                        board.getTile(i, l).setValue(Integer.valueOf((String) list.get(0)));
                        board.getTile(i, l).setX(Integer.valueOf((String) list.get(1)));
                        board.getTile(i, l).setY(Integer.valueOf((String) list.get(2)));
                        
                        if (list.get(3).toString().contains("true")) {
                            board.getTile(i, l).setBomb();
                        }

                        if (list.get(4).toString().contains("true")) {
                            board.getTile(i, l).setFlag(true);
                        }

                        if (list.get(5).toString().contains("true")) {
                            board.getTile(i, l).setClicked();
                        }
                    }
                }
                
            }
            scanner.close();
        } catch (NumberFormatException | FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return board;
    }
    
}
