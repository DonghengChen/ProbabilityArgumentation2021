/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author beishui
 */
public class genCoSubgraph {
    HashSet Co;
    long begin66 = System.currentTimeMillis();
    
    
    public double coSub(HashSet Args, HashSet[] Children, HashSet[] Parents, HashSet E, double[] p){
    Co = new HashSet();
    double cos;
    cos = 0;
    //Ad.addAll(E);
 //    System.out.println("!parents of 3 is "+ Parents[3]); 
    HashSet Ec, Ep, Rm;
    Ec = new HashSet(); //initialize E's children
    Ep = new HashSet(); // initialize E's parents
    Rm = new HashSet(); //initialize remaining arguments
    Rm.addAll(Args); // Rm = Args
   // System.out.println("1.Rm= " + Rm);
    
    Iterator its = E.iterator();
    while(its.hasNext()){
      int i = (int)its.next();
      Ep.addAll(Parents[i]); //E's parents
      Ec.addAll(Children[i]); //E's children
    }
   //  System.out.println("E's parents " + Ep);
   //  System.out.println("E's chilren " + Ec);
  //  System.out.println("!!parents of 3 is "+ Parents[3]); 
    Ep.removeAll(Ec);  //Ep \ Ec
    Rm.removeAll(Ep); // Args \ (Ep\Ec)
    Rm.removeAll(E); // the set of remaining arguments: Args - (Ep-Ec) - E -- this works for some examples, which can not be handleed by the ditect approach: consider a AF as follows:
                     // arguments:[1, 2, 3, 4, 5, 6]
                       // attacks[6][3]=1
                         // attacks[3][5]=1
                       // attacks[6][2]=1
                       // attacks[5][3]=1
                       // attacks[6][5]=1
                       // attacks[3][1]=1
                       // attacks[5][2]=1
                       //  attacks[4][3]=1
    
  //  System.out.println("Ec: " + Ec);
  // System.out.println("Ep: " + Ep);
 //   System.out.println("Rm: " + Rm);
    List ps = new Powerset().getPowerset(Rm); // get the power set of remaining arguments
    
    Iterator itps = ps.iterator();
  //  System.out.println("pre subgraphs: " + Ad);
    HashSet tem, tco;
    while(itps.hasNext()){
    List tm = (List) itps.next();
    //tem = (HashSet)itps.next();
    tem = new HashSet(tm);  
    
    tco = new HashSet(tm); 
    tco.removeAll(Ec); // A" = args(G')\(E\cup E+): used to test whether whether the admissible subgraph induced by tem is a complete subgraph
  //  System.out.println("tco: " + tco);
    tem.addAll(E);  //\Phi\cup E
   // System.out.println("tem: " + tem);
    
    Iterator ittco = tco.iterator();
 //   System.out.println("!!!parents of 3 " +Parents[3]);
    while(ittco.hasNext())
    {
       HashSet palpha = new HashSet();  //parents of each argument \alpha in A"
       int pa = (int)ittco.next();
       palpha.addAll(Parents[pa]);
   //    System.out.println("1: attackers of " + pa + " is " + palpha);
       palpha.retainAll(tem);
    //   System.out.println("attackers of " + pa + " is " + palpha);
       if(palpha.isEmpty()){
       tem.clear();
       break;
       }
    
    }
    if(!tem.isEmpty()){ 
      Co.add(tem);
      }
    
    long end66 = System.currentTimeMillis(); 
    //System.out.println("test2");
	             	if(end66-begin66>180000){ //新方法设定超时3分钟
	             		//HashSet error;
	             		//error = new HashSet();
	             		System.out.println("新方法求解完全语义超时，如下时间无效");
                                //exc = true;
	             		break;
	             	}
    
    }
    
  //  System.out.println("all subgraphs: " + Ad);
    
  Iterator itco = Co.iterator();
  while(itco.hasNext()){
      HashSet h = (HashSet)itco.next(); 
    //  System.out.println(h+" has an extension "+E);
      HashSet CO = new HashSet(); 
      CO.addAll(Args);
      CO.removeAll(h);
      
      Iterator itco1 = CO.iterator();
      Iterator itset = h.iterator();
      double x1=1;
      while(itco1.hasNext()){
         x1 = x1 * (1-p[(int)itco1.next()]);
        //   System.out.println("1x1= "+x1);
       }
      
      while(itset.hasNext()){
      int intst2 = (int)itset.next();  
      x1 = x1 * p[intst2];
      }
      
      cos = cos + x1;
      
  }
    
    return cos;
    
    }
}
