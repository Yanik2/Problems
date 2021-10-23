/*
Given a string s consisting of some words separated by some number of spaces, return the length of the last word in the string.

A word is a maximal substring consisting of non-space characters only.
*/

class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        String[] arr = s.split(" +");
        return arr[arr.length - 1].length();
    }
}
