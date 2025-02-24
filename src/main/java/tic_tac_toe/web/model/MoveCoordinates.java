package tic_tac_toe.web.model;

// Класс для Хранения Координат Хода Пользователя:
public class MoveCoordinates {

    private final int row;
    private final int col;

    public MoveCoordinates(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }
}