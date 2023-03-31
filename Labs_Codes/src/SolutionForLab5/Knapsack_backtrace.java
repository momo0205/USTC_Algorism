package SolutionForLab5;

/**
 * @author ChenMao
 * @create 2022-12-10 16:14
 */

//回溯算法
public class Knapsack_backtrace {
    public int[] weight;
    public int[] value;
    public int[] take;
    int curWeight = 0;
    int curValue = 0;
    int bestValue = 0;
    int[] bestChoice;
    int maxWeight = 0;
    int count;

    public Knapsack_backtrace(int[] weight, int[] value, int maxWeight, int n) {
        this.value = value;
        this.weight = weight;
        this.maxWeight = maxWeight;
        this.count = n;
        take = new int[n];
        bestChoice = new int[n];
    }

    public int[] backtrack(int x) {
        //终止条件
        if (x > count - 1) {
            //更新最优解
            if (curValue > bestValue) {
                bestValue = curValue;
                for (int i = 0; i < take.length; i++) {
                    bestChoice[i] = take[i];
                }
            }
        } else { //遍历当前节点（物品）的子节点：0 不放入背包 1：放入背包
            for (int i = 0; i < 2; i++) {
                take[x] = i;
                if (i == 0) {
                    //不放入背包，接着往下走
                    backtrack(x + 1);//递归下一个物品
                } else {
                    //约束条件，如果小于背包容量
                    if (curWeight + weight[x] <= maxWeight) {
                        //更新当前重量和价值
                        curWeight += weight[x];
                        curValue += value[x];
                        //继续向下深入
                        backtrack(x + 1);
                        //回溯
                        curWeight -= weight[x];
                        curValue -= value[x];
                    }
                }
            }
        }
        return bestChoice;
    }


    public static void backtrace_method(int[] w, int[] v, int m, int n) {

        Knapsack_backtrace bt = new Knapsack_backtrace(w, v, m, n);
        int[] result = bt.backtrack(0);
        System.out.print("最佳选择为：[");
        for (int i = 0; i < bt.bestChoice.length; i++) {
            if (i == bt.bestChoice.length - 1) {
                System.out.print(bt.bestChoice[i] + "]");
            } else {
                System.out.print(bt.bestChoice[i] + ",");
            }
        }
        System.out.print("\n此时价值最大，即" + bt.bestValue + "\n");

    }

    public static void main(String[] args) {
        int weight[] = {2,2,6,5,4};
        int value[] = {6,3,5,4,6};
        int maxweight = 10;
        int n = weight.length;

        long start,end;
        start = System.currentTimeMillis();

        backtrace_method(weight,value,maxweight,n);

        end = System.currentTimeMillis();

        System.out.println("Run Time:" + (end - start) + "(ms)");

    }
}
