package hillel.homeworks.game;

import java.util.Scanner;

public class Game {

    //  Выбор игрока
    public static final int STONE = 1;
    public static final int SCISSORS = 2;
    public static final int PAPER = 3;
    public static final int BREAK = 0;

    //  Результат матча
    public static final int DRAW = 1;
    public static final int PLAYER1 = 2;
    public static final int PLAYER2 = 3;

    //  Матрица для вычисления результатов матча
    private static int[][] win = {
            {DRAW, PLAYER1, PLAYER2},
            {PLAYER2, DRAW, PLAYER1},
            {PLAYER1, PLAYER2, DRAW}
    };

    /**
     * Трансляция выбора игрока из кода в название
     * @param choice Код выбора(STONE/SCISSORS/PAPER)
     * @return название выбора
     */
    public static String translateChoice(int choice) {
        switch (choice) {
            case STONE: return "STONE";
            case SCISSORS: return "SCISSORS";
            case PAPER: return "PAPER";
            default: return "";
        }
    }

    /**
     * Получить результат игры, на основе выбора игроков
     * @param player1Choice Выбор первого игрока
     * @param player2Choice Выбор второго игрока
     * @return Ничья(DRAW)/Победил игрок1(PLAYER1)/Победил игрок 2(PLAYER2)
     */
    public static int getGameResult(int player1Choice, int player2Choice) {
        return win[player1Choice-1][player2Choice-1];
    }

    /**
     * Генерация случайного выбора игрока
     * @return 1..3(STONE, SCISSORS, PAPER)
     */
    public static int generateRandomResult() {
        return 1 + (int) ( Math.random() * 3 );
    }

    /**
     * Запросить выбор игрока
     * @return 1(Камень)/2(Ножницы)/3(Бумага)
     */
    public static int askPlayer() {
        Scanner scanner = new Scanner(System.in);
        for(;;) {
            System.out.println();
            System.out.println(STONE + ": " + translateChoice(STONE));
            System.out.println(SCISSORS + ": " + translateChoice(SCISSORS));
            System.out.println(PAPER + ": " + translateChoice(PAPER));
            System.out.println(BREAK + ": break game");
            System.out.print("Your choice? Enter a number: ");
            if (scanner.hasNextInt()) {
                int res = scanner.nextInt();
                if (res == STONE || res == SCISSORS || res == PAPER || res == BREAK) {
                    return res;
                }
            } else {
                scanner.next();
            }
            System.out.println("\nInvalid choice!");
        }
    }

}

