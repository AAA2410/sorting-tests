package sorttest;
/**
 * 
 * @author Anurag Agarwa
 * @version 1.0.0
 * @since 12-Jul-2015
 */
public class Sort {
    
    /**
     * Runs a standard bubble sort on an input array
     * 
     * @param list The array to be sorted
     */
    public static void bubbleSort (int[] list)  {
        boolean flag;
        int position = list.length - 1;
        int temp;
        
        do  {
            //Assumes no swaps are made
            flag = false;
            for (int i = 0; i < position; i++)  {
                if (list[i] > list[i+1])  {
                    //Performs a swap if necessary
                    temp = list[i+1];
                    list[i+1] = list[i];
                    list[i] = temp;
                    //Checks when a swap is made
                    flag = true;
                }
            }
            //Iterates ensuring the end of the list is always sorted
            position--;
        }
        while (flag); 
        //The loop terminates if a full pass is made with no changes
    }
    
    /**
     * Selection sorts an input array of integers
     * 
     * @param list Array to be sorted
     */
    public static void selectionSort (int[] list)  {
        int temp;
        int minVal;
        int minIndex;
        
        //Iterates through the entire array
        //Structure ensures beginning of array is always sorted
        for (int i = 0; i < list.length; i++)  {
            minVal = list[i];
            minIndex = i;
            //Iterates through the array, finding the minimum value location
            for (int j = i; j < list.length; j++)  {
                if (list[j] < minVal)  {
                    minVal = list[j];
                    minIndex = j;
                }
            }
            
            //Pushes the minimum value to the first index
            temp = list[i];
            list[i] = minVal;
            list[minIndex] = temp;
        }
    }
    
    /**
     * Performs an insertion sort on an array of integers
     * 
     * @param list The array to be sorted
     */
    public static void insertionSort (int[] list)  {
        boolean flag; 
        int current, location;
        
        //Iterates through the entire array
        for (int i = 1; i < list.length; i++)  {
            //Starts with no sorted end
            location = i-1; 
            current = list[i];
            flag = false; 
            
            //Iterates until it moves at least one value to it's right place
            //or falls out of bounds
            while (location >= 0 && !flag)  {
                if (current < list[location])  {
                    //Moves values up the array as necessary
                    list[location+1] = list[location];
                    location--;
                    //If it falls off bounds, it stores the value at the 0th 
                    //place
                    if (location < 0)  {
                        list[0] = current;
                    }
                }
                
                else  {
                    //States that a change was made, and places the current 
                    //pivot where required
                    flag = true;
                    list[location+1] = current;
                }
            }
            //Everything left of the pivot value is always sorted
        }
    }
    
    /**
     * Recursively performs a quick sort on a list of integers
     * 
     * @param list The list to be sorted
     * @param left The left bound of the section being sorted
     * @param right The right bound of the section being sorted
     */
    public static void quickSort (int[] list, int left, int right)  {
        //Sets moving values starting at the left and right bounds
        int moveR = left, moveL = right, pivot;
        //Quits recursion after the bounds cross each other 
        if (left < right)  {
            //Holds the leftmost value on list 
            pivot = list[left];
            //Loops until the moving values cross each other
            while (moveR < moveL)  {
                //While the left end is in its appropriate location, the right
                //counter counts downwards
                while (list[moveR] <= list[moveL] && moveR < moveL)  {
                    moveL--;
                }
                //Swaps pivot into rightmost entry 
                list[moveR] = list[moveL];
                list[moveL] = pivot;
                //Swaps directions and performs same algorithm
                while (list[moveR] <= list[moveL]  && moveR < moveL)  {
                    moveR++;
                }
                list[moveL] = list[moveR];
                list[moveR] = pivot;
            }
            
            //Calls the function for the two new unsorted created 
            //They are sorted independently of each other
            quickSort(list, left, moveL-1);
            quickSort(list,  moveR+1, right);
        }
    }
    
    /**
     * Helper function to merge two separated lists created during a sort
     * 
     * @param list1 First half of list to merge
     * @param list2 Second half of list to merge
     * @return The merged list is returned as a value to the call stack
     */
    private static int[] merge (int[] list1, int[] list2)  {
        //Creates an array twice as long as the ones beinged merged
        int[] mergedList = new int[list1.length+list2.length];
        int i = 0, j = 0;
        
        //Loops as long as both lists have unsorted values
        while (i < list1.length && j < list2.length)  {
            //Places the lower one of the lowest indexed values into the 
            //newly created list
            if (list1[i] < list2[j])  {
                mergedList[i+j] = list1[i];
                i++;
            }
            else  {
                mergedList[i+j] = list2[j];
                j++;
            }
        }
        //After one of the list is out of values, it appends everything else 
        while (i < list1.length)  {
            mergedList[i+j] = list1[i];
            i++;
        }
        while (j < list2.length)  {
            mergedList[i+j] = list2[j];
            j++;
        }
        //Assumption is that the sorter always gets lists that are internally
        //sorted already, so appending doesn't unsort them 
        
        //Returns the new list
        return mergedList;
    }
    
    /**
     * Performs a recursive merge sort on an array of integer values
     * 
     * @param list The list being sorted
     * @return The merge sorted version of the list
     */
    public static int[] mergeSort (int[] list)  {
        //Creates two empty lists half as long as the original
        int[] firstHalf;
        if (list.length%2 == 0)  {
            firstHalf = new int[list.length/2];
        }
        else  {
            firstHalf = new int[list.length/2 + 1];
        }
        int[] secondHalf = new int[list.length/2];
        
        //If the function receives a one-element long list, it returns that 
        //value
        if (list.length == 1)  {
            return list;
        }
        
        //Populates the 2 lists with the first and second half of the original
        for (int i = 0, j = 0, k = 0; i < list.length; i++)  {
            if (i % 2 == 0)  {
                firstHalf[j] = list[i];
                j++;
            }
            else  {
                secondHalf[k] = list[i];
                k++;
            }
        }
        //Separately merge sorts the two halves, and takes the returned
        //merged lists once they are sorted
        firstHalf = mergeSort(firstHalf);
        secondHalf = mergeSort(secondHalf);
        //Merges the two and returns the value to the original sort
        return merge(firstHalf, secondHalf);
    }
}
