package excel.tools;

import org.springframework.beans.BeanUtils;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * author : liuanglin
 * date : 2022/7/2 20:24
 * description : 对象转换工具类
 */
public final class ObjectTransferUtils {

    private ObjectTransferUtils(){ }
    static class TransferImpl<S, T> implements Function<S, T> {

        private final T targetObject;
        public TransferImpl(T targetObject) {
            this.targetObject = targetObject;
        }

        @Override
        public T apply(S s) {
            return this.targetObject;
        }
    }

    public static <S, T>  T fromSourceToTargetObject(final S source, final Supplier<T> targetGen) {
        BiFunction<S , Supplier<T>, T> tranFunc =
            (s, tSupplier) -> {
                T target = targetGen.get();
                BeanUtils.copyProperties(s,target);
                return target;
            };
        return new TransferImpl<>(tranFunc.apply(source, targetGen)).apply(source);
    }

}
