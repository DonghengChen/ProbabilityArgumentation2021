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
public class IllegalOfAssignment{
    // Px denotes the parents of x
    public boolean legalIn(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet Px){
    boolean lin =false;
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    
    HashSet Po = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Po.addAll(Px);
    
    Po.retainAll(U);
    
 //   System.out.println("U="+U);
    
 //   System.out.println("P["+x+"]="+Px);
    
    if(In.contains(x)){ //x is labelled IN
       // System.out.println("Liao111");
    //     System.out.println("Po["+x+"]="+Po);
     if(Po.isEmpty()){ //every y that attacks x is labelled OUT
      //    System.out.println("Liao2");
       lin = true;
     }
     else{
       if(Out.containsAll(Po)){
  //     System.out.println("Liaotest");
    //    System.out.println("P["+x+"]="+Px);
        lin = true;
       }
     }
     
    }
  //  System.out.println("ligally in?="+lin);
    return lin;
    }
    
    public boolean legalOut(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet Px){
    boolean lout=false;
    HashSet In = L.first;
    HashSet Out = L.second;
   
    HashSet Undec = L.third;
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    
    HashSet Po = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Po.addAll(Px);
    
    Po.retainAll(U);
    
    
    if(Out.contains(x)){ //x is labelled OUT
      // System.out.println(x+" is labeled out");
     HashSet InCopy = (HashSet)In.clone();
     InCopy.retainAll(Po);
     if(!InCopy.isEmpty()){  //there is at least one y that attacks x and Y is labeled IN
       lout = true;
      // System.out.println(x+" is legal out");
     }
    }
    return lout;
    }
    
    public boolean legalUndec(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet Px){
    boolean lundec=false;
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    
    HashSet Po = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Po.addAll(Px);
    
    Po.retainAll(U);
    //Px.retainAll(U);
    
    if(Undec.contains(x)){ //x is labelled Undec
      // System.out.println(x+" is labeled Undec");
     HashSet UndecCopy = (HashSet)Undec.clone();
     HashSet InCopy = (HashSet)In.clone();
     InCopy.retainAll(Po);
     if(InCopy.isEmpty()){  //there 
       if(Out.containsAll(Po)){
       }
       else{
       lundec = true;
      // System.out.println(x+" ");
       }
     }
    }
    return lundec;
    }
    
    public boolean IllegalIn(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet Px){
    boolean Ill=false;
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    HashSet Ps = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Ps.addAll(Px);
    
    Ps.retainAll(U);
 //   System.out.println("postP["+x+"]="+Ps);
    if(In.contains(x)){ //x is labelled IN
    //    System.out.println("In "+In+" contains "+x);
     if(!legalIn(L, x, Ps)){ //every y that attacks x is labelled OUT
    //  System.out.println("illin: "+x);
       Ill = true;
     }
    }
  //  System.out.println("post:illin: "+x);
    return Ill;
    }
    
    public boolean SuperIllegalIn(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet[] P){
    boolean SIll=false;
    if(IllegalIn(L, x, P[x])){
     //   System.out.println("here");
        
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    
    HashSet Ps = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Ps.addAll(P[x]);
    
    Ps.retainAll(U);
    
    
        
    Iterator it = Ps.iterator();
    for(int i=1; i<=Ps.size(); i++){
    int y = (int)it.next();
  //  System.out.println("!!!liao y="+y);
    if(legalIn(L, y, P[y])){
     //   System.out.println("y is legally in");
        
    SIll=true;
    }
    if(legalUndec(L, y, P[y])){
    SIll=true;
 //   System.out.println(y+ " is legally undec");
    }
    
    }
    } 
    
    return SIll;
    }
    
    public boolean IllegalOut(ThreeTuple<HashSet,HashSet,HashSet> L, int x, HashSet Px){
    boolean Ill=false;
    HashSet In = L.first;
    HashSet Out = L.second;
    HashSet Undec = L.third;
    
    HashSet U = new HashSet();
    U.addAll(In);
    U.addAll(Out);
    U.addAll(Undec);
    
    HashSet Ps = new HashSet(); //to get of copy of Px, and keep Px unchanged
    Ps.addAll(Px);
    
    Ps.retainAll(U);
    
    
    //System.out.println("Liao1");
    if(Out.contains(x)){ //x is labelled OUT
     if(!legalOut(L, x, Ps)){ //every y that attacks x is labelled OUT
    //  System.out.println(x+ " is not legal out");
       Ill = true;
     }
    }
    return Ill;
    }
}
