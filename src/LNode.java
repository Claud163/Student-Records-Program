public class LNode {

	private StudentRecord data;	
	private LNode next;		

	//default and parameter constructors
	public LNode() {
		data = null;
		next = null; 
	}

	public LNode(StudentRecord data) {
		this.data = data;
	}

	public LNode(StudentRecord data, LNode next) {
		this.data = data;
		this.next = next;
	}

	//setters
	public void setData(StudentRecord data) {
		this.data = data;
	}

	public void setNext(LNode next) {
		this.next = next;
	}

	//getters
	public StudentRecord getData() {
		return data;
	}

	public LNode getNext() {
		return next;
	}

}