import java.util.Map;
import java.util.Queue;

public class CounterThread implements Runnable {
    private Helper helper;
    private Queue<String> dataQueue;
    private Map<String, Integer> valueMap;


    public CounterThread(Helper helper, Map<String, Integer> valueMap, Queue<String> dataQueue) {
        this.helper = helper;
        this.dataQueue = dataQueue;
        this.valueMap = valueMap;
    }


    @Override
    public void run() {
        while (!dataQueue.isEmpty()) {
            String line = dataQueue.poll();
            if (line != null) {
                String[] words = helper.convertTokens(line);
                String[] legalWords = helper.filterTokens(words);
                String[] lowerCaseWords = helper.convertToLowerCase(legalWords);
                for (String word : lowerCaseWords) {
                    helper.addToMap(valueMap, word);
                }
            }
        }
    }
}
