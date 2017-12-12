package utilities;

import java.util.HashMap;

/**
 * Created by deepak.verma on 08/12/17.
 */
public class ItoSMap<K,V> extends HashMap<String,Integer> {

    public void addVal(String key, Integer valToAdd) {

        Integer val = this.get(key);

        if(val==null){

            this.put(key,new Integer(valToAdd));

        } else {

            Integer addedVal = (Integer) val + valToAdd;

            this.put(key, addedVal);

        }

        if(this.size()>5){

            this.removeSmallest();

        }

    }

    public void printVal(String key){
        System.out.printf("Value at the key %s = %d \n",key,this.get(key));
    }

    public void PrintMap(){

        for (String key : this.keySet()) {

            System.out.println("key: " + key + " Value:"+ this.get(key));

        }

    }

    public void removeSmallest(){

        String smallestKey="";

        Integer smallestVal= new Integer(1000000000);

        for (String key : this.keySet()){

            if(smallestVal > this.get(key)){

                smallestKey = key;

                smallestVal = this.get(key);

            }
        }

        if(smallestKey!=""){

            this.remove(smallestKey);

        }

    }
}