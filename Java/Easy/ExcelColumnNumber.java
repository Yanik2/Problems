public class ExcelColumnNumber {
	public int titleToNumber(String columnNumber) {
		int len = columnTitle.length();
		int start = columnTitle.charAt(len - 1) - 64;
		int currentPowered = 1;

		for (int i = len -2; i >= 0; i--) {
			currentPowered *= 26;
			start += currentPowered * (columnTitle.charAt(i) - 64);
		}

		return start;
	}
}
