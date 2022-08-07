package facade;

/**
 * author : liuanglin
 * date : 2022/8/6 21:05
 * description : 门面模式
 * 联合系统中系统众多的接口
 * 门面提供一个更高阶的接口使得系统中的子系统更易于使用
 * 门面 Facade 直接提供所需要的内容
 * 一个系统如果存在多个复杂的子系统
 * 使用门面模式实现一个门面类
 * 提供对客户端一个或者多个简单直接的接口
 * 用于简化系统内部复杂的子系统
 * 门面模式不仅仅是简化了接口
 * 它可以将客户端与子系统或者模块的复杂性进行解耦
 * 家庭影院门面类
 * 家庭影院包括众多子系统（模块）
 * 包括：
 * 1、功放器
 * 2、调节器
 * 3、荧幕
 * 4、影院灯光
 * 5、流处理器
 * 6、投影仪
 * 7、爆米花
 * 客户端代码需要调用家庭影院门面提供的接口而不是各个子系统来实现以下操作
 * 看电影（包括调暗灯光、设置放映影片、点亮屏幕、连接功放、调解音量、开始播放电影等一系列的操作）
 * 门面模式并不会影响子系统
 * 如果需要使用子系统某一个具体的功能
 * 子系统的方法客户端也是可以直接调用的
 * 门面模式与适配器模式：
 * 门面模式和适配器模式都可以封装多个类
 * 门面模式的目的是对客户端简化系统的复杂度
 * 适配器模式是将接口转换成不同的东西以满足客户端的需要
 *
 * 最少知识原则:
 * 将对象之间的交互减少到几个最亲密的"朋友"
 * 只与最亲密的"朋友"交谈
 * 设计一个系统
 * 对于任意对象
 * 需要特别谨慎设计对象之间的交互关系
 * 以及对象之间的交互方式
 * 最少知识原则的一些指导原则：
 * 仅调用属于以下对象的方法：
 * 1、对象本身的方法
 * 2、作为方法参数传入的对象的方法
 * 3、该方法创建或者实例化的任意对象的方法
 * 4、对象任意组件对象（组合 has-a 关系的对象）的方法
 */
public class HomeTheaterFacade {
    private final Player player;
    private final Amplifier amplifier;
    private final Projector projector;
    private final Screen screen;
    private final TheaterLights lights;

    public HomeTheaterFacade(Player player, Amplifier amplifier,
                             Projector projector, Screen screen,
                             TheaterLights lights) {
        this.player = player;
        this.amplifier = amplifier;
        this.projector = projector;
        this.screen = screen;
        this.lights = lights;
    }

    /**
     * 对外提供看电影的接口
     * 客户端直接调用该接口即可
     * 这个接口内部实现看电影相关的所有逻辑
     * 简化了客户端调用的代码
     */
    public void watchMovie() {
        System.out.println("HomeTheaterFacade.watchMovie");
        amplifier.setPlayer(player);

        player.on();
        projector.on();
        screen.down();
        lights.on();

        amplifier.setVolume(10);
        projector.wideScreenMode();

        player.play();
    }
}
