/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;

import java.util.HashSet;
import java.util.Iterator;

/**
 *
 * @author beishui
 */
public class TransitionStep {
    public ThreeTuple<HashSet,HashSet,HashSet> getTransition(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet[] C, HashSet[] P){
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    In.remove(x); //the label of x is changed from IN to OUT
    Out.add(x);
   // System.out.println("begin");
    L.first = In;
    L.second = Out;
    
    HashSet Px = P[x];
    boolean illoutx = new IllegalOfAssignment().IllegalOut(L, x, Px);
   // System.out.println("step 2");
    if(illoutx){
       // System.out.println("illoutx");
    Out.remove(x); //when x is illegally OUT, its label is changed from OUT to UNDEC
    Undec.add(x);
    L.second = Out;
    L.third = Undec;
    }
   
    Iterator it = C[x].iterator();
    for(int i=1; i<=C[x].size(); i++){
       int y = (int)it.next();
       HashSet Py = P[y];
     //  System.out.println("y= "+y);
    boolean illouty = new IllegalOfAssignment().IllegalOut(L, y, Py);
       if(illouty){
         //  System.out.println("illoutx");
    Out.remove(y); //when x is illegally OUT, its label is changed from OUT to UNDEC
    Undec.add(y);
    L.second = Out;
    L.third = Undec;
    }
    }
    
    return L;
    
    }
}
