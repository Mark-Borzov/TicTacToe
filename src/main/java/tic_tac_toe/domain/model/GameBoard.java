package tic_tac_toe.domain.model;

// Класс для Игрового Поля:
public class GameBoard {

    // Создание Матрицы для Игрового Поля:
    private final int[][] gameBoard;

    public GameBoard() {
        this.gameBoard = new int[3][3];
    }

    // Гетт Метод для Получения Игрового Поля:
    public int[][] getBoard() {
        return this.gameBoard;
    }

    // Метод для Установки Значения в Ячейку Поля для Бота:
    public void setMoveForBot(int row, int col) {
        int valueForBot = 2;
        this.gameBoard[row][col] = valueForBot;
    }

    // Метод для Установки Значения в Ячейку Поля для Игрока:
    public void setMoveForPlayer(int row, int col) {
        int valueForBot = 1;
        this.gameBoard[row][col] = valueForBot;
    }
}