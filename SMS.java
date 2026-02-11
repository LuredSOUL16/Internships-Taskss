import java.util.ArrayList;
import java.util.Scanner;

public class SMS {
    public static void main(String x[]){

        Scanner scanner = new Scanner(System.in);
        ArrayList<student> data = new ArrayList<>();
        int choice =0;

        System.out.println("\t\t\t****Welcome to the Student Management System****");

        while(choice<5){
            System.out.printf("\n\n1. Add Student \n2. View Students \n3. Update Student \n4. Delete Student \n5. Exit \nEnter your choice:  ");
            choice =scanner.nextInt();

        
            switch (choice) {
                case 1 -> {
                    student s = new student(); 
                    s.enter();                 
                    data.add(s);
                }
                case 2 -> {
                    for (student s : data) {
                        s.display();
                    }
                }
                case 3 -> {
                    System.out.print("Enter ID of student whose data needs to be updated : ");
                    int Id = scanner.nextInt();
                    scanner.nextLine(); // consume newline

                    for (student s : data) {
                        if (s.id == Id) {
                            s.update(); 
                            break;
                        }
                    }
                }
                case 4 -> {
                    System.out.print("Enter ID of student whose data needs to be deleted : ");
                    int Id = scanner.nextInt();
                    for (student s : data) {
                        if (s.id == Id) {
                            data.remove(s); 
                            System.out.println("The data student id "+s.id+" has been deleted");
                            break;
                        }
                    }
                }
                default -> System.out.printf("\nExiting Student Management System......................................");
            }    
        }
    }  
}

class student{
    int id;
    String name;
    int marks;

    public void display(){
        System.out.printf("\nThe data of Student ");
        System.out.printf("\n1\tName : %s  \n\tMarks obtained : %d \n\tStudent ID :%d ", name, marks, id);
    }
    
    public  void enter() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the student :");
        name = scanner.nextLine();

        System.out.print("Enter the unique  student ID :");
        id = scanner.nextInt();

        System.out.print("Enter the marks of the student :");
        marks = scanner.nextInt(); 
    }

    public void update(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter new details for student with ID " + id + ":");

        System.out.printf("\nNew Name: ");
        name = scanner.nextLine();

        System.out.println("New Marks: ");
        marks = scanner.nextInt();

        System.out.println("New Roll Number: ");
        id = scanner.nextInt();
    }

}
