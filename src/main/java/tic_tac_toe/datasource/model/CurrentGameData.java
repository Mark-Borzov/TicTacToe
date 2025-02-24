package tic_tac_toe.datasource.model;

import tic_tac_toe.domain.model.GameBoard;
import tic_tac_toe.domain.model.IdGenerator;
import java.util.UUID;

// Модель Текущей Игры для Слоя "datasource":
public class CurrentGameData {

    // ID Текущей Игры:
    private UUID gameId;
    // Экземпляр Поля Игры;
    private GameBoard gameBoard;

    public CurrentGameData() {
        this.gameId = IdGenerator.generateID();
        this.gameBoard = new GameBoard();
    }

    // Метод для Получения Текущего ID Игры:
    public UUID getGameId() {
        return this.gameId;
    }

    // Метод для Установки Текущего ID Игры;
    public void setGameId(UUID gameId) {
        this.gameId = gameId;
    }

    // Метод для Получения Ссылки Поля Игры:
    public GameBoard getGameBoard() {
        return this.gameBoard;
    }

    // Метод для Установки Ссылки Поля Игры:
    public void setGameBoard(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }
}