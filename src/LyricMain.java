public class LyricMain {
  
  public static void main(String[] args)
  {
    LyricLine testLine = new LyricLine();
    testLine.setPlainText("Japanese", "honbun tesuto");
    testLine.setPlainText("English", "test text");
    System.out.println(testLine.getPlainText("Romaji"));
    System.out.println(testLine.getPlainText("English"));
    
    LyricSlice testSlice = testLine.createSlice();
    testSlice.setStartEnd("Japanese", -5, 6);
    testSlice.setStartEnd("English", 5, 18);
    System.out.println("Test Slice:\n" + testSlice);
    System.out.println("Test Line:\n" + testLine);
  }
}