/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package osproject2;

import com.sun.org.apache.bcel.internal.generic.GOTO;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author lenovo
 */
public class OSProject2 {
    
    
    boolean full(int arr[][])
    {
        int counter = 0;
        for(int row = 0 ; row<arr.length ; row++)
        {
            if(arr[row][1] != 0)
            {
                counter++;
            }
        }
        if(counter == arr.length)
        {
            return true;
        }
        else return false;
    }
    
   void firstFit(int Process,  int AllProcess[][])
   {
        if(Process <= 0)                      
        {
            System.out.println("Error!");
            return;
        }
        for(int i = 0; i < AllProcess.length; i++) //3andak kam oda halef 3leha 
        {
            if(Process <= AllProcess[i][0] && AllProcess[i][1] == 0)           
            {
                AllProcess[i][1] = Process;          
                break;                              
            }
        }
   }
    
    void bestFit(int processes, int [] [] holes)
    {
        int bestHole = 0;
        for(int j = 1; j < holes.length ; j++)
        {
            while(holes[bestHole][0] - processes < 0  && bestHole < holes.length - 1 || holes[bestHole][1] != 0 && bestHole < holes.length-1)
            {
                //if(holes[bestHole][0]-processes < 0 && holes[bestHole][1] != 0)
                bestHole++;
            }

            if(holes[j][0] - processes <= holes[bestHole][0] - processes && holes[j][0] - processes >= 0  && holes[j][1] == 0 )
            {
                bestHole = j;
            }
        }
        if (holes[bestHole][1] == 0 && holes[bestHole][0] - processes >= 0)
        {
            holes [bestHole][1] = processes;
        }
    }

    
    void worstFit (int processes , int [] [] holes)
    {
        int bestHole = 0;
        for(int j = 1; j < holes.length ; j++)
        {
           
            while(holes[bestHole][0]-processes < 0  && bestHole < holes.length-1 || holes[bestHole][1] != 0 && bestHole < holes.length-1)
            {
                //if(holes[bestHole][0]-processes < 0 && holes[bestHole][1] != 0)
                bestHole++;
            }

            if(holes[j][0] - processes >= holes[bestHole][0] - processes && holes[j][0] - processes >= 0  && holes[j][1] == 0 )
            {
                bestHole = j;
            }
        }
        if (holes[bestHole][1] == 0 && holes[bestHole][0] - processes >= 0)
        {
            holes [bestHole][1] = processes;
        }
    }


    
    void display ( int [] [] arr)
    {
        int print = 0;
        System.out.println("Hole size: " + arr[0][0] +" KB. (Address from 0 TO "+arr[0][0] +")." +"  Process allocated size: " + arr[0][1] + " KB.");
        for (int j = 1 ; j < arr.length; j++)
        {
            System.out.println("Hole size: " + arr[j][0] +" KB. (Address from " + arr[j-1][0] + " TO " + arr[j][0] +")." +"  Process allocated size: " + arr[j][1] + " KB.");
        }
    }
    
    
    
    public static void main(String[] args)
    { 
//        OsProject obj = new OsProject () ;
//        int holes [][] = {{300,0},{400,0},{300,0}}; 
//        int pros [] = {700,200 , 100 , 400 };
//         for(int i = 0 ; i< 6 ; i++){
//            obj.bestFit(pros[i], holes);
//            obj.display(holes);
//         }
//    }
        OSProject2 obj = new OSProject2 () ; 
        int memsize = 0;
        int numOfInputs = 0;
        int holeinput = 0;
        int prosinput = 0;
        int proscheck = 0;
        int memcheck = 0;
        int switchchoice = 0;
        ArrayList <Integer> a = new ArrayList<>(); //partitions
        ArrayList <Integer> b = new ArrayList<>();
        ArrayList <Integer> c = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of the memory in KB: ");
        memsize = sc.nextInt();
        System.out.println("How do you want to partition it in KB: ");
        memcheck = 0;
        while(true)
        {
            holeinput = sc.nextInt();
            if(holeinput < 0 )
            {
                break;
            }
            else
            {
                memcheck = memcheck + holeinput;
                if(memcheck >memsize)
                {
                    System.out.println("You have exceeded your memory limit, please try again.");
                    System.exit(0);
                }
                else if (memcheck == memsize)
                {   a.add(holeinput);
                    break;
                }
                else
                {
                    a.add(holeinput);
                }
            }

        }
        
       int holes [] [] = new int[a.size()][a.size()] ;
       for(int i = 0; i < a.size(); i++)
       {
           holes[i][0] = a.get(i);
       }
       
        System.out.println("Choose a method to allocate memory for your process ");
        System.out.println("1: Best Fit Method ");
        System.out.println("2: Worst Fit Method ");
        System.out.println("3: First Fit Method ");
        switchchoice = sc.nextInt();
        switch(switchchoice)
        {
            case 1:
                System.out.println("Enter the procesess you want (Stop input by entering negative number): ");
                proscheck = 0;
                
                while(!obj.full(holes))
                {
                    prosinput = sc.nextInt();
                   
                    
                    if(prosinput < 0)
                    {
                        break;
                    }
                    else
                    {
                        numOfInputs++;

                        proscheck = proscheck + prosinput;                      
                        obj.bestFit(prosinput, holes);
                    }
                }           
            break;
      
            case 2:
                System.out.println("Enter the procesess you want (Stop input by entering negative number): ");
                proscheck = 0;
                while(!obj.full(holes))
                {
                    prosinput = sc.nextInt();
                    if(prosinput < 0)
                    {
                        break;
                    }
                    else
                    {
                        numOfInputs++;
                        proscheck = proscheck + prosinput;  
                        obj.worstFit(prosinput, holes);
                    }
                }
           
            break;

            case 3:
                System.out.println("Enter the procesess you want (Stop input by entering negative number): ");
                proscheck = 0;
                while(!obj.full(holes))
                {
                    prosinput = sc.nextInt();
                   
                    if(prosinput < 0)
                    {
                        break;
                    }
                    else
                    {
                        obj.firstFit(prosinput, holes); 
                    }
                }
            break;     
            
        }
        System.out.println("Memory Size " + memsize + " KB: ");
        obj.display(holes);
        System.out.println("Enter 1 if you want to delete a procesess, Enter 2 to exit.");
        switchchoice = sc.nextInt();
        switch(switchchoice)
        {
            case 1:
                proscheck = 0;
                System.out.println("Enter  procesess size that you want to delete (Stop input by entering a negative number): ");
                while(true)
                {
                    prosinput = sc.nextInt();
                    if(prosinput < 0)
                    {
                        break;
                    }
                    else
                    {
                        c.add(prosinput);
                    }
                }
            int pros [] = new int[holes.length];
            for(int i = 0; i < c.size(); i++)
            {
                pros[i] = c.get(i);
            }
            int n = c.size();  
            int temp = 0;  
            for(int i = 0; i < n; i++)
            {  
                for(int j = 1; j < (n-i); j++)
                {  
                    if(pros[j-1] > pros[j])
                    {  
                        temp = pros[j - 1];  
                        pros[j - 1] = pros[j];  
                        pros[j] = temp;  
                    }         
                 }  
            }
            int proscheck1 = 0;
            for(int i = 0; i < holes.length; i++)
            {
                if(pros[proscheck1] == holes[i][1])
                {
                    System.out.println("Procesess " + holes[i][1] + " deleted!");
                    holes[i][1] = 0;
                    proscheck1++;
                }
            }
            System.out.println("Memory Size " + memsize + " KB: ");
            obj.display(holes);
            break;
            
            case 2:
                break;
        }
//        System.out.println("Memory Size " + memsize + " KB: ");
//        obj.display(holes);
    }   
}