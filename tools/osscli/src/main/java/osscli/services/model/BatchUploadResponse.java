package osscli.services.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : anger
 * 批量上传响应
 */
public class BatchUploadResponse extends CliResponse {

    private final List<UploadResult> uploadResults = new ArrayList<>();

    public void addResult(String key, boolean success) {
        uploadResults.add(new UploadResult(key, success));
    }

    public List<UploadResult> getUploadResults() {
        return Collections.unmodifiableList(new ArrayList<>(uploadResults));
    }

    private static final class UploadResult {
        private final String key;
        private final boolean success;

        public UploadResult(String key, boolean success) {
            this.key = key;
            this.success = success;
        }

        public String getKey() {
            return key;
        }

        public boolean isSuccess() {
            return success;
        }
    }

}
