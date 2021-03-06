package org.jlib.dac.impl;


import java.util.Arrays;
import java.util.Random;

import org.jlib.dac.DacBase;

public class CountInversion{
    
    int[] val = null;
    int invCount = 0;

    public static void main(String[] args) throws Exception {
        CountInversion m = new CountInversion();
        Random r = new Random();
        
        m.val = new int[]{1,9,6,4,5}; //5
        //m.val = new int[]{8,4,2,1}; //6
        //m.val = new int[]{2,4,1,3,5}; //3
        //m.val  = r.ints(6,0,20).toArray();
        
        Arrays.stream(m.val).mapToObj(a -> Integer.toString(a) + ",").forEach(System.out::print);
        
        m.sort();
        
        System.out.print("\n\n");
        Arrays.stream(m.val).mapToObj(a -> Integer.toString(a) + ",").forEach(System.out::print);
        System.out.println(m.invCount);

    }

    public void sort() throws Exception{
        if (val==null){
            throw new Exception("Input Null");
        }
        if (val.length<4){
            System.out.println("Please sort the input in your head :)");
            return;
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
            int diff = right -left;
            while (true) {
                if(val[left]<val[right]){
                    swapArray[swapctr] = val[left];
                    swapctr++;
                    left++;
                }else{
                    swapArray[swapctr] = val[right];
                    swapctr++;
                    right++;
                    invCount+=(rmsb-left+1);
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
            invCount++;
        }
        return wasSwapped;
    }
}