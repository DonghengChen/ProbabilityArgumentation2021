/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package probabilisticArg;

import java.util.HashSet;

/**
 *
 * @author beishui
 */
public class SetEqual {
    public boolean Equal(HashSet A, HashSet B){
    boolean eq = false;
    if(A.isEmpty()&&B.isEmpty()){
    eq = true;
    }
    else{
    if(A.containsAll(B)&&B.containsAll(A)){
    eq = true;
    }
        
    }
    
    
    return eq;
    }
}
