package hillel.homeworks.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class GameWithLogback extends GameWithLogging{

    /**
     * Логгер для текущих событий игры
     */
    public static Logger gameLogger = LoggerFactory.getLogger(GameWithLogback.class);
    /**
     * Логгер для вывода результатов игры
     */
    public static Logger gameResultLogger = LoggerFactory.getLogger("gameresult");

    /**
     * Запросить выбор игрока<br>
     * По сравнению с первоначальным статическим методом Game.askPlayer(), данный метод дополнительно реализует
     * логирование посредством Logback
     * @return 1(Камень)/2(Ножницы)/3(Бумага)
     */
    public static int askPlayer() {
        Scanner scanner = new Scanner(System.in);
        int resInt = 0;
        String resStr = "";
        for(;;) {
            System.out.println();
            System.out.println(STONE + ": " + translateChoice(STONE));
            System.out.println(SCISSORS + ": " + translateChoice(SCISSORS));
            System.out.println(PAPER + ": " + translateChoice(PAPER));
            System.out.println(BREAK + ": break game");
            System.out.print("Your choice? Enter a number: ");

            if (scanner.hasNextInt()) {
                resInt = scanner.nextInt();
                if (resInt == STONE || resInt == SCISSORS || resInt == PAPER || resInt == BREAK) {
                    return resInt;
                }
                gameLogger.debug("Invalid choice: " + resInt);
            } else {
                gameLogger.debug("Invalid choice: " + scanner.next());
            }
            System.out.println("\nInvalid choice!");
        }
    }

}
