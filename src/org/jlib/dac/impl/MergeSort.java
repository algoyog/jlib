package org.jlib.dac.impl;


import java.util.Arrays;
import java.util.Random;

import org.jlib.dac.DacBase;

public class MergeSort{
    
    int[] val = null;


    public static void main(String[] args) throws Exception {
        MergeSort m = new MergeSort();
        Random r = new Random();
        int[] input = r.ints(10,0,1000).toArray();
        //int[] input = {6,2,9,692,12,435,23,87};//
        m.val = input;
        System.out.println(m.val.length);
        Arrays.stream(m.val).mapToObj(a -> Integer.toString(a) + ",").forEach(System.out::print);
        m.sort();
        System.out.print("\n\n");
        
        System.out.println(m.val.length);
        Arrays.stream(m.val).mapToObj(a -> Integer.toString(a) + ",").forEach(System.out::print);

    }

    public void sort() throws Exception{
        if (val==null){
            throw new Exception("Input Null");
        }
        if (val.length<4){
            System.out.println("Please sort the input in your head :)");
        }
        divide(0, val.length-1);
    }

    public void divide(int start, int end) throws Exception {

        //Recurssion end cond.
        //when do i stop the recurssion?
        if ((end - start) <= 1 ){
            compareAndSwap(start, end);
            return;
        }
        // recur 1 on most significant half
        divide(start, start+(end-start)/2);
        
        // recur 2 on least significant half
        divide(start+1+((end-start)/2),end);
        
        // conquer/merge
        conquer(start, start+(end-start)/2, (end-start)/2 + 1+start, end);

        return;
    }

    //Local additional memory implementation. this method does not perform merge in-memory. :(
    public void conquer(int lmsb, int rmsb, int llsb, int rlsb) throws Exception {
        int[] swapArray = new int[rlsb-lmsb+1];
        int swapctr = 0;
        if(lmsb > rmsb || llsb > rlsb) {
            throw new Exception("Incorrect Algo Implementation");
        }
        
        if(rmsb==lmsb && rlsb == llsb){
            compareAndSwap(lmsb, llsb);
        }else{
            int left = lmsb;
            int right = llsb;
            while (true) {
                if(val[left]<val[right]){
                    swapArray[swapctr] = val[left];
                    swapctr++;
                    left++;
                }else{
                    swapArray[swapctr] = val[right];
                    swapctr++;
                    right++;
                }
                
                if(left >rmsb){
                    for (int i = right;i<=rlsb; i++){
                        swapArray[swapctr] = val[i];
                        swapctr++;
                    }
                    break;
                }

                if(right > rlsb){
                    for(int i = left;i<=rmsb;i++){
                        swapArray[swapctr] = val[i];
                        swapctr++;
                    }
                    break;
                }
            }
        }
        swapctr = 0;
        for(int i = lmsb;i<=rlsb;i++){
            val[i] = swapArray[swapctr];
            swapctr++;
        }

        return;
    }

    private boolean compareAndSwap(int left, int right){
        boolean wasSwapped = false;
        if (val[right]<val[left]){
            int temp = val[left];
            val[left] = val[right];
            val[right] = temp;
            wasSwapped = true;
        }
        return wasSwapped;
    }
 

}