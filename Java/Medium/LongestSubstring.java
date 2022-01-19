/*
 * Given a string s, find the length of the longest substring without repeating characters.
 */
public class LongestSubstring {
	public int lengthOfTheLongestSubstring(String s) {
		if(s.length() == 0) return 0;
		int len = 1;
		int p1 = 0;
		int p2 = 1;
		String tmp;

		while(p2 < s.length()) {
			tmp = s.substring(p1, p2);

			if(tmp.contains(String.valueOf(s.charAt(p2)) {
				p1++;
				if(p1 == p2)
					p2++;
			} else {
				p2++;
				if(len < p2 - p1)
					len = p2 - p1;
			}

		}
		return len;
	}
}
