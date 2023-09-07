import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList<String> Persons = new ArrayList<String>();
        Scanner input = new Scanner(System.in);

        while (true) {
            String ID = SafeInput.getNonZeroLenString(input, "What is the ID?");
            String firstName = SafeInput.getNonZeroLenString(input, "What is the first name?");
            String lastName = SafeInput.getNonZeroLenString(input, "What is the last name?");
            String title = SafeInput.getNonZeroLenString(input, "What is the title?");
            int yearOfBirth = SafeInput.getInt(input, "What is the year of birth?");

            String data = ID + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            Persons.add(data);

            boolean askUser = SafeInput.getYNConfirm(input, "Would you like to add another person to the file?");
            if (askUser == false) {
                break;
            }
        }

        Path fileName = Path.of("PersonTestData.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(fileName, StandardOpenOption.CREATE);

            for (int i = 0; i < Persons.size(); i++) {
                writer.write(Persons.get(i));
                writer.newLine();
            }
            writer.close();
            System.out.println("Persons saved to " + fileName);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}