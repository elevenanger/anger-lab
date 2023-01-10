package stopthreadbasedservice.limitationofshutdownnow;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * @author : anger
 */
public class SimpleWebCrawler extends WebCrawler {
    SimpleWebCrawler(URL startURL) {
        super(startURL);
    }

    @Override
    protected List<URL> processPage(URL url) {
        String urlHost = url.toString();
        System.out.println("Crawling url => " + urlHost);
        try {
            return Arrays.asList(
                new URL(urlHost + ".1"),
                new URL(urlHost + ".2"),
                new URL(urlHost + ".3"),
                new URL(urlHost + ".4"),
                new URL(urlHost + ".4")
            );
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
