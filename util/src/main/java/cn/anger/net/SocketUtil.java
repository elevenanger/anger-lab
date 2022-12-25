package cn.anger.net;

import cn.anger.concurrency.ThreadUtil;
import cn.anger.exception.LaunderThrowable;

import java.io.*;
import java.net.Socket;
import java.util.function.Predicate;

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
    public static void socketClient(String proxy, int port, String msg) {
        try (Socket socket = new Socket(proxy, port);
             PrintWriter writer = new PrintWriter(
                 socket.getOutputStream())){
            writer.write(msg);
            writer.flush();
            BufferedReader reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
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

        try (InputStream inputStream = connection.getInputStream();
                OutputStream outputStream = connection.getOutputStream()){
            readInput(inputStream);
            writeOutput(outputStream, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 读取服务器收到的客户端发送的数据
     * @param connection socket 连接
     */
    private static void readInput(InputStream inputStream) {
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream))) {
            reader.lines().forEach(System.out::println);
        } catch (IOException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }

    private static void writeOutput(OutputStream outputStream, String msg) {
        try (PrintWriter writer = new PrintWriter(
            outputStream)){
            writer.write(msg);
            writer.flush();
        }
    }

    public static boolean readCommand(InputStream inputStream, String command) {
        final Predicate<String> matchCommand = s -> s.contains(command);
        try (BufferedReader reader = new BufferedReader(
            new InputStreamReader(inputStream))) {
            return reader.lines().anyMatch(matchCommand);
        } catch (IOException e) {
            throw LaunderThrowable.launderThrowable(e.getCause());
        }
    }
}
