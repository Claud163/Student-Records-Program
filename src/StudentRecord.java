public class StudentRecord extends Student {
	
	private double gpa;
	private int credits;

	StudentRecord() {
		super();
		gpa = 0.0;
		credits = 0;
	}

	StudentRecord(String firstName, String lastName, String studentID, double gpa, int credits) {
		super(firstName, lastName, studentID);
		this.gpa = gpa;
		this.credits = credits;
	}

	public String toString() {
		String gpa = String.format("%.2f", this.gpa); //format gpa to two decimal places
		return super.toString() + " [Student Record - GPA: " + gpa + ", Credits: " + this.credits + "]\n";
	}

	public void setGPA(double gpa) {
		this.gpa = gpa;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public double getGPA() {
		return gpa;
	}

	public int getCredits() {
		return credits;
	}

	public void addCredits(int credits) {
		this.credits += credits;
	}

	public void calculateGPA(double gpa, int credits) {
		this.gpa = ((this.credits * this.gpa) + (credits * gpa)) / (this.credits + credits);
		this.credits += credits;
	}

	public void addGradeAndCredits(int grade, int credits) {
		if (grade <= 100 && grade >= 93) calculateGPA(4.0, credits);
		else if (grade <= 92 && grade >= 90) calculateGPA(3.7, credits);
		else if (grade <= 89 && grade >= 87) calculateGPA(3.3, credits);
		else if (grade <= 86 && grade >= 83) calculateGPA(3.0, credits);
		else if (grade <= 82 && grade >= 80) calculateGPA(2.7, credits);
		else if (grade <= 79 && grade >= 77) calculateGPA(2.3, credits);
		else if (grade <= 76 && grade >= 73) calculateGPA(2.2, credits);
		else if (grade <= 72 && grade >= 70) calculateGPA(1.7, credits);
		else if (grade <= 69 && grade >= 67) calculateGPA(1.3, credits);
		else if (grade <= 66 && grade >= 60) calculateGPA(1.0, credits);
		else if (grade <= 59 && grade >= 0) calculateGPA(0.0, credits);
		else System.out.println("Invalid input! The record was not updated. Grades must be between 0 - 100.");
	}

}

