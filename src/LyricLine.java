import java.util.*;

public class LyricLine {

// =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Note that the Line doesn't explicitly store the list of languages,
    // so it doesn't have them ordered - to produce them in proper order,
    // have to invoke each one manually from a higher class.
    private HashMap<String,String> bracketedtexts;
    private ArrayList<LyricSlice> slices;


// =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricLine(String[] languages) {
        bracketedtexts = new HashMap<String,String>();
        for (String lang : languages) {
            bracketedtexts.put(lang, "");
        }
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
            slice.updateReference(key, index, str.length());
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
            slice.updateReference(key, index, -length);
        }
    }


// =-=-= Lyric Slices =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Get the list of Lyric slices
    public ArrayList<LyricSlice> getSlices() {
        return slices;
    }

    public ArrayList<LyricSlice> getSlices(Integer cat) {
        ArrayList<LyricSlice> result = new ArrayList<LyricSlice>();
        for (LyricSlice s : slices) {
            if (s.isCategory(cat)) {
                result.add(s);
            }
        }
        return result;
    }

    public LyricSlice createSlice(Integer category, String annotation) {
        LyricSlice newSlice = new LyricSlice(bracketedtexts, slices, category, annotation);
        slices.add(newSlice);
        return newSlice;
    }

    public LyricSlice createSlice(Integer category) {
        return createSlice(category, "");
    }

    public LyricSlice createSlice(String annotation) {
        return createSlice(null, annotation);
    }

    public LyricSlice createSlice() {
        return createSlice(null, "");
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
        return insertSliceBounds(key, "[", "]");
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
        StringBuilder textSB = new StringBuilder(getBracketedText(key));
        ArrayList<LyricCoords> coordsList = getCoordsList(key);
        for (int i = 0; i < coordsList.size(); i++) {
            LyricCoords currentCoords = coordsList.get(i);
            if (!currentCoords.hasNull()) {
                String bracketHeader = String.format(headerTemplate, headers != null ? headers.get(i) + i : "");
                String bracketCloser = String.format(closerTemplate, closers != null ? closers.get(i) + i : "");
                textSB.replace(currentCoords.getStart(), currentCoords.getStart()+1, bracketHeader);
                textSB.replace(currentCoords.getEnd() + bracketHeader.length() - 1, currentCoords.getEnd() + bracketHeader.length(), bracketCloser);
                for (int j = i+1; j < coordsList.size(); j++) {
                    coordsList.get(j).updateReference(currentCoords.getStart(), bracketHeader.length() - 1, textSB.length());
                    coordsList.get(j).updateReference(currentCoords.getEnd()+bracketHeader.length(), bracketCloser.length() - 1, textSB.length());
                }
                // System.out.println(i + ": " + currentCoords + " -> " + textSB);
                // System.out.println(textSB);
                // System.out.println();
            }
        }
        return textSB.toString();
    }

    private ArrayList<LyricCoords> getCoordsList(String key) {
        ArrayList<LyricCoords> coordsList = new ArrayList<LyricCoords>();
        for (LyricSlice slice : slices) {
            coordsList.add(new LyricCoords(slice.getCoords(key)));
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
}