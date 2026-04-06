# Customer Relationship Management (CRM) Toolkit

A core Java application designed to manage customer details, statuses, and interactions efficiently. This project demonstrates foundational and advanced Java programming concepts.

## Features
- **Customer Management**: Add, view, search, and delete customers.
- **Status Tracking**: Keep track of customer status (Active, Inactive, Lead).
- **Interaction Logs**: Log calls, emails, and meetings associated with each customer to keep a history of engagement.
- **Data Persistence**: Uses Java Object Serialization to securely store and load all CRM data into a local file (`crm_data.ser`), guaranteeing no data loss between sessions.
- **Advanced Sorting & Searching**: Integrates Java Streams for robust sorting and pattern matching.

## Technologies Used
- **Language**: Java
- **Concepts Demonstrated**:
  - File I/O and Serialization (`ObjectInputStream`, `ObjectOutputStream`)
  - Java Collections Framework (`List`, `ArrayList`)
  - Java Stream API (`stream().filter()`, `stream().sorted()`)
  - Custom Exception Handling (`CustomerNotFoundException`)
  - Object-Oriented Principles (Encapsulation, Polymorphism)

## How to Run

1. Make sure you have the Java Development Kit (JDK) installed.
2. Open a terminal or command prompt in the `src` directory of the project.
3. Compile the Java files:
   ```bash
   javac crm/model/*.java crm/exception/*.java crm/service/*.java crm/Main.java
   ```
4. Run the main application:
   ```bash
   java crm.Main
   ```
5. Follow the on-screen console menu to operate the CRM. You can safely close the application at any time, and your data will be saved locally in `crm_data.ser`.
