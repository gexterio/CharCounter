package ua.com.foxminded.UniqueCharCounter;

//        Write an application that takes a string and returns the number of unique characters in the string.
//        It is expected that a string with the same character sequence may be passed several times to the method.
//        Since the counting operation can be time-consuming, the method should cache the results,
//        so that when the method is given a string previously
//        encountered, it will simply retrieve the stored result. Use collections and maps where appropriate.

import java.util.HashMap;
import java.util.Map;

public class CharCounter {
     private int MAX_SIZE;

     private final Map<String, Map<Character, Integer>> resultsCash = new CharCounterCash<>(MAX_SIZE);


    public CharCounter(int maxSize) {
        MAX_SIZE = maxSize;
    }

    public Map<Character, Integer> uniqueCharCounter(String text) {
        if (isStringCashed(text)) {
            return ResultFromCash(text);
        } else {
            char[] chars = text.toLowerCase().toCharArray();
            Map<Character, Integer> result = uniqueCharCounterForNewString(chars);
            addResultToCash(text, result);
            return result;
        }
    }

    private void addResultToCash(String key, Map<Character, Integer> value) {
        resultsCash.put(key, value);
        System.out.println("Cash:" + resultsCash);
    }

    private Map<Character, Integer> ResultFromCash(String text) {
        return resultsCash.get(text);
    }

    private boolean isStringCashed(String text) {
        boolean isCashed = resultsCash.containsKey(text);
        System.out.println("isCashed = " + isCashed);
        return isCashed;
    }

    private Map<Character, Integer> uniqueCharCounterForNewString(char[] chars) {
        Map<Character, Integer> charsCountMap = new HashMap<>(50);
        for (int i = 0; i < chars.length - 1; i++) {
            if (!charsCountMap.containsKey(chars[i])) {
                charsCountMap.put(chars[i], 1);
            } else {
                int count = charsCountMap.get(chars[i]);
                charsCountMap.put(chars[i], ++count);
            }
        }
        return charsCountMap;
    }


}
