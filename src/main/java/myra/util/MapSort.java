package myra.util;

public class MapSort {
    public static int[] sort(float[] arr){
        Couple[] couples = new Couple[arr.length];

        // zip couples
        for(int i =0;i<arr.length;i++){
            couples[i]=new Couple(arr[i], i);
        }

        innerSort(couples, 0, arr.length-1);
        
        // unzip couples
        int[] mapping = new int[arr.length];
        for(int i =0;i<arr.length;i++){
            mapping[i]=couples[i].in;
        }

        return mapping;
    }
    private static void innerSort(Couple[] arr, int low, int high){
        int len = high-low;
        if (len<1) return;
        int pivotInd = low;
        Couple pivot = arr[pivotInd];

        for(int i = low+1; i<high+1;i++){
            if(arr[i].fl<pivot.fl){
                arr[pivotInd]=arr[i];
                arr[i]=pivot;
                pivotInd++;
            }
        }
        innerSort(arr, low, pivotInd-1);
        innerSort(arr, pivotInd+1, high);
        
        return;
    }

    private static class Couple {
        public float fl;
        public int in;
        public Couple(float fl, int in){
            this.fl = fl;
            this.in = in;
        }
        public String toString(){
            return "fl: "+Float.toString(fl)+"\nin: "+Integer.toString(in);
        }
    }
}
