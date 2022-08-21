package hillel.homeworks.game;

import java.util.Scanner;

public class GameWithI18n extends GameWithLogback{

    private static LocaleResourceService res;

    GameWithI18n(LocaleResourceService res) {
        this.res = res;
    }

    /**
     * Трансляция выбора игрока из кода в название
     * @param choice Код выбора(STONE/SCISSORS/PAPER)
     * @return название выбора
     */
    public static String translateChoice(int choice) {
        switch (choice) {
            case STONE: return res.getResourceValue("stone");
            case SCISSORS: return res.getResourceValue("scissors");
            case PAPER: return res.getResourceValue("paper");
            default: return "";
        }
    }


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
            System.out.println(BREAK + ": " + res.getResourceValue("break_game"));
            System.out.print(res.getResourceValue("your_choice_enter_number"));

            if (scanner.hasNextInt()) {
                resInt = scanner.nextInt();
                if (resInt == STONE || resInt == SCISSORS || resInt == PAPER || resInt == BREAK) {
                    return resInt;
                }
                gameLogger.debug("Invalid choice: " + resInt);
            } else {
                gameLogger.debug("Invalid choice: " + scanner.next());
            }
            System.out.println("\n" + res.getResourceValue("warn_invalid_choice") + "!");
        }
    }


}
