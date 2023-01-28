package reducinglockgranularity;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;

/**
 * @author : anger
 */
@ThreadSafe
public class ServerStatus {
    @GuardedBy("this")
    public final Set<String> users;
    @GuardedBy("this")
    public final Set<String> queries;
    public ServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }

    public synchronized void addUser(String user) {
        users.add(user);
    }

    public synchronized void addQuery(String query) {
        queries.add(query);
    }

    public synchronized void removeUser(String user) {
        users.remove(user);
    }

    public synchronized void removeQuery(String query) {
        queries.remove(query);
    }
}
