/**
 *
 * You are given an integer array height of length n. There are n vertical lines drawn such that the two endpoints of the ith line are (i, 0) and (i, height[i]).

Find two lines that together with the x-axis form a container, such that the container contains the most water.

Return the maximum amount of water a container can store.
*/

public class ContainerWithMostWater {
	public int maxArea(int[] height) {
		int p1 = 0;
        int p2 = height.length - 1;
        int max = 0;
        int x;
        int tmp;
        for (int y = height.length - 1; y > 0; y--) {
            x = height[p1] > height[p2] ? height[p2] : height[p1];
            tmp = x * y;
            max = tmp > max ? tmp : max;
            if (height[p1] > height[p2]) {
                p2--;
            } else {
                p1++;
            }
        }

        return max;
	}
}
