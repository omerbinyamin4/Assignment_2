
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
 

public class ArrayTesterMain {
    
public static void saveToLogFile(String Filename,ArrayList<String> logInfo)
    {
        File logFileHandle;
        try{
             logFileHandle = new File(Filename); 
             if(!logFileHandle.exists()) 
               logFileHandle.createNewFile(); 
             
             Files.write(Paths.get(Filename),logInfo, StandardOpenOption.APPEND);  
        }catch(Exception savingException)
        {
        }
} 
public static void main(String [] args)
{
   /**
    *
    * @author Ron Rachev
    * This was done purely for my own testings
    * Some code can be obv improved, if you locate any bugs u can simply dm me on fb
    */
     
    ArrayList<ArrayTester> myTesters = new ArrayList<>(); 
    
    ArrayTester  currentTester;
    Random       testerRandomizer = new Random();
    int          randomArrLength  = 0;
    Scanner myScanner = new Scanner(System.in);
    
    
    int typeTest= 0;
    int numTests = 0; 
    
    System.out.println("Press 1 to test sorted array, 2 to test non sorted array");
    try{
    typeTest = myScanner.nextInt(); 
    }catch(Exception parseException){
        System.out.println("Invalid Input");
        return;
    }
    if(typeTest != 1 && typeTest != 2){
        System.out.println("Wrong input");
        return;
    }
    
    System.out.println("Number of tests :");
    try{
    numTests = myScanner.nextInt(); 
    }catch(Exception parseException){
        System.out.println("Invalid Input");
        return;
    }
    
    
    /*
    Sorted Array Vals
    */
    Array   tempArr;
    Stack   tempStack;
              
    int lowRange   = -1000;
    int highRange  = 1000;
    
    try{
        
    for(int i = 0 ; i < numTests ; i ++ ){
    tempStack = new Stack();
    
    randomArrLength = ArrayTester.getRandomNumberInRange(testerRandomizer, 5,25);
    if(typeTest == 1)
    tempArr     = new BacktrackingSortedArray(tempStack, randomArrLength);
    else
    tempArr     = new BacktrackingArray(tempStack, randomArrLength);
   
    currentTester   = new ArrayTester(tempArr,typeTest,i,randomArrLength,lowRange,highRange); 
    currentTester.startTest();
    myTesters.add(currentTester);
    }
    int failedTests  = 0;
    int successTests = 0;
    for(int i = 0 ; i < myTesters.size() ; i++) 
    {
        if(myTesters.get(i).failedTest()){
           saveToLogFile("InvalidArray.txt",myTesters.get(i).getLogInfo()); 
           failedTests ++;
        }
    } 
    
    successTests = myTesters.size() - failedTests;
    
    System.out.println("\r\n-------------------\r\nFinal Results\r\n-------------------  \r\nCorrect Tests - > "+successTests+"\r\nInvalid Cases  -> "+ failedTests);
    if(failedTests > 0)
    System.out.println("Check your current directory for invalid results. Saved as InvalidArray.txt");   
    else{
    System.out.println("No invalid cases detected.");
    int savingOption = 0;
    
    System.out.println("Save valid results? 1 to save 2 to not save");
    
    try{
    savingOption = myScanner.nextInt(); 
    }catch(Exception parseException){
        System.out.println("Invalid Input");
        return;
    }
    if(savingOption == 1){
    for(int i = 0 ; i < myTesters.size() ; i++) 
    {
        if(!myTesters.get(i).failedTest())
           saveToLogFile("ValidArray.txt",myTesters.get(i).getLogInfo());  
    } 
    
    System.out.println("Saved "+successTests + " results.");
    }
    }
    
    
    }catch(Exception startingThreadsException)
    {
        startingThreadsException.printStackTrace();
    }
}
}
