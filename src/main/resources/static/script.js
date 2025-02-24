// Переменная для Хранения Поля Игры:
const gameBoard = document.getElementById('game-board');
// Переменная для Хранения Сообщения Пользователю:
const messageDiv = document.getElementById('message');
// Переменная для Хранения Игры:
let webGame;

// Функция для Создания Новой Игры:
async function createNewGame() {
    try {
        const response = await fetch('http://localhost:8080/game/new', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.ok) {
            webGame = await response.json();
            console.log('Новая Игра Получена:', webGame);
        } else {
            console.error('Ошибка при Получении Новой Игры:', response.status, response.statusText);
        }
    } catch (error) {
        console.error('Ошибка Сети:', error);
    }
}

// Функция для Инициализации Игрового Поля:
function initializeBoard() {
    for (let i = 0; i < 3; i++) {
        for (let j = 0; j < 3; j++) {
            gameBoard.appendChild(createCell(i, j));
        }
    }
}

// Функция для Создания Ячейки на Игровом Поле:
function createCell(row, col) {
    const cell = document.createElement('div');
    cell.className = 'cell';
    cell.dataset.row = row;
    cell.dataset.col = col;
    cell.addEventListener('click', () => handleCellClick(row, col));
    return cell;
}

// Функция для Обработки Клика на Ячейку:
function handleCellClick(row, col) {
    if (webGame.gameBoard.board[row][col] !== 0) {
        return;
    }
    webGame.gameBoard.board[row][col] = 1;
    updateBoard();
    sendMoveToServer(row, col);
}

// Функция для Обновления Игрового Поля:
function updateBoard() {
    const cells = document.querySelectorAll('.cell');
    cells.forEach(cell => {
        const row = cell.dataset.row;
        const col = cell.dataset.col;
        const cellValue = webGame.gameBoard.board[row][col];
        cell.innerHTML = '';
        if (cellValue === 1) {
            const cross = document.createElement('div');
            cross.className = 'cross';
            cell.appendChild(cross);
        } else if (cellValue === 2) {
            const circle = document.createElement('div');
            circle.className = 'circle';
            cell.appendChild(circle);
        }
    });
}

// Функция для Отправки Хода Игрока на Сервер:
async function sendMoveToServer(row, col) {
    const response = await fetch(`http://localhost:8080/game/${webGame.gameId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ row, col })
    });
    if (response.ok) {
        const data = await response.json();
        webGame.gameBoard = data.gameBoard;
        updateBoard();
        checkGameStatus(data.gameStatus)
    } else {
        messageDiv.textContent = 'Ошибка при Отправке Хода.';
    }
}

// Функция для Установки Статуса Игры:
function checkGameStatus(gameStatus) {
    if (gameStatus !== undefined) {
        if (gameStatus === 1) {
            messageDiv.textContent = "Вы Выиграли!";
            setMessageStyles();
        } else if (gameStatus === 2) {
            messageDiv.textContent = "Вы Проиграли!";
            setMessageStyles();
        } else if (gameStatus === 0) {
            messageDiv.textContent = "Ничья!";
            setMessageStyles();
        }
        createNewGameButton();
    }
}

// Функция для Стилизации Сообщения:
function setMessageStyles() {
    messageDiv.style.display = 'flex';
}

// Функция для Вывода Кнопки Новой Игры:
function createNewGameButton() {
    const newGameButton = document.createElement('button');
    newGameButton.textContent = "Новая Игра";
    newGameButton.className = 'new-game-button';
    newGameButton.addEventListener('click', () => {
        location.reload();
    });
    messageDiv.appendChild(newGameButton);
}

createNewGame();
initializeBoard();