// Import libraries
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**.
* This program uses recursion to perform a binary search
*
* @author  Zak Goneau
* @version 1.0
* @since   2025-05-02
*/

// Creating class
public final class RecBinSearch {

    /**
     * This is a private constructor used to satisfy the style checker.
     *
     * @exception IllegalStateException Utility class.
     * @see IllegalStateException
     */
    private RecBinSearch() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * This is the main method.
     *
     * @param args Unused.
     * @throws Exception - Input/Output exception
     */

    public static void main(final String[] args) throws Exception {

        // Initialize output string
        String outputStr = "";

        // Declare low
        int low = 0;

        // Open file writer to write to output file
        final FileWriter myWriter = new FileWriter("output.txt");

        // Introduce program to user
        System.out.println("This program uses recursion to "
                + "perform a binary search. The output will be in a file.");

        // Open input file and prepare to read
        final File file = new File("./input.txt");
        final Scanner scanner = new Scanner(file);

        // Loop while there's another line in the file
        while (scanner.hasNextLine()) {

            // Get current line from file
            final String line = scanner.nextLine();

            // Check if array is empty
            if (line.isEmpty()) {
                // Add error message to output string
                outputStr += "Error: The array is empty.\n";

                // Continue to next line
                continue;
            }

             // Check if there's no more lines in the file
            if (!scanner.hasNextLine()) {
                outputStr += "Reached end of file\n";
                break;
            }

            // Get search number
            final String searchNumStr = scanner.nextLine();

            // Split line into array of strings
            final String[] stringArray = line.split(" ");

            // Create int array
            final int[] intArray = new int[stringArray.length];

            // Try casting string array to int array
            try {
                // Loop through string array and cast to int
                for (int counter = 0; counter < stringArray.length; counter++) {
                    intArray[counter] = Integer.parseInt(stringArray[counter]);
                }

                // Sort array
                java.util.Arrays.sort(intArray);

                // Try casting search number to int
                try {
                    // Cast search number to int
                    final int searchNum = Integer.parseInt(searchNumStr);

                    // Call function to find number
                    final int index = recBinSearch(intArray, searchNum, low,
                        intArray.length - 1);

                    // Add sorted array to output string
                    outputStr += "Sorted array: ";
                    for (int counter = 0; counter
                        < intArray.length; counter++) {
                            outputStr += intArray[counter] + " ";
                    }
                    outputStr += "\n";

                    // Check if number wasn't found
                    if (index == -1) {
                        // Add error message to output string
                        outputStr += searchNum + " was not found.\n";

                        // Continue to next line
                        continue;

                    // Otherwise number was found
                    } else {
                        // Add to output string
                        outputStr += searchNum + " was found at index "
                            + index + "\n";
                    }

                } catch (NumberFormatException error) {
                    // Add error message to output string
                    outputStr += "The search number must be a number.\n";
                }

            // Catch invalid inputs
            } catch (NumberFormatException error) {
                // Add error message to output string
                outputStr += "The array isn't only integers.\n";
            }
        }

        // Write to output file
        myWriter.write(outputStr);

        // Display success message
        System.out.println("Success, the result "
            + "is printed in the output file.");

        // Close scanner
        scanner.close();

        // Close writer
        myWriter.close();
    }

    /**.
    * This function performs a binary search recursively
    *
    * @param number The search number
    * @param array The array to search
    * @param low The lower bound of the search
    * @param high The upper bound of the search
    * @return The index of the number in the array
    */

    // Define function to perform binary search
    public static int recBinSearch(final int[] array, final int number,
                 final int low, final int high) {

        // Set base case
        if (low > high) {
            // Return error value if not found
            return -1;
        }

        // Find middle point
        final int mid = (low + high) / 2;

        // Check if number is equal to midpoint
        if (array[mid] == number) {
            // Return index if found
            return mid;

        // Check if midpoint is larger than number
        } else if (array[mid] > number) {
            // Recursively call function with new high
            return recBinSearch(array, number, low, mid - 1);

        // Otherwise midpoint is smaller than number
        } else {
            // Recursively call function with new low
            return recBinSearch(array, number, mid + 1, high);
        }
    }
}
