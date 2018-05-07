package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Bag extends HashMap<String, Integer> {

    private HashMap<String, Integer> universe;
    private ArrayList<String> vocabulary;

    Bag() {
        this.universe = new HashMap<>();
        this.vocabulary = new ArrayList<>();
    }

    @Override
    public Integer put(String key, Integer value) {
        return super.put(key, value);
    }

    void foundTag(String tag){
        Integer i = universe.get(tag);
        if (i == null){
            universe.put(tag, 1);
        }
        else {
            i++;
            universe.put(tag, i);
        }
    }

    void foundWord(String word){
        if (!vocabulary.contains(word)){
            vocabulary.add(word);
        }
    }

    private String getTagFromPair(String key){
        String[] cleanWord = key.split("_");
        return cleanWord[1];
    }

    Set<String> getUniverseKeySet(){
        return universe.keySet();
    }

    int getUniverseCountFor(String key){
        return universe.get(key);
    }

    int getUniverseCountForPair(String pair){
        return universe.get(getTagFromPair(pair));
    }

    int getVocabularySize(){
        return vocabulary.size();
    }

    int getUniverseSize(){
        return universe.size();
    }

    void destroyUniverse(){
        this.clear();
        this.universe.clear();
        this.vocabulary.clear();
    }

}
