public class LyricLine {
    private Map<String,String> plaintexts;
    private List<LyricSlice> slices;

    // Get the plain text from texts for the given language
    public String getPlainText(String key) {
        return plaintexts.get(key);
    }

    // Get the full list
    public Map<String,String> getPlainTexts() {
        return plaintexts;
    }

    public void orderSlices();
}