package osscli.cli;

import org.springframework.shell.standard.ShellCommandGroup;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
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

    private Oss oss = OssFactory.getInstance(OssConfigurationStore.defaultConfiguration());

    @ShellMethod(value = "初始化 oss 实例\n" +
        "\t用法 : --init type endPoint accessKey secreteKey", group = "init", key = "--init")
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

    @ShellMethod(value = "通过已保存的配置信息初始化 oss 实例\n" +
        "\t用法 : --init-from 配置ID (通过 --conf-all 查看所有配置信息)", group = "init", key = "--init-from")
    public void initFromPreset(String confKey) {
        oss = OssFactory.getInstance(OssConfigurationStore.getOne(confKey));
    }

    @ShellComponent
    @ShellCommandGroup("conf")
    public class ConfCommand {
        @ShellMethod(value = "获取所有配置信息", key = "--conf-all")
        public String all() {
            return OssConfigurationStore.getAllAsString();
        }

        @ShellMethod(value = "从本地文件加载配置\n" +
            "\t用法 : --conf-load 本地配置文件路径", key = "--conf-load")
        public void load(String path) {
            OssConfigurationStore.loadConfig(path);
        }

        @ShellMethod(value = "将配置信息 dump 到本地文件\n" +
            "\t用法 : --conf-dump 本地路径", key = "--conf-dump")
        public void dump(String path) {
            OssConfigurationStore.dumpConfig(path);
        }

        @ShellMethod(value = "获取当前的配置信息\n" +
            "\t用法 : --conf-current", key = "--conf-current")
        public String currentConfiguration() {
            return oss.getCurrentConfiguration().getEndPoint();
        }
    }


    @ShellComponent
    @ShellCommandGroup("bucket")
    public class BucketCommands {
        @ShellMethod(value = "获取所有的桶\n" +
            "\t用法 : --bucket-all", key = "--bucket-all")
        public String listBuckets() {
            return oss.listBuckets().toString();
        }

        @ShellMethod(value = "创建桶\n" +
            "\t用法 : --bucket-create bucket", key = "--bucket-create")
        public String createBucket(String bucketName) {
            return oss.createBucket(bucketName).getBucket().getName();
        }

        @ShellMethod(value = "删除桶\n" +
            "\t 用法 : --bucket-delete", key = "--bucket-delete")
        public String deleteBucket(String bucket) {
            return oss.deleteBucket(bucket).getBucket();
        }

        @ShellMethod(value = "获取一个桶中所有对象信息\n" +
            "\t用法 : --bucket-objects bucket prefix(默认值\"\")", key = "--bucket-objects")
        public String listAll(String bucket,
                              @ShellOption(defaultValue = ShellOption.NULL) String prefix) {
            return oss.listAllObjects(bucket, prefix).toString();
        }
    }

    @ShellComponent
    @ShellCommandGroup("object")
    public class ObjectCommands {
        @ShellMethod(value = "上传对象\n" +
            "\t用法 : --object-upload bucket 本地路径", key = "--object-upload")
        public String uploadFile(String bucket, String path) {
            return oss.putObject(bucket, new File(path)).getETag();
        }

        @ShellMethod(value = "删除对象\n" +
            "\t用法 : --object-delete bucket key" , key = "--object-delete")
        public String deleteObject(String bucket, String key) {
            return oss.deleteObject(bucket, key).toString();
        }

        @ShellMethod(value = "下载对象\n" +
            "\t用法 : --object-download bucket key 本地路径", key = "--object-download")
        public String downloadObject(String bucket, String key, String path) {
            return oss.downloadObject(bucket, key, path).toString();
        }
    }

    @ShellComponent
    @ShellCommandGroup("batch")
    public class BatchCommands {
        @ShellMethod(value = "批量上传对象\n" +
            "\t用法 : --batch-upload bucket 本地文件夹路径", key = "--batch-upload")
        public String batchUpload(String bucket, String path) {
            return oss.batchUpload(bucket, path).toString();
        }

        @ShellMethod(value = "批量下载对象\n" +
            "\t用法 : --batch-download bucket 本地下载路径 prefix(默认为 \"\")", key = "--batch-download")
        public String batchDownload(String bucket,
                                    String path,
                                    @ShellOption(defaultValue = ShellOption.NULL) String prefix) {
            return oss.batchDownload(bucket, prefix, path).toString();
        }

        @ShellMethod(value = "批量删除对象\n" +
            "\t用法 : --batch-delete bucket prefix(默认为 \"\")", key = "--batch-delete")
        public String batchDelete(String bucket,
                                  @ShellOption(defaultValue = ShellOption.NULL) String prefix) {
            return oss.batchDelete(bucket, prefix).toString();
        }
    }

}
