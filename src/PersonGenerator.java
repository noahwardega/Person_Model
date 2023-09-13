import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

public class PersonGenerator {
    public static void main(String[] args) {

        ArrayList<Person> Persons = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        while (true) {
            String ID = SafeInput.getNonZeroLenString(input, "What is the ID?");
            String firstName = SafeInput.getNonZeroLenString(input, "What is the first name?");
            String lastName = SafeInput.getNonZeroLenString(input, "What is the last name?");
            String title = SafeInput.getNonZeroLenString(input, "What is the title?");
            int yearOfBirth = SafeInput.getInt(input, "What is the year of birth?");

            Person person = new Person(ID, firstName, lastName, title, yearOfBirth);

            Persons.add(person);
            boolean askUser = SafeInput.getYNConfirm(input, "Would you like to add another person to the file?");
            if (askUser == false) {
                break;
            }
        }

        Path fileName = Path.of("PersonTestData.txt");

        try {
            BufferedWriter writer = Files.newBufferedWriter(fileName, StandardOpenOption.CREATE);
            for (Person person : Persons) {
                String csvRecord = person.toCSVDataRecord();
                writer.write(csvRecord);
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