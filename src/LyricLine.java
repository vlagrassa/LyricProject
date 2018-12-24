public class LyricLine {
    private Map<String,String> plaintexts;
    private List<LyricSlice> slices;

    // Get the plain text from texts for the given language
    public String getPlainText(String key) {
        return plaintexts.get(key);
    }

    public String setPlainText(String key, String val) {
        return plaintexts.set(key, val);
    }

    // Get the full plaintext map
    public Map<String,String> getPlainTextMap() {
        return plaintexts;
    }

    public Map<String,String> setPlainTextMap(Map<String,String> newmap) {
        Map<String,String> temp = plaintexts;
        plaintexts = newmap;
        return temp;
    }

    // Get the list of Lyric slices
    public List<LyricSlice> getSlices() {
        return slices;
    }

    public List<LyricSlice> getSlices(Integer cat) {
        List<LyricSlice> result = new List<LyricSlice>();
        for (LyricSlice s : slices) {
            if (s.isCategory(cat)) {
                result.add(s);
            }
        }
        return result;
    }

    public List<LyricSlice> setSlices(List<LyricSlice> newlist) {
        List<LyricSlice> temp = slices;
        slices = newlist;
        return temp;
    }

    // Order the list of Lyric slices based on category
    public void orderSlices();
}