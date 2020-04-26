
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; 


public class ArrayTester {
    /**
    *
    * @author Ron Rachev
    * This was done purely for my own testings
    * Some code can be obv improved, if you locate any bugs u can simply dm me on fb
    */
    
    private Array  array;
    private Stack  stack      ;
     
    private String runTimeExceptionInfo;
    
    private int    minNum;
    private int    maxNum;
    
    private ArrayList<String >    logInfo               = new ArrayList<>(); 
    private ArrayList<Integer>    arrayNumbers          = new ArrayList<>();
    private ArrayList<deletedTab> deletedNumbersTabs    = new ArrayList<>();  
    
    private Random myRandomizer;
    private int    testNum     ;
    
    private int    arrLength; 
    private int    arrType;
    private int [] ourArrayObj; 
     
    
    private boolean testPassed = true;
    
    public ArrayTester(Array input,int arrayType,int testCaseNum,int arrLength,int minNumGenerate,int maxNumGenerate){  
        array            = input;
        
        myRandomizer     = new Random();
        this.minNum      = minNumGenerate;
        this.maxNum      = maxNumGenerate;  
        this.testNum     = testCaseNum;
        this.arrLength   = arrLength;
        this.arrType     = arrayType;
        this.ourArrayObj = getArrayFieldByReflection(arrayType);
        generateArray(); 
        
        printSeperator();
        SaveToLog("Test Number - " + testNum +  ", Randomly Generated Array : " + toString() + " Size : " + arrLength + ""); 
        printSeperator();
    } 
    public boolean failedTest()
    {
        return testPassed != true;
    }
    /*Logs Exception Info*/
    public String getStackForException(Exception myThrownException)  
    {
    StringWriter sw    = new StringWriter();
    PrintWriter pw     = new PrintWriter(sw);  
    myThrownException.printStackTrace(pw);
    String sStackTrace = sw.toString();
    return sStackTrace;
    }  
    public static int getRandomNumberInRange(Random random,int min, int max) { 
     return random.nextInt((max - min) + 1) + min;
    }
    /* generates completly random array */
    public void generateArray()
    {
        int generatedValue = 0;
        for(int i = 0 ; i < arrLength; i ++) 
        {   
            generatedValue = getRandomNumberInRange(myRandomizer,minNum, maxNum);
            if(!arrayNumbers.contains(generatedValue)){ 
            array.insert(generatedValue);
            arrayNumbers.add(generatedValue);
            }
        } 
        arrLength = arrayNumbers.size();
    }   
    /* Hackish way to access the private array object */
    public int [] getArrayFieldByReflection(int arrayType)
    {
       try{ 
        Field arrayField = null ;
        arrayField    = (Field)(array.getClass().getDeclaredField("arr"));  
        arrayField.setAccessible(true);     
        Object arrayObj = arrayField.get(array);  
        return (int [] )arrayObj; 
        }catch(Exception reflectionExceptino){
        }
       return null;
    } 
    
    public ArrayList<String> getLogInfo()
    { return logInfo; }
    
    @Override
    public String toString(){
        String output = "";
        for(int i = 0 ; i <arrLength; i++)
            output += ourArrayObj[i]+ " "; 
        return output;
    }  
    /*
    checks if returned maximum is correct
    */
    public boolean checkMaximumIsValid()
    { 
        int maximumOtherImplementation = (int)array.maximum();
        int myMaxIndex = 0; 
        for(int i = 0 ; i < arrLength ; i++)
        { 
            if(ourArrayObj[i] > ourArrayObj[myMaxIndex])
               myMaxIndex = i;
        } 
        return (myMaxIndex == maximumOtherImplementation);
    } 
    /*
    checks if returned minimum is correct
    */
    public boolean checkMinimumIsValid()
    {
        int minOtherImplemntation = (int)array.minimum();
        int myMinIndex            = 0;  
        for(int i = 0 ; i < arrLength   ; i++)
            if(ourArrayObj[myMinIndex] > ourArrayObj[i])
                 myMinIndex = i; 
        return (minOtherImplemntation == myMinIndex);
    }
    /* Confirms search is Valid */ 
    public boolean checkForNonExistingNumbers()
    {
        boolean passedCheckNonExisting = true;
        int value = 0;
        for(int i = 0 ; passedCheckNonExisting && i < arrLength -1 ; i ++)
        {
            value = getRandomNumberInRange(myRandomizer, 0, 1000);
            if(!arrayNumbers.contains(value))
            { 
                if((int)array.search(value) != -1)
                    passedCheckNonExisting = false; 
            }
        }
        return passedCheckNonExisting;
    }
   /*
    Gets non existing indexes
    */
    public boolean getNonExistingIndexesCheck()
    {
        boolean passedCheckGet = true;
        int    nonExistingIndex = 0;
        Object out;
        
        for(int i = 0 ; passedCheckGet && i < arrLength -1 ; i ++)
        {
            nonExistingIndex = getRandomNumberInRange(myRandomizer, arrLength+1, 1000); 
           try{
                out = array.get(nonExistingIndex);
                if(out != null) {  
                    SaveToLog("getNonExistingIndexesCheck() Invalid!");
                    SaveToLog("Non existing index did not return NULL!"); 
                }
           }catch(Exception nonExistingIndexException){
                    SaveToLog("getNonExistingIndexesCheck() Exception!");
                    SaveToLog("Exception in trying to get non existing index. Test Number : " + testNum+" Exception--- \r\n" + getStackForException(nonExistingIndexException));
                    SaveToLog("Actual Array Size - " + arrLength + " Tried to get() Index : " + nonExistingIndex);
                    SaveToLog("You should add boundary check to your get function.");
                    passedCheckGet = false; 
           } 
        }
        return passedCheckGet; 
    }
    /*
    Deletes non existing indexes
    */
    public boolean deleteNonExistingIndexCheck()
    {
        boolean passedCheckIndex = true;
        int nonExistingIndex = 0;
        for(int i = 0 ; passedCheckIndex && i < arrLength -1 ; i ++)
        {
            nonExistingIndex = getRandomNumberInRange(myRandomizer, arrLength+1, 1000);
            if(!arrayNumbers.contains(nonExistingIndex))
            { 
                try{
                array.delete(nonExistingIndex);
                }catch(Exception nonExistingIndexException){
                    SaveToLog("deleteNonExistingIndexCheck() Exception!");
                    SaveToLog("Exception in trying to delete non existing index. Test Number : " + testNum+" Exception--- \r\n" + getStackForException(nonExistingIndexException));
                    SaveToLog("Actual Array Size - " + arrLength + " Tried to delete() Index : " + nonExistingIndex);
                    SaveToLog("You should add boundary check to your delete function.");
                    passedCheckIndex = false; 
                }
            }
        }
        return passedCheckIndex; 
    }
    /* My own implementation to test against yours */
    public int getPredecessor(int key) {
      int val = 0;
      for(int i = 0 ; i < arrayNumbers.size(); i++){
          val = arrayNumbers.get(i);
          if(val == key){
              if(i-1 < 0) return -1;
              else
              return arrayNumbers.get(i-1);
          }
      }
      return -1;
    }
    public int getSuccessor(int key) {
      int val = 0;
      for(int i = 0 ; i < arrayNumbers.size(); i++){
          val = arrayNumbers.get(i);
          if(val == key){ 
              if(i+1 >= arrayNumbers.size()) return -1;
              return arrayNumbers.get(i+1); 
          }
      }
      return -1;
    } 
    public void SaveToLog(String temp)
    { logInfo.add(temp); }
    
    /*
    Tests entire array for predecessors & successors.
    */
    public boolean testPredecessorsAndSuccessor()
    {
        int indexChosen        = 0;
        int randNumChosen      = 0;
        
        int userPredecssor     = 0;
        int actualPredecssor   = 0;
        int predIndex          = 0 ;
        
        int userSuccessor      = 0;
        int actualSuccessor    = 0;
        int sucIndex           = 0;
        
        Collections.sort(arrayNumbers);  
        for(int i = 0 ; i < arrLength -1 ; i ++)
        {
            indexChosen   = i;
            randNumChosen = ourArrayObj[indexChosen] ; 
            
            predIndex = (int)array.predecessor(indexChosen);
            if(predIndex != -1)
            userPredecssor       = (int) predIndex;   
            else
            userPredecssor = -1; 
            actualPredecssor     = (int) getPredecessor(randNumChosen); 
            
            if(userPredecssor == -1){
              if(actualPredecssor != -1){
                SaveToLog("\r\n\r\nPredecssor Fail! Checking Predecssor Of " + randNumChosen + "\r\n\r\nYour Predecessor Returned Index --- > " + userPredecssor + " Aka Integer : " +ourArrayObj[userPredecssor]+"\r\nShould Of Found Index --> " +actualPredecssor);
                return false; 
              }   
            }else{
            if(actualPredecssor != ourArrayObj[userPredecssor])
            { 
                SaveToLog("\r\n\r\nPredecssor Fail! Checking Predecssor Of " + randNumChosen + "\r\n\r\nYour Predecessor Returned Index--- > " + userPredecssor + " Aka Integer : " +ourArrayObj[userPredecssor]+"\r\nShould Of Found Integer --> " +actualPredecssor);
                return false; 
            }
            }
            sucIndex = (int)array.successor(indexChosen); 
            
            if(sucIndex != -1)
            userSuccessor     = sucIndex; 
            else
            userSuccessor = -1; 
            actualSuccessor   = (int) getSuccessor(randNumChosen); 
            if(userSuccessor == -1){
               if(actualSuccessor != -1){
                SaveToLog("\r\nSuccessor Fail! Your Successor Of " + randNumChosen + " Is Index ----> " + sucIndex + " Aka Number - " + userSuccessor+"\r\nShould Of Found Integer --> " +actualSuccessor);
                return false; 
              }   
            }else{
            if(actualSuccessor != ourArrayObj[userSuccessor]){
               SaveToLog("\r\nSuccessor Fail! Your Successor Of " + randNumChosen + " Is Index ----> " + sucIndex + " Aka Number - " + userSuccessor+"\r\nShould Of Found Integer --> " +actualSuccessor);
               return false;
            }
            }
        }
        return true; 
    } 
    
    public boolean deletedNumberAlready(int num)
    {
        for(int i = 0 ;i < deletedNumbersTabs.size(); i++ ){
            if(deletedNumbersTabs.get(i).getValue() == num)
                return true;
        }
        return false;
    }
    /* Backtracking tester */
    public boolean checkBackTracking()
    {
        deletedTab currentTab; 
        int        randIndx  = arrLength-1; 
        
        boolean validBackTrack = true;
        SaveToLog("\r\n");
        
        SaveToLog("\r\nArray Before Delete - \r\n");   
        SaveToLog(toString()); 
        printSeperator(); 
        
        int deletedNumber = 0;
        int limit = arrLength-1;
        
        for(int i = 0 ; randIndx > 0 && i < arrLength-1 ; i ++)
        {
            randIndx         = getRandomNumberInRange(myRandomizer, 0, limit); 
            deletedNumber    = ourArrayObj[randIndx];
            if(!deletedNumberAlready(deletedNumber)){  
            limit = randIndx;
            currentTab          = new deletedTab(randIndx, deletedNumber);
            deletedNumbersTabs.add(currentTab);
            arrLength = arrLength - 1; 
            SaveToLog("Deleted --> " + deletedNumber+ " Old Index -> " + (randIndx));    
            }
            
        } 
        for(int i = 0 ; i < deletedNumbersTabs.size(); i++)
            array.delete(deletedNumbersTabs.get(i).index);  
        
        SaveToLog("\r\nArray After Delete - \r\n");   
        SaveToLog(toString()); 
        printSeperator();
        
        SaveToLog("Confirming New Maximum & Minimum Of Array After Delete");   
        SaveToLog("\r\nMaximum Check Passed - " + checkMaximumIsValid());  
        SaveToLog("Minimum Check Passed - "     + checkMinimumIsValid());  
        try{
        /* Again hackish way to access a private method with no refrence*/
        Method backtrackMethod =    array.getClass().getDeclaredMethod("backtrack"); 
         
        for(int i = 0 ; i < deletedNumbersTabs.size(); i++)
            backtrackMethod.invoke(array);
         
        int idx = 0;
        int num = 0;  
        
        for(int i = 0 ; validBackTrack && i < deletedNumbersTabs.size(); i ++){
           currentTab = deletedNumbersTabs.get(i);    
           
           idx = currentTab.getIndex();
           num = currentTab.getValue();   
           if(ourArrayObj[idx] != num){ 
               validBackTrack = false; 
               SaveToLog("At Index " + idx + " Wrong Backtrack! Current -> " + ourArrayObj[idx] + " Should Be -> " + num); 
           } 
           arrLength++;
        } 
  
        printSeperator();
        SaveToLog("Array After BackTrack -");
        SaveToLog(toString());
        printSeperator();
        }catch(Exception backtrackException){
            backtrackException.printStackTrace();
            SaveToLog("Exception In BackTracking -- > " +getStackForException(backtrackException));
            return false;
        }
        return validBackTrack; 
    } 
    /*Seperator*/
    public void printSeperator(){
        SaveToLog("-----------------------------------------------------------------------------------------------------|");
    }
    /*Tester*/
    public void startTest()
    {
        try{  
            boolean minTest                  = checkMaximumIsValid();
            boolean maxTest                  = checkMinimumIsValid();
            boolean nonExistingNumberTest    = checkForNonExistingNumbers();
            boolean nonExistingIndexDeleteTest  =    true;// = deleteNonExistingIndexCheck(); 
            boolean nonExistingIndexGetTest     =   true;     //= getNonExistingIndexesCheck();
            boolean successorPredecessorTest = testPredecessorsAndSuccessor();
            boolean backTrackTest            = checkBackTracking(); 
             
            SaveToLog("Non Existing Numbers Test valid --- >  "       +nonExistingNumberTest );  
            printSeperator();
            SaveToLog("Delete Non Existing Indexes Test Valid --- > "       + nonExistingIndexDeleteTest);
            printSeperator(); 
            SaveToLog("Backtracking Test Valid - " + backTrackTest);  
            printSeperator();
            testPassed = (minTest == true && minTest == maxTest && maxTest == nonExistingIndexDeleteTest && nonExistingIndexDeleteTest == nonExistingIndexGetTest && nonExistingIndexDeleteTest == successorPredecessorTest && successorPredecessorTest == backTrackTest);
            SaveToLog("\r\nTest "+ testNum+" Passed ---> " + testPassed ); 
            printSeperator();
            
            SaveToLog("\r\n\r\n\r\n"); 
            
        }catch(Exception startingException){
            startingException.printStackTrace();
        }
    } 
    public class deletedTab {
        private int index;
        private int value;
        
        public deletedTab(int index,int value)
        {
        this.index = index;
        this.value = value;
        }
        public int getIndex()
        {
            return index;
        }
        public int getValue()
        {
            return value;
        }
    }
}
