package ua.com.alevel.service;

public interface GameOfLifeService {
    boolean isAlive(int cell);
    void setAlive(int x, int y);
    void fillBoard();
    void printBoard();
    int getCell(int x, int y);
    int countAliveNeighbours(int x, int y);
    void step();
    void setHeight(int height);
    void setWidth(int width);
}
