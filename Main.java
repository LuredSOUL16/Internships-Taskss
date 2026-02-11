// import java.util.ArrayList;
// import java.util.Scanner;

// // ---------------------------
// // 1. Abstract Class
// // ---------------------------
// abstract class LibraryItem {
//     protected int id;
//     protected String title;
//     protected String author;

//     // Constructor
//     public LibraryItem(int id, String title, String author) {
//         this.id = id;
//         this.title = title;
//         this.author = author;
//     }

//     // Abstract method (forces subclasses to implement)
//     public abstract void displayDetails();
// }

// // ---------------------------
// // 2. Interface
// // ---------------------------
// interface Borrowable {
//     void issueBook(User user);
//     void returnBook();
// }

// // ---------------------------
// // 3. Book Class
// // ---------------------------
// class Book extends LibraryItem implements Borrowable {
//     private boolean isIssued;
//     private User issuedTo;

//     public Book(int id, String title, String author) {
//         super(id, title, author); // calls parent constructor
//         this.isIssued = false;
//         this.issuedTo = null;
//     }

//     @Override
//     public void displayDetails() {
//         System.out.println("Book ID: " + id + ", Title: " + title + ", Author: " + author);
//         if (isIssued) {
//             System.out.println("   Status: Issued to " + issuedTo.getName());
//         } else {
//             System.out.println("   Status: Available");
//         }
//     }

//     @Override
//     public void issueBook(User user) {
//         if (!isIssued) {
//             this.isIssued = true;
//             this.issuedTo = user;
//             System.out.println("Book \"" + title + "\" issued to " + user.getName());
//         } else {
//             System.out.println("Book \"" + title + "\" is already issued.");
//         }
//     }

//     @Override
//     public void returnBook() {
//         if (isIssued) {
//             System.out.println("Book \"" + title + "\" returned by " + issuedTo.getName());
//             this.isIssued = false;
//             this.issuedTo = null;
//         } else {
//             System.out.println("Book \"" + title + "\" is not issued.");
//         }
//     }

//     public int getId() {
//         return id;
//     }
// }

// // ---------------------------
// // 4. User Class
// // ---------------------------
// class User {
//     private int userId;
//     private String name;

//     public User(int userId, String name) {
//         this.userId = userId;
//         this.name = name;
//     }

//     public void displayDetails() {
//         System.out.println("User ID: " + userId + ", Name: " + name);
//     }

//     public int getUserId() {
//         return userId;
//     }

//     public String getName() {
//         return name;
//     }
// }

// // ---------------------------
// // 5. Library Class
// // ---------------------------
// class Library {
//     private ArrayList<Book> books;
//     private ArrayList<User> users;

//     public Library() {
//         books = new ArrayList<>();
//         users = new ArrayList<>();
//     }

//     // CRUD for books
//     public void addBook(Book b) {
//         books.add(b);
//         System.out.println("Book added successfully.");
//     }

//     public void viewBooks() {
//         if (books.isEmpty()) {
//             System.out.println("No books in library.");
//         } else {
//             for (Book b : books) {
//                 b.displayDetails();
//             }
//         }
//     }

//     // CRUD for users
//     public void addUser(User u) {
//         users.add(u);
//         System.out.println("User added successfully.");
//     }

//     public void viewUsers() {
//         if (users.isEmpty()) {
//             System.out.println("No users registered.");
//         } else {
//             for (User u : users) {
//                 u.displayDetails();
//             }
//         }
//     }

//     // Issue and Return
//     public void issueBook(int bookId, int userId) {
//         Book book = findBookById(bookId);
//         User user = findUserById(userId);

//         if (book != null && user != null) {
//             book.issueBook(user);
//         } else {
//             System.out.println("Invalid book or user ID.");
//         }
//     }

//     public void returnBook(int bookId) {
//         Book book = findBookById(bookId);
//         if (book != null) {
//             book.returnBook();
//         } else {
//             System.out.println("Book not found.");
//         }
//     }

//     // Helpers
//     private Book findBookById(int id) {
//         for (Book b : books) {
//             if (b.getId() == id) return b;
//         }
//         return null;
//     }

//     private User findUserById(int id) {
//         for (User u : users) {
//             if (u.getUserId() == id) return u;
//         }
//         return null;
//     }
// }

// // ---------------------------
// // 6. Main Program
// // ---------------------------
// public class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         Library library = new Library();

//         int choice;
//         do {
//             System.out.println("\n===== Library Management System =====");
//             System.out.println("1. Add Book");
//             System.out.println("2. View Books");
//             System.out.println("3. Add User");
//             System.out.println("4. View Users");
//             System.out.println("5. Issue Book");
//             System.out.println("6. Return Book");
//             System.out.println("7. Exit");
//             System.out.print("Enter choice: ");
//             choice = scanner.nextInt();

//             switch (choice) {
//                 case 1 -> {
//                     System.out.print("Enter Book ID: ");
//                     int id = scanner.nextInt();
//                     scanner.nextLine();
//                     System.out.print("Enter Title: ");
//                     String title = scanner.nextLine();
//                     System.out.print("Enter Author: ");
//                     String author = scanner.nextLine();
//                     library.addBook(new Book(id, title, author));
//                 }
//                 case 2 -> library.viewBooks();
//                 case 3 -> {
//                     System.out.print("Enter User ID: ");
//                     int uid = scanner.nextInt();
//                     scanner.nextLine();
//                     System.out.print("Enter Name: ");
//                     String uname = scanner.nextLine();
//                     library.addUser(new User(uid, uname));
//                 }
//                 case 4 -> library.viewUsers();
//                 case 5 -> {
//                     System.out.print("Enter Book ID: ");
//                     int bid = scanner.nextInt();
//                     System.out.print("Enter User ID: ");
//                     int uid = scanner.nextInt();
//                     library.issueBook(bid, uid);
//                 }
//                 case 6 -> {
//                     System.out.print("Enter Book ID: ");
//                     int bid = scanner.nextInt();
//                     library.returnBook(bid);
//                 }
//                 case 7 -> System.out.println("Exiting system...");
//                 default -> System.out.println("Invalid choice.");
//             }
//         } while (choice != 7);

//         scanner.close();
//     }
// }
