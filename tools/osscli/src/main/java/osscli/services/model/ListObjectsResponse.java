package osscli.services.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : anger
 */
public class ListObjectsResponse extends CliResponse {
    private final List<ObjectSummary> objectSummaries = new ArrayList<>();
    private String startAfter;
    private int count;
    private int maxKey;

    public List<ObjectSummary> getObjectSummaries() {
        return objectSummaries;
    }

    public void setObjectSummaries(List<ObjectSummary> objectSummaries) {
        this.objectSummaries.addAll(objectSummaries);
    }

    public String getStartAfter() {
        return startAfter;
    }

    public void setStartAfter(String startAfter) {
        this.startAfter = startAfter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxKey() {
        return maxKey;
    }

    public void setMaxKey(int maxKey) {
        this.maxKey = maxKey;
    }

}
