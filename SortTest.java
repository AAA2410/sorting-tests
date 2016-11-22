package sorttest;

public class SortTest {

    public static void main(String[] args) {
        int[] list = new int[100000000];
        //Generates a random 
        for (int i = 0; i < list.length; i++)  {
            list[i] = (int) (Math.random()*100000000);
        }
        //Call the requisite sorting algorithm from the Sort class
        list = Sort.mergeSort(list);
        
        for (int i = 0; i < list.length; i++)  {
            System.out.print(list[i] + " ");
        }
        System.out.println();
    }

}
