# Vacation Pay Calculator
## Описание
Этот сервис предоставляет API для расчета отпускных.

## Запуск проекта локально
### Требования
- Docker
- Docker Compose

### Шаги для запуска
1. Перейдите в подготовленную папку для проекта:
    ```bash
    cd your-repo-name
    ```
2. Склонируйте проект:
    ```bash
    git clone https://github.com/Radionbes228/TestTask.git
    ```
4. Запустите очистку и сборку проекта с помощью maven:
   ```bash
   mvn clean package
   ```
5. Запустите сервис с помощью Docker Compose:
    ```bash
    docker-compose up --build
    ```

5. После запуска сервис будет доступен по адресу `http://localhost:8085`.

Существует лишь одно API `http://localhost:8085/api/calculate` - GET запрос <br>

Какие параметры принимает:
1. avgSalary - среднегодовая ЗП
2. countDays - количество отпускных дней

**`или`**

1. avgSalary - среднегодовая ЗП
2. startVacation - стартовая дата отпускных
3. endVacation - конечная дата отпускных

