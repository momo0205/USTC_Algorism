package SolutionForLab1;

import java.util.Arrays;

/**
 * @author ChenMao
 * @create 2022-12-19 22:38
 */
public class MaximumSubarray {

    public static class Result {
        public int low;
        public int high;
        public int sum;

        public Result(int low, int high, int sum) {
            this.low = low;
            this.high = high;
            this.sum = sum;
        }
    }

    public static Result findMaximumSubarray(int[] A, int low, int high) {
        if (low == high) {
            // 如果数组只有一个元素，则最大子数组就是这个元素本身
            return new Result(low, high, A[low]);
        } else {
            // 将数组划分为两个子数组
            int mid = (low + high) / 2;
            Result leftResult = findMaximumSubarray(A, low, mid);
            Result rightResult = findMaximumSubarray(A, mid + 1, high);
            Result crossResult = findMaxCrossingSubarray(A, low, mid, high);
            //找出三个部分的最大子数组

            if (leftResult.sum >= rightResult.sum && leftResult.sum >= crossResult.sum) {
                return leftResult;
            } else if (rightResult.sum >= leftResult.sum && rightResult.sum >= crossResult.sum) {
                return rightResult;
            } else {
                return crossResult;
            }
        }
    }

    public static Result findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        //找到左边部分的最大总和，每找到一个更大的总和，就更新maxLeft
        for (int i = mid; i >= low; i--) {
            sum += A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }
        //找到右边部分的最大总和，每找到一个更大的总和，就更新maxRight
        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        for (int i = mid + 1; i <= high; i++) {
            sum += A[i];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = i;
            }
        }

        return new Result(maxLeft, maxRight, leftSum + rightSum);
    }

    public static void main(String[] args) {
        int[] A = {-2,1,-3,4,-1,2,1,-5,4};
        Result result = findMaximumSubarray(A, 0, A.length - 1);
        System.out.println("最大子数组为：" + Arrays.toString(Arrays.copyOfRange(A, result.low, result.high + 1)));
        System.out.println("最大子数数组和为："+ result.sum);
    }
}
