import java.text.ParseException;
import java.util.*;

public class LyricMain {
  
  public static void main(String[] args) {

// =-= Initializing test LyricLine =-=
    LyricLine testLine = new LyricLine("English", "Japanese");
    testLine.setBracketedText("Japanese", "honbun tesuto");
    testLine.setBracketedText("English", "test text");
    System.out.println(testLine.getPlainText("Japanese"));
    System.out.println(testLine.getPlainText("English"));


// =-= Initializing test LyricSlice =-=
    LyricSlice testSlice = testLine.createSlice(1);
    testSlice.setCoordsUpdated("Japanese", -5, 6);
    testSlice.setCoordsUpdated("English", 5, 18);
    testSlice.setCoordsUpdated("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);


// =-= Adding more slices, with categories =-=
    testLine.createSlice(2).setCoordsUpdated("English", 8, 9);
    testLine.createSlice(1).setCoordsUpdated("English", 0, 4).setCoordsUpdated("Japanese", 9, 17);
    /*
    testLine.createSlice(3).setStartEnd("English", 0, 2).setStartEnd("Japanese", 7, 9);
    testLine.createSlice(3).setStartEnd("English", 2, 3).setStartEnd("Japanese", 9, 11);
    testLine.createSlice(3).setStartEnd("English", 3, 4).setStartEnd("Japanese", 11, 13);
    */
    testLine.createSlice(3).setCoordsUpdated("Japanese", 3, 4).addCoords("Japanese", 8, 9);


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

// =-=

    System.out.println(testLine);
    System.out.println(testLine.getTaggedText());

    try {
        String newLine = ">Line<\n";
        newLine += "\t@English: #05[This] #04[#01[is] #02[a] #03[test]]\n";
        newLine += "\t@Japanese: #05[Kore wa] #04[#03[tesuto] #01[desu yo]]";
        LyricLine testLine2 = LyricLine.parseTextToLine(newLine);
        
        System.out.println(testLine2.getCoordsList("English"));
        System.out.println(testLine2.getSlices());
        System.out.println(testLine2.getTaggedText());
    } catch (ParseException e) {
        e.printStackTrace();
    }
  }
}