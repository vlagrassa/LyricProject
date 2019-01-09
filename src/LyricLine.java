import java.text.ParseException;
import java.util.*;

public class LyricLine {
  // =-=-= Usage Info =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Note that the Line doesn't explicitly store the list of languages,
    // so it doesn't have them ordered - to produce them in proper order,
    // have to invoke each one manually from a higher class.


  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * A map from language strings to a bracketed text string representing
     * the line in that language.
     */
    private HashMap<String,String> bracketedtexts;

    /**
     * A list of the {@code LyricSlice} objects in the line.
     */
    private ArrayList<LyricSlice> slices;

    public enum textStyle {
        plain,
        bracketed,
        tagged
    }

    /**
     * A display name to identify the line by.
     */
    private String displayName;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Initialize a new {@code LyricLine} object with a set of languages to
     * be stored.
     * 
     * @param languages List of languages used in the line.
     */
    public LyricLine(String... languages) {

        // Inititalize bracketedtext with an empty string for each of the passed languages
        bracketedtexts = new HashMap<String,String>();
        for (String lang : languages) {
            bracketedtexts.put(lang, "");
        }

        // Initialize the array of slices
        slices = new ArrayList<LyricSlice>();
    }


  // =-=-= Plain Text =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Get the plain text from texts for the given language
    public String getBracketedText(String key) {
        return bracketedtexts.get(key);
    }

    public String getBracketedText() {
        String result = "";
        for (String lang : getLanguages()) {
            result += lang + ": " + getBracketedText(lang) + "\n";
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }

    public String setBracketedText(String key, String val) {
        return bracketedtexts.put(key, val);
    }

    // // Get the full bracketedtext map
    public HashMap<String,String> getBracketedTextMap() {
        return bracketedtexts;
    }

    public HashMap<String,String> setBracketedTextMap(HashMap<String,String> newmap) {
        HashMap<String,String> temp = bracketedtexts;
        bracketedtexts = newmap;
        return temp;
    }

    public Set<String> getLanguages() {
        return bracketedtexts.keySet();
    }

    public void addLanguage(String newLanguage) {
        addLanguages(newLanguage);
    }

    public void addLanguages(String... newLanguages) {
        for (String language : newLanguages) {
            bracketedtexts.put(language, "");
        }
        for (LyricSlice slice : slices) {
            slice.addLanguages(newLanguages);
        }
    }

    public void addToBracketedText(String key, String str, Integer index) {
        String origtext = bracketedtexts.get(key);
        bracketedtexts.put(key, origtext.substring(0, index) + str + origtext.substring(index));
        for (LyricSlice slice : slices) {
            slice.matchUpdatedReference(key, index, str.length());
        }
    }

    public String removeUnlessBrackets(String origtext, Integer length, Integer index) {
        while (length > 0 || index > origtext.length()) {
            if ("[]".contains(origtext.substring(index,index+1))) {
                index++;
            } else {
                origtext = origtext.substring(0, index) + origtext.substring(index+1);
                length--;
            }
        }
        return origtext;
    }

    public void deleteFromBracketedText(String key, Integer length, Integer index) {
        bracketedtexts.put(key, removeUnlessBrackets(bracketedtexts.get(key), length, index));
        for (LyricSlice slice : slices) {
            slice.matchUpdatedReference(key, index, -length);
        }
    }


  // =-=-= Lyric Slices =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the list of {@code LyricSlice} objects associated with this
     * line. If an {@code Integer} argument is provided, will return a
     * list of only the slices matching that category, serving as a way
     * to filter them.
     * 
     * @return The full list of {@code LyricSlice} objects.
     */
    public ArrayList<LyricSlice> getSlices() {
        return slices;
    }

    /**
     * Get the list of {@code LyricSlice} objects associated with this
     * line with a category matching the passed {@code Integer} value.
     * If no argument is provided, will return a list of all the slices
     * associated with this line, regardless of category.
     * 
     * @see {@link LyricSlice#isCategory(Integer)}
     * 
     * @param cat The category to filter by.
     * @return The filtered list of {@code LyricSlice} objects.
     */
    public ArrayList<LyricSlice> getSlices(Integer cat) {
        ArrayList<LyricSlice> result = new ArrayList<LyricSlice>();
        for (LyricSlice s : slices) {
            if (s.isCategory(cat)) {
                result.add(s);
            }
        }
        return result;
    }

    /**
     * Create a new {@code LyricSlice} object in the line with the given
     * category and annotation values. If either or both fields are omitted,
     * they will be filled with their default values of {@code null} and
     * {@code ""}, respectively.
     * 
     * @param category   The category of the new slice.
     * @param annotation The annotation for the new slice.
     * @return The newly created {@code LyricSlice}.
     */
    public LyricSlice createSlice(Integer category, String annotation) {
        LyricSlice newSlice = new LyricSlice(bracketedtexts, slices, category, annotation);
        slices.add(newSlice);
        return newSlice;
    }

    /**
     * Create a new {@code LyricSlice} object in the line with the given
     * category value and the default {@code ""} annotation value. If the
     * category field is omitted, it will be filled with its default value
     * of {@code null}.
     * 
     * @see {@link #createSlice(Integer, String)}
     * 
     * @param category The category of the new slice.
     * @return The newly created {@code LyricSlice}.
     */
    public LyricSlice createSlice(Integer category) {
        return createSlice(category, "");
    }

    /**
     * Create a new {@code LyricSlice} object in the line with the given
     * annotation value and the default {@code null} category value. If the
     * annotation field is omitted, it will be filled with its default value
     * of {@code ""}.
     * 
     * @see {@link #createSlice(Integer, String)}
     * 
     * @param annotation The annotation for the new slice.
     * @return The newly created {@code LyricSlice}.
     */
    public LyricSlice createSlice(String annotation) {
        return createSlice(null, annotation);
    }

    /**
     * Create a new {@code LyricSlice} object in the line with the default
     * category and annotation values of {@code null} and {@code ""}, respectively.
     * Specific values for either or both of these may also be passed as
     * arguments to the function.
     * 
     * @see {@link #createSlice(Integer, String)}
     * 
     * @return The newly created {@code LyricSlice}.
     */
    public LyricSlice createSlice() {
        return createSlice(null, "");
    }

    public void mergeSlices(LyricSlice mainSlice, LyricSlice... otherSlices) {
        for (LyricSlice slice : otherSlices) {
            mainSlice.mergeSlice(slice);
            removeSlice(slice);
        }
    }

    public LyricSlice removeSlice(LyricSlice slice) {
        for (String lang : getLanguages()) {
            slice.setCoordsUpdated(lang, null, null);
        }
        slices.remove(slice);
        return slice;
    }

    public ArrayList<LyricCoords> getCoordsListCopy(String key) {
        ArrayList<LyricCoords> coordsList = new ArrayList<LyricCoords>();
        for (LyricSlice slice : slices) {
            if (slice.getCoords(key).isContinuous()) {
                coordsList.add(new LyricCoords(slice.getCoords(key)));
            } else {
                coordsList.add(new LyricCoordsDiscontinuous(slice.getCoords(key)));
            }
        }
        return coordsList;
    }

    public ArrayList<LyricCoords> getCoordsList(String key) {
        ArrayList<LyricCoords> coordsList = new ArrayList<LyricCoords>();
        for (LyricSlice slice : slices) {
            coordsList.add(slice.getCoords(key));
        }
        return coordsList;
    }

    private ArrayList<String> getCategoryStrList() {
        ArrayList<String> categoryList = new ArrayList<String>();
        for (LyricSlice slice : slices) {
            categoryList.add(slice.getCategoryStr());
        }
        return categoryList;
    }

    public LyricSlice getSliceWithID(String id) {
        for (LyricSlice slice : slices) {
            if (slice.getHeader().equals(id)) {
                return slice;
            }
        }
        return null;
    }

    public void mergeSlicesWithSameID() {
        for (int i = 0; i < slices.size(); i++) {
            for (int j = i+1; j < slices.size(); j++) {
                if (slices.get(i) != slices.get(j) && slices.get(i).getHeader().equals(slices.get(j).getHeader())) {
                    mergeSlices(slices.get(i), slices.get(j));
                }
            }
        }
    }


  // =-=-= Line Name =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    /**
     * Get the line's display name, if it has one. If it doesn't, returns
     * the empty string {@code ""}.
     * 
     * @return The display name.
     */
    public String getName() {
        return hasName() ? displayName : "";
    }

    /**
     * Get a name to display for the line. If the line already has a name,
     * that name will be returned; if it does not, a temporary name will
     * be filled in using the plaintext associated with the provided
     * language string.
     * 
     * @param key The plaintext to fall back on if no name exists.
     * 
     * @return A non-empty name to display.
     */
    public String createDisplayName(String key) {
        return hasName() ? displayName : getPlainText(key);
    }

    /**
     * Get a name to display for the line, and cut it off to fit within
     * the given length if necessary. If the name is cut off, an ellipsis
     * (Unicode value {@code \u2026}) will be added to the end.
     * 
     * If the line already has a name, that name will be used; if it does
     * not, a temporary name will be filled in using the plaintext
     * associated with the provided language string.
     * 
     * @param key The plaintext to fall back on if no name exists.
     * @param maxlength The maximum length of the name.
     * 
     * @return A non-empty name to display.
     */
    public String createDisplayNameClipped(String key, Integer maxlength) {
        String result = createDisplayName(key);
        if (result.length() > maxlength) {
            return result.substring(0, maxlength - 1).trim() + "\u2026";
        } else {
            return result;
        }
    }

    /**
     * Return {@code true} if the line has a display name, and {@code false}
     * otherwise. May also be called with a boolean argument to check whether
     * the result matches that value.
     * 
     * @return Whether the line has a name value.
     */
    public Boolean hasName() {
        return displayName != null;
    }

    /**
     * Check whether the line currently has a display name, and return
     * {@code true} if this value matches the passed value.
     * 
     * @param val The expected value of {@code hasName()}.
     * @return Whether the value matches or not.
     */
    public Boolean hasName(Boolean val) {
        return hasName() == val;
    }

    /**
     * Set the display name of the line.
     * 
     * @param newname The new display name for the line.
     */
    public void setName(String newname) {
        displayName = newname;
    }

    /**
     * Set the display name of the line if it does not currently have a
     * value; otherwise, leave it unchanged. Returns {@code true} if the
     * name is changed, and false if it is not.
     * 
     * @param newname The new display name for the line.
     * @return Whether the name is changed.
     */
    public Boolean setNameIfEmpty(String newname) {
        if (hasName(false)) {
            setName(newname);
            return true;
        }
        return false;
    }


  // =-=-= Strings =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = ">Line<\n";
        for (String lang : getLanguages()) {
            result += "\t@" + lang + ": " + getPlainText(lang) + "\n";
        }
        return result;
    }

    public String getText(String key, textStyle style) {
        switch (style) {
            case bracketed:
                return getBracketedText(key);
            case plain:
                return getPlainText(key);
            case tagged:
                return getTaggedText(key);
            default:
                return "";
        }
    }

    public String getText(textStyle style) {
        switch (style) {
            case bracketed:
                return getBracketedText();
            case plain:
                return getPlainText();
            case tagged:
                return getTaggedText();
            default:
                return "";
        }
    }

    public String getPlainText(String key) {
        return bracketedtexts.get(key).replace("[", "").replace("]", "");
    }

    public String getPlainText() {
        String result = "";
        for (String lang : getLanguages()) {
            result += getPlainText(lang) + "\n";
        }
        if (result.length() > 0) {
            result = result.substring(0, result.length()-1);
        }
        return result;
    }

    public String getTaggedText() {
        return getTaggedText(0);
    }

    public String getTaggedText(String key) {
        return "@" + key + ": " + formatLangBody(key, "#%s");
    }

    public String getTaggedText(Integer indent) {
        String tabs = getTabString(indent);
        String result = tabs.substring(0, tabs.length()-1);
        result += String.format(">Line%s<", hasName() ? " \"" + getName() + "\"" : "");
        for (String lang : getLanguages()) {
            result += "\n" + getTaggedText(lang);
        }
        // TODO: Update temporary code to insert categories
        result += "\n";
        for (int cat = 1; cat <= 3; cat++) {
            result += "\n~Category " + cat + ": ";
            for (LyricSlice slice : getSlices(cat)) {
                result += slice.getHeader() + ", ";
            }
        }
        result = result.replace("\n", "\n" + tabs);
        return result;
    }

    private String getTabString(Integer indent) {
        String tabs = "";
        for (int i = 0; i <= indent; i++) {
            tabs += "\t";
        }
        return tabs;
    }

    private String formatLangBody(String key, String headerTemplate) {
        // Initialize final text as copy of the bracketed text
        StringBuilder textSB = new StringBuilder(getBracketedText(key));

        // Initialize list to accumulate all the changes to the reference string
        ArrayList<LyricInsertion> insertions = new ArrayList<LyricInsertion>();

        // Make sure all slices have the proper headers
        genHeaders(headerTemplate);

        // Add a change for each opening bracket and closing bracket
        for (LyricSlice slice : slices) {
            insertions.addAll(slice.getInsertionList(key));
        }

        // Sort the insertions in reverse order, so that adding them in order works from back to front
        Collections.sort(insertions, Collections.reverseOrder());

        // Add each insertion to the string
        for (LyricInsertion insertion : insertions) {
            insertion.addToText(textSB);
        }

        // Return the final, built up string
        return textSB.toString();
    }

    public void genHeaders(String headerTemplate) {
        ArrayList<String> usedHeaders = new ArrayList<String>();
        for (LyricSlice slice : slices) {
            if (slice.hasHeader()) {
                usedHeaders.add(slice.getHeader());
            } else {
                String newHeader;
                for (int i = 0; true; i++) {
                    newHeader = String.format(headerTemplate, slice.getCategoryStr() + i);
                    if (!usedHeaders.contains(newHeader)) {
                        usedHeaders.add(newHeader);
                        break;
                    }
                }
                slice.setHeader(newHeader);
            }
        }
    }


  // =-=-= Parsing =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public static LyricLine parseTextToLine(String originalLines) throws ParseException {
        LyricLine result = new LyricLine();
        // ID -> (language -> coords)
        HashMap<String, HashMap<String, LyricCoords>> parseMap = new HashMap<String, HashMap<String, LyricCoords>>();
        Boolean slicesAdded = false;
        for (String currentLine : originalLines.split("\n")) {
            currentLine = currentLine.replace("\t", "");
            if (currentLine.startsWith(">Line")) {
                int firstQuote  = currentLine.indexOf("\"") + 1;
                int secondQuote = currentLine.indexOf("\"", firstQuote);
                result.setName(currentLine.substring(firstQuote, secondQuote));
            }
            else if (currentLine.startsWith("@")) {
                Stack<LyricCoords> openCoords = new Stack<LyricCoords>();

                // Separate the language name and the line body
                String[] headAndBody = currentLine.substring(1).split(":", 2);
                String head = headAndBody[0].trim();
                String body = headAndBody[1].trim();
                result.addLanguages(head);

                // Run through character by character, adding to the stack
                for (int i = 0; i < body.length(); i++) {
                    if (body.charAt(i) == '#') {
                        LyricCoords newCoords = new LyricCoords(i, null);
                        String newID = body.substring(i, body.indexOf('[', i));
                        openCoords.push(newCoords);
                        parseMap.putIfAbsent(newID, new HashMap<String, LyricCoords>());
                        parseMap.get(newID).put(head, newCoords);
                        body = body.substring(0, i) + body.substring(i + newID.length());
                    }
                    else if (body.charAt(i) == ']') {
                        try {
                            openCoords.pop().setEnd(i);
                        } catch (EmptyStackException e) {
                            throw new ParseException("Unmatched closing bracket found at index " + i + " in line \"" + currentLine + "\".", i);
                        }
                    }
                }
                
                // If the end is reached and something is still open on the stack, throw a parse error
                if (!openCoords.isEmpty()) {
                    LyricCoords remainder = openCoords.pop();
                    for (String id : parseMap.keySet()) {
                        if (parseMap.get(id).containsValue(remainder)) {
                            throw new java.text.ParseException(String.format("No closing bracket found for slice \"%s\" in line \"%s\".", id, currentLine), remainder.getStart());
                        }
                    }
                    throw new RuntimeException("Error with open LyricCoords stack");
                }

                result.setBracketedText(head, body);
            }
            // ...more cases below
            else {
                if (!slicesAdded) {
                    for (String id : parseMap.keySet()) {
                        LyricSlice newSlice = result.createSlice();
                        newSlice.setHeader(id);
                        newSlice.setCoordsMap(parseMap.get(id));
                        newSlice.matchLanguages(result);
                    }
                    slicesAdded = true;
                }
                if (currentLine.startsWith("~")) {
                    // Parse categories
                    System.out.println("Should add category: " + currentLine);
                }
                else if (currentLine.startsWith("$")) {
                    // Parse annotations
                    System.out.println("Should add annotation: " + currentLine);
                }
            }
        }

        

        return result;
    }
}


class LyricInsertion implements Comparable<LyricInsertion> {
    private String text;
    private Integer index;
    private Integer length;

    public LyricInsertion(String text, Integer index, Integer length) {
        this.text = text;
        this.index = index;
        this.length = length;
    }

    public String getText() {
        return text;
    }

    public void setText(String newtext) {
        text = newtext;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer newindex) {
        index = newindex;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer newlength) {
        length = newlength;
    }

    public Integer getEndIndex() {
        return index + length;
    }

    public void addToText(StringBuilder text) {
        text.replace(getIndex(), getEndIndex(), getText());
    }

    public String toString() {
        return "Insert string \"" + text + "\" at index " + index;
    }

    public int compareTo(LyricInsertion e2) {
        return index.compareTo(e2.index);
    }
}