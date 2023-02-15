package cache.data;

import cache.data.po.EchoPO;

/**
 * author : anger
 * date : 2022/7/6 14:26
 * description : 简单的存储接口
 */
public interface EchoRepository {
    EchoPO saveEcho(String echo);
    String getByEcho(String echo);
}
