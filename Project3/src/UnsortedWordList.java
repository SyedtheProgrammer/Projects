public class UnsortedWordList extends WordList {

    public UnsortedWordList() {
        super();
    }

    public void add(Word word) { 
        WordNode newNode = new WordNode(word);
        last.next = newNode;
        last = newNode;
        length++;
    }

}