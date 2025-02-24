package tic_tac_toe.domain.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

// Класс для Генерации ID:
public class IdGenerator {

    // Хранилище Уникальных ID:
    private static final Set<UUID> generatedIDs = new HashSet<>();

    // Метод для Получения Уникального ID:
    public static UUID generateID() {
        UUID newID = UUID.randomUUID();
        while (generatedIDs.contains(newID)) {
            newID = UUID.randomUUID();
        }
        generatedIDs.add(newID);
        return newID;
    }
}