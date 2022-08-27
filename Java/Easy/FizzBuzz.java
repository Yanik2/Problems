/*
 * Given an integer n, return a string array answer (1-indexed) where:
*
*	answer[i] == "FizzBuzz" if i is divisible by 3 and 5.
*	answer[i] == "Fizz" if i is divisible by 3.
*	answer[i] == "Buzz" if i is divisible by 5.
*	answer[i] == i (as a string) if none of the above conditions are true.
*/


public class FizzBuzz {
	public List<String> fizzBuzz(int n) {
		var list = new ArrayList<String>();

		for(int i = 1; i <= n; i++) {
			var str = "";

			if(i % 3 == 0)
				str = "Fizz";
			if(i % 5 == 0)
				str += "Buzz";
			if(str.length() == 0)
				str = String.valueOf(i);
			list.add(str);
		}

		return list;
	}
}
