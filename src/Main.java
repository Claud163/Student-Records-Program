import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		//Student Registration Program

		System.out.println("---WELCOME TO THE STUDENT REGISTER PROGRAM---");
		System.out.println("\nInstructions: type in any of the numbers below and press enter.");
		System.out.println("\t1) Load student records into the program via a text file.");
		System.out.println("\t2) Enter a new student record manually.");
		System.out.println("\t3) Remove an existing student record from the program.");
		System.out.println("\t4) Search for a student record by ID or name.");
		System.out.println("\t5) Add a course/grade to a student's record");
		System.out.println("\t6) Display all student records in the program.");
		System.out.println("\t7) Save student records to a text file.");
		System.out.println("\t8) Quit the program, notice data may be lost unless records are saved.");
		
		Scanner scnr = new Scanner(System.in);
		SortedLinkedList list = new SortedLinkedList();
		boolean isRunning = true;
       
        while (isRunning) {
			System.out.print("\nPlease enter a option: ");
			String response = scnr.next();
            switch (response) {
                case "1": 
  					load(list, scnr); //enter student records via txt file
                    break;				
                case "2": 
                	enter(list, scnr); //enter student record manually
                    break;
                case "3": 
                	removeStudent(list, scnr); //remove existing student
					break;			
				case "4": 
					searchStudent(list, scnr); //search for student
					break;
				case "5": 
					addCourseAndGrade(list, scnr); //add a course/grade
					break;
				case "6": 
					displayRecords(list); //displays student records
					break;
             	case "7": 
					save(list); //save student record
					break;
				case "8": 
					isRunning = exit(list, isRunning, scnr); //exit program
					break;
				default:
                    System.out.println("Invalid response. Please try again.");
                    break;
                }
         }
         System.out.println("Exiting the program.");
	}

	public static void load(SortedLinkedList list, Scanner scnr) {
        System.out.println("\nINSTRUCTIONS: ");
		System.out.println("\t1) Please make sure the text file is in the same directory.");
		System.out.println("\t2) Please make sure the text file's name is \"students.txt\"");
		System.out.println("\t3) Please make sure each student is on a separate line in the following order: ");
		System.out.println("\t   firstName lastName ID# GPA# Credit# (spaces inbetween each information)");
		System.out.print("\nPress any key to confirm: ");
		scnr.next();
		System.out.println();
		try {
			File file = new File("students.txt");
			Scanner in = new Scanner(file);
			int numStudents = 0;
			while (in.hasNext()) {
				String firstName = in.next();
				String lastName = in.next();
				String id = in.next();
				double gpa = in.nextDouble();
				int credits = in.nextInt();
				StudentRecord studentRecord = new StudentRecord(firstName, lastName, id, gpa, credits);
				list.insertSorted(studentRecord);
				numStudents++;
			}
			System.out.println(numStudents + " students have transferred to the program.");
			in.close();
		}
		catch (FileNotFoundException e) {
			System.out.println("File was not found.");
		}
	}

	public static void enter(SortedLinkedList list, Scanner scnr) {
		String response = "";
		while (!response.equals("0")){
			try { 	
				System.out.print("\nPlease enter the first name: ");
				String firstName = scnr.next();
				System.out.print("Please enter the last name: ");
				String lastName = scnr.next();
				System.out.print("Please enter the id number: ");
				String id = scnr.next();
				System.out.print("Please enter the student's GPA number: ");
				double gpa = scnr.nextDouble();
				System.out.print("Please enter the number of credits taken: ");
				int credits = scnr.nextInt();
				StudentRecord studentRecord = new StudentRecord(firstName, lastName, id, gpa, credits);
				list.insertSorted(studentRecord);
				System.out.print("Enter any key to continue, otherwise, enter \"0\" if you are done: ");
				response = scnr.next();
			}
			catch (InputMismatchException e) {
				System.out.println("\nInvalid input! GPA/credits must be a number. Please start over.");
				scnr.next();
			}
		}
	}

	public static void removeStudent(SortedLinkedList list, Scanner scnr){
		if (list.isEmpty()) {
			System.out.println("You haven't entered any students yet!");
		}
		else {
			System.out.print("Please enter the ID of the student that you would like to remove: ");
			list.remove(scnr.next());
		}
	}

	public static void searchStudent(SortedLinkedList list, Scanner scnr) {
		if (list.isEmpty()) {
			System.out.println("You haven't entered any students yet!");
		}
		else {
			System.out.print("Press \"0\" if you would like to search by ID, otherwise press any other key to search by name: ");
			String response = scnr.next();
			if (response.equals("0")) {
				System.out.print("Please enter the student's ID to look them up: ");
				Student isFound = list.searchID(scnr.next());
				if (isFound == null) {
					System.out.println("Student not found!");
				}
				else {
					System.out.println("Here is the student's information: " + "\n" + isFound);
				}
			}
			else {
				System.out.print("Please enter the student's first name: ");
				String firstName = scnr.next();
				System.out.print("Please enter the student's last name: ");
				String lastName = scnr.next();
				Student isFound = list.searchName(firstName, lastName);
				if (isFound == null) {
					System.out.println("Student not found!");
				}
				else {
					System.out.println("Here is the student's information: " + "\n" + isFound);
				}
			}
		}
	}

	public static void addCourseAndGrade(SortedLinkedList list, Scanner scnr) {
		if (list.isEmpty()) {
			System.out.println("You need to enter a student into the program before entering grades!");
		}
		else {
			System.out.print("Please enter the student's ID for whom you would like to add a grade and credits to: ");
			StudentRecord isFound = list.searchID(scnr.next());
			if (isFound == null) {
				System.out.println("Student was not found!");
			}
			else {
				System.out.println("\nRecord pulled up for: " + isFound);
				System.out.print("Please enter the new grade (from 0 - 100) to be added for this student: ");
				int grade = scnr.nextInt();
				System.out.print("Please enter the number of credits for this course: ");
				int credits = scnr.nextInt();
				isFound.addGradeAndCredits(grade, credits);
				if (grade >= 0 && grade <= 100) {
					System.out.println("Student record was updated to: " + isFound);
				}
			}
		}
	}

	public static void displayRecords(SortedLinkedList list) {
		if (list.isEmpty()) {
			System.out.println("You haven't entered any students yet!");
		}
		else {
			System.out.println("\n---LIST OF STUDENTS---");
		System.out.print(list.toString());
		}
	}

	public static void save(SortedLinkedList list) {
		if (list.isEmpty()) {
			System.out.println("You haven't entered any students yet!");
		}
		else {
			try {
				PrintWriter out = new PrintWriter("saved_students.txt"); 
				out.print(list.toString());
				out.close();
				System.out.println("Your file has been saved in the same directory with the name \"saved_students.txt\"");
			}
			catch (FileNotFoundException e) {
				System.out.println("File could not be created.");
			}
		}	
	}

	public static boolean exit(SortedLinkedList list, boolean isRunning, Scanner scnr) {
		if (list.isEmpty()) {
			return false;
		}
		else {
			System.out.println("\nAre you sure you want to quit? Any unsaved data will be lost.");
			System.out.print("Enter \"0\" if you would like to quit, or enter any key to go back: ");
			String response = scnr.next();
				if (response.equals("0")) {
					return false;
				}
				else {
					return true;
				}
        }
	}
}
