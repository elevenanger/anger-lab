package stopthreadbasedservice.limitationofshutdownnow;

import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : anger
 */
class SimpleWebCrawlerTest {

    @Test
    void webCrawlerTest() throws MalformedURLException {
        SimpleWebCrawler crawler = new SimpleWebCrawler(new URL("https://anger.cn"));
        crawler.start();
    }
}