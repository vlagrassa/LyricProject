public class LyricMain {
  
  public static void main(String[] args)
  {
    String[] languages = {"English", "Japanese", "Kanji"};
    LyricLine testLine = new LyricLine(languages);
    testLine.setPlainText("Japanese", "honbun tesuto");
    testLine.setPlainText("English", "test text");
    System.out.println(testLine.getPlainText("Japanese"));
    System.out.println(testLine.getPlainText("English"));

    LyricSlice testSlice = testLine.createSlice();
    testSlice.setStartEnd("Japanese", -5, 6);
    testSlice.setStartEnd("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);
  }
}