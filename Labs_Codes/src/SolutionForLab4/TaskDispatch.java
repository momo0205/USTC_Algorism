package SolutionForLab4;

/**
 * @author ChenMao
 * @create 2022-12-07 22:54
 */
import java.util.Arrays;


// 在单处理器上具有期限和惩罚的单位时间任务调度问题。
public class TaskDispatch {

    private static void greedyTaskDispatch(Task[] tasks) {
        int n = tasks.length;
        Arrays.sort(tasks); // 根据惩罚从大到小排序
        System.out.println("Task按惩罚从大到小排序后：");
        for(Task t : tasks) {
            System.out.println(t.toString());
        }

        int[] route = new int[n]; // 记录任务的最终调度顺序，route[i]表示第(i+1)天调度的任务id。天数从1开始，而数组下标从0开始。
        Arrays.fill(route, -1);

        int punishment = 0; // 记录总的惩罚值
        for(int i = 0; i < n; ++i) { // 处理第i个任务
            for(int j = tasks[i].deadline - 1; j >= 0; --j) { // deadline的范围是[1,n]，故此处需修正下标
                // j为第i个任务的deadline，应将第i个任务尽可能在靠近deadline的天完成。
                // 这样卡点完成的好处是：让后续有更紧急deadline要求的任务更可能按时完成。
                if(route[j] == -1) { // 若第j天未安排任务
                    route[j] = tasks[i].id;
                    break;
                }
                if(j == 0) { // 第1天~第deadline天都已安排了任务，故当前任务必超时
                    punishment += tasks[i].weight;
                }
            }
        }

        System.out.println("总的惩罚为：" + punishment);
        System.out.println("任务执行顺序为：");
        for(int i = 0; i < n; ++i) {
            System.out.print("第" + (i+1) + "天执行的任务为：");
            if(route[i] != -1) {
                System.out.println(" " + tasks[route[i]]);
            }
            else {
                System.out.println(" 任选一个已超时任务执行");
            }
        }

    }

    public static void main(String[] args) {
        int n = 7; // 任务数量
        int[] deadline = new int[]{4,2,4,3,1,4,6}; // 任务期限，范围是闭区间[1,n]
        int[] weight = new int[]{70,60,50,40,30,20,10}; // 任务未按时完成的惩罚

        // 将 Wi 替换为 max{W1,W2……Wn} - Wi
        int maxWeight = weight[0];
        for(int w : weight) {
            maxWeight = w > maxWeight ? w: maxWeight;
        }
        for(int i = 0; i < n; ++i) {
            weight[i] = maxWeight - weight[i];
        }

        Task[] tasks = new Task[n];
        for(int i = 0; i < n; ++i) {
            tasks[i] = new Task(i, deadline[i], weight[i]);
        }
        greedyTaskDispatch(tasks);
    }

}
