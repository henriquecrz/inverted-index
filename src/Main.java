import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Converter.toBinaryFile(Path.TEXT_FILES_FOLDER, Path.BINARY_FILES_FOLDER);

        String inputText = Constant.EMPTY_STRING;
        Scanner keyboard = new Scanner(System.in);

        do {
            System.out.println("Type a text to find: ");
            inputText = keyboard.nextLine();

            List<String> occurrencesFiles = new ArrayList<String>();
            File folder = new File(Path.BINARY_FILES_FOLDER);
            File[] files = folder.listFiles();

            for (File file : files) {
                Scanner fileScanner = new Scanner(file);

                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    StringTokenizer parser = new StringTokenizer(line, Constant.DELIMITER);

                    while (parser.hasMoreTokens()) {
                        String token = parser.nextToken();

                        if (Constant.STOP_WORDS.contains(token)) {
                            continue;
                        }

                        String fileName = file.getName();
                        List<String> splittedInputText = Arrays.asList(inputText.split(" "));

                        if (splittedInputText.contains(token) && !occurrencesFiles.contains(fileName)) {
                            occurrencesFiles.add(fileName);
                        }
                    }
                }

                fileScanner.close();
            }

            System.out.println("File occurrences:");

            for (String fileName : occurrencesFiles) {
                System.out.println(" " + fileName);
            }
        } while (!inputText.equals(Command.QUIT));

        keyboard.close();
    }
}
