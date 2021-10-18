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
public class ParentsOfArgument {
    public HashSet[] getParent(int[][] r, int ArgumentNumber, HashSet Args){
    HashSet[] Parent = new HashSet[ArgumentNumber+1];
    Iterator it = Args.iterator();
    for(int i=1; i<=Args.size(); i++){
    int k=(int)it.next();
    Parent[k] = new HashSet();
    }
    for(int i=1; i<=ArgumentNumber; i++){
     for(int j=1; j<=ArgumentNumber; j++){
         if(r[i][j]==1){
             if(Args.contains(i)&&Args.contains(j)){  //if i belongs to Args
              Parent[j].add(i); 
             }
         }
     }
     }
   
    return Parent;
    }
    
    public HashSet[] getChildren(int[][] r, int ArgumentNumber, HashSet Args){
    HashSet[] Children = new HashSet[ArgumentNumber+1];
    for(int i=1; i<=ArgumentNumber; i++){
    Children[i] = new HashSet();
    }
    for(int i=1; i<=ArgumentNumber; i++){
     for(int j=1; j<=ArgumentNumber; j++){
         if(r[i][j]==1){
              if(Args.contains(i)&&Args.contains(j)){  //if j belongs to Args
                Children[i].add(j);
              }
         }
     }
     }
    return Children;
    }
    
    }
    
    

