import java.util.*;

public class LyricHead {

  // =-=-= Instance Variables =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    private ArrayList<String> listOfLanguages;
    private ArrayList<LyricCategory> listOfCategories;


  // =-=-= Constructor(s) =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public LyricHead(ArrayList<String> languages, ArrayList<LyricCategory> categories) {
        listOfLanguages = languages;
        listOfCategories = categories;
    }

    public LyricHead() {
        this(new ArrayList<String>(), new ArrayList<LyricCategory>());
    }


  // =-=-= Language List =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public ArrayList<String> getLanguages() {
        return listOfLanguages;
    }

    public String getLanguage(int index) {
        return listOfLanguages.get(index);
    }

    public void setLanguageList(ArrayList<String> newLanguageList) {
        listOfLanguages = newLanguageList;
    }

    public void addLanguage(String language) {
        listOfLanguages.add(language);
    }

    public void addLanguage(int index, String language) {
        listOfLanguages.add(index, language);
    }

    public void addLanguages(String... languages) {
        for (String language : languages) {
            addLanguage(language);
        }
    }

    public void removeLanguage(String language) {
        listOfLanguages.remove(language);
    }

    public void removeLanguage(int index) {
        listOfLanguages.remove(index);
    }

    public void removeLanguages(String... languages) {
        for (String language : languages) {
            removeLanguage(language);
        }
    }

    public Boolean moveLanguage(int currentIndex, int newIndex) {
        String languageToMove = getLanguage(currentIndex);
        removeLanguage(currentIndex);
        if (newIndex < 0) {
            addLanguage(0, languageToMove);
            return false;
        }
        else if (newIndex > listOfLanguages.size()) {
            addLanguage(languageToMove);
            return false;
        }
        addLanguage(newIndex, languageToMove);
        return true;
    }

    public Boolean moveLanguage(String language, int newIndex) {
        return moveLanguage(listOfLanguages.indexOf(language), newIndex);
    }

    public Boolean hasLanguage(String language) {
        return listOfLanguages.contains(language);
    }


  // =-=-= Category List =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public ArrayList<LyricCategory> getCategories() {
        return listOfCategories;
    }

    public LyricCategory getCategory(int index) {
        return listOfCategories.get(index);
    }

    public void setCategoryList(ArrayList<LyricCategory> newCategoryList) {
        listOfCategories = newCategoryList;
    }

    public void addCategory(LyricCategory newCategory) {
        if (!listOfCategories.contains(newCategory)) {
            listOfCategories.add(newCategory);
        }
    }

    public void addCategories(LyricCategory... newCategories) {
        for (LyricCategory category : newCategories) {
            addCategory(category);
        }
    }

    public void removeCategory(LyricCategory category) {
        listOfCategories.remove(category);
    }

    public void removeCategories(LyricCategory... categories) {
        for (LyricCategory category : categories) {
            removeCategory(category);
        }
    }

    //TODO: Reordering categories


  // =-=-= Parsing =-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=

    public static LyricHead parseTextToHead(String originalLines) /*throws ParseException*/ {
        LyricHead result = new LyricHead();
        for (String currentLine : originalLines.split("\n")) {
            currentLine = currentLine.replace("\t", "");
            if (currentLine.startsWith(">Head<")) {
                // Skip it?
            }
            else if (currentLine.startsWith("@")) {
                int colonIndex = currentLine.indexOf(":");
                result.addLanguage(currentLine.substring(1, colonIndex >= 0 ? colonIndex : currentLine.length()));
            }
            else if (currentLine.startsWith("~")) {
                String[] headAndBody = currentLine.substring(1).split(":", 2);
                LyricCategory newCategory = new LyricCategory(result.getCategories().size(), headAndBody[0].trim());
                newCategory.setDisplayColor(headAndBody[1].trim());
                result.addCategory(newCategory);
            }
        }
        return result;
    }

    public String getAsTagged(int indent) {
        String tabs = "";
        for (int i = 0; i <= indent; i++) {
            tabs += "\t";
        }
        String result = tabs.substring(1) + ">Head<";
        for (String language : listOfLanguages) {
            result += "\n@" + language + ":";
        }
        result += "\n";
        for (LyricCategory category : listOfCategories) {
            result += "\n~" + category.getDisplayName() + ": " + category.getDisplayColorStr();
        }
        result = result.replace("\n", "\n" + tabs) + "\n";
        return result;
    }
}