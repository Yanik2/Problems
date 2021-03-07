/*
    Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2.

    Note:

    The length of both num1 and num2 is < 5100.
    Both num1 and num2 contains only digits 0-9.
    Both num1 and num2 does not contain any leading zero.
    You must not use any built-in BigInteger library or convert the inputs to integer directly.
*/
class AddStrings {
    public String addStrings(String num1, String num2) {
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int n1;
        int n2;
        int acc = 0;
        int tmp;
        String out = "";

        for(; i >= 0 || j >= 0; i--, j--) {
            if(i >= 0) n1 = num1.charAt(i) - 48;
            else n1 = 0;
            if(j >= 0) n2 = num2.charAt(j) - 48;
            else n2 = 0;
            tmp = n1 + n2 + acc;
            acc = 0;
            if(tmp > 9) {
                tmp -= 10;
                acc = 1;
            }
            out = Integer.toString(tmp) + out;
        }
        if(acc == 1) return "1" + out;
        else return out;
    }
}
