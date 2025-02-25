# TicTacToe

## Описание

Проект представляет собой реализацию игры "Крестики-Нолики". Данный проект является клиент-серверным приложением, в котором клиент взаимодействует с веб-сервером при помощи браузера. Логика веб-приложения распределена между сервером и клиентом, хранение данных осуществляется преимущественно на сервере, обмен информацией происходит по сети. Реализована поддержка нескольких игр одновременно.

![gameplay](docs/gameplay.gif)

## Требования

- Java Development Kit (JDK) версии 17 или выше
- Наличие доступа к интернету
- Наличие браузера

## Установка и Запуск

### Для Linux

- Клонировать репозиторий: `git clone git@github.com:Mark-Borzov/TicTacToe.git`
- Перейти в директорию проекта: `cd TicTacToe`
- Осуществить сборку проекта при помощи команды: `./gradlew build`
- Запустить приложение при помощи команды: `./gradlew bootRun`
- Открыть браузер и перейти по ссылке: `http://localhost:8080`

### Для Windows

- Клонировать репозиторий: `git clone git@github.com:Mark-Borzov/TicTacToe.git`
- Перейти в директорию проекта: `cd TicTacToe`
- Осуществить сборку проекта при помощи команды: `.\gradlew build`
- Запустить приложение при помощи команды: `.\gradlew bootRun`
- Открыть браузер и перейти по ссылке: `http://localhost:8080`