/**
 *Implement a last-in-first-out (LIFO) stack using only two queues. The implemented stack should support all the functions of a normal stack (push, top, pop, and empty).

Implement the MyStack class:

    void push(int x) Pushes element x to the top of the stack.
    int pop() Removes the element on the top of the stack and returns it.
    int top() Returns the element on the top of the stack.
    boolean empty() Returns true if the stack is empty, false otherwise.

Notes:

    You must use only standard operations of a queue, which means that only push to back, peek/pop from front, size and is empty operations are valid.
    Depending on your language, the queue may not be supported natively. You may simulate a queue using a list or deque (double-ended queue) as long as you use only a queue's standard operations.

 */

public class ImplementStackUsingQueues {
	private Node first;
    private int size;

    public MyStack() {
        this.size = 0;
    }

    public void push(int x) {
        final Node newNode = new Node();
        newNode.value = x;

        if (!empty()) {
            newNode.next = first;
        }
        first = newNode;

        this.size++;
    }

    public int pop() {
        final int value = first.value;
        first = first.next;
        this.size--;
        return value;
    }

    public int top() {
        return first.value;
    }

    public boolean empty() {
        return this.size == 0;
    }

    class Node {
        int value;
        Node next;
    }
}
