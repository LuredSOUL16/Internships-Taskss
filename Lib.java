import java.util.*;

abstract class LibraryItem {
    protected int id;
    protected String title;
    protected String author;

    public LibraryItem(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public abstract void showDetails();
}

interface Borrowable {
    void borrow(User user);
    void giveBack();
}

class Book extends LibraryItem implements Borrowable {
    private boolean borrowed;
    private User borrowedBy;

    public Book(int id, String title, String author) {
        super(id, title, author);
        this.borrowed = false;
        this.borrowedBy = null;
    }

    @Override
    public void showDetails() {
        System.out.println("Book ID: " + id + " | Title: " + title + " | Author: " + author);
        if (borrowed) {
            System.out.println("   Status: Borrowed by " + borrowedBy.getName());
        } else {
            System.out.println("   Status: Available");
        }
    }

    @Override
    public void borrow(User user) {
        if (!borrowed) {
            borrowed = true;
            borrowedBy = user;
            System.out.println("✅ \"" + title + "\" has been borrowed by " + user.getName());
        } else {
            System.out.println("❌ Sorry, \"" + title + "\" is already borrowed.");
        }
    }

    @Override
    public void giveBack() {
        if (borrowed) {
            System.out.println("📚 \"" + title + "\" has been returned by " + borrowedBy.getName());
            borrowed = false;
            borrowedBy = null;
        } else {
            System.out.println("⚠️ This book was not borrowed.");
        }
    }

    public int getId() {
        return id;
    }
}

class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public void showDetails() {
        System.out.println("User ID: " + userId + " | Name: " + name);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
        System.out.println("✅ Book added: " + book.title);
    }

    public void listBooks() {
        if (books.isEmpty()) {
            System.out.println("📚 No books in the library yet.");
        } else {
            System.out.println("\n--- Library Books ---");
            for (Book book : books) {
                book.showDetails();
            }
        }
    }

    public void addUser(User user) {
        users.add(user);
        System.out.println("👤 User registered: " + user.getName());
    }

    public void listUsers() {
        if (users.isEmpty()) {
            System.out.println("👥 No users registered yet.");
        } else {
            System.out.println("\n--- Registered Users ---");
            for (User user : users) {
                user.showDetails();
            }
        }
    }

    public void borrowBook(int bookId, int userId) {
        Book book = findBook(bookId);
        User user = findUser(userId);

        if (book != null && user != null) {
            book.borrow(user);
        } else {
            System.out.println("⚠️ Invalid book ID or user ID.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBook(bookId);
        if (book != null) {
            book.giveBack();
        } else {
            System.out.println("⚠️ Book not found.");
        }
    }

    private Book findBook(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    private User findUser(int id) {
        for (User user : users) {
            if (user.getUserId() == id) return user;
        }
        return null;
    }
}

public class Lib {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        int choice;
        do {
            System.out.println("\n===== Library Management System =====");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Add User");
            System.out.println("4. View Users");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Book ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Author: ");
                    String author = scanner.nextLine();
                    library.addBook(new Book(id, title, author));
                }
                case 2 -> library.listBooks();
                case 3 -> {
                    System.out.print("Enter User ID: ");
                    int uid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String uname = scanner.nextLine();
                    library.addUser(new User(uid, uname));
                }
                case 4 -> library.listUsers();
                case 5 -> {
                    System.out.print("Enter Book ID: ");
                    int bid = scanner.nextInt();
                    System.out.print("Enter User ID: ");
                    int uid = scanner.nextInt();
                    library.borrowBook(bid, uid);
                }
                case 6 -> {
                    System.out.print("Enter Book ID: ");
                    int bid = scanner.nextInt();
                    library.returnBook(bid);
                }
                case 7 -> System.out.println("👋 Exiting the system... Goodbye!");
                default -> System.out.println("⚠️ Invalid choice, please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
}