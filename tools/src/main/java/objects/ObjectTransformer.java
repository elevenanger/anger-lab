package objects;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * author : liuanglin
 * date : 2022/7/2 20:24
 * description : 对象转换工具类
 */
public final class ObjectTransformer {

    private ObjectTransformer(){}
    static class TransferImpl<T> implements Supplier<T> {

        private final T targetObject;
        public TransferImpl(T targetObject) {
            this.targetObject = targetObject;
        }

        @Override
        public T get() {
            return targetObject;
        }
    }

    public static <S, T>  T fromSourceToTargetObject(final S source, final Supplier<T> targetGen) {
        BiFunction<S , Supplier<T>, T> tranFunc =
            (s, tSupplier) -> {
                T target = targetGen.get();
                BeanUtils.copyProperties(s,target);
                return target;
            };
        return new TransferImpl<>(tranFunc.apply(source, targetGen)).get();
    }

    public static <S, T> List<T> batchTrans(final List<S> sourceList, final Supplier<T> tarGen) {
        return sourceList.stream()
            .map(source -> fromSourceToTargetObject(source, tarGen))
            .collect(Collectors.toList());
    }
}
