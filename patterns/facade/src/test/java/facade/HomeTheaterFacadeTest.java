package facade;

import org.junit.jupiter.api.Test;

/**
 * author : anger
 * date : 2022/8/7 16:25
 * description : 测试 HomeTheaterFacade.watchMovie() 方法
 */
class HomeTheaterFacadeTest {

    @Test
    void watchMovieTest() {
        Player player = new StreamingPlayer();
        Projector projector = new Projector();
        Screen screen = new Screen();
        TheaterLights lights = new TheaterLights();
        Amplifier amplifier = new Amplifier();

        amplifier.setPlayer(player);

        HomeTheaterFacade facade =
            new HomeTheaterFacade(player, amplifier, projector,
                                    screen, lights);

        facade.watchMovie();
    }

}