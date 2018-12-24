public class LyricLine {

// =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    private HashMap<String,String> plaintexts;
    private ArrayList<LyricSlice> slices;


// =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricLine() {
        plaintexts = new HashMap<String,String>();
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
}