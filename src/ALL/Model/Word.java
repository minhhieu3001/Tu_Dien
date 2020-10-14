package ALL.Model;

public class Word {
    //    private int Index;
    private String word_target;
    private String word_explain;

    public Word() {
        word_explain ="";
        word_target = "";
    }

    public Word(String english) {
        word_target = english;
        word_explain = "";
    }

    public Word(String english, String vietnam) {
        word_explain = vietnam;
        word_target = english;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_target() {
        return word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

}