package osscli.services.model;

/**
 * @author Anger
 * created on 2023/2/26
 */
public class DeleteObjectResponse extends CliResponse {
    private ObjectSummary deletedObject;

    public ObjectSummary getDeletedObject() {
        return deletedObject;
    }

    public void setDeletedObject(ObjectSummary deletedObject) {
        this.deletedObject = deletedObject;
    }
}
