package osscli.cli;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import osscli.services.Oss;
import osscli.services.OssFactory;
import osscli.services.config.OssConfigurationStore;
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
        OssConfiguration configuration =
            new OssConfiguration()
                .withAccessKey(accessKey)
                .withSecreteKey(secreteKey)
                .withEndPoint(endPoint)
                .withType(Oss.Type.fromValue(type));

        oss = OssFactory.getInstance(configuration);

        OssConfigurationStore.addOne(configuration);
    }

    @ShellMethod("通过预置的配置初始化 oss 实例")
    public void initFromPreset(int index) {
        oss = OssFactory.getInstance(OssConfigurationStore.getOne(index));
    }

    @ShellMethod("获取所有预置的配置信息")
    public String allConf() {
        return OssConfigurationStore.getAllAsString();
    }

    @ShellMethod("加载配置")
    public void loadConf(String path) {
        OssConfigurationStore.loadConfig(path);
    }

    @ShellMethod("将配置信息 dump 到本地指定文件")
    public void dumpConfig(String path) {
        OssConfigurationStore.dumpConfig(path);
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
