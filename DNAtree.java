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

/**
 * The DNA tree class of project runner
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.2.25
 *
 */
public class DNAtree {
    /**
     * Main project runner for the DNAtree project
     * 
     * @param args
     *            the input file name
     * @throws FileNotFoundException
     *             if the file not found
     */
    public static void main(String[] args) throws FileNotFoundException {

        if (args.length == 1) {
            TextReader reader = new TextReader();
            reader.readFile(args[0]);
        }
        else {
            TextReader reader = new TextReader();
            reader.readFile("SampleInput.txt");
        }
    }
}
