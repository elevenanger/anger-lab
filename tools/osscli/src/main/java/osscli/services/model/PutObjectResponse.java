package osscli.services.model;

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
