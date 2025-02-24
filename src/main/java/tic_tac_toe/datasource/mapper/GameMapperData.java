package tic_tac_toe.datasource.mapper;

import tic_tac_toe.datasource.model.CurrentGameData;
import tic_tac_toe.domain.model.CurrentGameDomain;

// Класс для Преобразования Игр между "domain" и "datasource" Слоями:
public class GameMapperData {

    // Метод для Преобразования из CurrentGameDomain "domain" в CurrentGameData "datasource":
    public static CurrentGameData fromDomainToDataSource(CurrentGameDomain currentGameDomain) {
        CurrentGameData gameData = new CurrentGameData();
        gameData.setGameId(currentGameDomain.getGameId());
        gameData.setGameBoard(currentGameDomain.getGameBoard());
        return gameData;
    }

    // Метод для Преобразования из CurrentGameData "datasource" в CurrentGameDomain "domain":
    public static CurrentGameDomain fromDataSourceToDomain(CurrentGameData gameData) {
        CurrentGameDomain currentGame = new CurrentGameDomain();
        currentGame.setGameId(gameData.getGameId());
        currentGame.setGameBoard(gameData.getGameBoard());
        return currentGame;
    }
}