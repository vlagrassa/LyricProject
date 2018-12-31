import java.util.*;

public class LyricLine {

// =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Note that the Line doesn't explicitly store the list of languages,
    // so it doesn't have them ordered - to produce them in proper order,
    // have to invoke each one manually from a higher class.
    private HashMap<String,String> plaintexts;
    private ArrayList<LyricSlice> slices;


// =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricLine(String[] languages) {
        plaintexts = new HashMap<String,String>();
        for (String lang : languages) {
            plaintexts.put(lang, "");
        }
        slices = new ArrayList<LyricSlice>();
    }


// =-=-= Plain Text =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    // Get the plain text from texts for the given language
    public String getPlainText(String key) {
        return plaintexts.get(key);
    }

    public String setPlainText(String key, String val) {
        return plaintexts.put(key, val);
    }

    // Get the full plaintext map
    public HashMap<String,String> getPlainTextMap() {
        return plaintexts;
    }

    public HashMap<String,String> setPlainTextMap(HashMap<String,String> newmap) {
        HashMap<String,String> temp = plaintexts;
        plaintexts = newmap;
        return temp;
    }

    public Set<String> getLanguages() {
        return plaintexts.keySet();
    }

    public void addToPlainText(String key, String str, Integer index) {
        String origtext = plaintexts.get(key);
        plaintexts.put(key, origtext.substring(0, index) + str + origtext.substring(index));
        for (LyricSlice slice : slices) {
            slice.updateReference(key, index, str.length());
        }
    }

    public void deleteFromPlainText(String key, Integer length, Integer index) {
        String origtext = plaintexts.get(key);
        if (index + length > origtext.length()) {
            plaintexts.put(key, origtext.substring(0, index));
        } else {
            plaintexts.put(key, origtext.substring(0, index) + origtext.substring(index + length));
        }
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

    public ArrayList<LyricSlice> setSlices(ArrayList<LyricSlice> newlist) {
        ArrayList<LyricSlice> temp = slices;
        slices = newlist;
        return temp;
    }

    public LyricSlice addSlice(LyricSlice slice) {
        slices.add(slice);
        return slice;
    }

    public LyricSlice createSlice(Integer category, String annotation) {
        LyricSlice newSlice = new LyricSlice(plaintexts, category, annotation);
        slices.add(newSlice);
        return newSlice;
    }

    public LyricSlice createSlice(Integer category) {
        return createSlice(category, "");
    }

    public LyricSlice createSlice(String annotation) {
        return createSlice(-1, annotation);
    }

    public LyricSlice createSlice() {
        return createSlice(-1, "");
    }


// =-=-= Strings =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public String toString() {
        String result = ">Line<\n";
        for (String lang : plaintexts.keySet()) {
            result += "\t@" + lang + ": " + plaintexts.get(lang) + "\n";
        }
        return result;
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
        StringBuilder textSB = new StringBuilder(plaintexts.get(key));
        ArrayList<LyricCoords> coordsList = getCoordsList(key);
        for (int i = 0; i < coordsList.size(); i++) {
            LyricCoords currentCoords = coordsList.get(i);
            String bracketHeader = String.format(headerTemplate, headers != null ? headers.get(i) + i : "");
            String bracketCloser = String.format(closerTemplate, closers != null ? closers.get(i) + i : "");
            textSB.insert(currentCoords.getStart(), bracketHeader);
            textSB.insert(currentCoords.getEnd() + bracketHeader.length(), bracketCloser);
            // System.out.println((i) + ": " + currentCoords);
            for (int j = i+1; j < coordsList.size(); j++) {
                coordsList.get(j).updateReference(currentCoords.getStart(), bracketHeader.length(), textSB.length());
                coordsList.get(j).updateReference(currentCoords.getEnd()+bracketHeader.length(), bracketCloser.length(), textSB.length());
            }
            // System.out.println(i + ": " + currentCoords + " -> " + textSB);
            // System.out.println(textSB);
            // System.out.println();
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