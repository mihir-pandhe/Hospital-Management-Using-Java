import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Patient {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String ailment;

    public Patient(int id, String name, int age, String gender, String ailment) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.ailment = ailment;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Age: " + age + ", Gender: " + gender + ", Ailment: " + ailment;
    }
}

public class HospitalManagementSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Patient> patients = new ArrayList<>();

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

        int choice = scanner.nextInt();
        scanner.nextLine();

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

        int choice = scanner.nextInt();
        scanner.nextLine();

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

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getId() == id) {
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
                found = true;
                break;
            }
        }
        if (!found) {
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
}
