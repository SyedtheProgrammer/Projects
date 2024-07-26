public abstract class WordList {
	WordNode first; 
	WordNode last;
	int length;

	
	public WordList() {
		first = new WordNode(null); //Dummy node
		last = first;  //last node
		length = 0; // Number of data items in list
	}


	public int getLength() {
		return length;	
		}

	
	public abstract void add(Word d);


	//Appends a String data element to this linked list
	public void append(String d) {
		WordNode newNode = new WordNode(new Word(d));
		last.next = newNode;
		last = newNode;
		length++;

	}

	public void prepend(Word d) {
		WordNode newNode = new WordNode(d);
		first.next = newNode;
		if(first == last) last = newNode;
		length++;

	}
	public String toString() {

		StringBuilder p = new StringBuilder();
		WordNode current = first.next; // Skip the dummy head node
		while (current != null) {
	            p.append(current.data.getdata()).append("\n"); // Append word data to StringBuilder
	            current = current.next;
	        }
	        return p.toString(); // Convert StringBuilder to String and return
	    }
}