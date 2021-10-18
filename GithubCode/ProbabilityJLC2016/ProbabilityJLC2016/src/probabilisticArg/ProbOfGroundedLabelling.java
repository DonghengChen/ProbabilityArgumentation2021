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
public class ProbOfGroundedLabelling {
    public double GetProb(HashSet Args, HashSet Sub, HashSet S, HashSet[] P, HashSet[] C, double[] p){
    	//P parent C children A initU
    
    double prob = 0;	
    HashSet in, out, in1;
    
    in = new HashSet();
    out = new HashSet(); 
    
    
    boolean eq;
    
    do{
    	
    	//System.out.println("guo1"); 
    	in1 = (HashSet)in.clone();
    	Iterator it = Sub.iterator();
    	//HashSet RM;
    	//RM = new HashSet();
    	//System.out.println("RM="+RM);
    	
    	for(int i=0; i<Sub.size(); i++){
    		
    		//System.out.println("guo2"); 
    		int x=(Integer)it.next();
    		HashSet copyP = new HashSet();
                copyP.addAll(P[x]);
    		System.out.println("P["+x+"]="+copyP); 
                System.out.println("Sub="+Sub); 
    		copyP.retainAll(Sub); 
                System.out.println("post-P["+x+"]="+copyP);
    		
    		if(copyP.isEmpty()|out.containsAll(copyP)){
    			
    			//System.out.println("guo3"); 
    			in.add(x);
                        System.out.println("in="+in); 
    			//RM.removeAll(C[x]);
    			//System.out.println("Pre-C{"+x+"]="+C[x]); 
    			
    			C[x].retainAll(Sub);
    			//System.out.println("Post-C{"+x+"]="+C[x]); 
    			out.addAll(C[x]);

    		}
    		else{
    			HashSet inCopy = (HashSet)in.clone();
    			inCopy.retainAll(copyP);
    			if(!inCopy.isEmpty()){
    				out.add(x);
    			}
        
    		}
    
    	}
        System.out.println("!!!!test"); 
        System.out.println("in="+in); 
        System.out.println("in1="+in1);
    	eq = new SetEqual().Equal(in, in1);   
    } while(!eq);
    
    if(in.equals(S)){
    
    HashSet CO = new HashSet(); 
    CO.addAll(Args);
    CO.removeAll(Sub);
   
   // System.out.println("Co= "+ CO);
    Iterator itco = CO.iterator();
    Iterator itset = Sub.iterator();
    double x1=1;
    while(itco.hasNext()){
    x1 = x1 * (1-p[(int)itco.next()]);
 //   System.out.println("1x1= "+x1);
    }
    
    while(itset.hasNext()){
    int intst2 = (int)itset.next();   
   // System.out.println("intst2= "+intst2);  
   // System.out.println("p["+intst2+"]= "+p[intst2]);
  //  System.out.println("pre-x1= "+x1);
    x1 = x1 * p[intst2];
  //  System.out.println("post-x1= "+x1);
    }
  //   System.out.println("probabiity of all aruments" + x1);
    prob = x1;
    
    }
    System.out.println("IN= "+in);
    System.out.println("S= "+S);
    if(in.equals(S)){
      System.out.println("The Grounded labelling of "+ Sub+" is  "+in);
    }
    return prob;
}}
