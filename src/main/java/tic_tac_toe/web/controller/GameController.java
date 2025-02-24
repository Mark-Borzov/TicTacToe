package tic_tac_toe.web.controller;

import static tic_tac_toe.web.mapper.GameMapperWeb.fromDomainToWeb;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import tic_tac_toe.domain.model.CurrentGameDomain;
import tic_tac_toe.domain.service.GameServiceImpl;
import tic_tac_toe.exception.GameNotFoundException;
import tic_tac_toe.exception.InvalidMoveException;
import tic_tac_toe.web.model.CurrentGameWeb;
import tic_tac_toe.web.model.MoveCoordinates;
import java.util.UUID;

@Controller
@RequestMapping("/game")
public class GameController {

    private final GameServiceImpl gameServiceImp;

    public GameController(GameServiceImpl gameServiceImp) {
        this.gameServiceImp = gameServiceImp;
    }

    // Метод для Создания Новой Игры:
    @GetMapping("/new")
    public ResponseEntity<CurrentGameWeb> createNewGame() {
        CurrentGameWeb currentGameWeb = new CurrentGameWeb();
        this.gameServiceImp.addNewGameToStorage(currentGameWeb);
        this.gameServiceImp.printGamesFromStorage();
        return ResponseEntity.ok(currentGameWeb);
    }

    // Метод для Обновления Полученной Игры:
    @PostMapping("/{game_id}")
    public ResponseEntity<CurrentGameWeb> updateCurrentGame(@PathVariable UUID game_id,
                                                     @RequestBody MoveCoordinates moveCoordinates) {
        CurrentGameDomain currentGameDomain = this.gameServiceImp.getGameFromStorage(game_id);
        if (currentGameDomain == null) {
            throw new GameNotFoundException("Игра с ID " + game_id + " не Найдена.");
        }
        if (!this.gameServiceImp.validateGameBoard(currentGameDomain, moveCoordinates.getRow(), moveCoordinates.getCol())) {
            throw new InvalidMoveException("Невалидное состояние поля игры.");
        }
        currentGameDomain.getGameBoard().setMoveForPlayer(moveCoordinates.getRow(), moveCoordinates.getCol());
        ResponseEntity<CurrentGameWeb> response = checkGameStatus(currentGameDomain);
        if (response != null) {
            return response;
        }
        this.gameServiceImp.getNextMove(currentGameDomain);
        response = checkGameStatus(currentGameDomain);
        if (response != null) {
            return response;
        }
        return ResponseEntity.ok(fromDomainToWeb(currentGameDomain));
    }

    // Метод для Проверки Текущего Статуса Игры:
    private ResponseEntity<CurrentGameWeb> checkGameStatus(CurrentGameDomain currentGameDomain) {
        int gameStatus = this.gameServiceImp.isGameOver(currentGameDomain);
        if (gameStatus > -1) {
            CurrentGameWeb currentGameWeb = fromDomainToWeb(currentGameDomain);
            currentGameWeb.setGameStatus(gameStatus);
            return ResponseEntity.ok(currentGameWeb);
        }
        return null;
    }
}