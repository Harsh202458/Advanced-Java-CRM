package crm.service;

import crm.exception.CustomerNotFoundException;
import crm.model.Customer;
import crm.model.Interaction;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CRMSystem {
    private List<Customer> customers;
    private DataManager dataManager;
    private int nextId;

    public CRMSystem() {
        this.dataManager = new DataManager();
        this.customers = dataManager.loadCustomers();
        this.nextId = customers.stream()
                .mapToInt(Customer::getId)
                .max()
                .orElse(0) + 1;
    }

    public void addCustomer(String name, String email, String phone, String status) {
        Customer customer = new Customer(nextId++, name, email, phone, status);
        customers.add(customer);
        saveData();
    }

    public void viewAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        customers.forEach(System.out::println);
    }

    public Customer searchCustomerById(int id) throws CustomerNotFoundException {
        return customers.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found."));
    }

    public void searchCustomerByName(String name) {
        List<Customer> results = customers.stream()
                .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());

        if (results.isEmpty()) {
            System.out.println("No customers found matching: " + name);
        } else {
            results.forEach(System.out::println);
        }
    }

    public void updateCustomerStatus(int id, String newStatus) throws CustomerNotFoundException {
        Customer c = searchCustomerById(id);
        c.setStatus(newStatus);
        saveData();
    }

    public void addInteraction(int id, String type, String note) throws CustomerNotFoundException {
        Customer c = searchCustomerById(id);
        c.addInteraction(new Interaction(type, note));
        saveData();
    }

    public void viewInteractions(int id) throws CustomerNotFoundException {
        Customer c = searchCustomerById(id);
        System.out.println("Interactions for " + c.getName() + ":");
        List<Interaction> interactions = c.getInteractions();
        if (interactions.isEmpty()) {
            System.out.println("No interactions found.");
        } else {
            interactions.forEach(System.out::println);
        }
    }
    
    public void deleteCustomer(int id) throws CustomerNotFoundException {
        Customer c = searchCustomerById(id);
        customers.remove(c);
        saveData();
    }

    // Advanced feature using Streams
    public void displayCustomersSortedByName() {
        customers.stream()
                .sorted(Comparator.comparing(Customer::getName))
                .forEach(System.out::println);
    }

    public void saveData() {
        dataManager.saveCustomers(customers);
    }

    public List<Customer> getCustomers() {
        return customers;
    }
}
Pressing key...Clicking...Stopping...

Stop Agent
