package app;

import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

class MLMagic implements Parser.ParserResults {

    private MLLog logger;
    private Bag bagOfWords;

    public MLMagic(MLLog logger, Bag bagOfWords) {
        this.logger = logger;
        this.bagOfWords = bagOfWords;
    }

    void summary(){
        SortedSet<String> sortedSet = new TreeSet<>(bagOfWords.keySet());
        for (String pair : sortedSet) {
            String[] pairS = pair.split("_");
            logger.mlLog(String.format("P(%s | %s) = %s/%s", pairS[0], pairS[1], bagOfWords.get(pair), bagOfWords.getUniverseCountForPair(pair)));
        }
        logger.mlLog("- - - - - - - - -");
        logger.mlLog("Vocabulary size: " + bagOfWords.getVocabularySize());
        logger.mlLog("Universe size: " + bagOfWords.getUniverseSize());
        int sum = 0;
        for (String key : bagOfWords.getUniverseKeySet()){
            sum += bagOfWords.getUniverseCountFor(key);
        }
        for (String key : bagOfWords.getUniverseKeySet()){
            logger.mlLog(String.format("P(%s) = %s/%s", key, bagOfWords.getUniverseCountFor(key), sum));
        }
    }


    @Override
    public void correctPair(String word, String tag) {
        String key = word + "_" + tag;
        Integer i = bagOfWords.get(key);
        if (i == null){
            bagOfWords.put(key, 1);
        }
        else {
            i++;
            bagOfWords.put(key, i);
        }
    }

    @Override
    public void tagFound(String tag) {
        bagOfWords.foundTag(tag);
    }

    @Override
    public void wordFound(String word) {
        bagOfWords.foundWord(word);
    }

    @Override
    public void errorFound(String message) {
        logger.mlError(message);
    }

    public interface MLLog {
        void mlLog(String logMessage);

        void mlError(String errorMessage);
    }

}
