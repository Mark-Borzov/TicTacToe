package tic_tac_toe.datasource.model;

import org.springframework.stereotype.Repository;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

// Класс для Хранения Текущих Игр:
@Repository
public class GameStorage {

    // Коллекция "HashMap" для Хранения Игр:
    private final ConcurrentHashMap<UUID, CurrentGameData> games = new ConcurrentHashMap<>();

    // Метод для Сохранения Игры в Коллекцию "games":
    public void saveGame(CurrentGameData gameData) {
        games.put(gameData.getGameId(), gameData);
    }

    // Метод для Получения Игры по ID из Коллекции "games":
    public CurrentGameData getGame(UUID gameId) {
        return games.get(gameId);
    }

    // Метод для Вывода Содержимого Хранилища:
    public void printStorage() {
        if (games.isEmpty()) {
            System.out.println("Хранилище Игр Пусто.");
        } else {
            System.out.println("\nСодержимое Хранилища Игр на Данный Момент:");
            games.forEach((id, game) -> System.out.println("ID Игры datasource Слоя: " + id));
        }
    }
}