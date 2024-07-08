public class PascalTriangleRecursion {

	public List<List<Integer>> generate(int numRows) {
		final var container = new ArrayList<List<Integer>>();
		rec(container, numRows);
		return container;
	}

	private void rec(List<List<Integer> container, numRows) {
		if (numRows <= 2) {
			container.add(List.of(1));
			if (numRows == 2) 
				container.add(List.of(1, 1));
		}

		rec(container, numRows - 1);

		final var prev = container.get(numRows - 2);
		final var current = new ArrayList<Integer>();
		current.add(1);

		for (int i = 0; i < prev.size() - 1; i++) {
			current.add(prev.get(i) + prev.get(i + 1));
		}

		current.add(1);
		container.add(current);
	}
}
