import java.io.FileNotFoundException;
import java.io.IOException;
public class Main {
    public static void main(String[] args) {

        try {

            Helper helper =new Helper();
            FileWordsCountMultiThread fileWordsCountMultiThread = new FileWordsCountMultiThread(helper);
            fileWordsCountMultiThread.countWords_withThread();

            ReadFileWordCount readFileWordCount  =new ReadFileWordCount(helper);
            readFileWordCount.countWords_withoutThread();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException io) {
            io.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
