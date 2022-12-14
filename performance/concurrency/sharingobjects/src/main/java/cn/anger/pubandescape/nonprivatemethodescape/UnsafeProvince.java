package cn.anger.pubandescape.nonprivatemethodescape;

import cn.anger.annotation.NotThreadSafe;

/**
 * @author : anger
 * 非 private 方法也会发布返回的对象
 * 如果这个对象是可变的
 * 内部可变状态会逃逸出去被外部修改
 */
@NotThreadSafe
public class UnsafeProvince {
    private String[] provinces = new String[] {
        "Hunan", "Guangdong", "Fujian"
    };
    public String[] getProvinces() {
        return provinces;
    }
}
