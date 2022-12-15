package cn.anger.threadconfinement.threadlocal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : anger
 * 数据库连接分配器
 * 为每个线程分配一个独占的数据库连接
 */
public class ConnectionDispenser {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/anger_labs";
    private static ThreadLocal<Connection> connectionHolder =
        ThreadLocal.withInitial(() -> {
            try {
                return DriverManager.getConnection(DB_URL);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

    public static Connection getConnection() {
        return connectionHolder.get();
    }
}