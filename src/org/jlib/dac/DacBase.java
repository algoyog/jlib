package org.jlib.dac;

//Divide and Conquer Base interface
public interface DacBase<T extends Number>{
    
    //divide
    public T divide(T input);
    
    //conquer
    T conquer(T input);

}