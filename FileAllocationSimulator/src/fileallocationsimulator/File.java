/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileallocationsimulator;

/**
 *
 * @author seif
 */

//not an actual file class
public class File {
    //length is how many blocks the file would take
    int length;
    private int  startingBlock;
    String name;
    
    public File(int length, String name) {
        this.length = length;
        this.name = name;
    }
    
    public void setStartingBlock(int index)
    {
        startingBlock = index;
    }
    
    public int getStartingBlock()
    {
        return startingBlock;
    }



    
}
