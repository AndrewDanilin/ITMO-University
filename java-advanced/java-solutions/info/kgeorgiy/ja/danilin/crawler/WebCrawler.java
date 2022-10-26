package info.kgeorgiy.ja.danilin.crawler;

import info.kgeorgiy.java.advanced.crawler.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.*;

public class WebCrawler implements Crawler {
    private final Downloader downloader;
    private final ExecutorService downloaderService;
    private final ExecutorService extractorService;

    private static final int SERVICE_TIMEOUT = 100;

    public WebCrawler(Downloader downloader, int downloaders, int extractors, int perHost) {
        this.downloader = downloader;
        downloaderService = Executors.newFixedThreadPool(downloaders);
        extractorService = Executors.newFixedThreadPool(extractors);
    }

    @Override
    public Result download(String url, int depth) {
        return new DownloadWorker().processAndGetResult(url, depth);
    }

    private class DownloadWorker {
        private final Set<String> downloaded;
        private final Map<String, IOException> errors;
        private final Phaser phaser;

        public DownloadWorker() {
            this.downloaded = ConcurrentHashMap.newKeySet();
            this.errors = new ConcurrentHashMap<>();
            this.phaser = new Phaser(1);
        }

        public Result processAndGetResult(String url, int depth) {
            // :NOTE: к этой очереди только однопоточный доступ
            Queue<String> urlsToDownload = new ConcurrentLinkedDeque<>();
            urlsToDownload.add(url);
            Queue<String> tempQueue = new ConcurrentLinkedDeque<>();
            for (int i = depth; i >= 1; i--) {
                while (!urlsToDownload.isEmpty()) {
                    String currentUrl = urlsToDownload.poll();
                    if (downloaded.add(currentUrl)) {
                        phaser.register();
                        download(currentUrl, depth, tempQueue);
                    }
                }
                phaser.arriveAndAwaitAdvance();
                urlsToDownload.addAll(tempQueue);
            }
            downloaded.removeAll(errors.keySet());
            return new Result(new ArrayList<>(downloaded), errors);
        }

        private void download(String url, int depth, Queue<String> tempQueue) {
            downloaderService.submit(() -> {
                try {
                    Document document = downloader.download(url);
                    if (depth > 1) {
                        phaser.register();
                        extractorService.submit(extractAction(document, url, tempQueue));
                    }
                } catch (IOException e) {
                    errors.put(url, e);
                } finally {
                    phaser.arriveAndDeregister();
                }
            });
        }

        private Runnable extractAction(Document document, String url, Queue<String> tempQueue) {
            return () -> {
                try {
                    tempQueue.addAll(document.extractLinks());
                } catch (IOException e) {
                    errors.put(url, e);
                } finally {
                    phaser.arriveAndDeregister();
                }
            };
        }
    }

    @Override
    public void close() {
        close(downloaderService);
        close(extractorService);
    }

    private void close(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(SERVICE_TIMEOUT, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public static void main(String[] args) {
        if (!argsAreValid(args)) {
            System.out.println(String.join(
                    System.lineSeparator(),
                    "Arguments aren't valid",
                    "Usage:",
                    "1. url",
                    "2. depth",
                    "3. number of threads to download",
                    "4. number of threads to extract",
                    "5. limit of handles per host"
            ));
        }
        try {
            Result result = new WebCrawler(
                    new CachingDownloader(),
                    Integer.parseInt(args[2]),
                    Integer.parseInt(args[3]),
                    Integer.parseInt(args[4])
            ).download(args[0], Integer.parseInt(args[1]));
        } catch (IOException e) {
            System.err.println("Failed to download: " + e.getMessage());
        }
    }

    // :NOTE: использование подразумевает, что все аргументы, кроме url, опциональные
    // и должны иметь разумные дефолтные значение
    private static boolean argsAreValid(String[] args) {
        if (args == null || args.length != 5) {
            return false;
        }

        for (String arg : args) {
            if (arg == null) {
                return false;
            }
        }
        return true;
    }
}
