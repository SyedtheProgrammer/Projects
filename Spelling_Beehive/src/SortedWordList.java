public class SortedWordList extends WordList {

    public SortedWordList() {//calling it without passing any parameter
        super();

    }


    public SortedWordList(UnsortedWordList unsortedList) {
        super();

        // Iterate over the unsorted list and add words to the sorted list

        WordNode current = unsortedList.first.next; // Skip the dummy head node
        while (current != null) {
            add(current.data); // Add each word to the sorted list
            current = current.next;

        }

    }
    public void add(Word word) {
        WordNode newNode = new WordNode(word);
        WordNode current = first.next;
        WordNode prev = first;

        while (current != null && word.getdata().compareTo(current.data.getdata()) > 0) {
            prev = current;
            current = current.next;
        }

        prev.next = newNode;
        newNode.next = current;
        length++;

    }

}