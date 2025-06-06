/**
 *Given an array of integers arr, replace each element with its rank.

The rank represents how large the element is. The rank has the following rules:

    Rank is an integer starting from 1.
    The larger the element, the larger the rank. If two elements are equal, their rank must be the same.
    Rank should be as small as possible.

 */

public class RankTransformOfAnArray {
	public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0) return arr;

        final Heap heap = new Heap(arr.length);

        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i], i);
        }

        int currentRank = 1;
        var currentNode = heap.pop();
        arr[currentNode.index] = currentRank;

        while (!heap.isEmpty()) {
            var newNode = heap.pop();
            if (currentNode.value < newNode.value) {
                currentRank++;
            }

            currentNode = newNode;
            arr[currentNode.index] = currentRank;

        }

        return arr;
    }

    class Heap {
        Node[] heap;
        int size;

        Heap(int capacity) {
            this.heap = new Node[capacity];
            this.size = 0;
        }

        void add(int value, int index) {
            final Node newNode = new Node();
            newNode.value = value;
            newNode.index = index;

            heap[size] = newNode;
            trickleUp(size);
            size++;
        }

        Node pop() {
            final Node val = heap[0];
            heap[0] = heap[size - 1];
            size--;
            trickleDown(0);
            return val;
        }

        void trickleUp(int index) {
            Node tmp = heap[index];
            int parent = (index - 1) / 2;

            while (index > 0 && tmp.value < heap[parent].value) {
                heap[index] = heap[parent];
                index = parent;
                parent = (index - 1) / 2;
            }

            heap[index] = tmp;
        }

        void trickleDown(int index) {
            Node tmp = heap[index];

            while (index < size / 2) {
                int smallestChild = index * 2 + 1;

                if (smallestChild < size - 1 && heap[smallestChild].value > heap[smallestChild + 1].value) {
                    smallestChild++;
                }

                if (tmp.value < heap[smallestChild].value) {
                    break;
                } else {
                    heap[index] = heap[smallestChild];
                    index = smallestChild;
                }
            }

            heap[index] = tmp;
        }

        boolean isEmpty() {
            return size == 0;
        }

        class Node {
            int value;
            int index;
        }
    }
}
