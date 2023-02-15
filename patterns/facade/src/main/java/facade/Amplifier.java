package facade;

/**
 * author : anger
 * date : 2022/8/7 16:11
 * description : 公放
 */
public class Amplifier {
    private Player player;

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setVolume(int volume) {
        System.out.println("Amplifier.setVolume");
        System.out.println("volume = " + volume);
    }

}
