package osscli.cli;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellCommandGroup;
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

    @ShellMethod(value = "初始化 oss 实例", group = "init", key = "--init")
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

    @ShellMethod(value = "通过已保存的配置信息初始化 oss 实例", group = "init", key = "--init-conf")
    public void initFromPreset(String key) {
        oss = OssFactory.getInstance(OssConfigurationStore.getOne(key));
    }

    @ShellComponent
    @ShellCommandGroup("conf")
    public static class ConfCommand {
        @ShellMethod(value = "获取所有配置信息", key = "--conf-all")
        public String all() {
            return OssConfigurationStore.getAllAsString();
        }

        @ShellMethod(value = "从本地文件加载配置", key = "--conf-load")
        public void load(String path) {
            OssConfigurationStore.loadConfig(path);
        }

        @ShellMethod(value = "将配置信息 dump 到本地文件", key = "--conf-dump")
        public void dump(String path) {
            OssConfigurationStore.dumpConfig(path);
        }
    }


    @ShellComponent
    @ShellCommandGroup("bucket")
    public class BucketCommands {
        @ShellMethod(value = "获取所有的桶", key = "--bucket-list")
        public String listBuckets() {
            return oss.listBuckets().toString();
        }

        @ShellMethod(value = "创建桶", key = "--bucket-create")
        public String createBucket(String bucketName) {
            return oss.createBucket(bucketName).getBucket().getName();
        }

        @ShellMethod(value = "获取一个桶中所有的对象的 key ", key = "--bucket-keys")
        public String listAll(String bucket) {
            return oss.listAllObjects(bucket).toString();
        }
    }

    @ShellComponent
    @ShellCommandGroup("object")
    public class ObjectCommands {
        @ShellMethod(value = "上传文件到指定桶", key = "--object-upload")
        public String uploadFile(String bucket, String path) {
            return oss.putObject(bucket, new File(path)).getETag();
        }
    }

    @ShellComponent
    @ShellCommandGroup("batch")
    public class BatchCommands {
        @ShellMethod(value = "批量上传文件", key = "--batch-upload")
        public String batchUpload(String bucket, String path) {
            return oss.batchUpload(bucket, path).toString();
        }

        @ShellMethod(value = "批量下载文件", key = "--batch-download")
        public String batchDownload(String bucket, String path) {
            return oss.batchDownload(bucket, path).toString();
        }
    }

    public Availability ossInstanceCheck() {
        return oss != null ?
            Availability.available() :
            Availability.unavailable("必须初始化 oss 实例");
    }

}
