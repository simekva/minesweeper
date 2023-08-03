package minesweeper;

public interface SaveAndLoadInterface {

    public void save(String filename, Board board);

	public Board load(String filename);
    
}
