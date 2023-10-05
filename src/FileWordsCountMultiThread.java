import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FileWordsCountMultiThread {
    private static final int THREAD_COUNT = 4;

    private Helper helper;

    FileWordsCountMultiThread(Helper helper){
        this.helper = helper;
    }

    public void countWords_withThread() throws Exception {
        Map<String, Integer> valueMap = new HashMap<>();
        final Queue<String> dataQueue = new ConcurrentLinkedQueue<>();
        new Thread(() -> {
            try (CustomFileIterator customerFileIterator = new CustomFileIterator("RawTextFile.txt")) {
                while (customerFileIterator.hasNext()) {
                    dataQueue.add(customerFileIterator.next());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        while (dataQueue.isEmpty()) {
            Thread.sleep(10);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.execute(new CounterThread(helper, valueMap, dataQueue));
        }
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println("Output from Multithread:\n" + valueMap);
    }
}

