package poly.dto;

public class WordDTO {
    private String Word;
    private String WordCount;
    private String project_contents;

    public String getProject_contents() {
        return project_contents;
    }

    public void setProject_contents(String project_contents) {
        this.project_contents = project_contents;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    public String getWordCount() {
        return WordCount;
    }

    public void setWordCount(String wordCount) {
        WordCount = wordCount;
    }
}
