package osscli.services.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Anger
 * created on 2023/2/26
 * 获取所有的桶对象
 */
public class ListBucketsResponse extends CliResponse {
    private final List<Bucket> buckets = new ArrayList<>();

    public ListBucketsResponse(List<Bucket> buckets) {
        this.setBuckets(buckets);
    }

    public List<Bucket> getBuckets() {
        return Collections.unmodifiableList(buckets);
    }

    public void setBuckets(List<Bucket> buckets) {
        this.buckets.clear();
        this.buckets.addAll(buckets);
    }

    @Override
    public String toString() {
        return "ListBucketsResponse : " +
                getBuckets().stream()
                    .map(bucket -> bucket.toString().concat("\n"))
                    .reduce("", String::concat);
    }
}
