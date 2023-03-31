package SolutionForLab1;

import java.util.Arrays;

/**
 * @author ChenMao
 * @create 2022-11-18 16:09
 */
public class MedianSortedArrays {

    public static void main(String[] args) {
        int[] nums1 = {1};
        int[] nums2 = {3,4};

        System.out.println(findMedianSortedArrays(nums1,nums2));
    }

    public static int findKth(int[] nums1, int[] nums2, int k) {

        // 如果nums1数组为空，返回nums2数组的第k小的数
        if (nums1.length == 0) {
            return nums2[k - 1];
        }
        // 如果nums2数组为空，返回nums1数组的第k小的数
        if (nums2.length == 0) {
            return nums1[k - 1];
        }
        // 如果k=1，返回nums1数组的第1小的数和nums2数组的第1小的数的较小值
        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }
        // 取出nums1数组的第k/2小的数a和nums2数组的第k/2小的数b
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        if (k / 2 - 1 < nums1.length) {
            a = nums1[k / 2 - 1];
        }
        if (k / 2 - 1 < nums2.length) {
            b = nums2[k / 2 - 1];
        }

        // 如果a=b，返回a
        if (a == b) {
            return a;
        }
        // 如果a<b，将nums1数组的前k/2个数排除，递归地求解剩余的nums1和nums2数组的第k-k/2小的数
        if (a < b) {
            int[] newNums1 = Arrays.copyOfRange(nums1, k / 2, nums1.length);
            return findKth(newNums1, nums2, k - k / 2);
        }
        // 如果a>b，将nums2数组的前k/2个数排除，递归地求解剩余的nums1和nums2数组的第k-k/2小的数
        else {
            int[] newNums2 = Arrays.copyOfRange(nums2, k / 2, nums2.length);
            return findKth(nums1, newNums2, k - k / 2);
        }
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 计算两个数组的总长度
        int n1 = nums1.length;
        int n2 = nums2.length;
        int n = n1 + n2;
        // 如果两个数组的总长度是奇数，求解第(n1+n2+1)/2小的数并返回
        if (n % 2 == 1) {
            return findKth(nums1, nums2, (n1 + n2 + 1) / 2);
        }
        // 如果两个数组的总长度是偶数，求解第(n1+n2)/2小的数a和第(n1+n2)/2+1小的数b，并返回(a+b)/2
        else {
            int a = findKth(nums1, nums2, (n1 + n2) / 2);
            int b = findKth(nums1, nums2, (n1 + n2) / 2 + 1);
            return (a + b) / 2.0;
        }
    }


}

