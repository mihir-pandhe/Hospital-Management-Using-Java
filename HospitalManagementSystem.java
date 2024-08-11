import java.util.Scanner;

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Hospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. View Patient Records");
            System.out.println("3. Search Patient Records");
            System.out.println("4. Manage Patient Records");
            System.out.println("5. Billing and Payment");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("Register Patient functionality will be implemented in Version 2.");
                    break;
                case 2:
                    System.out.println("View Patient Records functionality will be implemented in Version 3.");
                    break;
                case 3:
                    System.out.println("Search Patient Records functionality will be implemented in Version 4.");
                    break;
                case 4:
                    System.out.println("Manage Patient Records functionality will be implemented in Version 5.");
                    break;
                case 5:
                    System.out.println("Billing and Payment functionality will be implemented in Version 6.");
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
