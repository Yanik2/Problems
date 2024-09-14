class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        final var map = new Map(nums.length / 2 + 1);

        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i]);
        }

        final var heap = map.getAsArray();
        heapify(heap);

        int[] result = new int[k];
        int heapSize = heap.length;
        for (int i = 0; i < k; i++, heapSize--) {
            result[i] = popValue(heap, heapSize);
        }

        return result;
    }

    private class Pair {
        int value, counter;
    }

    private void heapify(Pair[] pairs) {
        for (int i = (pairs.length - 1) / 2; i >= 0; i--) {
            tickleDown(pairs, i, pairs.length);
        }
    }

    private int popValue(Pair[] pairs, int size) {
        int value = pairs[0].value;
        pairs[0] = pairs[--size];
        tickleDown(pairs, 0, size);
        return value;
    }

    private void tickleDown(Pair[] pairs, int index, int size) {
        final var temp = pairs[index];

        while (index < size / 2) {
            int leftChild = index * 2 + 1;
            int largestChild = leftChild;

            if (leftChild + 1 < size && pairs[leftChild].counter < pairs[leftChild + 1].counter) {
                largestChild++;
            }

            if (temp.counter > pairs[largestChild].counter) {
                break;
            } else {
                pairs[index] = pairs[largestChild];
                index = largestChild;
            }
        }

        pairs[index] = temp;
    }

    private class Map {

        Node[] nodes;
        int size = 0;

        Map(int size) {
            nodes = new Node[size];
        }

        void put(int value) {
            int rem = value % nodes.length;
            int index = rem < 0 ? rem * -1 : rem;
            Node currentNode = nodes[index];

            if (currentNode != null) {
                while (currentNode != null) {
                    if (currentNode.value.value == value) {
                        currentNode.value.counter++;
                        return;
                    }

                    currentNode = currentNode.next;
                }
            }
            createNewNode(value, index);
            size++;
        }

        Pair[] getAsArray() {
            Pair[] pairs = new Pair[size];

            for (int i = 0, j = 0; i < nodes.length; i++) {
                Node currentNode = nodes[i];

                while (currentNode != null) {
                    pairs[j++] = currentNode.value;
                    currentNode = currentNode.next;
                }
            }

            return pairs;
        }

        void createNewNode(int value, int index) {
            Pair newPair = new Pair();
            newPair.value = value;
            newPair.counter = 1;
            Node newNode = new Node();
            newNode.value = newPair;
            newNode.next = nodes[index];
            nodes[index] = newNode;
        }

        private class Node {
            Pair value;
            Node next;
        }
    }
}
