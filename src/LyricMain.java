import java.text.ParseException;
import java.util.*;

public class LyricMain {
  
  public static void main(String[] args) {

// =-= Initializing test LyricLine =-=
    LyricLine testLine = new LyricLine("English", "Japanese");
    testLine.setBracketedText("Japanese", "honbun tesuto");
    testLine.setBracketedText("English", "test text");
    // System.out.println(testLine.getPlainText("Japanese"));
    // System.out.println(testLine.getPlainText("English"));


// =-= Initializing Categories =-=
    LyricCategory category1 = new LyricCategory(1, "One");
    LyricCategory category2 = new LyricCategory(2, "Two");
    LyricCategory category3 = new LyricCategory(3, "Three");
    ArrayList<LyricCategory> categorySet = new ArrayList<LyricCategory>();
    categorySet.add(category1);
    categorySet.add(category3);
    categorySet.add(category2);
    // System.out.println(categorySet);


// =-= Initializing test LyricSlice =-=
    LyricSlice testSlice = testLine.createSlice(category1);
    testSlice.setCoordsUpdated("Japanese", -5, 6);
    testSlice.setCoordsUpdated("English", 5, 18);
    testSlice.setCoordsUpdated("English", 5, 18);
    // System.out.println("Test Slice:\n" + testSlice);
    // System.out.println("Test Line:\n" + testLine);


// =-= Adding more slices, with categories =-=
    testLine.createSlice(category2).setCoordsUpdated("English", 8, 9);
    testLine.createSlice(category1).setCoordsUpdated("English", 0, 4).setCoordsUpdated("Japanese", 9, 17);
    /*
    testLine.createSlice(category3).setStartEnd("English", 0, 2).setStartEnd("Japanese", 7, 9);
    testLine.createSlice(category3).setStartEnd("English", 2, 3).setStartEnd("Japanese", 9, 11);
    testLine.createSlice(category3).setStartEnd("English", 3, 4).setStartEnd("Japanese", 11, 13);
    */
    testLine.createSlice(category3).setCoordsUpdated("Japanese", 3, 4).addCoords("Japanese", 8, 9);


    String testVerseStr = "";
    testVerseStr += ">Verse \"Verse 1\"<\n";
    testVerseStr += "\t>Line \"Line 1\"<\n";
    testVerseStr += "\t\t@Japanese: #01[Tesuto]\n";
    testVerseStr += "\t\t@English: #01[Test]\n";
    testVerseStr += "\t\t~Noun: #01\n";
    testVerseStr += "\t>Line \"Line 2\"<\n";
    testVerseStr += "\t\t@Japanese: #02[honbun]\n";
    testVerseStr += "\t\t@English: #02[text]\n";
    testVerseStr += "\t\t~Verb: #02\n";


// =-=
/*
    try {
        String newLine = ">Line \"Test Line 2\"<\n";
        newLine += "\t@English: #05[This] #04[#10[is] #02[a] #03[test]]\n";
        newLine += "\t@Japanese: #05[Kore wa] #04[#03[tesuto] #10[desu yo]]\n";
        newLine += "\t~Test Category 1\n\t~Test Category 2\n\t$Test Annotation\n";
        LyricLine testLine2 = LyricLine.parseTextToLine(newLine);
        // testLine2.setName("Test Line 2");

        // System.out.println(testLine2.getCoordsList("English"));
        // System.out.println(testLine2.getSlices());
        // System.out.println(testLine2.getTaggedText());

        testLine2.getSlices().get(0).setCategory(category1);
        testLine2.getSlices().get(1).setCategory(category1);
        testLine2.getSlices().get(2).setCategory(category2);
        testLine2.getSlices().get(3).setCategory(category2);
        testLine2.getSlices().get(4).setCategory(category3);

        // System.out.println(testLine2.getCoordsList("English"));
        // System.out.println(testLine2.getSlices());
        // System.out.println(testLine2.getTaggedText());

        LyricSlice testSliceCat = testLine2.createSlice(category1).setCoordsUpdated("English", 2, 4).setCoordsUpdated("Japanese", 2, 4);
        testLine2.createSlice(category2).setCoordsUpdated("English", 4, 5).setCoordsUpdated("Japanese", 4, 5);
        // System.out.println(testLine2.getTaggedText());

        // Expecting to see this reflected in the name: #11 -> #20 and #20 -> #21
        testSliceCat.setCategory(category2);
        // System.out.println(testLine2.getTaggedText());

        // Expecting to see first digit of all slices match category number
        testLine2.removeAllManualHeaders();
        // System.out.println(testLine2.getTaggedText());

        // Expecting to see the languages printed Japanese then English, rather than the default alphabetical order
        ArrayList<String> orderedLanguages = new ArrayList<String>();
        orderedLanguages.add("Japanese");
        orderedLanguages.add("English");
        // System.out.println(testLine2.getTaggedText(orderedLanguages));

        LyricHead testHeader = new LyricHead(orderedLanguages, categorySet);

        LyricVerse testVerse = new LyricVerse();
        testVerse.setName("Test Verse");
        testVerse.addLine(testLine);
        testVerse.addLine(testLine2);
        testVerse.addLine(testLine);
        LyricLine testLine3 = testVerse.createLine(testHeader);
        testLine3.setBracketedText("English", "Test 3");
        testLine3.setBracketedText("Japanese", "Tesuto San");

        System.out.println(testVerse.getTaggedText(0, testHeader));

        LyricHead testHead2 = LyricHead.parseTextToHead(">Head<\n@Japanese\n@English\n~Noun: #000000\n~Verb: #111111");
        
        System.out.println(testHead2.getLanguages());
        System.out.println(testHead2.getCategories());

        System.out.println(testLine3.getTaggedText(testHead2));
        System.out.println(testHead2.getAsTagged(3));

    } catch (ParseException e) {
        e.printStackTrace();
    }
*/
    try {
        LyricHead testHead = LyricHead.parseTextToHead(">Head<\n@Japanese\n@English\n~Noun: #000000\n~Verb: #111111");
        LyricVerse testVerse = LyricVerse.parseTextToVerse(testVerseStr, testHead);

        System.out.println(testHead.getAsTagged(0));
        System.out.println(">Body<");
        System.out.println(testVerse.getTaggedText(1, testHead));

    } catch (ParseException e) {
        e.printStackTrace();
    }
  }
}