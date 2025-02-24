package tic_tac_toe;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import tic_tac_toe.datasource.model.GameStorage;
import tic_tac_toe.datasource.repository.GameRepository;
import tic_tac_toe.domain.model.CurrentGameDomain;
import tic_tac_toe.web.model.CurrentGameWeb;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tic_tac_toe.web.mapper.GameMapperWeb.fromDomainToWeb;
import static tic_tac_toe.web.mapper.GameMapperWeb.fromWebToDomain;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TiTacToeApplicationTests {

	// Тест для Сохранения Игры из Слоя "domain" в Слой "datasource":
	@Test
	@Order(1)
	void testFromDomainToDatasource() {
		System.out.println("\nТест Сохранения Игры из Слоя \"domain\" в Слой \"datasource\".");

		// Создание Игры из Слоя "domain":
		CurrentGameDomain currentGameDomain = new CurrentGameDomain();
		System.out.println("ID Игры domain Слоя: " + currentGameDomain.getGameId());

		// Создание Хранилища:
		GameStorage gameStorage = new GameStorage();
		// Создание Репозитория:
		GameRepository gameRepository = new GameRepository(gameStorage);

		// Сохраняем Текущую Игру из "domain" Слоя в Слой "datasource" при Помощи "GameMapper":
		gameRepository.saveCurrentGameInStorage(currentGameDomain);
		// Выводим Содержимое Хранилища:
		gameStorage.printStorage();
	}

	// Тест для Получения Игры из Слоя "datasource" в Слой "domain":
	@Test
	@Order(2)
	void testFromDatasourceToDomain() {
		System.out.println("\nТест Получения Игры из Слоя \"datasource\" в Слой \"domain\".");

		// Создание Игры из Слоя "domain":
		CurrentGameDomain currentGameDomain = new CurrentGameDomain();
		System.out.println("ID Игры domain Слоя: " + currentGameDomain.getGameId());

		// Создание Хранилища:
		GameStorage gameStorage = new GameStorage();
		// Создание Репозитория:
		GameRepository gameRepository = new GameRepository(gameStorage);

		// Сохраняем Текущую Игру из "domain" Слоя в Слой "datasource" при Помощи "GameMapper":
		gameRepository.saveCurrentGameInStorage(currentGameDomain);

		// Выводим Содержимое Хранилища:
		gameStorage.printStorage();

		// Получаем Игру из Слоя "datasource" в Слой "domain":
		CurrentGameDomain retrievedGame = gameRepository.getCurrentGameFromStorage(currentGameDomain.getGameId());
		System.out.println("ID Игры domain Слоя После datasource Слоя: " + retrievedGame.getGameId());

		assertEquals(currentGameDomain.getGameId(), retrievedGame.getGameId(), "ID Игры Должен Совпадать.");
		assertEquals(currentGameDomain.getGameBoard(), retrievedGame.getGameBoard(), "Поле Должно Совпадать.");
	}

	// Тест для Сохранения Игры из Слоя "domain" в Слой "web":
	@Test
	@Order(3)
	void testFromDomainToWeb() {
		System.out.println("\nТест Сохранения Игры из Слоя \"domain\" в Слой \"web\".");

		// Создание Игры из Слоя "domain":
		CurrentGameDomain currentGameDomain = new CurrentGameDomain();
		System.out.println("ID Игры domain Слоя: " + currentGameDomain.getGameId());

		// Преобразование Игры из CurrentGameDomain в CurrentGameWeb:
		CurrentGameWeb webModel = fromDomainToWeb(currentGameDomain);
		System.out.println("ID Игры web Слоя: " + webModel.getGameId());

		assertEquals(currentGameDomain.getGameId(), webModel.getGameId(), "ID Игры Должен Совпадать.");
		assertEquals(currentGameDomain.getGameBoard(), webModel.getGameBoard(), "Поле Должно Совпадать.");
	}

	// Тест для Сохранения Игры из Слоя "web" в Слой "domain":
	@Test
	@Order(4)
	void testFromWebToDomain() {
		System.out.println("\nТест Сохранения Игры из Слоя \"web\" в Слой \"domain\".");

		// Создание Игры из Слоя "web":
		CurrentGameWeb webModel = new CurrentGameWeb();
		System.out.println("ID Игры web Слоя: " + webModel.getGameId());

		// Преобразование Игры из CurrentGameWeb в CurrentGameDomain:
		CurrentGameDomain currentGameDomain = fromWebToDomain(webModel);
		System.out.println("ID Игры domain Слоя: " + currentGameDomain.getGameId());

		assertEquals(webModel.getGameId(), currentGameDomain.getGameId(), "ID Игры Должен Совпадать.");
		assertEquals(webModel.getGameBoard(), currentGameDomain.getGameBoard(), "Поле Должно Совпадать.");
		System.out.println();
	}
}