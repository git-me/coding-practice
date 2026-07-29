package org.code.problems.arrays;

public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println("-------------" + maxArea(height));
    }

    public static int maxArea(int[] height) {
        int j = height.length - 1;
        int i = 0;
        int max_area = 0;
        while (j > i) {
            int length = j - (i);
            int breadth = Math.min(height[i], height[j]);
            int area = length * breadth;
            max_area = Math.max(area, max_area);
            if (height[i] >= height[j])
                j--;
            else i++;
        }
        return max_area;
    }
}
