package crm;

import crm.exception.CustomerNotFoundException;
import crm.service.CRMSystem;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CRMSystem crm = new CRMSystem();

    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("      Customer Relationship Management (CRM)      ");
        System.out.println("==================================================");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            try {
                switch (choice) {
                    case "1":
                        addCustomer();
                        break;
                    case "2":
                        viewAllCustomers();
                        break;
                    case "3":
                        searchCustomer();
                        break;
                    case "4":
                        updateCustomerStatus();
                        break;
                    case "5":
                        addInteraction();
                        break;
                    case "6":
                        viewInteractions();
                        break;
                    case "7":
                        deleteCustomer();
                        break;
                    case "8":
                        crm.displayCustomersSortedByName();
                        break;
                    case "9":
                        running = false;
                        System.out.println("Saving and exiting CRM Toolkit. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            } catch (CustomerNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add New Customer");
        System.out.println("2. View All Customers");
        System.out.println("3. Search Customer (by Name)");
        System.out.println("4. Update Customer Status");
        System.out.println("5. Add Interaction (Call/Email/Meeting)");
        System.out.println("6. View Customer Interactions");
        System.out.println("7. Delete Customer");
        System.out.println("8. View Customers (Sorted by Name)");
        System.out.println("9. Exit");
    }

    private static void addCustomer() {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Status (e.g., Active, Lead, Inactive): ");
        String status = scanner.nextLine();

        crm.addCustomer(name, email, phone, status);
        System.out.println("Customer added successfully!");
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- All Customers ---");
        crm.viewAllCustomers();
    }

    private static void searchCustomer() {
        System.out.print("Enter name to search: ");
        String query = scanner.nextLine();
        crm.searchCustomerByName(query);
    }

    private static void updateCustomerStatus() throws CustomerNotFoundException {
        System.out.print("Enter Customer ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter new Status: ");
        String status = scanner.nextLine();
        crm.updateCustomerStatus(id, status);
        System.out.println("Status updated successfully.");
    }

    private static void addInteraction() throws CustomerNotFoundException {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter Interaction Type (Call/Email/Meeting/Other): ");
        String type = scanner.nextLine();
        System.out.print("Enter Notes: ");
        String note = scanner.nextLine();
        crm.addInteraction(id, type, note);
        System.out.println("Interaction recorded successfully.");
    }

    private static void viewInteractions() throws CustomerNotFoundException {
        System.out.print("Enter Customer ID: ");
        int id = Integer.parseInt(scanner.nextLine());
        crm.viewInteractions(id);
    }
    
    private static void deleteCustomer() throws CustomerNotFoundException {
        System.out.print("Enter Customer ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        crm.deleteCustomer(id);
        System.out.println("Customer deleted.");
    }
}
