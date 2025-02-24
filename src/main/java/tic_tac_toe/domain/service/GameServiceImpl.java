package tic_tac_toe.domain.service;

import org.springframework.stereotype.Service;
import tic_tac_toe.datasource.repository.GameRepository;
import tic_tac_toe.domain.model.CurrentGameDomain;
import tic_tac_toe.web.model.CurrentGameWeb;
import static tic_tac_toe.web.mapper.GameMapperWeb.fromWebToDomain;
import java.util.UUID;

// Класс Реализующий Интерфейс "GameService":
@Service
public class GameServiceImpl implements GameService {

    // Объект Репозиторий для Работы с Хранилищем:
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    // Метод для Проверки Валидности Игрового Поля:
    @Override
    public boolean validateGameBoard(CurrentGameDomain currentGame, int row, int col) {
        int[][] originalBoard = currentGame.getGameBoard().getBoard();
        if ((row < 0 || row >= 3 || col < 0 || col >= 3)) {
            return false;
        }
        return originalBoard[row][col] == 0;
    }

    // Метод Проверки Окончания Игры:
    @Override
    public int isGameOver(CurrentGameDomain currentGame) {
        // Проверка Выигрыша Игрока:
        if (this.checkWin(currentGame.getGameBoard().getBoard(), 1)) {
            return 1;
        }
        // Проверка Выигрыша Бота:
        if (this.checkWin(currentGame.getGameBoard().getBoard(), 2)) {
            return 2;
        }
        // Проверка на Ничью:
        if (this.checkDrawGame(currentGame.getGameBoard().getBoard())) {
            return 0;
        }
        return -1;
    }

    // Метод для Получения Следующего хода Алгоритмом Минимакс:
    @Override
    public void getNextMove(CurrentGameDomain currentGame) {
        int[][] originalBoard = currentGame.getGameBoard().getBoard();
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (originalBoard[i][j] == 0) {
                    int[][] boardCopy = this.copyBoard(originalBoard);
                    boardCopy[i][j] = 2;
                    int score = this.minimax(boardCopy,false);
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        if (bestMove[0] != -1 && bestMove[1] != -1) {
            currentGame.getGameBoard().setMoveForBot(bestMove[0], bestMove[1]);
        }
    }

    // Метод для Добавления Игры из Соля "web" в Хранилище:
    public void addNewGameToStorage(CurrentGameWeb currentGameWeb) {
        CurrentGameDomain currentGameDomain = fromWebToDomain(currentGameWeb);
        this.gameRepository.saveCurrentGameInStorage(currentGameDomain);
    }

    public CurrentGameDomain getGameFromStorage(UUID game_id) {
        return this.gameRepository.getCurrentGameFromStorage(game_id);
    }

    // Метод для Вывода Всех Игр из Хранилища:
    public void printGamesFromStorage() {
        this.gameRepository.printGamesFromStorage();
    }

    // Реализация Алгоритма "Минимакс":
    private int minimax(int[][] board, boolean isMaximizing) {
        if (checkWin(board, 2)) {
            return 1;
        } else if (checkWin(board, 1)) {
            return -1;
        } else if (checkDrawGame(board)) {
            return 0;
        }
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        int[][] newBoard = copyBoard(board);
                        newBoard[i][j] = 2;
                        int eval = minimax(newBoard, false);
                        maxEval = Math.max(maxEval, eval);
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board[i][j] == 0) {
                        int[][] newBoard = copyBoard(board);
                        newBoard[i][j] = 1;
                        int eval = minimax(newBoard, true);
                        minEval = Math.min(minEval, eval);
                    }
                }
            }
            return minEval;
        }
    }

    // Метод для Создания Копии Игрового Поля:
    private int[][] copyBoard(int[][] original) {
        int[][] copyBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            System.arraycopy(original[i], 0, copyBoard[i], 0, 3);
        }
        return copyBoard;
    }

    // Метод для Проверки Выигрыша или Ничьи:
    private boolean checkWin(int[][] board, int player) {
        return checkingBoardRow(board, player) ||
                checkingBoardColumn(board, player) ||
                checkingBoardDiagonal(board, player);
    }

    // Метод для Проверки Выигрыша по Строке:
    private boolean checkingBoardRow(int[][] board, int player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        return false;
    }

    // Метод для Проверки Выигрыша по Столбцу:
    private boolean checkingBoardColumn(int[][] board, int player) {
        for (int i = 0; i < 3; i++) {
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        return false;
    }

    // Метод для Проверки Выигрыша по Диагонали:
    private boolean checkingBoardDiagonal(int[][] board, int player) {
        boolean mainDiagonal = (board[0][0] == player && board[1][1] == player && board[2][2] == player);
        boolean antiDiagonal = (board[0][2] == player && board[1][1] == player && board[2][0] == player);
        return mainDiagonal || antiDiagonal;
    }

    // Метод для Проверки Завершения Игры в Ничью:
    private boolean checkDrawGame(int[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}