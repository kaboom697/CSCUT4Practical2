package checkPoint4;

import java.io.*;
import java.util.*;

/**
 * 
 * CSCU9T4 Java strings and files exercise.
 *
 */
public class FilesInOut {

    public static void main(String[] args) {
    	// Parse command line arguments
        boolean useUpperCase = false;
        String inputFileName = "";
        String outputFileName = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-u")) {
                useUpperCase = true;
            } else if (inputFileName.equals("")) {
                inputFileName = args[i];
            } else if (outputFileName.equals("")) {
                outputFileName = args[i];
            }
        }
        
        List<String> formattedNames = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                String firstName = toTitleCase(parts[0]);
                String middleInitial = "";
                if (parts.length > 2) {
                    middleInitial = parts[1].substring(0, 1).toUpperCase() + ". ";
                }
                String lastName = toTitleCase(parts[parts.length - 1]);
                String dateOfBirth = formatDate(parts[parts.length - 2]);
                String formattedName = firstName + " " + middleInitial + lastName + "     " + dateOfBirth;
                if (useUpperCase) {
                    formattedName = formattedName.toUpperCase();
                }
                formattedNames.add(formattedName);
            }
        } catch (FileNotFoundException e) {
            System.err.println("unable to find input file");
            return;
        }
        

        try (PrintWriter writer = new PrintWriter(new File(outputFileName))) {
            for (String formattedName : formattedNames) {
                writer.println(formattedName);
            }
        } catch (FileNotFoundException e) {
            System.err.println("unable to create output file");
            return;
        }
    }
    
    private static String toTitleCase(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    private static String formatDate(String str) {
        return str.substring(0, 2) + "/" + str.substring(2, 4) + "/" + str.substring(4);
    }


} // FilesInOut
