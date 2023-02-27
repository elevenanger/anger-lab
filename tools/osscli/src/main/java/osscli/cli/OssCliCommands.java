package osscli.cli;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import osscli.services.Oss;
import osscli.services.OssFactory;
import osscli.services.config.PresetConfiguration;
import osscli.services.model.OssConfiguration;

import java.io.File;

/**
 * @author : anger
 * Oss 命令行方法
 */
@ShellComponent
public class OssCliCommands {

    private Oss oss;

    @ShellMethod("初始化 oss 实例")
    public void ossInit(String type, String endPoint, String accessKey, String secreteKey) {
        oss = OssFactory.getInstance(
                new OssConfiguration()
                        .withAccessKey(accessKey)
                        .withSecreteKey(secreteKey)
                        .withEndPoint(endPoint)
                        .withType(Oss.Type.fromValue(type)));
    }

    @ShellMethod("通过预置的配置初始化 oss 实例")
    public void initFromPreset(int index) {
        oss = OssFactory.getInstance(PresetConfiguration.all().get(index));
    }

    @ShellMethod("获取所有预置的配置信息")
    public String allConf() {
        return PresetConfiguration.allStringForm();
    }

    @ShellMethod("获取所有的桶")
    public String listBuckets() {
        return oss.listBuckets().toString();
    }

    @ShellMethod("创建桶")
    public String createBucket(String bucketName) {
        return oss.createBucket(bucketName).getBucket().getName();
    }

    @ShellMethod("获取一个桶中所有的对象的 key ")
    public String listAll(String bucket) {
        return oss.listAllObjects(bucket).toString();
    }

    @ShellMethod("上传文件到指定桶")
    public String uploadFile(String bucket, String path) {
        return oss.putObject(bucket, new File(path)).getETag();
    }

    @ShellMethod("批量上传文件")
    public String batchUpload(String bucket, String path) {
        return oss.batchUpload(bucket, path).toString();
    }

    @ShellMethod("批量下载文件")
    public String batchDownload(String bucket, String path) {
        return oss.batchDownload(bucket, path).toString();
    }

    public Availability ossInstanceCheck() {
        return oss != null ?
            Availability.available() :
            Availability.unavailable("必须初始化 oss 实例");
    }

}
