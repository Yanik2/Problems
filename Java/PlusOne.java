/*
You are given a large integer represented as an integer array digits, where each digits[i] is the ith digit of the integer. The digits are ordered from most significant to least significant in left-to-right order. The large integer does not contain any leading 0's.

Increment the large integer by one and return the resulting array of digits.
*/

class PlusOne {
    public int[] plusOne(int[] digits) {
        int len = digits.length;
        int res = 1;
        for(int i = len - 1; i >= 0; i--) {
            digits[i] = digits[i] + res;
            res = digits[i] / 10;
            digits[i] = digits[i] % 10;
        }
        return checkOverflow(digits);
    }
    
    private int[] checkOverflow(int[] digits) {
        if(digits[0] != 0) return digits;
        int[] newDigits = new int[digits.length + 1];
        newDigits[0] = 1;
        for(int i = 1; i <= digits.length; i++) {
            newDigits[i] = digits[i - 1];
        }
        return newDigits;
    }
}
