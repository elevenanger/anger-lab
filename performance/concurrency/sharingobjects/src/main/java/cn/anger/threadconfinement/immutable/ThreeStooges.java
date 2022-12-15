package cn.anger.threadconfinement.immutable;

import cn.anger.annotation.Immutable;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : anger
 */
@Immutable
public final class ThreeStooges {
    private final Set<String> stooges = new HashSet<>();

    public ThreeStooges() {
        stooges.add("Moe");
        stooges.add("Larry");
        stooges.add("Curly");
    }

    public boolean isStooge(String name) {
        return stooges.contains(name);
    }
}
