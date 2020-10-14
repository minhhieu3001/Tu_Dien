package ALL.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Dictionary {

    static List<ALL.Model.Word> word_List;

    public Dictionary() {
        word_List = new ArrayList<Word>();
    }

    public static List<Word> getWord_List() {
        return word_List;
    }

    public static void addNewWord(Word newWord) {
        word_List.add(newWord);
    }

    public void showAllWords(int c1, int c2, int c3) {
        String l1 = Integer.toString(c1);
        String l2 = Integer.toString(c2);
        String l3 = Integer.toString(c3);
        String outputFormat = "%-" + l1 + "s| %-" + l2 + "s| %-" + l3 + "s%n";
        System.out.printf(outputFormat, "No", "English", "Vietnamese");
        char[] lineBreak = new char[c1+c2+c3+5];
        Arrays.fill(lineBreak, '-');
        System.out.println(lineBreak);
        outputFormat = "%-" + l1 + "d| %-" + l2 + "s| %-" + l3 + "s%n";
        for (int i = 0; i < word_List.size(); i++) {
            System.out.printf(outputFormat, i + 1, word_List.get(i).getWord_target(), word_List.get(i).getWord_explain());
        }
    }

    public static String dictionaryLookup(String findWord) {
        int left = 0;
        int right = word_List.size()-1;
        while(left <= right) {
            int mid = (left + right)/2;
            int res = findWord.compareToIgnoreCase(word_List.get(mid).getWord_target());
            if(res == 0) {
                return word_List.get(mid).getWord_explain();
            }
            else if(res > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return "Khong co tu nay trong tu dien";
    }

    public void dictionarySearcher(String findWord) {
        String result ="";
        int left = 0;
        int right = word_List.size()-1;
        while(left <= right) {
            int mid = (left + right)/2;
            int res = findWord.compareToIgnoreCase(word_List.get(mid).getWord_target());
            if(res == 0) {
                result =  word_List.get(mid).getWord_explain();
                break;
            }
            else if(res > 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println(result);
    }

    public static ObservableList<String> addToListView(String findWord) {

        int length = findWord.length();
        ObservableList<String> result = FXCollections.observableArrayList();
        for(Word i: word_List) {
            int index = i.getWord_target().indexOf(findWord);
            if(index == 0) {
                result.add(i.getWord_target());
            }
        }
        return result;
    }

    public void removeWord (String word) {
        int j =0, check =0;
        for(Word i : word_List) {
            if(i.getWord_target().equals(word)) {
                check =1;break;
            }
            else {
                j++;
            }
        }
        if(check == 1 ) {
            word_List.remove(j);
        } else System.out.println("Khong co tu day tron tu dien");
    }

    public void ExportFile() {
        try {
            FileWriter fileOut = new FileWriter("E:\\algorithm\\BT_OOP\\src\\DictionaryOut.txt");
            for(Word i : word_List) {
                fileOut.write(i.getWord_target());
                fileOut.append(" ");
                fileOut.write(i.getWord_explain());
                fileOut.append("\n");
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void AddWordToFile(String word_target, String word_explain) {
        try {
            FileWriter file = new FileWriter("src/ALL/Dictionary.txt", true);
            file.append(word_target);
            file.append("\t");
            file.append(word_explain);
            file.append("\n");
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}