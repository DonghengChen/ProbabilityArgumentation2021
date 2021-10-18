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
public class VerifyPreferredLabellings {
 //  HashSet candidatelabellings;
 //  candidatelabellings = new HashSet();
   long begin2 = System.currentTimeMillis();  
   
    boolean bl = true;
    //boolean bl1 = true;
   // boolean bl2 = true;
    
  //  HashSet candidatelabellings;
 //  candidatelabellings = new HashSet();
    
   public Boolean VerifyLabellings(ThreeTuple<HashSet,HashSet,HashSet> L, HashSet[] C, HashSet[] P, HashSet S){
   
    HashSet In = L.first;
 //   System.out.println("Pre: "+ In);
    HashSet Illin, SIllin;
    Illin = new HashSet();
    SIllin = new HashSet();
    if(!In.isEmpty()){   //get the set of arguments that are illegally IN
     //   System.out.println("Third");
       Iterator Itin = In.iterator();
       for(int i=1; i<=In.size(); i++){
          int x = (int)Itin.next();
         //  System.out.println("parents["+x+"]="+P[x]);
          if(new IllegalOfAssignment().IllegalIn(L, x, P[x])){
             //  System.out.println("illigal of "+x);
               Illin.add(x);
              if(new IllegalOfAssignment().SuperIllegalIn(L, x, P)){
              //    System.out.println("condition="+Condition);
              //    if(!Condition.contains(x)){
                     SIllin.add(x);
                     
                  //  System.out.println("add "+x);
               //   }
              }
           
              }
          }
       
 //    System.out.println("Illin:"+Illin);
 //    System.out.println("SIllin:"+SIllin);
     
      
      
    }
    
      HashSet su = new HashSet();
      su.addAll(S);
   //   System.out.println("S is "+S); 
   //   System.out.println("In= "+In); 
      su.retainAll(SIllin); //some arguments in S are super illegally in. So, S can not be a preferred extension: this point is not correct
      if(!su.isEmpty()){
     //     System.out.println("point1");
          if(In.containsAll(S)){
      //         System.out.println("point2");
         bl=false;}  
      }
      else{
    //L中的in全部合法，检验是否比现有结果candid中更优，替换
    if(Illin.isEmpty()){  //if L does not have an argument that is illegally IN
      //  System.out.println("no illegally IN");
      //  System.out.println("IN: "+In);
      //   System.out.println("S: "+S);
 //       if(In.equals(S)){  //if find another extension that is equal to S, then reset the value of bl
  //       bl=true;
   //     }
        boolean test = In.containsAll(S);
     //   System.out.println("In contains S is "+test);
     if(test){
      //   System.out.println("S size "+S.size());
       //  System.out.println("In size "+In.size());
        if(S.size()<In.size()){
            bl=false;
        }
     }
         
    }
    else{
    //	 System.out.println("seventh");
    	//存在superillegalin，递归其中一个
    	if(!SIllin.isEmpty()){ //if L has an argument that is super-illegally IN  如果L包含superillegallyin
    	//	System.out.println("super-illegally");
            int x = (Integer)SIllin.iterator().next();
    	//	System.out.println("x= "+x);
            HashSet in, out, undec;
            in = (HashSet)L.first.clone();
            out = (HashSet)L.second.clone();
            undec = (HashSet)L.third.clone();
            ThreeTuple<HashSet, HashSet, HashSet> L5 = new ThreeTuple<>(in, out, undec);
        //    System.out.println("L5= "+L5);
            ThreeTuple<HashSet,HashSet,HashSet> L2 = new TransitionStep().getTransition(L5, x, C, P); 
       //     System.out.println("L2= "+L2);
             In = L2.first;
        //    System.out.println("L2: In "+In);
            VerifyLabellings(L2, C, P, S);
    	} 
        else{
        	// System.out.println("illegally");
        	//存在illegalin，递归所有
        	if(!Illin.isEmpty()){
	            Iterator it3 = Illin.iterator();
	            for(int i=0; i<Illin.size(); i++){
	            	int y = (Integer)it3.next();
	           // 	System.out.println("y="+y);
	            	//System.out.println("!!L is:"+L);
	            	//preserve the state of L
                        if(!S.contains(y)){
	            	HashSet in1, out1, undec1;
	            	in1 = (HashSet)L.first.clone();
	             	out1 = (HashSet)L.second.clone();
	             	undec1 = (HashSet)L.third.clone();
	             	ThreeTuple<HashSet, HashSet, HashSet> L6 = new ThreeTuple<>(in1, out1, undec1);
	             	ThreeTuple<HashSet,HashSet,HashSet> L1 = new TransitionStep().getTransition(L6, y, C, P); 
                    //    System.out.println("!!L1 is:"+L1);
	               	In = L1.first;
                     //   System.out.println("L1: In "+In);
	             	VerifyLabellings(L1, C, P, S);
                        }
	            }
        	}
        }    
    }
      
           
      }
   //   System.out.println("post:" + In);
   //   System.out.println("bl:" + bl);
      return bl;  
   }
    
   
    }
    
