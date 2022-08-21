package hillel.homeworks.game;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Класс для работы с пакетом ресурсов
 * Ключевой функционал:
 * - инициализация заданного языкового пакета ресурсов
 * - получение значения ресурса из пакета по ключу
 */
public class LocaleResourceService {
    private Locale locale;
    private ResourceBundle resourceBundle;

    /**
     * Конструктор
     * @param base Базовое имя пакета ресурсов
     * @param language Язык, для которого предназначен пакет ресурсов
     * @param location Местоположение, для которого предназначен пакет ресурсов
     */
    LocaleResourceService(String base, String language, String location) {
        locale = new Locale(language, location);
        resourceBundle = ResourceBundle.getBundle(base, locale);
    }

    /**
     * Получить значение ресурса по ключу
     * @param key Ключ
     * @return Значение
     */
    String getResourceValue(String key){
        return resourceBundle.getString(key);
    };

    Locale getLocale() {
        return locale;
    }
}
