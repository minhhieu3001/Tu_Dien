package ALL.Model;

import java.io.IOException;

public class DictionaryCommanLine {

    public static Dictionary wordList = new Dictionary();
    public static void showAllWords() {
        int listWidth = 5;
        int EngWidth = 20;
        int VieWidth = 25;
        wordList.showAllWords(listWidth, EngWidth, VieWidth);
    }


    public static void dictionaryBasis() {
        DictionaryManagement.insertFromCommandline(wordList);
        showAllWords();
    }

    public static void dictionaryAdvanced() throws IOException {
        DictionaryManagement.insertFromFile(wordList);
        showAllWords();
    }

    public static void dictionarySearch() {
        DictionaryManagement.dictionarySearch(wordList);
    }

    public static void main(String[] args) throws IOException {
        // dictionaryAdvanced();
        //  DictionaryManagement.dictionaryExportFile(wordList);
        //   dictionarySearch();
        // DictionaryManagement.insertFromFile(wordList);
        DictionaryManagement.readDatabase(wordList);
        System.out.println(Dictionary.word_List.size());
        System.out.println(Dictionary.word_List.get(138475).getWord_target());
//        DictionaryCommanLine.showAllWords();
        System.out.println(Dictionary.dictionaryLookup("adf"));

    }

}