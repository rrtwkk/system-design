import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * https://www.youtube.com/watch?v=krsuaUp__pM
 * It uses a breadth-first search (BFS) algorithm to crawl the web.
 * It is a simple implementation and is not optimized for performance.
 * It is not a complete web crawler and is not suitable for production use.
 * It is a simple implementation and is not optimized for performance.
 * 
 * Design Link: https://app.excalidraw.com/l/56zGeHiLyKZ/9hC265dNFoo
 */
public class WebCrawler {
    // Keep track of which URLs we've already seen
    private Set<String> visited = new HashSet<>();

    /**
     * Crawl starting from startUrl, up to maxDepth layers deep.
     * Returns a list of all URLs visited (including the startUrl).
     */
    public List<String> crawl(String startUrl, int maxDepth) {
        List<String> result = new ArrayList<>();
        Queue<UrlDepthPair> queue = new LinkedList<>();
        queue.offer(new UrlDepthPair(startUrl, 0));
        visited.add(startUrl);

        while (!queue.isEmpty()) {
            UrlDepthPair current = queue.poll();
            result.add(current.url);

            // Don't expand further if we've reached maxDepth
            if (current.depth >= maxDepth)
                continue;

            // Fetch and parse outgoing links
            for (String link : fetchLinks(current.url)) {
                if (visited.add(link)) { // add returns false if already present
                    queue.offer(new UrlDepthPair(link, current.depth + 1));
                }
            }
        }

        return result;
    }

    /**
     * Fetches the HTML of the given URL and returns a set of all absolute
     * HTTP/HTTPS links found.
     * Uses a simple regex—good enough for an interview demo.
     */
    private Set<String> fetchLinks(String urlStr) {
        Set<String> links = new HashSet<>();
        Pattern hrefPattern = Pattern.compile(
                "href\\s*=\\s*\"(http[s]?://[^\"]+)\"",
                Pattern.CASE_INSENSITIVE);

        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(3000);
            conn.setReadTimeout(3000);

            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Matcher m = hrefPattern.matcher(line);
                    while (m.find()) {
                        links.add(m.group(1));
                    }
                }
            }
        } catch (IOException e) {
            // In an interview you might log or ignore bad URLs
        }

        return links;
    }

    // Simple pair to track URL + its depth in the BFS
    private static class UrlDepthPair {
        String url;
        int depth;

        UrlDepthPair(String u, int d) {
            url = u;
            depth = d;
        }
    }

    // --- Example usage ---
    public static void main(String[] args) {
        WebCrawler crawler = new WebCrawler();
        List<String> visited = crawler.crawl("https://www.google.com/search?q=what+is+AI", 2);
        visited.forEach(System.out::println);
    }
}
