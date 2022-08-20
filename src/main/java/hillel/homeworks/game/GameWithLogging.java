package hillel.homeworks.game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class GameWithLogging extends Game {
    private FileWriter out;

    /**
     * Включить логирование
     */
    void openLogging() {
        try {
            out = new FileWriter(getLogFileName(), true);
        } catch (IOException e) {
            out = null;
        }
    }

    /**
     * Завершить логирование
     */
    void closeLogging() {
        try {
            if (out != null) out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывести в файл лога строку
     * @param str Строка для вывода
     */
    void printLogging(String str) {
        try {
            System.out.print(str);                                              //  на консоль
            if (out != null) out.write(LocalDateTime.now() + "  " + str);       //  в файл
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Вывести в файл лога строку с завершающим переводом каретки
     * @param str Строка для вывода
     */
    void printlnLogging(String str) {
        printLogging(str + "\n");
    }

    /**
     * Получить имя файла на основе имени запущенного jar-файла
     * @return Полный путь к файлу лога
     */
    private String getLogFileName() {
        String jarAbstractName = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
        String jarFileName = jarAbstractName.substring(jarAbstractName.lastIndexOf("/") + 1);
        if (jarFileName.contains(".")) {
            return getCurrentFolder() + jarFileName.substring(0, jarFileName.lastIndexOf(".")) + ".log";
        } else {
            return getCurrentFolder() + File.separator + ".log";
        }
    }

    /**
     * Получить текущий каталог
     * @return Текущий каталог
     */
    private String getCurrentFolder() {
        String folder = System.getProperty("user.dir");
        if (folder.endsWith(File.separator)) return folder ;
            else return folder + File.separator;
    }
}
