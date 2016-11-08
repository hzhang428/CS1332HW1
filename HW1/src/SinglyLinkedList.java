import java.util.Collection;

/**
 * Your implementation of a SinglyLinkedList.
 *
 * @author Hao Zhang
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {

    // DO NOT ALTER OR ADD INSTANCE VARIABLES
    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size;

    /**
     * Creates a new node with data {@code data} and adds the node to the
     * end of the Linked List.  The tail should be the new node.
     *
     * Should be O(1).
     *
     * @param data The data the new node should hold.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (head == null && tail == null) {
            head = new LinkedListNode<>(data, null);
            tail = head;
            size += 1;
        } else {
            LinkedListNode<T> node = new LinkedListNode<>(data, null);
            tail.setNext(node);
            tail = node;
            size += 1;
        }
    }

    /**
     * Adds the element to the index specified.  If I were to add to index i
     * and get the data at index i, I should get the newly added data.
     *
     * Should be O(1) if index is 0 or {@code size}.
     * Should be O(n) otherwise.
     *
     * @param index The index where you want the new element.
     * @param data Any object of type T.
     * @throws java.lang.IndexOutOfBoundsException if index is negative
     * or index > size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public void addToIndex(int index, T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            if (head == null) {
                head = new LinkedListNode<>(data, null);
                tail = head;
                size += 1;
            } else {
                LinkedListNode<T> node = new LinkedListNode<>(data, null);
                node.setNext(head);
                head = node;
                size += 1;
            }
        } else if (index == size) {
            LinkedListNode<T> node = new LinkedListNode<>(data, null);
            tail.setNext(node);
            tail = node;
            size += 1;
        } else {
            LinkedListNode<T> node = new LinkedListNode<>(data, null);
            LinkedListNode<T> temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.getNext();
            }
            node.setNext(temp.getNext());
            temp.setNext(node);
            size += 1;
        }
    }

    /**
     * Creates a new node with {@code data} and adds it to the front of the
     * linked list.  This new node should be the new head of the list.
     *
     * Should be O(1).
     *
     * @param data The data that the new node should hold.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (head == null) {
            head = new LinkedListNode<T>(data, null);
            tail = head;
            size += 1;
        } else {
            LinkedListNode<T> node = new LinkedListNode<>(data, null);
            node.setNext(head);
            head = node;
            size += 1;
        }
    }

    /**
     * Appends all the data from the collection in order of appearance in the
     * collection.
     *
     * @param collection The collection with all the data being added to the
     * Linked list.
     * @throws java.lang.IllegalArgumentException if collection is null.
     */
    @Override
    public void addAll(Collection<T> collection) {
        if (collection == null) {
            throw new IllegalArgumentException();
        }
        for (T element : collection) {
            add(element);
        }
    }

    /**
     * Returns whether the Linked List contains {@code data}.
     *
     * Should be O(1) if data is in head or tail.
     * Should be O(n) otherwise.
     *
     * @param data The data to check in the Linked List
     * @return True if data is found. False otherwise.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public boolean contains(T data) {
        LinkedListNode<T> temp = head;
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (head == null) {
            return false;
        } else if (head.getData() == data) {
            return true;
        } else {
            for (int i = 0; i < size - 1; i++) {
                temp = temp.getNext();
                if (temp.getData() == data) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the element at the given index.
     *
     * Should be O(1) if index is 0 or {@code size} - 1.
     * Should be O(n) otherwise.
     *
     * @param index The index of the element
     * @return The object stored at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    @Override
    public T get(int index) {
        LinkedListNode<T> temp = head;
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else {
            for (int i = 0; i < index; i++) {
                temp = temp.getNext();
            }
        }
        return temp.getData();
    }

    /**
     * Removes the last node from the Linked List and returns the data from it.
     * If the list is empty, return {@code null}.
     *
     * Must be O(n).
     *
     * @return The data from the last node or null.
     */
    @Override
    public T remove() {
        LinkedListNode<T> temp = head;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            size -= 1;
            head = null;
            tail = null;
            return temp.getData();
        }
        for (int i = 1; i < size - 1; i++) {
            temp = temp.getNext();
        }
        T dataFound = temp.getNext().getData();
        temp.setNext(null);
        tail = temp;
        size -= 1;
        return dataFound;
    }

    /**
     * Removes and returns the element at index.
     *
     * Should be O(1) if index is 0.
     * Should be O(n) otherwise.
     *
     * @param index The index of the element.
     * @return The data that was formerly at that index.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     */
    @Override
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            return removeFromFront();
        } else if (index == size -1) {
            return remove();
        } else {
            LinkedListNode<T> temp = head;
            for (int i = 1; i < index; i++) {
                temp = temp.getNext();
            }
            T data = temp.getNext().getData();
            temp.setNext(temp.getNext().getNext());
            size -= 1;
            return data;
        }
    }

    /**
     * Remove the front node from the list and return the data from it. If the
     * list is empty, return {@code null}.
     *
     * Must be O(1).
     *
     * @return The data from the front node or null.
     */
    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T data = head.getData();
        head = head.getNext();
        if (size == 1) {
            tail = null;
        }
        size -= 1;
        return data;
    }

    /**
     * Sets the data of the node at index with {@code data}.
     * Returns the replaced data.  This is not the same as {@code addAtIndex}
     *
     * Should be O(1) if index is in 0 or {@code size - 1}
     * Should be O(n) otherwise.
     *
     * @param index The index to replace to data.
     * @param data The new data to replace with.
     * @return The replaced data.
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or
     * index >= size.
     * @throws java.lang.IllegalArgumentException if data is null.
     */
    @Override
    public T setAtIndex(int index, T data) {
        LinkedListNode<T> temp = head;
        if (data == null) {
            throw new IllegalArgumentException();
        } else if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }
        T result = temp.getData();
        temp.setData(data);
        return result;
    }

    /**
     * Returns the Linked List represented as an array of objects.
     * An empty list should return an empty array.
     *
     * Must be O(n).
     *
     * @return An array of length {@code size} holding each element in the same
     * order as it is in the linked list.
     */
    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        LinkedListNode<T> temp = head;
        array[0] = temp.getData();
        for (int i = 1; i < size; i++) {
            temp = temp.getNext();
            array[i] = temp.getData();
        }
        return array;
    }

    /**
     * Returns a boolean value representing whether the Linked List is empty.
     *
     * Should be O(1).
     *
     * @return True if empty. False otherwise.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the Linked List as an integer.
     *
     * Should be O(1).
     *
     * @return The size of the list.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Clears the Linked List.
     *
     * Should be O(1).
     */
    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }


    // DO NOT MODIFY CODE OR USE CODE BEYOND THIS POINT.
    @Override
    public LinkedListNode<T> getHead() {
        return head;
    }


    @Override
    public LinkedListNode<T> getTail() {
        return tail;
    }
}
