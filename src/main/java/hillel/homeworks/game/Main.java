package hillel.homeworks.game;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //  Выбор языкового пакета, ресурсы которого будут использоваться для вывода на консоль
        LocaleResourceService res;
        if (args.length >= 1) {
            switch (args[0].toLowerCase()) {
                case "en": res = new LocaleResourceService("message", "en", "US"); break;
                case "de": res = new LocaleResourceService("message", "de", "DE"); break;
                default: res = new LocaleResourceService("message", "uk", "UA");
            }
        } else {
            res = new LocaleResourceService("message", "uk", "UA");
        }

        //  Создаем объект игры с поддержкой интернационализации и включаем логирование в файл(HW17)
        GameWithI18n game = new GameWithI18n(res);
        game.openLogging();

        //  Определение кодовой страницы консоли
        //  Установка преобразования UTF8(внутренняя кодировка строк) в кодовую страницу консоли при выводе в OutputStream
        //  В случае, если JVM не имеет поддержки кодовой страницы консоли, вывод в консоль будет осуществляться в UTF8
        try {
            String codePage = System.getProperty("sun.stdout.encoding");
            System.setOut(new PrintStream(System.out, true, codePage));
            GameWithLogback.gameLogger.info("Console Codepage: " + codePage);
        } catch (UnsupportedEncodingException e) {
            GameWithLogback.gameLogger.info("JVM does not support your console codepage. The console output will be made using UTF8");
        }


        //  Будущее количество игр, которые закажет второй игрок
        int gamesNumber;

        Player player1 = new PlayerI18n(res);
        Player player2 = new PlayerI18n(res);

        //  Первый игрок - компьютер
        player1.setName("COMPUTER");

        //  Второй игрок - человек
        Scanner scanner = new Scanner(System.in);
        System.out.print(res.getResourceValue("enter_your_name") + ": ");
        player2.setName(scanner.next());

        //  Сколько игр будем играть?
        for(;;) {
            System.out.print(res.getResourceValue("enter_number_of_game") + ": ");
            if (scanner.hasNextInt()) {
                gamesNumber = scanner.nextInt();
                break;
            } else {
                scanner.next();
                System.out.println(res.getResourceValue("warning_invalid_enter"));
            }
        }

        //  Логирование предварительных данных перед игрой
        System.out.println();
        //  Логирование в файл(HW17)
        game.printlnLogging(res.getResourceValue("player1") + ": " + player1.getName());
        game.printlnLogging(res.getResourceValue("player2") + ": " + player2.getName());
        game.printlnLogging(res.getResourceValue("estimated_number_of_games") + ": " + gamesNumber);
        //  Логирование посредством Logback
        GameWithLogback.gameLogger.info("Player1: " + player1.getName());
        GameWithLogback.gameLogger.info("Player2: " + player2.getName());
        GameWithLogback.gameLogger.info("Estimated number of games: " + gamesNumber);

        //  Процесс игры
        int choicePlayer1, choicePlayer2;
        String printStr;
        for (int i = 1; i <= gamesNumber; i++) {
            choicePlayer1 = Game.generateRandomResult();    //  случайный выбор компьютера
            choicePlayer2 = GameWithI18n.askPlayer();    //  выбор человека
            if (choicePlayer2 != Game.BREAK) {

                printStr = player1.getName() + ": " + GameWithI18n.translateChoice(choicePlayer1) + "  " +
                               player2.getName() + ": " + GameWithI18n.translateChoice(choicePlayer2);

                player1.setGamesPlayed(player1.getGamesPlayed() + 1);
                player2.setGamesPlayed(player2.getGamesPlayed() + 1);

                switch (Game.getGameResult(choicePlayer1, choicePlayer2)) {
                    case Game.DRAW :
                        //  Логирование в файл(HW17)
                        game.printlnLogging(printStr + "  " + res.getResourceValue("draw"));
                        //  Логирование посредством Logback
                        GameWithLogback.gameLogger.info(printStr + "  DRAW");
                        player1.setDraw(player1.getDraw() + 1);
                        player2.setDraw(player2.getDraw() + 1);
                        break;
                    case Game.PLAYER1:
                        //  Логирование в файл(HW17)
                        game.printlnLogging(printStr + "  " + player1.getName() + " " + res.getResourceValue("is_win"));
                        //  Логирование посредством Logback
                        GameWithLogback.gameLogger.info(printStr + "  " + player1.getName() + " is win");
                        player1.setWin(player1.getWin() + 1);
                        player2.setLoss(player2.getLoss() + 1);
                        break;
                    case Game.PLAYER2:
                        //  Логирование в файл(HW17)
                        game.printlnLogging(printStr + "  " + player2.getName() + " " + res.getResourceValue("is_win"));
                        //  Логирование посредством Logback
                        GameWithLogback.gameLogger.info(printStr + "  " + player2.getName() + " is win");
                        player1.setLoss(player1.getLoss() + 1);
                        player2.setWin(player2.getWin() + 1);
                        break;
                }
                //  Логирование посредством Logback
                GameWithLogback.gameLogger.debug("Game played: " + i + "  Game left: " + (gamesNumber - i));
            } else {
                GameWithLogback.gameLogger.debug("Game interrupted");
                break;
            }
        }

        //  Вывод сводных результатов игры в файл(HW17)
        System.out.println();
        game.printlnLogging(player1.toString());
        game.printlnLogging(player2.toString());
        System.out.println();
        game.closeLogging();
        //  Логирование посредством Logback
        GameWithLogback.gameResultLogger.info(player1.toString());
        GameWithLogback.gameResultLogger.info(player2.toString());
    }
}
