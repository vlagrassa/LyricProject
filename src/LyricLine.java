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


  // =-=-= Strings =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = ">Line<\n";
        for (String lang : getLanguages()) {
            result += "\t@" + lang + ": " + getAsPlaintext(lang) + "\n";
        }
        return result;
    }

    public String getAsPlaintext(String key) {
        return bracketedtexts.get(key).replace("[", "").replace("]", "");
    }

    public String getAsBracketed(String key) {
        return getBracketedText(key);
    }

    public String getAsHTML() {
        Integer indent = 1;
        String result = ">Line<\n";
        for (String lang : getLanguages()) {
            for (int i = 0; i < indent; i++) {
                result += "\t";
            }
            result += "@" + lang + ": ";
            result += formatLangBody(lang, "#%s[");
            result += "\n";
        }
        return result;
    }

    private String formatLangBody(String key, String headerTemplate) {
        // Initialize final text as copy of the bracketed text
        StringBuilder textSB = new StringBuilder(getBracketedText(key));

        // Initialize list to accumulate all the changes to the reference string
        ArrayList<LyricInsertion> insertions = new ArrayList<LyricInsertion>();

        // Make sure all slices have the proper headers
        genHeadersAndClosers(headerTemplate);

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

    public void genHeadersAndClosers(String headerTemplate) {
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
            slice.setCloser("]");
        }
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