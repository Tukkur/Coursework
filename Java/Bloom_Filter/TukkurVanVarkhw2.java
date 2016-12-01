
import java.util.Arrays;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**import LineReader.java;
/**
 *
 * @author Tukkur
 */


class driver{
    
    public driver(){
        BloomFilter bloom = new BloomFilter(850);
        LineReader lr = new LineReader("/Users/Tukkur/Desktop/Classes/4041/TukkurVanVarkhw2/src/basic.txt");
        LineReader lr2 = new LineReader("/Users/Tukkur/Desktop/Classes/4041/TukkurVanVarkhw2/src/basic.txt");
        
        while (lr.hasNext()){
            bloom.add(lr.next());
        }
        while (lr2.hasNext()){
            bloom.isIn(lr2.next());
        }
        
        System.out.println("Accuracy: " + bloom.accuracy());
        
    }
}

class BitArray {

	public int m;
	public int[] a;

	public BitArray(int m){
		if (m<0){
			throw new IllegalArgumentException();
		} else {
			this.m = m;
			this.a = new int[m/32+1];
                        for (int element: this.a){
                            element=0;
                        }
		}
	}

        public boolean get(int n){
            if (n<0 || n>=m){
                throw new IndexOutOfBoundsException();
             } else{
                return a[n/32] == 1;
             }
        }
        
        public void set(int n){
            if (n<0 || n>=m){
                throw new IndexOutOfBoundsException();
            } else{
                  a[n/32] = 1;
            }
            
        }
        
        

}




class BloomFilter {
   
    public BitArray a;
    
    public BloomFilter(int m){
        if (m<0){
            throw new IllegalArgumentException();
        } else{
             this.a = new BitArray(m);
        }
    }
    
    private int h1(String w){
        return Math.abs(w.hashCode()%a.m);
    }
    
    private int h2(String w){
        return Math.abs(w.hashCode()*953%a.m);
    }
    
    public void add(String w){
        this.a.set(h1(w));
        this.a.set(h2(w));
    }
    
    public boolean isIn(String w){
        if (!a.get(h1(w)) || !a.get(h2(w))){
            System.out.println(w);
        }
        return a.get(h1(w)) || a.get(h2(w));
    }
    
    
    public double accuracy(){
        double e = 2.718281828;
        double x = Math.pow(e, (-2*850/a.m));
        return (Math.pow((1-x),2));
    }
    
    
}

//OUTPUT Accuracy: 0.7476450723364627