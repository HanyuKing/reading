
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * @Author Hanyu.Wang
 * @Date 2024/3/27 15:00
 * @Description
 * @Version 1.0
 **/
public class VirtualThread {
    public static void main(String[] args) throws MalformedURLException {
        String result = new VirtualThread().handle();
        System.out.printf(result);
    }

    String handle() throws MalformedURLException {
        var url1 = URI.create("https://www.baidu.com/s?wd=a").toURL();
        var url2 = URI.create("https://www.baidu.com/s?wd=b").toURL();

        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            var future1 = executor.submit(() -> fetchURL(url1));
            var future2 = executor.submit(() -> fetchURL(url2));
            return future1.get() + future2.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    String fetchURL(URL url) throws IOException {
        try (var in = url.openStream()) {
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
