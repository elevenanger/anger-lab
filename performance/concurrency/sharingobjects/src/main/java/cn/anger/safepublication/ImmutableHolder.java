package cn.anger.safepublication;

import cn.anger.annotation.Immutable;

/**
 * @author : anger
 */
@Immutable
public class ImmutableHolder {
    private final int hold;
    public ImmutableHolder(int hold) {
        this.hold = hold;
    }
}
