
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Tester Author -  Ron Rachev
 */
public class TreeGenerationTester {
    
    public static void saveToLogFile(String Filename,ArrayList<String> logCommands)
    {
        File logFileHandle;
        try{
             logFileHandle = new File(Filename);
             if(!logFileHandle.exists()) 
               logFileHandle.createNewFile(); 
             Files.write(Paths.get(Filename),logCommands, StandardOpenOption.APPEND);  
        }catch(Exception savingException)
        {
        }
    } 
    public static void main(String [] args)
    {
       
        GenerateTree            treeGenerator;
        
        ArrayList<GenerateTree> backTrackCorrect = new ArrayList<>();
        ArrayList<GenerateTree> backTrackInvalid = new ArrayList<>();
        
        ArrayList<GenerateTree> reTrackCorrect = new ArrayList<>();
        ArrayList<GenerateTree> reTrackInvalid = new ArrayList<>();
        
        
        ArrayList<GenerateTree> treeGenerators   = new ArrayList<>(); 
        
        Scanner      inputScanner = new Scanner(System.in);
        
        int correctCasesBackTrack = 0 ;
        int invalidCasesBackTrack = 0 ;
        
        int correctCasesReTrack   = 0 ;
        int invalidCasesReTrack   = 0 ;
        
        boolean checkRetrack = false;
        
        int numNodes     = 0;
        int numTests     = 0;
        
        System.out.println("Input number of nodes to test with : (Minimum 2)");
        numNodes = inputScanner.nextInt();
        
        if(numNodes >= 2){
         
        System.out.println("Input number of tests :");
        numTests = inputScanner.nextInt();
        
        System.out.println("Check retrack? type 1 to check, type 2 to skip");
        checkRetrack = (inputScanner.nextInt()==1);
        
        
        for(int i = 0 ; i < numTests; i ++){
      
        treeGenerator = new GenerateTree(numNodes,1000,i,checkRetrack); 
        
        treeGenerator .startTest(); 
        treeGenerators.add(treeGenerator);
        }
        
        /*
        Simply saves the correct cases/invalid ones
        */
        for(int i = 0; i < numTests; i++){
            
            if(treeGenerators.get(i).isValidRetrack()){
                reTrackCorrect.add(treeGenerators.get(i));
                correctCasesReTrack++;
            }else
            {
                reTrackInvalid.add(treeGenerators.get(i));
                invalidCasesReTrack++;
            }
            
            if(treeGenerators.get(i).isValidBackTrack()) { 
                backTrackCorrect.add(treeGenerators.get(i));
                correctCasesBackTrack++;
            }else{
                backTrackInvalid.add(treeGenerators.get(i));
                invalidCasesBackTrack++;
            }
        }
        System.out.println("\r\n\r\n");
        if(checkRetrack)
        System.out.println("\r\n-------------------\r\nFinal Results\r\n-------------------  \r\nCorrect BackTrack - > "+correctCasesBackTrack+"\r\nInvalid Cases BackTrack -> "+invalidCasesBackTrack+"\r\nCorrect reTrack - > "+correctCasesReTrack+"\r\nInvalid Cases reTrack -> "+invalidCasesReTrack);
        else
        System.out.println("\r\n-------------------\r\nFinal Results\r\n-------------------  \r\nCorrect BackTrack - > "+correctCasesBackTrack+"\r\nInvalid Cases BackTrack -> "+invalidCasesBackTrack);
           
        System.out.println("Saving tests to .txt files. Check your program directory");
        
        for(int i = 0 ;i < correctCasesBackTrack; i++)
          saveToLogFile("ValidBacktrack.txt"   ,backTrackCorrect.get(i).getLogInfo());
        
        for(int i = 0 ;i < invalidCasesBackTrack; i++)
          saveToLogFile("InvalidBacktrack.txt" ,backTrackInvalid.get(i).getLogInfo());
         
        if(checkRetrack){
        for(int i = 0 ;i < correctCasesReTrack; i++)
          saveToLogFile("ValidRetrack.txt"     ,reTrackCorrect.get(i).getLogInfo());
        
        for(int i = 0 ;i < invalidCasesReTrack; i++)
          saveToLogFile("InvalidRetrack.txt"   ,reTrackInvalid.get(i).getLogInfo());
        }
        }
    }
}
