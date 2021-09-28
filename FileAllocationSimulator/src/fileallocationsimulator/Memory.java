/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileallocationsimulator;
import java.util.Random; 

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

import static java.lang.System.exit;

/**
 *
 * @author Mohamed & Sief
 * 
 */
//basically a list of blocks 
public class Memory {

    private ArrayList<Block> blockList = new ArrayList<>();
    private int size;
    private ArrayList<File> fileList = new ArrayList<>();

    public Memory(int size) {
        this.size = size;

        for (int i = 0; i < size; i++) {
            blockList.add(new Block());
        }
    }

    void printFilesInMemory() {

        System.out.println("File Name\t\t filelength \t\t fileStart "/*\t\t BlocksAllocated*/);
        for (File f : fileList) {
            System.out.println(f.name + "\t\t\t " + f.length + "\t\t\t " + f.getStartingBlock() /*+ "\t\t\t "*/);
         /*   for (int i =f.getStartingBlock();i<=f.length;i++){
            System.out.print(i + "/ ");
        }
            
            System.out.println();
            */
        }

    }


    //there are two current options to how this would work 
    //1-allocate with random bases in the memory  
    //2- allocate from the very beggning 
    //hypo stands for hypothetical so it doesnt get confused with the original java
    //file class

    public void contigousAllocation(File fileHypo) {
        //will work randomised for now
        int fileLength = fileHypo.length;
        Random rand = new Random();
        int randomStart = rand.nextInt(size);

        //to check whether or not the files is insertable
        //we need to sort the fileList interms of starting point 
        fileList.sort(Comparator.comparing(a -> a.getStartingBlock()));


        int noOfFiles = fileList.size();

        for (int i = 0; i < noOfFiles; i++) {
            //check if the random start is valid or not
            int space = 0;


            if (randomStart + fileLength > size) {
                if (i + 1 > noOfFiles)
                    space = size - (fileList.get(i).length + fileList.get(i).getStartingBlock());
                else
                    space = fileList.get(i + 1).getStartingBlock() - (fileList.get(i).length + fileList.get(i).getStartingBlock());

                //not so random now
                randomStart = fileList.get(i).length;
            }
            //if there is space reserve itt
            System.out.println("test?");
            if (fileLength == space) {

                int finish = randomStart + fileLength;

                fileHypo.setStartingBlock(randomStart);
                fileList.add(fileHypo);

                while (randomStart <= finish) {
                    blockList.get(randomStart).reserve();
                    randomStart++;
                }
            } else {
                System.out.println("sorry not enough space...");
            }
        }
    }

    //mainly for testing a case
    public void contigousAllocation(File fileHypo, int start) {
        //will work randomised for now
        int fileLength = fileHypo.length;
        //Random rand = new Random(); 
        int randomStart = start;

        //to check whether or not the files is insertable
        //we need to sort the fileList interms of starting point 
        fileList.sort(Comparator.comparing(a -> a.getStartingBlock()));
        fileList.add(fileHypo);
        int noOfFiles = fileList.size();
        //System.out.println(noOfFiles);

        for (int i = 0; i < noOfFiles; i++) {
            int space = size;
            //if there are already allocated files we dont want to just
            //randomly keep trying to land a file so to make sure we get the 
            //most out of our allocation we made this condition

            if ((randomStart + fileLength) > size && (blockList.get(randomStart).isReserved())) {

                if (i + 1 > noOfFiles) {
                    space = size - (fileList.get(i).length + fileList.get(i).getStartingBlock());
                } else {
                    space = fileList.get(i + 1).getStartingBlock() - (fileList.get(i).length + fileList.get(i).getStartingBlock());

                }
                randomStart = fileList.get(i).length;

            }
            //if there is space reserve itt
            if (fileLength <= space) {
//                for (int j=randomStart;j<=(randomStart+fileLength);j++){
//                if (index is reserved)
//                
//                } 
                int finish = (randomStart + fileLength) - 1;

                fileHypo.setStartingBlock(randomStart);


                //fileList.add(fileHypo);

                while (randomStart <= finish) {
                    blockList.get(randomStart).reserve();

                    randomStart++;
                }
                break;
            } else {
                System.out.println("sorry not enough space...");
            }
        }
    }





}
    
    //search for the file then make all the files blocks deallocated
    /*
    public void deAllocate(File fileHypo)
    {
        //search for the file we want to deallocate
        for(int i = 0;i<size;i++)
        {
            //if the file was found Note that we are searching by name
            //deAllocate every single block from the files start to its length
            if((fileList.get(i).name).equals(fileHypo.name))
            {
                //calculate the start and finish
                int start = fileList.get(i).getStartingBlock();
                int finish = start + fileList.get(i).length;
                fileList.remove(i);
                //keep deallocating until you reach the finish Line !!
                while(start <= finish)
                {
                    blockList.get(start).unreserve();
                    start++;
                }
            }
            
        }
        
        
    }
    */
    
    

