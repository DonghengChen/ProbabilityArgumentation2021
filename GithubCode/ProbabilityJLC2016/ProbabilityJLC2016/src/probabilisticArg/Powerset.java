/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;
import java.util.*;
 
class Powerset{
  public <T> List<List<T>> getPowerset(Collection<T> list) {
    List<List<T>> ps = new ArrayList<>();
    ps.add(new ArrayList<T>());   // start with the empty set
 
    // for every item in the original list
    for (T item : list) {
      List<List<T>> newPs = new ArrayList<>();
 
      for (List<T> subset : ps) {
        // copy all of the current powerset's subsets
        newPs.add(subset);
 
        // plus the subsets appended with the current item
        List<T> newSubset = new ArrayList<>(subset);
        newSubset.add(item);
        newPs.add(newSubset);
      }
 
      // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
      ps = newPs;
    }
    return ps;
  }
}
