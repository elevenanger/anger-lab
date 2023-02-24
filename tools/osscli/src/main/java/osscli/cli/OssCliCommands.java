package osscli.cli;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import osscli.services.Oss;
import osscli.services.aws.SeqAws;

import java.io.File;

/**
 * @author : anger
 * Oss 命令行方法
 */
@ShellComponent
public class OssCliCommands {

    private final Oss oss = new SeqAws();

    @ShellMethod("设置 OSS endPoint")
    public String setEndpoint(String endPoint) {
        oss.setEndpoint(endPoint);
        return "success";
    }

    @ShellMethod("获取一个桶中所有的对象的 key ")
    public String listAll(String bucket) {
        return oss.listAllObjects(bucket, "").toString();
    }

    @ShellMethod("上传文件到指定桶")
    public String uploadFile(String bucket, String localFilePath) {
        return oss.putObject(bucket, new File(localFilePath)).getETag();
    }

}
