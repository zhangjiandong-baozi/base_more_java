package com.baowen.sgg.dcxy.binary_search2;

/**
 *
 * 二分查找
 * 对已经排好序的数组进行二分查找，时间复杂度是logn
 * @author mangguodong
 * @create 2022-05-18
 */
public class BinarySearch1 {

    public static void main(String[] args) {
        int[] is = {1,3,7,9,11,16,38,49,77,79,83,91};
        int i = binarySearch(is, 78);
        int j = binarySearch(is, 79);
        System.out.println("i = " + i);
        System.out.println("j = " + j);
    }

    public static int binarySearch(int[] ints,int target){

        int low = 0 ;
        int high = ints.length-1 ;

        if(target<ints[low] && target>ints[high]){
            return -1;
        }

        while (low<=high){
            int mid = (low+high)/2;

            if(target<ints[mid]){
                //取右边
                high = mid-1;
            }else if(target>ints[mid]){
                //取左边
                low = mid+1;
            }else {
                return mid;
            }
        }

        return -1;
    }

    //递归实现
    public static int binarySearchByRecursion(int[] ints,int target,int fromIndex,int endIndex){
        if(target<ints[fromIndex] && target>ints[endIndex] && fromIndex<endIndex){
            return -1;
        }

        int mid = (fromIndex+endIndex)/2;
        if(target<ints[mid]){
            return binarySearchByRecursion(ints,target,fromIndex,mid-1);
        }else if(target>ints[mid]){
            return binarySearchByRecursion(ints,target,mid+1,endIndex);
        }else {
            return mid;
        }
    }


}
