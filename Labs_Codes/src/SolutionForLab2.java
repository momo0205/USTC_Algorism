import java.util.Arrays;

/**
 * @author ChenMao
 * @create 2022-11-27 20:26
 */
public class SolutionForLab2 {

    public static void main(String[] args) {
        int[] nums1 = {-2,1,-3,4,-1,2,1,-5,4};


        SolutionForLab2 test = new SolutionForLab2();

        //快速排序结果
        test.quicksort(nums1,0,nums1.length - 1);
//        System.out.println("快速排序实验结果：" + Arrays.toString(nums1));


        int [] nums2 = {95, 94, 91, 98, 99, 90, 99, 93, 91, 92};

        int[] res2 = new int[nums2.length];

        int k = nums2[0];
        for(int i = 1;i<nums2.length;i++){
            k = Math.max(nums2[i],k);
        }

        test.countingSort(nums2,res2,k);


        System.out.println("计数排序实验结果：" + Arrays.toString(res2));




    }

    public int[] countingSort(int[] nums,int[] res,int k){
        int[] count = new int[k];
        int maxN = nums[0];
        int minN = nums[0];

        //找最大值和最小值
        for(int i = 1;i<nums.length;i++){
            maxN = Math.max(nums[i],maxN);
            minN = Math.min(nums[i],minN);
        }

        //计数，此时result数组里存放着每个数的数量
        for (int i = 0;i<nums.length;i++){
            count[nums[i] - minN] ++;
        }

        //对所有计数累加，累加之后，count[i]里面就存放着比该元素小的元素数量
        for (int i = 1;i < k; i++){
            count[i] = count[i] + count[i - 1];
        }

        //反向填充目标数组：将每个元素i放在新数组的第count(i)项，每放一个元素就将count(i)减去1
        for (int i = nums.length - 1;i >= 0 ;i--){
            res[count[nums[i] - minN] - 1] = nums[i];
            //通过count找到这个元素应该放在哪个位置
            count[nums[i] - minN] --;
        }

        return res;

    }

    public void quicksort(int[] nums, int p, int r){
        if (p < r){
            int pos = partition(nums,p,r);
            quicksort(nums,p,pos-1);
            quicksort(nums,pos+1,r);
        }
    }

    public int partition(int[] nums, int p, int r){
        int pivot = nums[r];
        int i = p, j = p;
        while( j < r){
            if(nums[j] < pivot){
                swap(nums,i,j);
                i++;
            }
            j++;
            //如果比pivot小，j用来检查每一个数，i用来记录下一个数被交换过来时应该放的位置
            //当找到大于等于pivot的数时，i停在这个位置，让j继续往后找，找到比pivot小的数时，换到这个位置上
        }

        swap(nums,i,r);
        return i;
    }

    public void swap(int[] a,int i,int j){
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }


}
