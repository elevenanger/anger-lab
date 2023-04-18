package labutils.safepublication;

import labutils.annotation.Immutable;

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
