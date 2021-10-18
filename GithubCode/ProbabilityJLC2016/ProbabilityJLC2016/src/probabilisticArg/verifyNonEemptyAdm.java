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
public class verifyNonEemptyAdm {
   long begin2 = System.currentTimeMillis();  
    boolean bl = false;
    
   public Boolean VerifyAdm(ThreeTuple<HashSet,HashSet,HashSet> L, HashSet[] C, HashSet[] P){
  
    HashSet In = L.first;
    HashSet AD = L.first; //an admissible set
  //  System.out.println("second");
    HashSet Illin, SIllin;
    Illin = new HashSet();
    SIllin = new HashSet();
    if(!In.isEmpty()){   //get the set of arguments that are illegally IN
     //   System.out.println("Third");
       Iterator Itin = In.iterator();
       for(int i=1; i<=In.size(); i++){
          int x = (int)Itin.next();
        //   System.out.println("????L= "+L);
          if(new IllegalOfAssignment().IllegalIn(L, x, P[x])){
            //   System.out.println("illigal of "+x);
                 Illin.add(x);
              if(new IllegalOfAssignment().SuperIllegalIn(L, x, P)){
              //    System.out.println("condition="+Condition);
              //    if(!Condition.contains(x)){
                     SIllin.add(x);
                     
                  //  System.out.println("add "+x);
               //   }
              }
            // else{
             //     if(!Condition.contains(x)){  //exclude the conditioning arguments whose status is IN
            //      Illin.add(x);
             //     }
                 // System.out.println("six");
              }
          }
       
     //     System.out.println("Illin:"+Illin);
     //     System.out.println("SIllin:"+SIllin);
    }
    
    if(!Illin.isEmpty()){  
      //    System.out.println("seventh");
           if(!SIllin.isEmpty()){ //if L has an argument that is super-illegally IN
        //    System.out.println("super-illegally");
            int x = (int)SIllin.iterator().next();
        //    System.out.println("x= "+x);
            
            HashSet in, out, undec;
            in = (HashSet)L.first.clone();
            out = (HashSet)L.second.clone();
            undec = (HashSet)L.third.clone();
            ThreeTuple<HashSet, HashSet, HashSet> L5 = new ThreeTuple<>(in, out, undec);
        //    System.out.println("L5= "+L5);
            
            ThreeTuple<HashSet,HashSet,HashSet> L2 = new TransitionStep().getTransition(L5, x, C, P); 
       //     System.out.println("L2= "+L2);
       //     AD = L2.first;
            VerifyAdm(L2, C, P);
          } 
         else{
          //    System.out.println("illegally");
          if(!Illin.isEmpty()){
            Iterator it3 = Illin.iterator();
            for(int i=1; i<=Illin.size(); i++){
             int y = (int)it3.next();
         //    System.out.println("y="+y);
          //   System.out.println("!!L is:"+L);
             
             //preserve the state of L
             HashSet in1, out1, undec1;
             in1 = (HashSet)L.first.clone();
             out1 = (HashSet)L.second.clone();
             undec1 = (HashSet)L.third.clone();
             ThreeTuple<HashSet, HashSet, HashSet> L6 = new ThreeTuple<>(in1, out1, undec1);
             
              ThreeTuple<HashSet,HashSet,HashSet> L1 = new TransitionStep().getTransition(L6, y, C, P); 
              AD = L1.first;
         //     System.out.println("AD="+AD);
          //    System.out.println("L1 is:"+L1);
              long end2 = System.currentTimeMillis();  
              if(end2-begin2>30000){
                  HashSet error;
                  error = new HashSet();
                  System.out.println("exceeds 10...");
                  break;
              }
              VerifyAdm(L1, C, P);
            }
          }
        }
    }
    else {
    //    System.out.println("here AD="+AD);
           if(!AD.isEmpty()){
           bl = true; // if there is a nonempty admissible set, then exist
     //      System.out.println("I am here");
           return bl;
           }
       }
 //   System.out.println("bl ="+bl);
   return bl; 
    
}
}
