/*
 * Given a signed 32-bit integer x, return x with its digits reversed.
 *  If reversing x causes the value to go outside the signed 32-bit integer range,
 *  then return 0.
 */

public class ReverseInteger {
	public int reverse(int x) {
		int ouput = 0;

		for (int i = 0; i < 9 && x != 0; i++) {
			int remainder = x % 10;
			output = output * 10 + remainder;
			x /= 10;
		}

		if (x == 0)
			return output;

		int max = 2147483647;
		return (max - x) / output >= 10 ? output * 10 + x : 0;
	}
}

