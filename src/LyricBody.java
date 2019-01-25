import java.util.*;

public class LyricBody {
    private ArrayList<LyricVerse> listOfVerses;
    private LyricHead head;

    public LyricBody(LyricHead head) {
        this.head = head;
        listOfVerses = new ArrayList<LyricVerse>();
    }

    public LyricBody(ArrayList<String> languages, ArrayList<LyricCategory> categories) {
        head = new LyricHead(languages, categories);
        listOfVerses = new ArrayList<LyricVerse>();
    }

    public LyricBody() {
        head = new LyricHead();
        listOfVerses = new ArrayList<LyricVerse>();
    }

    public LyricHead getHead() {
        return head;
    }

    public LyricVerse createVerse() {
        return createVerse(listOfVerses.size());
    }

    public LyricVerse createVerse(int index) {
        LyricVerse newVerse = new LyricVerse();
        listOfVerses.add(index, newVerse);
        return newVerse;
    }

    public LyricLine createLine(int verse) {
        return createLine(verse, listOfVerses.size());
    }

    public LyricLine createLine(int verse, int index) {
        return listOfVerses.get(verse).createLine(index, head);
    }

    public LyricLine createLine(LyricVerse verse) {
        return createLine(verse, listOfVerses.size()-1);
    }

    public LyricLine createLine(LyricVerse verse, int index) {
        if (!listOfVerses.contains(verse)) {
            throw new IllegalArgumentException("Verse \" " + verse.getName() + "\" not found in body.");
        }
        return verse.createLine(index, head);
    }

    public void addVerse(LyricVerse newVerse) {
        listOfVerses.add(newVerse);
    }

    public void addVerse(int index, LyricVerse newVerse) {
        listOfVerses.add(index, newVerse);
    }

    public ArrayList<LyricVerse> getVerses() {
        return listOfVerses;
    }

    public LyricVerse getVerse(int index) {
        return listOfVerses.get(index);
    }

    public ArrayList<LyricLine> getLines(int verse) {
        return getVerse(verse).getLines();
    }

    public LyricLine getLine(int verse, int line) {
        return getVerse(verse).getLine(line);
    }

    public String getAsTagged(int indent) {
        String result = head.getAsTagged(indent) + "\n";
        for (LyricVerse verse : getVerses()) {
            result += verse.getTaggedText(indent, head) + "\n";
        }
        return result;
    }

    public String getAsTagged() {
        return getAsTagged(0);
    }


    // TODO: Implement the following methods
    // public void duplicateVerse(int originIndex, int newIndex) {};
    // public static LyricBody parseTextToBody(String originalLines) {}
}