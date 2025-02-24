package tic_tac_toe.web.mapper;

import tic_tac_toe.domain.model.CurrentGameDomain;
import tic_tac_toe.web.model.CurrentGameWeb;

// Класс для Преобразования Игр между "domain" и "web" Слоями:
public class GameMapperWeb {

    // Метод для Преобразования из CurrentGameDomain "domain" в CurrentGameWeb "web":
    public static CurrentGameWeb fromDomainToWeb(CurrentGameDomain currentGame) {
        CurrentGameWeb webModel = new CurrentGameWeb();
        webModel.setGameId(currentGame.getGameId());
        webModel.setGameBoard(currentGame.getGameBoard());
        return webModel;
    }

    // Метод для Преобразования из CurrentGameWeb "web" в CurrentGameDomain "domain":
    public static CurrentGameDomain fromWebToDomain(CurrentGameWeb webModel) {
        CurrentGameDomain currentGame = new CurrentGameDomain();
        currentGame.setGameId(webModel.getGameId());
        currentGame.setGameBoard(webModel.getGameBoard());
        return currentGame;
    }
}