package app;

import com.oracle.tools.packager.Log;

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
        logger.mlLog("Universe size: " + bagOfWords.getCategoryCount());

        for (String key : bagOfWords.getUniverseKeySet()){
            logger.mlLog(String.format("P(%s) = %s/%s", key, bagOfWords.getUniverseCountFor(key), bagOfWords.getUniverseSize()));
        }
    }

    void classify(String query){
        logger.mlLog(String.format("\nAnalysing %s\n", query));
        String[] queryWords = query.split(" ");
        SortedSet<String> sortedSet = new TreeSet<>(bagOfWords.getUniverseKeySet());
        ArrayList<Double> classSums = new ArrayList<>();
        for (String classifier : sortedSet){
            double logSum = calcPriorProbability(classifier);
            logger.mlLog(String.format("  P(%s) = %s", classifier, logSum));
            for (String word : queryWords){
                word = Parser.removePunctuation(word).trim();
                logSum += calcProbabilityLog(word, classifier);
                logger.mlLog(String.format("+ P(%s | %s) = %s", word, classifier, logSum));
            }
            classSums.add(logSum);
        }
        //todo normalize
    }

    private double calcProbabilityLog(String word, String classifier){
        double numerator = bagOfWords.get(word + "_" + classifier);
        double denominator = (double) bagOfWords.getUniverseCountFor(classifier);
        return Math.log(numerator / denominator);
    }

    private double calcPriorProbability(String classifier){
        double numerator = bagOfWords.getUniverseCountFor(classifier);
        double denominator = bagOfWords.getUniverseSize();
        return Math.log(numerator / denominator);
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
