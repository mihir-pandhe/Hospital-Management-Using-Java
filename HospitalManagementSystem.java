import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    registerPatient();
                    break;
                case 2:
                    viewPatientRecords();
                    break;
                case 3:
                    searchPatientRecords();
                    break;
                case 4:
                    managePatientRecords();
                    break;
                case 5:
                    billingAndPayment();
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

    private static void displayMenu() {
        System.out.println("Hospital Management System");
        System.out.println("1. Register Patient");
        System.out.println("2. View Patient Records");
        System.out.println("3. Search Patient Records");
        System.out.println("4. Manage Patient Records");
        System.out.println("5. Billing and Payment");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    private static void registerPatient() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Patient Gender: ");
        String gender = scanner.nextLine();

        System.out.print("Enter Patient Ailment: ");
        String ailment = scanner.nextLine();

        Patient newPatient = new Patient(id, name, age, gender, ailment);
        patients.add(newPatient);

        System.out.println("Patient registered successfully.\n");
    }

    private static void viewPatientRecords() {
        if (patients.isEmpty()) {
            System.out.println("No patient records found.\n");
        } else {
            System.out.println("Patient Records:");
            for (Patient patient : patients) {
                System.out.println(patient);
            }
            System.out.println();
        }
    }

    private static void searchPatientRecords() {
        System.out.println("Search Patient Records");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.print("Select an option: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByName();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void searchById() {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                System.out.println("Patient found: " + patient);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No patient found with ID: " + id);
        }
        System.out.println();
    }

    private static void searchByName() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                System.out.println("Patient found: " + patient);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("No patient found with name: " + name);
        }
        System.out.println();
    }

    private static void managePatientRecords() {
        System.out.println("Manage Patient Records");
        System.out.println("1. Edit Patient Details");
        System.out.println("2. Delete Patient Records");
        System.out.print("Select an option: ");

        int choice = getUserChoice();

        switch (choice) {
            case 1:
                editPatientDetails();
                break;
            case 2:
                deletePatientRecords();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private static void editPatientDetails() {
        System.out.print("Enter Patient ID to Edit: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Patient patient = findPatientById(id);
        if (patient != null) {
            System.out.print("Enter New Name: ");
            String newName = scanner.nextLine();
            patient.setName(newName);

            System.out.print("Enter New Age: ");
            int newAge = scanner.nextInt();
            scanner.nextLine();
            patient.setAge(newAge);

            System.out.print("Enter New Gender: ");
            String newGender = scanner.nextLine();
            patient.setGender(newGender);

            System.out.print("Enter New Ailment: ");
            String newAilment = scanner.nextLine();
            patient.setAilment(newAilment);

            System.out.println("Patient details updated successfully.\n");
        } else {
            System.out.println("No patient found with ID: " + id + "\n");
        }
    }

    private static void deletePatientRecords() {
        System.out.print("Enter Patient ID to Delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = patients.removeIf(patient -> patient.getId() == id);

        if (removed) {
            System.out.println("Patient record deleted successfully.\n");
        } else {
            System.out.println("No patient found with ID: " + id + "\n");
        }
    }

    private static void billingAndPayment() {
        System.out.print("Enter Patient ID for Billing: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Patient patient = findPatientById(id);
        if (patient != null) {
            System.out.println("Enter service charges to add to the bill:");
            double charges = scanner.nextDouble();
            scanner.nextLine();
            patient.addBillAmount(charges);

            System.out.println("Total Bill Amount: $" + patient.getBillAmount());

            System.out.println("Do you want to process the payment? (yes/no): ");
            String response = scanner.nextLine();

            if (response.equalsIgnoreCase("yes")) {
                processPayment(patient);
            } else {
                System.out.println("Payment not processed.\n");
            }
        } else {
            System.out.println("No patient found with ID: " + id);
        }
    }

    private static void processPayment(Patient patient) {
        System.out.println("Enter payment amount: ");
        double payment = scanner.nextDouble();
        scanner.nextLine();

        if (payment >= patient.getBillAmount()) {
            patient.markAsPaid();
            System.out.println("Payment successful. Bill is marked as paid.\n");
        } else {
            System.out.println("Insufficient payment. Payment not processed.\n");
        }
    }

    private static Patient findPatientById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        return null;
    }
}
