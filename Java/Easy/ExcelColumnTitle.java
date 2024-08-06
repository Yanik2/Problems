/*
 * Given an integer columnNumber, return its corresponding column title
 *  as it appears in an Excel sheet.
 * For example:
 * A -> 1
 * B -> 2
 * C -> 3
 * ...
 * Z -> 26
 * AA -> 27
 * AB -> 28 
 * ...
*/

public class ExcelColumnTitle {
	public String convertToTitle(int columnNumber) {
		char a = 65;
		int size = 26;
		StringBuilder sb = new StringBuilder();

		while(columnNumber != 0) {
			columnNumber -= 1;
			char c = (char) (columnNumber % size + a);
			sb.insert(0, c);
			columnNumber /= size;
		}

		return sb.toString();
	}
}
