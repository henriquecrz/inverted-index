import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class Converter {
    public static void toBinaryFile(String inputPathName, String binaryPathName) {
        try {
            File folder = new File(inputPathName);
            File[] files = folder.listFiles();

            for (File file : files) {
                Scanner inputFileScanner = new Scanner(file);
                String fileName = file.getName();
                String[] splittedFileName = fileName.split("\\.txt");
                RandomAccessFile binaryFileScanner = new RandomAccessFile(new File(binaryPathName + splittedFileName[0] + ".bin"), Constant.READ_WRITE_ACCESS_MODE);

                binaryFileScanner.setLength(0);

                while (inputFileScanner.hasNextLine()) {
                    String line = inputFileScanner.nextLine();

                    saveToFile(binaryFileScanner, line);
                }

                inputFileScanner.close();
                binaryFileScanner.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToFile(RandomAccessFile file, String line) throws IOException {
        file.seek(file.length());
        file.writeUTF(line);
    }
}
