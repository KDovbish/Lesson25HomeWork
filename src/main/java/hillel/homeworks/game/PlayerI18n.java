package hillel.homeworks.game;

public class PlayerI18n extends Player{

    private LocaleResourceService res;

    PlayerI18n(LocaleResourceService res) {
        this.res = res;
    }

    @Override
    public String toString() {

        return res.getResourceValue("player") + "{" +
                res.getResourceValue("name") + "='" + this.getName() + '\'' +
                ", " + res.getResourceValue("gamesPlayed") + "=" + this.getGamesPlayed() +
                ", " + res.getResourceValue("player_tostring_win") + "=" + this.getWin() +
                ", " + res.getResourceValue("player_tostring_loss") + "=" + this.getLoss() +
                ", " + res.getResourceValue("player_tostring_draw") + "=" + this.getDraw() +
                '}';
    }
}
