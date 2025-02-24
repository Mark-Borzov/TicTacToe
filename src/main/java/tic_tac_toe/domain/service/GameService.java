package tic_tac_toe.domain.service;

import tic_tac_toe.domain.model.CurrentGameDomain;

public interface GameService {

    // Метод для Получения Следующего хода Алгоритмом Минимакс:
    void getNextMove(CurrentGameDomain currentGame);

    // Метод Валидации Игрового Поля:
    boolean validateGameBoard(CurrentGameDomain currentGame, int row, int col);

    // Метод Проверки Окончания Игры:
    int isGameOver(CurrentGameDomain currentGame);
}