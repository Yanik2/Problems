public class PascalTriangleII {
	public List<Integer> getRow(int rowIndex) {
        rowIndex++;
        final List<Integer> result = new ArrayList<>(rowIndex);
        long currentValue = 1;
        result.add((int) currentValue);

        for (int i = 1; i < rowIndex; i++) {
            currentValue = currentValue * (rowIndex - i) / i;
            result.add((int) currentValue);
        }
        return result;
    }
}

