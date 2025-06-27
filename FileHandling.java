package rePractice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class FileHandling {
    private static Scanner sc = new Scanner(System.in);

    public static void addNote() {
        System.out.println("Enter file name:");
        String fileName = sc.nextLine();
        fileName += ".txt";

        try {
            File f = new File(fileName);
            if (f.createNewFile()) {
                System.out.println("File Created: " + f.getName());
               
            } else {
                System.out.println("File already exists: " + f.getName());
                System.out.println(f.getAbsolutePath());
                return;
            }

            FileWriter fw = new FileWriter(fileName, true);
            System.out.println("Enter your notes:");
            String notes = sc.nextLine();
            fw.write(notes + "\n");
            fw.close();
            System.out.println("File saved!");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void viewNote() {
        System.out.print("Enter note name (without .txt): ");
        String fileName = sc.nextLine();

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
            String line;
            System.out.println("\n--- Content of " + fileName + " ---");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Note not found.");
        }
    }

    public static void searchNote() {
        System.out.print("Enter keyword to search: ");
        String keyword = sc.nextLine();
        File folder = new File(".");
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        boolean found = false;
        for (File file : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    if (line.contains(keyword)) {
                        System.out.println("Found in: " + file.getName());
                        found = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (!found) {
            System.out.println("No notes found with that keyword.");
        }
    }

    public static void deleteNote() {
        System.out.print("Enter note name to delete (without .txt): ");
        String fileName = sc.nextLine();
        File file = new File(fileName + ".txt");

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("Note deleted successfully.");
            } else {
                System.out.println("Failed to delete the note.");
            }
        } else {
            System.out.println("Note not found.");
        }
    }

    public static void main(String[] args) {
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nWelcome to Notes App");
            System.out.println("1. Add New Note");
            System.out.println("2. View Note");
            System.out.println("3. Search Note");
            System.out.println("4. Delete Note");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    addNote();
                    break;
                case 2:
                    viewNote();
                    break;
                case 3:
                    searchNote();
                    break;
                case 4:
                    deleteNote();
                    break;
                case 5:
                    keepRunning = false;
                    System.out.println("Exiting Notes App. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

