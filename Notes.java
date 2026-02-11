import java.util.*;

public class Notes {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Notes App =====");
            System.out.println("1. Add Note");
            System.out.println("2. View Notes");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = s.nextInt();
            s.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter your note: ");
                    String note = s.nextLine();

                    try (java.io.BufferedWriter writer = new java.io.BufferedWriter(new java.io.FileWriter("notes.txt", true))) {
                        writer.write(note);
                        writer.newLine(); // adds a new line after each note
                        System.out.println("Note saved successfully.");
                    } catch (java.io.IOException e) {
                        System.out.println("Error writing note: " + e.getMessage());
                    }
                }
                case 2 -> {
                    System.out.println("\nYour Notes ");
                    try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.FileReader("notes.txt"))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (java.io.IOException e) {
                        System.out.println("Error reading notes: " + e.getMessage());
                    }
                }

                case 3 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice, try again.");
            }

        } while (choice != 3);

        s.close();
    }
}
