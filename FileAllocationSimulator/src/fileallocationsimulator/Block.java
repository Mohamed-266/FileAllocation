/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileallocationsimulator;

/**
 *
 * @author Mohamed
 */
public class Block {
    boolean reserved;
    
    Block()
    {
        reserved = false;
    }
    
    void reserve()
    {
        reserved = true;
    }
    
    void unreserve()
    {
        reserved = false;
    }
    
    boolean isReserved()
    {
        return reserved;
    }
    
}
