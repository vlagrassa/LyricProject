import java.util.*;

public class LyricVerse {
    //TODO: Move languages and categories to LyricHead object
    private ArrayList<String> listOfLanguages;
    private ArrayList<LyricCategory> listOfCategories;
    private ArrayList<LyricLine> listOfLines;
    private String displayName;

    public LyricVerse() {
        listOfLanguages = new ArrayList<String>();
        listOfCategories = new ArrayList<LyricCategory>();
        listOfLines = new ArrayList<LyricLine>();
    }

    public LyricLine createLine() {
        LyricLine newLine = new LyricLine(listOfCategories, listOfLanguages);
        listOfLines.add(newLine);
        return newLine;
    }

    public LyricLine createLine(int index) {
        LyricLine newLine = new LyricLine(listOfCategories, listOfLanguages);
        listOfLines.add(index, newLine);
        return newLine;
    }

    public void addLine(LyricLine newLine) {
        listOfLines.add(newLine);
    }

    public void addLine(int index, LyricLine newLine) {
        listOfLines.add(index, newLine);
    }

    public String getTaggedText(int indent) {
        // Initialize result string
        String result = "";

        // Add initial tabs
        for (int i = 0; i < indent; i++) {
            result += "\t";
        }

        // Add the header `>Verse "name"<` with the verse name (if it exists)
        result += String.format(">Verse%s<", hasName() ? " \"" + getName() + "\"" : "");

        // Add the tagged text for each line
        for (LyricLine line : listOfLines) {
            result += "\n\n" + line.getTaggedText(indent+1, listOfLanguages);
        }

        // Add final newline and return
        result += "\n";
        return result;
    }

    public Boolean hasName() {
        return displayName != null;
    }

    public String getName() {
        return displayName;
    }

    public void setName(String newName) {
        displayName = newName;
    }

    public void setLanguages(ArrayList<String> newLanguages) {
        listOfLanguages = newLanguages;
    }
}