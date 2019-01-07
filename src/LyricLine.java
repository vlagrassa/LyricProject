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
            result += formatLangBody(lang);
            result += "\n";
        }
        return result;
    }

    private String formatLangBody(String key) {
        return insertSliceBounds(key, "#%s[", getCategoryStrList(), "]", null);
    }

    public String insertSliceBounds(String key, String headerTemplate, ArrayList<String> headers, String closerTemplate) {
        return insertSliceBounds(key, headerTemplate, headers, closerTemplate, null);
    }

    public String insertSliceBounds(String key, String headerTemplate, String closerTemplate, ArrayList<String> closers) {
        return insertSliceBounds(key, headerTemplate, null, closerTemplate, closers);
    }

    public String insertSliceBounds(String key, String headerTemplate, String closerTemplate) {
        return insertSliceBounds(key, headerTemplate, null, closerTemplate, null);
    }

    public String insertSliceBounds(String key, String headerTemplate, ArrayList<String> headers, String closerTemplate, ArrayList<String> closers) {
        // Initialize final text as copy of the bracketed text
        StringBuilder textSB = new StringBuilder(getBracketedText(key));

        // Initialize list of coordinates for a given language from each slice as copies, so they can be changed
        ArrayList<LyricCoords> coordsListTemp = getCoordsListCopy(key);
        ArrayList<LyricCoords> coordsList = new ArrayList<LyricCoords>();

        // Set header and closer strings for each set of coords, keeping discontinuous sets together, then add
        // all individual coords (either on their own or contained in a discontinuous set) to the final array
        for (int i = 0; i < coordsListTemp.size(); i++) {
            coordsListTemp.get(i).setHeaderIfEmpty(String.format(headerTemplate, headers != null ? headers.get(i) + i : ""));
            coordsListTemp.get(i).setCloserIfEmpty(String.format(closerTemplate, closers != null ? closers.get(i) + i : ""));
            coordsList.addAll(coordsListTemp.get(i).getCoordsList());
        }

        // Loop through all coordinates
        for (int i = 0; i < coordsList.size(); i++) {
            LyricCoords currentCoords = coordsList.get(i);

            // If one or both of the coordinates is null, skip it
            if (!currentCoords.hasNull()) {

                // Insert the opening and closing character sequences into the string
                currentCoords.insertHeaderAndCloser(textSB);

                // Update each subsequent coordinate set in the (copy) list, to reflect that the new openers and closers have shifted them
                for (int j = i+1; j < coordsList.size(); j++) {
                    coordsList.get(j).matchUpdatedReference(currentCoords.getStart(), currentCoords.getHeader().length() - 1, textSB.length());
                    coordsList.get(j).matchUpdatedReference(currentCoords.getEnd() + currentCoords.getHeader().length(), currentCoords.getCloser().length() - 1, textSB.length());
                }
            }
        }

        // Return the final, built up string
        return textSB.toString();
    }

    public void genHeadersAndClosers() {
        ArrayList<String> usedHeaders = new ArrayList<String>();
        String headerTemplate = "#%s[";
        for (String key : getLanguages()) {
            ArrayList<LyricCoords> coordsList = getCoordsList(key);
            for (int i = 0; i < coordsList.size(); i++) {
                if (coordsList.get(i).hasHeader()) {
                    usedHeaders.add(coordsList.get(i).getHeader());
                } else {
                    String newHeader;
                    for (int j = i; true; j++) {
                        newHeader = String.format(headerTemplate, j);
                        if (!usedHeaders.contains(newHeader)) {
                            break;
                        }
                    }
                    coordsList.get(i).setHeader(newHeader);
                    // TODO: Add category as first digit and increment within each category
                }
            }
        }
    }
}