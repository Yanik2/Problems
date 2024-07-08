public class PascalTriangleDynamicList { 

	public List<List<Integer> generate(int numRows) {
		final var container = new ArrayList<List<Integer>>();
		container.add(List.of(1));
		if (numRows > 1)
			container.add(List.of(2));

		for (int i = 2; i < numRows; i++) {
			final var prev = container.get(i - 1);
			final var current = new ArrayList<Integer>();
			current.add(1);

			for (int j = 0; j < prev.size() - 1; j++) {
				current.add(prev.get(j) + prev.get(j + 1));
			}

			current.add(1);
			container.add(current);
		}

		return container;
	}
}
