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
public class genAdmSubgraph {
    HashSet Ad;
    
    public HashSet admSub(HashSet Args, HashSet[] Children, HashSet[] Parents, HashSet E){
    Ad = new HashSet();
    //Ad.addAll(E);
    
    
    HashSet Ec, Ep, Rm;
    Ec = new HashSet();
    Ep = new HashSet();
    Rm = new HashSet();
    Rm.addAll(Args);
    
    Iterator its = E.iterator();
    while(its.hasNext()){
      Ep.addAll(Parents[(int)its.next()]);
      Ec.addAll(Children[(int)its.next()]);
    }
    
    Ep.removeAll(Ec);  //Ep - Ec
    Rm.removeAll(Ep); // Args - (Ep-Ec)
    Rm.removeAll(E); // the set of remaining arguments: Args - (Ep-Ec) - E
    
    List ps = new Powerset().getPowerset(Rm); // get the power set of remaining arguments
    
    Iterator itps = ps.iterator();
  //  System.out.println("pre subgraphs: " + Ad);
    HashSet tem;
    while(itps.hasNext()){
    List tm = (List) itps.next();
    //tem = (HashSet)itps.next();
    tem = new HashSet(tm);
    tem.addAll(E);
  //  System.out.println("subgraph: " + tem);
    Ad.add(tem); 
    }
  //  System.out.println("all subgraphs: " + Ad);
    return Ad;
    
    }
    
}
