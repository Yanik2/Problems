/*
 * Given two arrays of strings list1 and list2, find the common strings with the least index sum.

A common string is a string that appeared in both list1 and list2.

A common string with the least index sum is a common string such that if it appeared at list1[i] and list2[j] then i + j should be the minimum value among all the other common strings.

Return all the common strings with the least index sum. Return the answer in any order
*/

class MinimumIndexSumOfTwoLists {
    public String[] findRestaurant(String[] list1, String[] list2) {
        var map1 = new HashMap<String, Integer>();
        
        ArrayList<String>[] words = new ArrayList[list1.length + list2.length];

        for (int i = 0; i < list1.length; i++) {
            map1.put(list1[i], i);
        }

        var min = Integer.MAX_VALUE;
        for (int i = 0; i < list2.length && i <= min; i++) {
             var temp = map1.get(list2[i]);

            if (temp != null) {
                var sum = temp + i;

                min = sum < min ? sum : min;

                var list = words[sum];
                if (list == null)
                    list = words[sum] = new ArrayList<>();
                list.add(list2[i]);
            }
        }

        return words[min].toArray(new String[words[min].size()]);
    }
}
