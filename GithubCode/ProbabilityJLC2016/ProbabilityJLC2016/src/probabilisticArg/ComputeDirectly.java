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
public class ComputeDirectly {
   public double verifySemantics(HashSet Args, HashSet Sub, HashSet S, HashSet[] Children, HashSet[] Parents, double[] p){
    
     double prs;
     prs = 0;
     Boolean bl; 
    // bl = false;
  //  System.out.println("SSUB"+Sub);
     HashSet in2, out2, undec2;
     out2 = new HashSet();
    // out2.removeAll(S); 
     undec2 = new HashSet();
     in2 = Sub;
   // in2 = (HashSet)Args.clone();
    
 //   System.out.println("prepre: "+in2); 
    
    ThreeTuple<HashSet, HashSet, HashSet> L = new ThreeTuple<>(in2, out2, undec2);
    
    bl = new VerifyPreferredLabellings().VerifyLabellings(L, Children, Parents, S);
      
    if(bl==true){
  //  System.out.println(Sub+" has an extension "+S);
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
 //   System.out.println("probability of remaining arguments " + x1);
    //pr = pr +x1;
    while(itset.hasNext()){
    int intst2 = (int)itset.next();   
   // System.out.println("intst2= "+intst2);  
   // System.out.println("p["+intst2+"]= "+p[intst2]);
  //  System.out.println("pre-x1= "+x1);
    x1 = x1 * p[intst2];
  //  System.out.println("post-x1= "+x1);
    }
  //   System.out.println("probabiity of all aruments" + x1);
    prs = x1;
    }
  //  System.out.println("prs" + prs); 
    return prs; 
}
}
