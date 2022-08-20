package hillel.homeworks.game;

import lombok.Getter;
import lombok.Setter;

/**
 * Класс, описывающий игрока
 */
@Getter
@Setter
public class Player {
    private String name;            //  имя игрока
    private int gamesPlayed = 0;    //  сыграно игр
    private int win = 0;            //  побед
    private int loss = 0;           //  поражений
    private int draw = 0;           //  ничья

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", gamesPlayed=" + gamesPlayed +
                ", win=" + win +
                ", loss=" + loss +
                ", draw=" + draw +
                '}';
    }
}
