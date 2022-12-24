package cn.anger.net;

import cn.anger.concurrency.ThreadUtil;
import cn.anger.exception.LaunderThrowable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author : anger
 */
public class SocketUtil {
    private SocketUtil() {}

    /**
     * 简单 socket client
     * 打印 socket server 返回的数据
     * @param proxy server 地址
     * @param port 端口
     */
    public static void socketClient(String proxy, int port) {
        try (Socket socket = new Socket(proxy, port);
             BufferedReader reader = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()))){
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }

    /**
     * 简单的 connection 请求延时处理方法
     * 一定时间延迟后，返回信息给 client
     * @param connection socket connection
     * @param msg 返回 client 的信息
     * @param latency 延迟时间，单位 ms
     */
    public static void handleConnection(Socket connection, String msg, long latency) {
        ThreadUtil.sleep(latency);

        System.out.printf("Request from => %s, handled by => %s%n",
            connection.getInetAddress().getHostAddress(),
            Thread.currentThread().getName());

        try (PrintWriter writer = new PrintWriter(
            connection.getOutputStream())){
            writer.write(msg);
            writer.flush();
        } catch (IOException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
