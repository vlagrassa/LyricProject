import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LyricTest {
    String[] languages = {"English", "Japanese"};
    LyricLine testLine;

    @Before
    public void setUp() throws Exception {
        testLine = new LyricLine(languages);
    }

    @Test
    public void testPlaintext() {
        testLine.setBracketedText("Japanese", "honbun tesuto");
        testLine.setBracketedText("English", "test text");
        Assert.assertEquals("honbun tesuto", testLine.getPlainText("Japanese"));
        Assert.assertEquals("test text", testLine.getPlainText("English"));
    }

//     // =-= Test Discontinuous Lyric Coords =-=
//     LyricCoords              testCoordsOrig = new LyricCoords             (1, 3);
//     LyricCoords              testCoordsCast = new LyricCoordsDiscontinuous(1, 3);
//     LyricCoordsDiscontinuous testCoordsDisc = new LyricCoordsDiscontinuous(1, 3);
//     String testText = "abcdefghij";

//     // Plain Coordinates
//     System.out.println("\nPlain Coordinates:");
//     System.out.println("Discontinuous: " + testCoordsOrig.isDiscontinuous());
//     System.out.print(testCoordsOrig + " -> ");
//     System.out.println(testCoordsOrig.getBoundCharacters(testText));
//     testCoordsOrig = testCoordsOrig.addCoords(5, 7);
//     System.out.println("Discontinuous: " + testCoordsOrig.isDiscontinuous());
//     System.out.print(testCoordsOrig + " -> ");
//     System.out.println(testCoordsOrig.getBoundCharacters(testText));

//     // Polymorphic Discontinuous Coordinates
//     System.out.println("\nPolymorphic Discontinuous Coordinates:");
//     System.out.println("Discontinuous: " + testCoordsCast.isDiscontinuous());
//     System.out.print(testCoordsCast + " -> ");
//     System.out.println(testCoordsCast.getBoundCharacters(testText));
//     testCoordsCast = testCoordsCast.addCoords(5, 7);
//     System.out.println("Discontinuous: " + testCoordsCast.isDiscontinuous());
//     System.out.print(testCoordsCast + " -> ");
//     System.out.println(testCoordsCast.getBoundCharacters(testText));

//     // Plain Discontinuous Coordinates
//     System.out.println("\nPlain Discontinuous Coordinates:");
//     System.out.println("Discontinuous: " + testCoordsDisc.isDiscontinuous());
//     System.out.print(testCoordsDisc + " -> ");
//     System.out.println(testCoordsDisc.getBoundCharacters(testText));
//     testCoordsDisc = testCoordsDisc.addCoords(5, 7);
//     System.out.println("Discontinuous: " + testCoordsDisc.isDiscontinuous());
//     System.out.print(testCoordsDisc + " -> ");
//     System.out.println(testCoordsDisc.getBoundCharacters(testText));

//     // Using Discontinuous Coordinates in a LyricSlice
//     System.out.println("\nDiscontinuous Coordinates in a LyricSlice:");
//     LyricSlice testSliceDisc = testLine.createSlice();
//     LyricCoordsDiscontinuous testCoordsDisc_ = new LyricCoordsDiscontinuous(0, 5);
//     testCoordsDisc_.addCoords(10, 12);
//     testCoordsDisc_.addCoords(7, 14);
//     testSliceDisc.getCoordsMap().put("English", testCoordsDisc_);
//     System.out.println(testSliceDisc.getReferenceString("English"));
//     System.out.println(testSliceDisc);


// // =-= Test removing non-bracket characers =-=
//     System.out.println("\nRemove non-bracket characters:");
//     for (int i = 0; i < 5; i++) {
//       for (int j = 0; j < 6-i; j++) {
//         System.out.println("Remove " + j + " at index " + i + ": " + testLine.removeUnlessBrackets("a[b[]c[d]]e", j, i));
//       }
//     }

// // =-= Add and remove from bracketed text =-=
//     System.out.println("\nAdd to and remove from bracketed text:");
//     System.out.println(testLine.getBracketedText("English"));
//     testLine.addToBracketedText("English", "abcde", 1);
//     System.out.println(testLine.getBracketedText("English"));
//     testLine.deleteFromBracketedText("English", 5, 0);
//     System.out.println(testLine.getBracketedText("English"));
//     System.out.println(testLine.getPlainText("English"));


// // =-= Test removing non-bracket characters
//     System.out.println("\nRemove non-bracket characters from sample string:");
//     String thing = "a[b[]c[d]]e";
//     System.out.println(testLine.removeUnlessBrackets(thing, 3, 1));
//     System.out.println("Test thing: " + thing);
//     System.out.println("\n" + testLine.getSlices());
//     System.out.println("\n" + testLine.getTaggedText());
//     // testSlice.moveStart("English", 1);
//     // testSlice.moveEnd("English", -6);


// // =-= Moving end to same position as start =-=
//     System.out.println("\nMove end position to same as start position:");
//     System.out.println(testLine.getBracketedText("English"));
//     testSlice.moveEnd("English", -7);
//     System.out.println(testLine.getBracketedText("English"));

// // =-= Incrementing end toward start with -1, then moving past with -2 =-=
//     // System.out.println(testLine.getBracketedText("English"));
//     // for (int i = 0; i < 15; i++) {
//     //   testSlice.moveEnd("English", -1);
//     //   System.out.println(testLine.getBracketedText("English"));
//     // }
//     // testSlice.moveEnd("English", -2);
//     // System.out.println(testLine.getBracketedText("English"));
//     // testSlice.moveEnd("English", -2);
//     // System.out.println(testLine.getBracketedText("English"));
//     // testSlice.moveStart("English", 2);
//     // System.out.println(testLine.getBracketedText("English"));
//     // testSlice.moveStart("English", 2);
//     // System.out.println(testLine.getBracketedText("English"));

//     System.out.println("\n" + testLine.getTaggedText());
//     System.out.println(testLine.getBracketedText("English"));
//     System.out.println(testLine.getSlices());
//     testSlice.setCoordsUpdated("English", null, null);
//     System.out.println("\n" + testLine.getTaggedText());
//     System.out.println(testLine.getBracketedText("English"));
//     System.out.println(testLine.getSlices());

// // =-= Test variable argument Constructor for discontinuous Lyric coords =-=
//     System.out.println("\nLyricCoordsDiscontinuous Constructor:");
//     LyricCoordsDiscontinuous test0 = new LyricCoordsDiscontinuous();
//     LyricCoordsDiscontinuous test1 = new LyricCoordsDiscontinuous(new LyricCoords(1, 2));
//     LyricCoordsDiscontinuous test2 = new LyricCoordsDiscontinuous(new LyricCoords(1, 2), new LyricCoords(3, 4));
//     LyricCoordsDiscontinuous test3a = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), test2);
//     LyricCoordsDiscontinuous test3b = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), new LyricCoords(test2));
//     LyricCoordsDiscontinuous test3c = new LyricCoordsDiscontinuous(new LyricCoords(5, 6), new LyricCoordsDiscontinuous(test2));
//     LyricCoordsDiscontinuous test3d = new LyricCoordsDiscontinuous(new LyricCoords(5, 6));
//     test3d.addCoords(test2);

//     System.out.println("Test 0: " + test0);
//     System.out.println("Test 1: " + test1);
//     System.out.println("Test 2: " + test2);
//     System.out.println("Test 3a: " + test3a);
//     System.out.println("Test 3b: " + test3b);
//     System.out.println("Test 3c: " + test3c);
//     System.out.println("Test 3d: " + test3d);
//     System.out.println("Changing second test coords...");
//     test2.getCoordsList().get(0).moveCoords(5, 7);
//     System.out.println("Test 2: " + test2);
//     System.out.println("Test 3a: " + test3a);
//     System.out.println("Test 3b: " + test3b);
//     System.out.println("Test 3c: " + test3c);
//     System.out.println("Test 3d: " + test3d);

// // =-= Editing inside a HashMap =-=
//     System.out.println("\nEditing inside a HashMap:");
//     HashMap<Integer, LyricCoords> testMap = new HashMap<Integer, LyricCoords>();
//     LyricCoords test4 = new LyricCoords(1, 2);
//     testMap.put(0, test4);
//     System.out.println(test4);
//     System.out.println(testMap.get(0));
//     System.out.println(testMap.get(0).setEnd(4));
//     System.out.println(test4);
//     System.out.println(testMap.get(0));

//     System.out.println("\nUsing setStartEnd to make discontinuous into continuous");
//     LyricLine testLine3 = new LyricLine("English", "Japanese");
//     testLine3.setBracketedText("English", "abcdefghijklmnopqrst");
//     System.out.println(testLine3.getPlainText("English"));
//     LyricSlice testSlice5 = testLine3.createSlice().setCoordsUpdated("English", 0, 2);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);
//     testSlice5.addCoords("English", 5, 7);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);
//     testSlice5.setCoordsUpdated("English", 10, 12);
//     // test5 = test5.setStartEnd(10, 12, new StringBuilder("[a]bc[d]efghijklmnopqrst"), );
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);

//     System.out.println("\nRemoving Coords from a Slice:");
//     LyricCoords test6 = new LyricCoords(4, 8);
//     testSlice5.addCoords("English", test6);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);
//     // testSlice5.removeCoords(test6); // Fails -> The object test6 has been changed to be used by the slice
//     LyricCoords test7 = testSlice5.getCoords("English").getCoordsList().get(0);
//     testSlice5.removeCoords(test7);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);
//     testSlice5.removeCoords(testSlice5.getCoords("English"));
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testSlice5);

//     System.out.println("\nAdd Range:");
//     testLine3.createSlice().setCoordsUpdated("English", 1, 4).addCoords("English", 7, 9);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testLine3.getSlices());
//     testLine3.getSlices().get(1).addRange("English", 3, 8);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testLine3.getSlices());

//     System.out.println("\nRemove Range:");
//     testLine3.getSlices().get(1).removeRange("English", 3, 5);
//     System.out.println(testLine3.getBracketedText("English"));
//     System.out.println(testLine3.getSlices());

//     System.out.println("\nGet coords list as copy:");
//     System.out.println("Original:  " + testLine.getCoordsList("English"));
//     System.out.println("Duplicate: " + testLine.getCoordsListCopy("English"));

// =-= Filtering slices by category =-=
    // System.out.println("\nFiltering slices by category:");
    // System.out.println("No filter:");
    // System.out.println(testLine.getSlices());
    // System.out.println("Filtered by category 1:");
    // System.out.println(testLine.getSlices(1));


// =-= Modifying the text =-=
    // System.out.println(testLine.getAsHTML());
    // System.out.println(testLine.getSlices());
    // testLine.deleteFromPlainText("English", 1, 4);
    // testLine.addToPlainText("English", "___", 8);

    // testLine.modifyPlainText("English", " thing", 4);
    // System.out.println(testLine.getAsHTML());
    // System.out.println(testLine.getSlices());
    // System.out.println(testLine.getAsBracketed("English"));

// =-= Printing text in different styles =-=
    // System.out.println(testLine);
    // System.out.println(testLine.getTaggedText());
    // System.out.println("\nPrinting as plain:");
    // System.out.println(testLine.getText(LyricLine.textStyle.plain));
    // System.out.println("\nPrinting as bracketed:");
    // System.out.println(testLine.getText(LyricLine.textStyle.bracketed));
    // System.out.println("\nPrinting as tagged:");
    // System.out.println(testLine.getText(LyricLine.textStyle.tagged));

// =-= Merging slices =-=
    // try {
    //     System.out.println("\n\nTest Line 3:\n");
    //     String newLine = ">Line \"Test Line 3\"<\n";
    //     newLine += "\t@English: abcdefghijklmnop\n";
    //     newLine += "\t@Japanese: abcdefghijklmnop\n";
    //     LyricLine testLine_ = LyricLine.parseTextToLine(newLine);

    //     LyricSlice testSlice1 = testLine_.createSlice();
    //     testSlice1.setCoordsUpdated("English", 1, 5).setCoordsUpdated("Japanese", 7, 9).setHeader("#00");
    //     LyricSlice testSlice2 = testLine_.createSlice();
    //     testSlice2.setCoordsUpdated("English", 3, 7).setCoordsUpdated("Japanese", 2, 3).setHeader("#11");
    //     LyricSlice testSlice3 = testLine_.createSlice();
    //     testSlice3.setCoordsUpdated("English", 15, 18).setCoordsUpdated("Japanese", 15, 18).setHeader("#00");
        
    //     System.out.println("Original:");
    //     System.out.println(testLine_.getSlices());
    //     System.out.println(testLine_.getTaggedText());

    //     System.out.println("\nMerge by reference:");
    //     testLine_.mergeSlices(testSlice1, testSlice2);
    //     System.out.println(testLine_.getSlices());
    //     System.out.println(testLine_.getTaggedText());

    //     System.out.println("\nMerge by ID:");
    //     testLine_.mergeSlicesWithSameID();
    //     System.out.println(testLine_.getSlices());
    //     System.out.println(testLine_.getTaggedText());

    // } catch (ParseException e) {
    //     e.printStackTrace();
    // }
}