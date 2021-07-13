package ua.com.alevel.service.impl;

import ua.com.alevel.service.GameOfLifeService;

public class GameOfLifeImpl implements GameOfLifeService {
    private int height;
    private int width;
    private int[][] board;

    @Override
    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean isAlive(int cell) {

        return cell == 1;
    }

    @Override
    public int getCell(int x, int y) {
        if (x < 0 || x >= height) {
            return 0;
        }
        if (y < 0 || y >= width) {
            return 0;
        }

        return board[x][y];
    }

    @Override
    public void fillBoard() {
        board = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = 0;

            }
        }
    }

    @Override
    public void printBoard() {
        for (int i = 0; i <= width; i++) {
            if (i == 0) {
                System.out.print("  ");
                continue;
            }
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < height; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(" ");
    }

    @Override
    public void setAlive(int x, int y) {
        board[x - 1][y - 1] = 1;

    }

    @Override
    public int countAliveNeighbours(int x, int y) {
        int count = 0;
        count += getCell(x - 1, y - 1);
        count += getCell(x - 1, y);
        count += getCell(x - 1, y + 1);

        count += getCell(x, y - 1);
        count += getCell(x, y + 1);

        count += getCell(x + 1, y - 1);
        count += getCell(x + 1, y);
        count += getCell(x + 1, y + 1);
        return count;
    }

    @Override
    public void step() {
        int [][] newBoard = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (this.isAlive(board[i][j])) {
                    if (this.countAliveNeighbours(i, j) < 2) {
                        newBoard[i][j] = 0;
                    } else if (this.countAliveNeighbours(i, j) == 2 ||this.countAliveNeighbours(i, j) == 3 ) {
                        newBoard[i][j] = 1;
                    } else {
                        newBoard[i][j] = 0;
                    }
                }
                else if ( this.countAliveNeighbours(i, j) == 3) {
                    newBoard[i][j] = 1;
                }
            }
        }
        board = newBoard;
    }
}
