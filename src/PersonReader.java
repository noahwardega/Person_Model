import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersonReader {
        public static void main(String[] args) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                java.io.File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getAbsolutePath();
                List<String[]> records = new ArrayList<>();

                try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                    System.out.println("File Contents:");
                    String line;

                    while ((line = reader.readLine()) != null) {
                        String[] data = line.split(", ");

                        if (data.length == 5) {
                            records.add(data);
                        }
                    }

                    System.out.printf("%-10s %-15s %-15s %-10s %-10s%n", "ID#", "First Name", "Last Name", "Title", "YOB");
                    System.out.println("==========================================================");

                    for (String[] data : records) {
                        System.out.printf("%-10s %-15s %-15s %-10s %-10s%n", data[0], data[1], data[2], data[3], data[4]);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
}