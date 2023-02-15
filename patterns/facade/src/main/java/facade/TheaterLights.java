package facade;

/**
 * author : anger
 * date : 2022/8/6 21:29
 * description : 影院灯光
 */
public class TheaterLights {

    // 亮度
    private int luminance = 0;

    public void on() {
        System.out.println("TheaterLights.on");
        luminance = 10;
    }

    public void off() {
        System.out.println("TheaterLights.off");
        luminance = 0;
    }

    public void dim() {
        System.out.println("TheaterLights.dim");
        luminance = 3;
    }

    @Override
    public String toString() {
        return "TheaterLights{" +
            "luminance=" + luminance +
            '}';
    }
}
