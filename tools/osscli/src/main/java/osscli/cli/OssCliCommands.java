package osscli.cli;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import osscli.services.Oss;
import osscli.services.OssFactory;
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
    public void ossInit(String type, String endPoint) {
        oss = OssFactory.getInstance(new OssConfiguration()
                                        .withEndPoint(endPoint)
                                        .withType(Oss.Type.valueOf(type.toUpperCase())));
    }

    @ShellMethod("获取一个桶中所有的对象的 key ")
    public String listAll(String bucket) {
        return oss.listAllObjects(bucket, "").toString();
    }

    @ShellMethod("上传文件到指定桶")
    public String uploadFile(String bucket, String localFilePath) {
        return oss.putObject(bucket, new File(localFilePath)).getETag();
    }

    public Availability ossInstanceCheck() {
        return oss != null ?
            Availability.available() :
            Availability.unavailable("必须初始化 oss 实例");
    }

}
