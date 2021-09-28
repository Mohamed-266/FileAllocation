package fileallocationsimulator;

import java.util.Scanner;
import java.util.Random;

import static java.lang.System.exit;

public class Allocations {
    //public int size;
    //public int [] blocks= new int[size];
    //public int [] indexBlock= new int [size];
    int indexedBlock;

    int numOfFiles;
    Scanner ib = new Scanner(System.in); //s
    Scanner nof = new Scanner(System.in);
    void indexed(int size ,int blocks [] ,int indexBlock []) {
       // System.out.println("Enter size of memory : ");
        //int size = ib.nextInt();

        //int [] blocks = new int[size];
        //int [] indexBlock = new int[size];
        System.out.println("Enter the index block");
        indexedBlock = ib.nextInt();
        int rand;
        Random randnum = new Random();
        if (blocks[indexedBlock] != 1) {
            System.out.println("Enter the number of files needed for the index " + indexedBlock + " on the disk: ");
            numOfFiles = nof.nextInt();
            int flag = 0;


            for (int i = 0; i < numOfFiles; i++) {
                if (blocks[indexBlock[i]] == 0)
                    flag++;
                indexBlock[i] = 0 + randnum.nextInt(size);
            }
            if (flag == numOfFiles) {
                for (int j = 0; j < numOfFiles; j++) {
                    blocks[indexBlock[j]] = 1;
                }
                System.out.println("Allocated");
                System.out.println("File indexed");
                for (int k = 0; k < numOfFiles; k++) {
                    System.out.println(indexedBlock + "----->" + indexBlock[k] + " : " + blocks[indexBlock[k]]);
                }
            } else {
                System.out.println("File in the index is already allocated");
                System.out.println("Enter another indexed file");
            }
            System.out.println("Do you want to enter more files?");
            System.out.println("Enter 1 for Yes, Enter 0 for No: ");
            int ch;
            ch = ib.nextInt();
            if (ch == 1)
                indexed(size , blocks, indexBlock);
            else
                exit(0);
        } else {
            System.out.println(indexedBlock + " is already taken");
            indexed(size , blocks, indexBlock);
        }

       // indexedAllocation(size , blocks, indexBlock);

    }

 /*   void indexedAllocation(int size , int blocks[] , int indexBlock []) {
        int flag = 0;
        for (int i = 0; i < numOfFiles; i++) {
            indexBlock[i] = ib.nextInt();
            if (blocks[indexBlock[i]] == 0)
                flag++;
        }
        if (flag == numOfFiles) {
            for (int j = 0; j < numOfFiles; j++) {
                blocks[indexBlock[j]] = 1;
            }
            System.out.println("Allocated");
            System.out.println("File indexed");
            for (int k = 0; k < numOfFiles; k++) {
                System.out.println(indexedBlock + "----->" + indexBlock[k] + " : " + blocks[indexBlock[k]]);
            }
        } else {
            System.out.println("File in the index is already allocated");
            System.out.println("Enter another indexed file");
        }
        System.out.println("Do you want to enter more files?");
        System.out.println("Enter 1 for Yes, Enter 0 for No: ");
        int ch;
        ch = ib.nextInt();
        if (ch == 1)
            indexed();
        else
            exit(0);
        return;
    }*/



    public void linkedAllocation(int size, int blocks[]) {

        Scanner sb = new Scanner(System.in);
        Scanner len = new Scanner(System.in);
        Scanner fn = new Scanner(System.in);
        Scanner ch = new Scanner(System.in);
        System.out.println("Enter the starting block number : ");
        int startBlock = sb.nextInt();
        System.out.println("Enter the name of the new file : ");
        String fileName = fn.next();
        System.out.println("Enter the length of the file : ");
        int length = len.nextInt();
        Random num = new Random();
        int clen = length;
        int actend = length + startBlock +1;
        int end = clen + startBlock;
        if (blocks[startBlock] == 0){
            blocks[startBlock]=1;
            System.out.println(fileName + " " +startBlock + " ----> " + blocks[startBlock]);
            for (int i = startBlock;i < end-1;i++){
                int randnum = 0 + num.nextInt(size);

                if (blocks[randnum] == 0){
                    blocks[randnum] = 1;
                    System.out.println( fileName + " " + randnum + " ----> " + randnum);
                }
                else {
                    while(blocks[randnum]==1){
                        randnum = 0 + num.nextInt(size);
                        clen++;
                    }


                }
            }
        }
        else

            System.out.println(fileName + " " + "The block with number : " + startBlock + " is already allocated !");
        System.out.println("Want to enter another file ? ");
        System.out.println("1 for yes ");
        System.out.println("0 for no : ");
        int choice = ch.nextInt();
        if (choice == 1){
            linkedAllocation(size,blocks);

        }
        else  {
            System.exit (0);
        }



    }
}
