public class SortedLinkedList {

	private LNode head;	

	//create a dummy head node
	public SortedLinkedList() {
		head = new LNode();	
	}

	//checks if list is empty
	public boolean isEmpty() {
		return head.getNext() == null;
	}

	//prints the list
	public String toString() {
		String temp = "";			
		LNode curr = head.getNext();		
		while (curr != null) {						
			temp += curr.getData().toString();		
			curr = curr.getNext();						
		}
		temp += "\n";
		return temp;				
	}

	//insert sorts the students into the list
	public void insertSorted(StudentRecord student) {
		LNode curr = head.getNext(); 
		LNode prev = head;		
		while (curr != null && student.compareTo(curr.getData()) > 0) {
			prev = curr;		
			curr = curr.getNext();
		}
		curr = new LNode(student, curr); 
		prev.setNext(curr); 
	}

	//search for the student by their ID and returns the student data
	public StudentRecord searchID(String id) {
		LNode curr = head.getNext();							
		while (curr != null){							
			if (curr.getData().getStudentID().equals(id)) {		
				return curr.getData();							
			}
			curr = curr.getNext();								
		}
		return null;
	}

	//search for the student by their first and last name and returns the student data
	public StudentRecord searchName(String firstName, String lastName) {
		LNode curr = head.getNext();
		while (curr != null) {
			if (curr.getData().getFirstName().equals(firstName)) {
				if (curr.getData().getLastName().equals(lastName)) {
					return curr.getData();
				}
			}
			curr = curr.getNext();
		}
		return null;
	}

	//removes the student record from the list
	public void remove(String id) {
		LNode curr = head.getNext();
		LNode prev = head;
		boolean found = false;
		while (curr != null) {
			if (curr.getData().getStudentID().equals(id)) {
				prev.setNext(curr.getNext());
				System.out.println("Student record was successfully removed.");
				found = true;
			}
			prev = curr;
			curr = curr.getNext();
		} 
		if (!found) {
			System.out.println("This student record was not found.");
		}
	}

}