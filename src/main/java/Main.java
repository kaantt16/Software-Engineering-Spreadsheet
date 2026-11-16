import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Spreadsheet spreadsheet = new Spreadsheet();
        boolean running = true;

        while (running) {
            printMenu();
            String option = scanner.nextLine();

            try {
                switch (option) {
                        case "1":
                            spreadsheet = new Spreadsheet();
                            System.out.println("New spreadsheet created.");
                            break;

                        case "2":
                            System.out.print("Enter cell coordinate: ");
                            String position = scanner.nextLine();
                            System.out.print("Enter cell content: ");
                            String content = scanner.nextLine();
                            spreadsheet.setCell(position, content);
                            System.out.println("Cell updated successfully.");
                            break;

                        case "3":
                            System.out.print("Enter cell coordinate: ");
                            String cellPosition = scanner.nextLine();
                            String cellContent = spreadsheet.getCellContent(cellPosition);
                            System.out.println("Content: " + cellContent);
                            break;

                        case "4":
                            spreadsheet.display();
                            break;

                        case "5":
                            System.out.print("Enter file path: ");
                            String loadPath = scanner.nextLine();
                            spreadsheet = S2VFileManager.load(loadPath);
                            System.out.println("Spreadsheet loaded.");
                            break;

                        case "6":
                            System.out.print("Enter file path: ");
                            String savePath = scanner.nextLine();
                            S2VFileManager.save(spreadsheet, savePath);
                            System.out.println("Spreadsheet saved.");
                            break;

                        case "7":
                            running = false;
                            System.out.println("Goodbye!");
                            break;

                        default:
                            System.out.println("Invalid option.");
                }
            } catch (IOException | IllegalArgumentException exception) {
                System.out.println("Error: " + exception.getMessage());
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n===== SPREADSHEET APPLICATION =====");
        System.out.println("1. Create Spreadsheet");
        System.out.println("2. Set Cell Content");
        System.out.println("3. Get Cell Content");
        System.out.println("4. Display Spreadsheet");
        System.out.println("5. Load Spreadsheet");
        System.out.println("6. Save Spreadsheet");
        System.out.println("7. Exit");
        System.out.print("Choose an option: ");
    }
}
