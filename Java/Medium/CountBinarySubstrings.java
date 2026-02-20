/*
 *Given a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.

Substrings that occur multiple times are counted the number of times they occur.

 

Example 1:

Input: s = "00110011"
Output: 6
Explanation: There are 6 substrings that have equal number of consecutive 1's and 0's: "0011", "01", "1100", "10", "0011", and "01".
Notice that some of these substrings repeat and are counted the number of times they occur.
Also, "00110011" is not a valid substring because all the 0's (and 1's) are not grouped together.

Example 2:

Input: s = "10101"
Output: 4
Explanation: There are 4 substrings: "10", "01", "10", "01" that have equal number of consecutive 1's and 0's.

 

Constraints:

    1 <= s.length <= 105
    s[i] is either '0' or '1'.


 *
 */

public class CountBinarySubstrings {
	public int countBinarySubstrings(String s) {
        final var len = s.length();
        int p1 = 0;
        int p2 = 1;
        char tmp;
        int counter = 0;
        int currentCounter;
        while (p2 < len) {
            tmp = s.charAt(p1);
            currentCounter = 1;
            while (++p1 < len && s.charAt(p1) == tmp) {
                currentCounter++;
            }

            p2 = p1;

            while (currentCounter > 0 && p2 < len && s.charAt(p2) != tmp) {
                currentCounter--;
                counter++;
                p2++;
            }
        }

        return counter;
    }
}
