package reducinglockgranularity;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Set;

/**
 * @author : anger
 */
@ThreadSafe
public class FineGrainedServerStatus {
    @GuardedBy("users")
    public final Set<String> users;
    @GuardedBy("queries")
    public final Set<String> queries;

    public FineGrainedServerStatus(Set<String> users, Set<String> queries) {
        this.users = users;
        this.queries = queries;
    }

    public void addUser(String user) {
        synchronized (users) {
            users.add(user);
        }
    }

    public void addQuery(String query) {
        synchronized (queries) {
            queries.add(query);
        }
    }

    public void removeUser(String user) {
        synchronized (users) {
            users.remove(user);
        }
    }

    public void removeQuery(String query) {
        synchronized (queries) {
            queries.remove(query);
        }
    }

}
