public class LyricLine {
    private HashMap<String,String> plaintexts;
    private ArrayList<LyricSlice> slices;

    public LyricLine() {
        plaintexts = new HashMap<String,String>();
        slices = new ArrayList<LyricSlice>();
    }

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

    public void addSlice(LyricSlice slice) {
        slices.add(slice);
    }

    // Order the list of Lyric slices based on category
    // public void orderSlices();

    public String toString() {
        String result = ">Line<\n";
        for (String lang : plaintexts.keySet()) {
            result += "\t@" + lang + ": " + plaintexts.get(lang);
        }
        return result;
    }
}