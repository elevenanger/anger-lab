package labutils.pubandescape.innerclassescape;

import org.junit.jupiter.api.Test;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class ListenerTest {
    private static final int SYNTHETIC = 0x00001000;
    private static final int FINAL = 0x00000010;
    private static final int SYNTHETIC_AND_FINAL = SYNTHETIC | FINAL;
    private static boolean checkModifier(int modifier) {
        return modifier == SYNTHETIC_AND_FINAL;
    }
    private static Object getExternalClass(Object target) throws NoSuchFieldException {
        return getField(target, null, null);
    }
    private static Object getField(Object target, String name, Class classCache)
        throws NoSuchFieldException {
        if (classCache == null)
            classCache = target.getClass();
        if (name == null || name.isEmpty()) {
            name = "this$0";
        }

        Field field = classCache.getDeclaredField(name);
        field.setAccessible(true);

        if (checkModifier(field.getModifiers())) {
            try {
                return field.get(target);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return getField(target, name + "$", classCache);
    }
    @Test
    void testThisEscape() throws NoSuchFieldException {
        EventSource source = new EventSource();
        ThisEscape thisEscape = new ThisEscape(source);

        ThisEscape escapedThis = (ThisEscape) getExternalClass(source.getListener());
        assertSame(thisEscape, escapedThis);
    }

    @Test
    void testSafeListener() throws NoSuchFieldException {
        EventSource source = new EventSource();
        SafeListener safeListener = SafeListener.newInstance(source);

        SafeListener safeListener1 = (SafeListener) getExternalClass(source.getListener());
        assertSame(safeListener, safeListener1);
    }
}