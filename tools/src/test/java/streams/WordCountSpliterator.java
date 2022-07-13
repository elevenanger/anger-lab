package streams;

import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * author : liuanglin
 * date : 2022/7/13 08:31
 * description : 单词分片计数器
 */
public class WordCountSpliterator implements Spliterator<Character> {

    private final String string;
    private int currentChar = 0;

    public WordCountSpliterator(String string) {
        this.string = string;
    }

    @Override
    public boolean tryAdvance(Consumer<? super Character> action) {
        action.accept(string.charAt(currentChar++));
        // 如果还有字符需要处理，则返回为 true
        return currentChar < string.length();
    }

    @Override
    public Spliterator<Character> trySplit() {
        int currentSize = string.length() - currentChar;
        // 返回 null 表示要处理的 string 已经足够小，可以顺序处理，无需继续拆分
        if (currentSize < 10) return null;
        // 将试探拆分的位置设定为 string 的中间位置
        for (int splitPos = currentSize / 2 + currentChar;
                splitPos < string.length(); splitPos ++) {
            // 将拆分位置前进至下一个空格的位置
            if (Character.isWhitespace(string.charAt(splitPos))) {
                // 创建一个新的 Spliterator 来解析 string 从开始到拆分位置的部分
                Spliterator<Character> spliterator =
                    new WordCountSpliterator(string.substring(currentChar, splitPos));
                // 将这个 Spliterator 的起始位置设置为拆分的位置
                currentChar = splitPos;
                return spliterator;
            }
        }
        return null;
    }

    @Override
    public long estimateSize() {
        return string.length() - currentChar;
    }

    @Override
    public int characteristics() {
        return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
    }

    public static void main(String[] args) {
        String sentence = "hello darkness my old friend  extraordinary";
        Spliterator<Character> spliterator = new WordCountSpliterator(sentence);
        Stream<Character> stream = StreamSupport.stream(spliterator, true);
        System.out.printf("found words:%s", stream.reduce(
                                            new WordCounter(0, true),
                                            WordCounter::accumulate,
                                            WordCounter::combine).getCounter());
    }
}

class WordCounter {
    private final int counter;
    private final boolean lastSpace;

    public WordCounter(int counter, boolean lastSpace) {
        this.counter = counter;
        this.lastSpace = lastSpace;
    }

    public WordCounter accumulate(Character c) {
        if (Character.isWhitespace(c))
            return lastSpace ? this : new WordCounter(counter, true);
        else
            return lastSpace ? new WordCounter(counter + 1, false) : this;
    }

    public WordCounter combine(WordCounter wordCounter) {
        return new WordCounter(counter + wordCounter.counter
                                , wordCounter.lastSpace);
    }

    public int getCounter() {
        return counter;
    }
}