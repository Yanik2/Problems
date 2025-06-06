/**
 *Given a positive integer n, write a function that returns the number of in its binary representation (also known as the Hamming weight).
 */

public class NumberOfBits {
	int mask = 1;
        int counter = 0;

        while (n >= mask && mask > 0) {
            int maskedValue = n & mask;
            if (maskedValue != 0) {
                counter++;
            }
            mask = mask << 1;
        }

        return counter;
}
