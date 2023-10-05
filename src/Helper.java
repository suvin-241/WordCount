import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Helper {
    public String[] convertTokens(String input) {
        return input.split(Constants.SPACEREGEX);
    }


    public String[] filterTokens(String[] words) {
        List<String> filteredList = new ArrayList<>();
        for (String word : words) {
            if (word.matches(Constants.SPECIALREGEX)) {
                filteredList.add(word);
            }
        }
        return filteredList.toArray(new String[filteredList.size()]);
    }


    public String[] convertToLowerCase(String[] words) {
        String[] filteredList = new String[words.length];
        for (int i = 0; i < words.length; i++) {
            filteredList[i] = words[i].toLowerCase();
        }
        return filteredList;
    }

    public synchronized void addToMap(Map<String, Integer> valueMap, String word) {
        if (valueMap.containsKey(word)) {
            valueMap.put(word, valueMap.get(word) + 1);
        } else {
            valueMap.put(word, 1);
        }
    }
}
