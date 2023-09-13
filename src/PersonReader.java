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
            List<Person> Persons = new ArrayList<>();

            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                System.out.println("File Contents:");
                String line;

                System.out.printf("%-10s %-15s %-15s %-10s %-10s%n", "ID#", "First Name", "Last Name", "Title", "YOB");
                System.out.println("==========================================================");

                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(", ");

                    if (data.length == 5) {
                        String ID = data[0];
                        String firstName = data[1];
                        String lastName = data[2];
                        String title = data[3];
                        int YOB = Integer.parseInt(data[4]);

                        Person records = new Person(ID, firstName, lastName, title, YOB);

                        Persons.add(records);
                    }
                }

                // Display
                for (Person person : Persons) {
                    System.out.printf("%-10s %-15s %-15s %-10s %-10s%n",
                            person.getID(), person.getFirstName(), person.getLastName(), person.getTitle(), person.getYOB());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
