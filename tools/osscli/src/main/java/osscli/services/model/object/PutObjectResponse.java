package osscli.services.model.object;

import osscli.services.model.CliResponse;

/**
 * @author : anger
 */
public class PutObjectResponse extends CliResponse {
    private String eTag;

    public String getETag() {
        return eTag;
    }

    public void setETag(String eTag) {
        this.eTag = eTag;
    }
}
