/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;

/**
 *
 * @author beishui
 */
public class Tuple<A, B> {
    public A first;
    public B second;
	
    public Tuple(A a, B b) {
       this.first = a;
       this.second = b;
	}
    @Override
    public String toString(){
         return "("+first+","+second+")";
      }

}

