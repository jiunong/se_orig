package cloud.sort;

import java.util.Arrays;

public class Sort {


    public static void main(String[] args) {
        int []tData = new int[]{45,456,468,15,135,645,456,15,123,123,456,456,45,12,12,11,5,25,53,45,53,12,45,7445};
        // maoPaoSort(tData);
        quickSort(tData,0,tData.length/2);
        Arrays.stream(tData).forEach(System.out::println);
    }

    /**
     * TODO 1、冒泡排序
     */
    public static void maoPaoSort(int[] array) {
        for(int i=array.length-1;i>=0;i--) {
            for(int j=0;j<i;j++) {
                if(array[j]>=array[j+1]) {
                    int t=array[j];
                    array[j]=array[j+1];
                    array[j+1]=t;
                }
            }
        }
    }

    /**
     * TODO 快速排序
     * @param array
     * @param left
     * @param right
     */
    public static void quickSort(int[] array,int left,int right) {
        int low=left;
        int high=right;
        if(low>high) {
            return ;
        }
        int k=array[low];
        while(low<high) {
            while(low<high&&array[high]>=k) {
                high--;
            }
            array[low]=array[high];
            while(low<high&&array[low]<=k) {
                low++;
            }
            array[high]=array[low];
        }
        array[low]=k;
        quickSort(array,left,low-1);
        quickSort(array,low+1,right);
    }

}
