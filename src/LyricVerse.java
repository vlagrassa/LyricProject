import java.text.ParseException;
import java.util.*;

public class LyricVerse extends DisplayNameObject {
    private ArrayList<LyricLine> listOfLines;

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

    public static LyricVerse parseTextToVerse(String originalLines, LyricHead head) throws ParseException {

        // Variables to store final LyricVerse object and section of text for current LyricLine object
        LyricVerse result = new LyricVerse();
        String lineToParse = null;

        // Loop through each line in the input
        for (String currentLine : originalLines.split("\n")) {

            // Parse header line of form `>Verse "name"<`
            if (currentLine.trim().startsWith(">Verse")) {
                int firstQuote  = currentLine.indexOf("\"") + 1;
                int secondQuote = currentLine.indexOf("\"", firstQuote);
                result.setName(currentLine.substring(firstQuote, secondQuote));
            }

            // Add the current LyricLine object and start constructing a new one
            else if (currentLine.trim().startsWith(">Line")) {
                if (lineToParse != null) {
                    result.addLine(LyricLine.parseTextToLine(lineToParse));
                }
                lineToParse = currentLine;
            }

            // Add a regular line
            else {
                lineToParse += "\n" + currentLine;
            }
        }

        // Parse the last LyricLine, which will be left over from the loop, and return the final LyricVerse
        result.addLine(LyricLine.parseTextToLine(lineToParse));
        return result;
    }

    public String getDefaultName(String key) {
        return (listOfLines.size() > 0) ? listOfLines.get(0).getDefaultName(key) : "<Empty Verse>";
    }
}