public class SortedLinkedList implements SortedList {
    private Node head;
    private Node tail;
    private int size;
    private boolean ascendingOrder;

    public SortedLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.ascendingOrder = true; // Default to ascending order
    }

    public int size() {
        return size;
    }

    public void add(String string) {
        if (isPresent(string) == false) {
            Node newNode = new Node(string);
            add(newNode);
        }
    }

    public void add(Node node) {
        if (head == null) {
            head = node;
            tail = node;
        } else {
            Node current = head;
            while (current != null && compareStrings(node.getString(), current.getString()) > 0) {
                current = current.getNext();
            }
            if (current == head) {
                node.setNext(head);
                head.setPrev(node);
                head = node;
            } else if (current == null) {
                tail.setNext(node);
                node.setPrev(tail);
                tail = node;
            } else {
                node.setPrev(current.getPrev());
                node.setNext(current);
                current.getPrev().setNext(node);
                current.setPrev(node);
            }
        }
        size++;
    }

    public Node getFirst() {
        return head;
    }
    
    public Node getLast() {
        return tail;
    }
        

    public Node get(int index) {
        if (index < 0 || index >= size) {
            return null; 
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public boolean isPresent(String string) {
        Node current = head;
        while (current != null) {
            String currentString = current.getString().toUpperCase();
            String stringU = string.toUpperCase();
            if (currentString.equals(stringU)) {
                return true;
            }
            current = current.getNext();
        }
        return false;
    }

    public boolean removeFirst() {
        if (head == null) {
            return false;
        }
        head = head.getNext();
        if (head != null) {
            head.setPrev(null);
        } else {
            tail = null;
        }
        size--;
        return true;
    }

    public boolean removeLast() {
        if (tail == null) {
            return false;
        }
        tail = tail.getPrev();
        if (tail != null) {
            tail.setNext(null);
        } else {
            head = null;
        }
        size--;
        return true;
    }

    public boolean remove(int index) {
        if (index < 0 || index >= size) {
            return false;
        }
        if (index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        } else {
            Node current = get(index);
            current.getPrev().setNext(current.getNext());
            current.getNext().setPrev(current.getPrev());
            size--;
            return true;
        }
    }

    public boolean remove(String string) {
        Node current = head;
        while (current != null) {
            String currentU = current.getString().toUpperCase();
            String stringU = string.toUpperCase();
            if (currentU.equals(stringU)) {
                if (current == head) {
                    return removeFirst();
                } else if (current == tail) {
                    return removeLast();
                } else {
                    current.getPrev().setNext(current.getNext());
                    current.getNext().setPrev(current.getPrev());
                    size--;
                    return true;
                }
            }
            current = current.getNext();
        }
        return false;
    }

    public void orderAscending() {
        if (ascendingOrder == false) {
            ascendingOrder = true;
            reverseList();
        }
    }

    public void orderDescending() {
        if (ascendingOrder == true) {
            ascendingOrder = false;
            reverseList();
        }
    }

    private void reverseList() {
        Node current = head;
        while (current != null) {
            Node temp = current.getNext();
            current.setNext(current.getPrev());
            current.setPrev(temp);
            current = temp;
        }
        Node temp = head;
        head = tail;
        tail = temp;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.getString());
            current = current.getNext();
        }
    }
    

    private int compareStrings(String str1, String str2) {
        int minLength = 0;
        int length1 = str1.length();
        int length2 = str2.length();
        String str1U = str1.toUpperCase();
        String str2U = str2.toUpperCase();
        if (length1 < length2){
            minLength = str1.length();
        } else {
            minLength = str2.length();
        }

        for (int i = 0; i < minLength; i++) {
            char char1 = str1U.charAt(i);
            char char2 = str2U.charAt(i);
            if (char1 != char2) {
                if (ascendingOrder) {
                    return (char1 - char2);
                } else {
                    return (char2 - char1);
                }
            }
        }
        return str1.length() - str2.length();
    }
}    
