
/* Bailey Garrett 
 * module 4 assignment 3  
 * 2/9/24  
 * MyLinkedList revised to TwoWayLinkedList
 */
import java.util.ListIterator;

public class TwoWayLinkedList<E> implements MyList<E> {
    private Node<E> head, tail;
    private int size = 0; // Number of elements in the list

    /** Create an empty list */
    public TwoWayLinkedList() {
    }

    /** Create a list from an array of objects */
    public TwoWayLinkedList(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    /** Return the head element in the list */
    public E getFirst() {
        if (size == 0) {
            return null;
        } else {
            return head.element;
        }
    }

    /** Return the last element in the list */
    public E getLast() {
        if (size == 0) {
            return null;
        } else {
            return tail.element;
        }
    }

    /** Add an element to the beginning of the list */
    public void addFirst(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node
        newNode.next = head; // link the new node with the head
        if (head != null) {
            head.previous = newNode; // update previous reference of head if head is not null
        }
        head = newNode; // head points to the new node
        if (tail == null) // The new node is the only node in list
            tail = head;
        size++; // Increase list size
    }

    /** Add an element to the end of the list */
    public void addLast(E e) {
        Node<E> newNode = new Node<>(e); // Create a new node for e

        if (tail == null) {
            head = tail = newNode; // The only node in list
        } else {
            tail.next = newNode; // Link the new node with the last node
            newNode.previous = tail; // Set previous of new node to the current tail
            tail = newNode; // tail now points to the last node
        }

        size++; // Increase size
    }

    /**
     * Add a new element at the specified index in this list. The index of the head
     * element is 0
     */
    public void add(int index, E e) {
        if (index == 0) {
            addFirst(e);
        } else if (index >= size) {
            addLast(e);
        } else {
            Node<E> current = head;
            for (int i = 1; i < index; i++) {
                current = current.next;
            }
            Node<E> temp = current.next;
            Node<E> newNode = new Node<>(e);
            current.next = newNode;
            newNode.previous = current;
            newNode.next = temp;
            temp.previous = newNode;
            size++;
        }
    }

    /**
     * Remove the head node and return the object that is contained in the removed
     * node.
     */
    public E removeFirst() {
        if (size == 0) {
            return null; // Nothing to delete
        } else {
            Node<E> temp = head; // Keep the first node temporarily
            head = head.next; // Move head to point to next node
            if (head != null) {
                head.previous = null; // remove reference to the previous node for the new head
            } else {
                tail = null; // List becomes empty
            }
            size--; // Reduce size by 1
            return temp.element; // Return the deleted element
        }
    }

    /**
     * Remove the last node and return the object that is contained in the removed
     * node.
     */
    public E removeLast() {
        if (size == 0) {
            return null; // Nothing to delete
        } else {
            Node<E> temp = tail; // Keep the last node temporarily
            tail = tail.previous; // Move tail to point to previous node
            if (tail != null) {
                tail.next = null; // remove reference to the next node for the new tail
            } else {
                head = null; // List becomes empty
            }
            size--; // Reduce size by 1
            return temp.element; // Return the deleted element
        }
    }

    /**
     * Remove the element at the specified position in this list. Return the element
     * that was removed from the list.
     */
    public E remove(int index) {
        if (index < 0 || index >= size) {
            return null; // Out of range
        } else if (index == 0) {
            return removeFirst(); // Remove first
        } else if (index == size - 1) {
            return removeLast(); // Remove last
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            size--; // Reduce size by 1
            return current.element; // Return the deleted element
        }
    }

    /** Override toString() to return elements in the list */
    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        while (current != null) {
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", "); // Separate two elements with a comma
            } else {
                result.append("]"); // Insert the closing ] in the string
            }
        }

        return result.toString();
    }

    /** Clear the list */
    public void clear() {
        size = 0;
        head = tail = null;
    }

    /** Return true if this list contains the element e */
    public boolean contains(Object e) {
        // Left as an exercise
        return true;
    }

    /** Return the element at the specified index */
    public E get(int index) {
        // Left as an exercise
        return null;
    }

    /**
     * Return the index of the first matching element in this list. Return −1 if no
     * match.
     */
    public int indexOf(Object e) {
        // Left as an exercise
        return 0;
    }

    /**
     * Return the index of the last matching element in this list. Return −1 if no
     * match.
     */
    public int lastIndexOf(E e) {
        // Left as an exercise
        return 0;
    }

    /**
     * Replace the element at the specified position in this list with the specified
     * element.
     */
    public E set(int index, E e) {
        // Left as an exercise
        return null;
    }

    /** Override iterator() defined in Iterable */
    public java.util.Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    /** Return a ListIterator starting from the beginning of the list */
    public ListIterator<E> listIterator() {
        return new LinkedListIterator();
    }

    /** Return a ListIterator starting from the specified index */
    public ListIterator<E> listIterator(int index) {
        return new LinkedListIterator(index);
    }

    private class LinkedListIterator implements ListIterator<E> {
        private Node<E> current; // Current node
        private int index = 0; // Current index

        public LinkedListIterator() {
            current = head; // Start from the head of the list
        }

        public LinkedListIterator(int index) {
            current = head; // Start from the head of the list
            for (int i = 0; i < index && current != null; i++) {
                current = current.next;
            }
            this.index = index;
        }

        public boolean hasNext() {
            return current != null;
        }

        public E next() {
            E e = current.element;
            current = current.next;
            index++;
            return e;
        }

        public boolean hasPrevious() {
            return current != null && current.previous != null;
        }

        public E previous() {
            E e = current.element;
            current = current.previous;
            index--;
            return e;
        }

        public int nextIndex() {
            return index;
        }

        public int previousIndex() {
            return index - 1;
        }

        public void remove() {
            // Left as an exercise
        }

        public void set(E e) {
            // Left as an exercise
        }

        public void add(E e) {
            // Left as an exercise
        }
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> previous;

        public Node(E e) {
            element = e;
        }
    }

    /** Return the number of elements in this list */
    public int size() {
        return size;
    }
}
