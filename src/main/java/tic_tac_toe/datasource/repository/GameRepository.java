package tic_tac_toe.datasource.repository;

import org.springframework.stereotype.Repository;
import tic_tac_toe.datasource.mapper.GameMapperData;
import tic_tac_toe.datasource.model.CurrentGameData;
import tic_tac_toe.datasource.model.GameStorage;
import tic_tac_toe.domain.model.CurrentGameDomain;
import java.util.UUID;

// Класс Репозиторий для Работы с Хранилищем:
@Repository
public class GameRepository {

    // Объект для Хранения Текущих Игр:
    private final GameStorage gameStorage;

    public GameRepository(GameStorage gameStorage) {
        this.gameStorage = gameStorage;
    }

    // Метод для Сохранения Текущей Игры в Хранилище:
    public void saveCurrentGameInStorage(CurrentGameDomain currentGame) {
        CurrentGameData gameData = GameMapperData.fromDomainToDataSource(currentGame);
        gameStorage.saveGame(gameData);
    }

    // Метод для Получения Текущей Игры по ID из Хранилища:
    public CurrentGameDomain getCurrentGameFromStorage(UUID gameId) {
        CurrentGameData gameData = gameStorage.getGame(gameId);
        return GameMapperData.fromDataSourceToDomain(gameData);
    }

    // Метод для Вывода Всех Игр из Хранилища:
    public void printGamesFromStorage() {
        this.gameStorage.printStorage();
    }
}