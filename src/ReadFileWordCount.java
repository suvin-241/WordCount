import java.io.IOException;
import java.util.*;

class ReadFileWordCount {

    private Helper helper;

    public ReadFileWordCount(Helper helper) {
        this.helper = helper;
    }

    public Map<String, Integer> countWords_withoutThread() {
        Map<String, Integer> valueMap = new HashMap<>();

        try (CustomFileIterator customFileIterator = new CustomFileIterator("RawTextFile.txt")) {
            while (customFileIterator.hasNext()) {
                String[] words = helper.convertTokens(customFileIterator.next());
                String[] legalWords = helper.filterTokens(words);
                String[] lowerCaseWords = helper.convertToLowerCase(legalWords);
                for (String word : lowerCaseWords) {
                    helper.addToMap(valueMap, word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Output from Single Thread:\n" + valueMap);

        return null;
    }
}