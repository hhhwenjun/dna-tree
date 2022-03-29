// On my honor:
// - I have not used source code obtained from another student,
// or any other unauthorized source, either modified or
// unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project
// with anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

import java.io.FileNotFoundException;
import student.TestCase;

/**
 * Test the main method of dna tree class
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.3.10
 *
 */
public class DNAtreeTest extends TestCase {
    
    /**
     * test main of DNAtree class
     */
    public void testMainInput() {
        try {
            DNAtree.main(new String[0]);
            DNAtree.main(new String[] { "sample3.txt" });
            // non-existing file
            DNAtree.main(new String[] { "sample5.txt" });
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assertTrue(contains(systemOut().getHistory(),
            "AAACCCCGGTGAAAACGTA A:42.11 C:26.32 G:21.05 T:10.53"));
    }
}
