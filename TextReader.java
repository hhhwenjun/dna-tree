import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Read file and provide
 * 
 * @author Wenjun Han(hwenjun)
 * @version 2022.2.25
 *
 */

public class TextReader {

    private Tree tree;

    /**
     * Constructor of text reader
     */
    public TextReader() {
        tree = new Tree();
    }


    /**
     * Read the file and output the answer
     * 
     * @param fileName
     *            The name of text file
     * @throws FileNotFoundException
     *             Throw when the file is not found
     */
    public void readFile(String fileName) throws FileNotFoundException {

        tree.clear();

        File newFile = new File(fileName);
        Scanner file = new Scanner(newFile);

        while (file.hasNextLine()) {
            String line = file.nextLine();
            line = line.trim();
            line = line.replaceAll("[\\n\\t]", " ");
            if (!line.equals("")) {
                String[] cleanStrings = formatter(line);
                try {
                    allocator(cleanStrings);
                }
                catch (Exception e) {
                    continue;
                }
            }
        }
        file.close();
    }


    /**
     * Method to allocate the data to corresponding operations
     * 
     * @param data
     *            The input clean data
     * @throws IllegalArgumentException
     *             If the operator does not follow by data
     */
    public void allocator(String[] data) throws IllegalArgumentException {

        String operator = data[0];
        if (operator.equals("insert")) {
            String match = "[ACGT]+";
            String value = data[1];
            if (!data[1].matches(match)) {
                throw new IllegalArgumentException();
            }
            boolean insertFlag = tree.insert(value);
            if (insertFlag) {
                TreeTraverse traverser = new TreeTraverse(tree.getRoot());
                int level = traverser.findNode(value).getLevel();
                System.out.println("sequence " + value + " inserted at level "
                    + level);
            }
            // data already exists
            else {
                System.out.println("sequence " + value + " already exists");
            }
        }
        else if (operator.equals("remove")) {
            String value = data[1];
            String match = "[ACGT]+";
            if (!data[1].matches(match)) {
                throw new IllegalArgumentException();
            }
            boolean removeFlag = tree.remove(value);
            if (removeFlag) {
                System.out.println("sequence " + value + " removed");
            }
            // data not exist
            else {
                System.out.println("sequence " + value + " does not exist");
            }

        }
        else if (operator.equals("search")) {
            String match = "[ACGT$]+";
            if (!data[1].matches(match)) {
                throw new IllegalArgumentException();
            }
            TreeTraverse traverser = new TreeTraverse(tree.getRoot());
            String value = data[1];
            char endChar = value.charAt(value.length() - 1);
            // exact search
            if (endChar == '$') {
                traverser.search(value.substring(0, value.length() - 1), 0);
            }
            // prefix search
            else {
                traverser.search(value, 1);
            }

        }
        // print
        else if (operator.equals("print")) {
            String printMethod = data[1];
            TreeTraverse traverser = new TreeTraverse(tree.getRoot());
            if (printMethod.equals("")) {
                traverser.printTree();
            }
            else if (printMethod.equals("stats")) {
                traverser.printStats();
            }
            else if (printMethod.equals("lengths")) {
                traverser.printLength();
            }
        }
    }


    /**
     * Clean the data string and call cleanString method to clean 0's
     * 
     * @param line
     *            The raw line data
     * @return The cleaned string array data
     */
    public String[] formatter(String line) {
        line = line.trim();
        String[] rawData = line.split(" ");
        String strings = "";
        for (String string : rawData) {
            string = string.trim();
            if (!string.equals("")) {
                strings += string;
                strings += " ";
            }
        }
        strings = strings.trim();
        String[] cleanStrings = new String[2];
        String param = "";
        int i = 0;
        for (String string : strings.split("\\s+")) {
            if (i == 0) {
                cleanStrings[i] = string;
            }
            else {
                param += string;
            }
            i++;
        }
        cleanStrings[1] = param;
        return cleanStrings;
    }

}
