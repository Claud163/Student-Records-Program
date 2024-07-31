public class Student implements Comparable<Student>{

	private String firstName;
	private String lastName;
	private String studentID;

	//default constructor
	public Student() {
		firstName = null;
		lastName = null;
		studentID = "0";
	}

	//constructor with parameters
	public Student(String firstName, String lastName, String studentID) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.studentID = studentID;
	}

	//setters
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setStudentID(String studentID) {
		this.studentID = studentID;
	}

	//getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getStudentID() {
		return studentID;
	}

	//returns all information on student
	public String toString() {
		return "Name: " + lastName + ", " + firstName + "\t --- ID #: " + studentID + "\t";
	}

	//compare the IDs of two students
	public boolean equals(Student other) {
		return this.studentID.equals(other.studentID);
	}

	//compares the names of two students
	public int compareTo(Student other) {
		int value = this.lastName.compareTo(other.lastName);
		if (value == 0) {
			value = this.firstName.compareTo(other.lastName);
		}
		return value;
	}

}