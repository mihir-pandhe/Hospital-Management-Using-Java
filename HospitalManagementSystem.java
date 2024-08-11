import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== Hospital Management System ===");
        System.out.println("1. Register Patient");
        System.out.println("2. View Patient Records");
        System.out.println("3. Search Patient Records");
        System.out.println("4. Manage Patient Records");
        System.out.println("5. Billing and Payment");
        System.out.println("6. Exit");
        System.out.print("Select an option: ");
    }

    private static int getUserChoice() {
        int choice = 0;
        boolean valid = false;
        while (!valid) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 1 || choice > 6) {
                    throw new InputMismatchException();
                }
                valid = true;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number between 1 and 6: ");
                scanner.nextLine();
            }
        }
        return choice;
    }

    private static void registerPatient() {
        int id = 0;
        String name;
        int age = 0;
        String gender;
        String ailment;

        try {
            System.out.print("Enter Patient ID: ");
            id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Patient Name: ");
            name = scanner.nextLine();

            System.out.print("Enter Patient Age: ");
            age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Patient Gender: ");
            gender = scanner.nextLine();

            System.out.print("Enter Patient Ailment: ");
            ailment = scanner.nextLine();

            Patient newPatient = new Patient(id, name, age, gender, ailment);
            patients.add(newPatient);

            System.out.println("Patient registered successfully.\n");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct details.");
            scanner.nextLine();
        }
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
        int id = 0;
        System.out.print("Enter Patient ID: ");
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        Patient patient = findPatientById(id);
        if (patient != null) {
            System.out.println("Patient found: " + patient);
        } else {
            System.out.println("No patient found with ID: " + id);
        }
        System.out.println();
    }

    private static void searchByName() {
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        Patient patient = findPatientByName(name);
        if (patient != null) {
            System.out.println("Patient found: " + patient);
        } else {
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
        int id = 0;
        System.out.print("Enter Patient ID to Edit: ");
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        Patient patient = findPatientById(id);
        if (patient != null) {
            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter correct details.");
                scanner.nextLine();
            }
        } else {
            System.out.println("No patient found with ID: " + id + "\n");
        }
    }

    private static void deletePatientRecords() {
        int id;
        System.out.print("Enter Patient ID to Delete: ");
        try {
            id = scanner.nextInt();
            scanner.nextLine();

            final int patientId = id;

            boolean removed = patients.removeIf(patient -> patient.getId() == patientId);

            if (removed) {
                System.out.println("Patient record deleted successfully.\n");
            } else {
                System.out.println("No patient found with ID: " + patientId + "\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void billingAndPayment() {
        int id = 0;
        System.out.print("Enter Patient ID for Billing: ");
        try {
            id = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
            return;
        }

        Patient patient = findPatientById(id);
        if (patient != null) {
            try {
                System.out.print("Enter service charges to add to the bill: ");
                double charges = scanner.nextDouble();
                scanner.nextLine();
                patient.addBillAmount(charges);

                System.out.println("Total Bill Amount: $" + patient.getBillAmount());

                System.out.print("Do you want to process the payment? (yes/no): ");
                String response = scanner.nextLine();

                if (response.equalsIgnoreCase("yes")) {
                    processPayment(patient);
                } else {
                    System.out.println("Payment not processed.\n");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        } else {
            System.out.println("No patient found with ID: " + id);
        }
    }

    private static void processPayment(Patient patient) {
        try {
            System.out.print("Enter payment amount: ");
            double payment = scanner.nextDouble();
            scanner.nextLine();

            if (payment >= patient.getBillAmount()) {
                patient.markAsPaid();
                System.out.println("Payment successful. Bill is marked as paid.\n");
            } else {
                System.out.println("Insufficient payment. Payment not processed.\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid payment amount.");
            scanner.nextLine();
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

    private static Patient findPatientByName(String name) {
        for (Patient patient : patients) {
            if (patient.getName().equalsIgnoreCase(name)) {
                return patient;
            }
        }
        return null;
    }
}
