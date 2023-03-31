package SolutionForLab4;

/**
 * @author ChenMao
 * @create 2022-12-07 20:41
 */

public class Task implements Comparable<Task> {
    public int id;
    public int deadline;
    public int weight;

    public Task(int id, int deadline, int weight) {
        this.id = id;
        this.deadline = deadline;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", deadline=" + deadline +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Task newTask) { // 按惩罚由大到小排序，调用Arrays.sort时会用到
        return Integer.compare(newTask.weight, this.weight);
    }

}
