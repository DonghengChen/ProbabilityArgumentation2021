/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;

/**
 *
 * @author beishui
 */

public class ThreeTuple<A, B, C> extends Tuple<A, B> {
    public C third;
	
    public ThreeTuple(A a, B b, C c) {
	super(a, b);
        this.third = c;
	}
    @Override
   public String toString(){
         return"("+first+","+second+","+third+")";
      }


}

