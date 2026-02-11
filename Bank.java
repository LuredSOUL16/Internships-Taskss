import java.util.*;

class Account {
    private int accNumber;
    private String holderName;
    private double balance;
    private List<String> transactions;

    public Account(int accNumber, String holderName, double initialBalance) {
        this.accNumber = accNumber;
        this.holderName = holderName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        transactions.add("Account created with balance: " + initialBalance);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactions.add("Deposited: " + amount);
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactions.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void showBalance() {
        System.out.println("Current balance: " + balance);
    }

    public void showTransactions() {
        System.out.println("Transaction history:");
        for (String t : transactions) {
            System.out.println("- " + t);
        }
    }

    public int getAccNumber() {
        return accNumber;
    }

    public String getHolderName() {
        return holderName;
    }
}

public class Bank {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Account account = null;
        int choice;

        do {
            System.out.println("\n===== Bank Account Simulation =====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. View Balance");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    if (account == null) {
                        System.out.print("Enter account number: ");
                        int accNo = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter holder name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter initial balance: ");
                        double balance = scanner.nextDouble();
                        account = new Account(accNo, name, balance);
                        System.out.println("Account created for " + name);
                    } else {
                        System.out.println("Account already exists.");
                    }
                }
                case 2 -> {
                    if (account != null) {
                        System.out.print("Enter amount to deposit: ");
                        double amt = scanner.nextDouble();
                        account.deposit(amt);
                    } else {
                        System.out.println("No account found. Create one first.");
                    }
                }
                case 3 -> {
                    if (account != null) {
                        System.out.print("Enter amount to withdraw: ");
                        double amt = scanner.nextDouble();
                        account.withdraw(amt);
                    } else {
                        System.out.println("No account found. Create one first.");
                    }
                }
                case 4 -> {
                    if (account != null) account.showBalance();
                    else System.out.println("No account found.");
                }
                case 5 -> {
                    if (account != null) account.showTransactions();
                    else System.out.println("No account found.");
                }
                case 6 -> System.out.println("Exiting...");
                default -> System.out.println("Invalid choice.");
            }
        } while (choice != 6);

        scanner.close();
    }
}