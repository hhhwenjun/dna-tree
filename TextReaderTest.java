import java.io.FileNotFoundException;
import student.TestCase;

/**
 * The test for Text Reader class
 * 
 * @author Wenjun Han
 * @version 2022.3.6
 *
 */
public class TextReaderTest extends TestCase {

    private TextReader reader;

    /**
     * set up the reader
     */
    public void setUp() throws FileNotFoundException {
        reader = new TextReader();
    }


    /**
     * test the reader of file
     */
    public void testReadFile() {
        Exception fnfe = null;
        
        try {
            reader.readFile("sample2.txt");
            System.out.println("*** Complete sample2 ***");
        }
        catch (Exception e) {
            fnfe = e;
        }
        assertTrue(contains(systemOut().getHistory(),
            "sequence AA inserted at level 3"));
        try {
            reader.readFile("SampleInput.txt");
            System.out.println("*** Complete SampleInput ***");
            reader.readFile("sample1.txt");
            System.out.println("*** Complete sample1 ***");
            reader.readFile("sample3.txt");
            System.out.println("*** Complete sample3 ***");
            reader.readFile("sample4.txt"); // non-existing file
        }
        catch (Exception e) {
            fnfe = e;
        }

        assertTrue(fnfe instanceof FileNotFoundException);

    }


    /**
     * test formatter()
     */
    public void testFormatter() {
        String test1 = "32       ";
        String test2 = "  insert  AAA ";
        assertEquals(reader.formatter(test1)[0], "32");
        assertEquals(reader.formatter(test2)[0], "insert");
        assertEquals(reader.formatter(test2)[1], "AAA");
    }

}
