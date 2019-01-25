import java.util.*;

public class LyricVerse {
    private ArrayList<LyricLine> listOfLines;
    private String displayName;

    public LyricVerse() {
        listOfLines = new ArrayList<LyricLine>();
    }

    public LyricLine createLine(LyricHead head) {
        LyricLine newLine = new LyricLine(head);
        listOfLines.add(newLine);
        return newLine;
    }

    public LyricLine createLine(int index, LyricHead head) {
        LyricLine newLine = new LyricLine(head);
        listOfLines.add(index, newLine);
        return newLine;
    }

    public void addLine(LyricLine newLine) {
        listOfLines.add(newLine);
    }

    public void addLine(int index, LyricLine newLine) {
        listOfLines.add(index, newLine);
    }

    public String getTaggedText(int indent, LyricHead head) {
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
            result += "\n\n" + line.getTaggedText(indent+1, head);
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
}