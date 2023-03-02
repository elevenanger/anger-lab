package osscli.services.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : anger
 * 批量上传响应
 */
public abstract class BatchOperationResponse extends CliResponse {

    protected abstract String getOperationName();

    private final List<OperationResult> operationResults = new ArrayList<>();

    public void addSuccessResult(String key) {
        operationResults.add(new OperationResult(key, true));
    }

    public void addErrorResult(String key, String errorMsg) {
        operationResults.add(new OperationResult(key, false, errorMsg));
    }

    public List<String> getUploadResults() {
        return Collections.unmodifiableList(new ArrayList<>(operationResults)).stream()
                .map(OperationResult::toString)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "Batch " + getOperationName() + " Results : \n" +
                operationResults.stream()
                        .map(OperationResult::toString)
                        .collect(Collectors.joining("\n"))
                + "\n total : " + operationResults.size();
    }

    private final class OperationResult {
        private final String key;
        private final boolean success;
        private final String errorMsg;

        public OperationResult(String key, boolean success) {
            this.key = key;
            this.success = success;
            this.errorMsg = "";
        }

        public OperationResult(String key, boolean success, String errorMsg) {
            this.key = key;
            this.success = success;
            this.errorMsg = errorMsg;
        }

        @Override
        public String toString() {
            return
                getOperationName() + " " + key +
                    (success ? " success" :
                                " failed, error msg " + errorMsg);
        }
    }

}
