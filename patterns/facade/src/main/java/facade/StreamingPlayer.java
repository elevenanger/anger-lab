package facade;

/**
 * author : liuanglin
 * date : 2022/8/7 16:10
 * description : 流媒体播放器
 */
public class StreamingPlayer implements Player{
    @Override
    public void on() {
        System.out.println("StreamingPlayer.on");
    }

    @Override
    public void off() {
        System.out.println("StreamingPlayer.off");
    }

    @Override
    public void play() {
        System.out.println("StreamingPlayer.play");
    }

    @Override
    public void pause() {
        System.out.println("StreamingPlayer.pause");
    }

    @Override
    public void stop() {
        System.out.println("StreamingPlayer.stop");
    }
}
