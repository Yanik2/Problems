/**
 *Reverse bits of a given 32 bits unsigned integer.

Note:

    Note that in some languages, such as Java, there is no unsigned integer type. In this case, both input and output will be given as a signed integer type. They should not affect your implementation, as the integer's internal binary representation is the same, whether it is signed or unsigned.
    In Java, the compiler represents the signed integers using 2's complement notation. Therefore, in Example 2 above, the input represents the signed integer -3 and the output represents the signed integer -1073741825.

 */

public class ReverseBeats {
	public int reverseBits(int n) {
        int mask = 1;
        int result = 0;

        for (int i = 0; i < 32; i++) {
            int maskedValue = n & mask;
            int shiftedMask = maskedValue >> i;
            result = result << 1;
            result += shiftedMask < 0 ? -shiftedMask : shiftedMask;
        
            mask = mask << 1;
        }

        return result;
    }
}
