/**
 *You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

    For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].

You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.
 */

public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        final Map<String, Heap> connections = new HashMap<>();

        for (List<String> connection : tickets) {
            final Heap heap = connections.computeIfAbsent(connection.getFirst(), s -> new Heap());
            heap.add(connection.getLast());
            connections.computeIfAbsent(connection.getLast(), s -> new Heap());
        }

        int ticketCount = tickets.size();
        final Stack stack = new Stack();

        recursion(connections, "JFK", ticketCount, stack);
        return stack.snapshot();
    }

    private void recursion(Map<String, Heap> connections, String airport, int remainingTickets, Stack stack) {
        if (remainingTickets == 0) {
            stack.add(airport);
            return;
        }

        var currentConnections = connections.get(airport);
        if (currentConnections.isEmpty()) {
            final String prevAirport = stack.pop();
            recursion(connections, prevAirport, remainingTickets, stack);
            stack.add(airport);
        } else {
            stack.add(airport);
            final String nextAirport = currentConnections.pop();
            recursion(connections, nextAirport, remainingTickets - 1, stack);
        }
    }

    class Stack {
        private Node first;
        private int size;

        String pop() {
            final String val = first.value;
            first = first.next;
            size--;
            return val;
        }

        void add(String value) {
            final Node newNode = new Node();
            newNode.value = value;
            newNode.next = first;
            first = newNode;
            size++;
        }

        List<String> snapshot() {
            final List<String> list = new LinkedList<String>();
            Node start = first;

            while(start != null) {
                list.addFirst(start.value);
                start = start.next;
            }

            return list;
        }


        class Node {
            String value;
            Node next;
        }
    }

    class Heap {
        String[] heap;
        int size;
        int capacity = 8;

        Heap() {
            heap = new String[capacity];
            this.size = 0;
        }

        void heapify() {
            for (int i = (size - 1) / 2; i >= 0; i--) {
                trickleDown(i);
            }
        }

        void trickleDown(int index) {
            String tmp = heap[index];

            while (index < size / 2) {
                int smallestChild = index * 2 + 1;

                if (heap[smallestChild + 1] != null && heap[smallestChild].compareTo(heap[smallestChild + 1]) > 0) {
                    smallestChild++;
                }

                if (tmp.compareTo(heap[smallestChild]) < 0) {
                    break;
                } else {
                    heap[index] = heap[smallestChild];
                    index = smallestChild;
                }
            }

            heap[index] = tmp;
        }

        void add(String value) {
            if (size == heap.length) {
                resize();
            }

            heap[size] = value;
            trickleUp(size);
            size++;
        }

        String pop() {
            final String value = heap[0];
            heap[0] = heap[size - 1];
            size--;
            trickleDown(0);
            return value;
        }

        private void resize() {
            final int newCapacity = capacity / 2 * 3;
            String[] newHeap = new String[newCapacity];
            for (int i = 0; i < heap.length; i++) {
                newHeap[i] = heap[i];
            }

            heap = newHeap;
            capacity = newCapacity;
        }

        void trickleUp(int index) {
            int parent = (index - 1) / 2;
            String tmp = heap[index];

            while (index > 0 && tmp.compareTo(heap[parent]) < 0) {
                heap[index] = heap[parent];
                index = parent;
                parent = (index - 1) / 2;
            }

            heap[index] = tmp;
        }

        boolean isEmpty() {
            return size == 0;
        }
    }
}
