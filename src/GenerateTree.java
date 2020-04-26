

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Tester Author -  Ron Rachev
 */

public class GenerateTree {
    
    
    private BacktrackingBST                 tree;
    private BacktrackingBST                 backtrackTree; 
    
    private int                             treeSize;
    
    private ArrayList<BacktrackingBST.Node> treeNodes    = new ArrayList<>();  
    private ArrayList<BacktrackingBST.Node> removedNodes = new ArrayList<>();
    
    private ArrayList<Integer>              removedIntegerKeys = new ArrayList<>();
    private ArrayList<String>               logInfo      = new ArrayList<>();
    
    private Stack                           backTrackingStack;
    private Stack                           retrackStack; 
    
    private Stack                           backupBackTrackingStack;
    private Stack                           backupRetrackStack; 
    
    private int                             nodeRange;  
    private int                             runNumber;
    
    
    private int                             numberDeleteActionsDone;
    
    private boolean                         isValidBackTrack;  
    private boolean                         isRetrackValid;
    
    private String                          beforeBackTrackOrder;
    private String                          afterRetrackOrder   ;
    
    
    private boolean doRetrackCheck;
    
    public GenerateTree(int treeSize,int nodeRange,int runNumber,boolean checkRetrack)
    {
        this.treeSize                = treeSize; 
        this.nodeRange               = nodeRange; 
        
        this.backTrackingStack       = new Stack();
        this.retrackStack            = new Stack(); 
        
        this.backupBackTrackingStack = new Stack(); 
        this.retrackStack            = new Stack(); 
        this.backupRetrackStack      = new Stack();
         
        this.runNumber         = runNumber;
        
        tree          = new BacktrackingBST(backTrackingStack      , retrackStack);  
        backtrackTree = new BacktrackingBST(backupBackTrackingStack, backupRetrackStack); 
        
        logInfo.add("--------------------------------------------"); 
        logInfo.add("Tree Generator Test Number : " + runNumber); 
        logInfo.add("--------------------------------------------\r\n");  
        logInfo.add("Insertion Code- \r\n");
        
        logInfo.add("Stack backTrackingStack     = new Stack();"); 
        logInfo.add("Stack retrackStack            = new Stack();");
        logInfo.add("BacktrackingBST tree = new BacktrackingBST(backTrackingStack, retrackStack);");
        
        this.generateTree(); 
        this.doRetrackCheck = checkRetrack;
    }  
    public boolean isValidBackTrack()
    {
        return isValidBackTrack;
    }
    public int getTreeSize(){
        return treeSize;
    }  
    public void printTree()
    {
        BTreePrinter.printNode(tree.getRoot());  
    } 
    public boolean isValidRetrack()
    {
        return isRetrackValid;
    }
    public ArrayList<String> getLogInfo()
    {
        return logInfo;
    } 
    
    /*
    hackish way to get the parent without utilizing any "getter" method.
    bypasses the private reference
    */
    public BacktrackingBST.Node getParentFieldByReflection(BacktrackingBST.Node node)
    {
       try{
        Field parentField = node.getClass().getDeclaredField("parent");  
        parentField.setAccessible(true);   
        Object parentObj = parentField.get(node); 
        
        return (BacktrackingBST.Node)parentObj;
        }catch(Exception reflectionExceptino){
        }
       return null;
    }
    /*
    Generates random nodes & inserts them into the tree
    */
    public void generateRandomNode()
    { 
        BacktrackingBST.Node nodeToAdd;
        BacktrackingBST.Node backupNodeToAdd;
        int nodeNumber  = (int) (Math.random() * nodeRange);
        
        nodeToAdd       =  new  BacktrackingBST.Node  (nodeNumber,null); 
        backupNodeToAdd =  new  BacktrackingBST.Node  (nodeNumber,null); 
        
        if(treeNodes.size() == 0 || tree.search(nodeNumber) == null){ //Sanity check (Do not add same node)
        tree         .insert(nodeToAdd);  
        backtrackTree.insert(backupNodeToAdd);
         
        logInfo  .add("BacktrackingBST.Node node"+nodeNumber +" = new BacktrackingBST.Node("+nodeNumber+",null"+");");
        logInfo  .add("tree.insert(node"+nodeNumber+");");
        treeNodes.add(tree.search(nodeNumber) );
        } 
    }
    
    /*
    preOrder (Used for backtrack confirmation upon each state of the tree)
    */
    public String getPreorder(BacktrackingBST.Node node) 
    {  
        String Tmp = ""; 
        try{
        try{ 
        if(node != null)
        Tmp = Tmp + " "+node.getKey(); 
        
        if(node != null && node.left  != null)
        Tmp = Tmp +" "+ getPreorder(node.left);   
        if(node != null && node.right != null)
        Tmp = Tmp +" "+ getPreorder(node.right); 
        
        }catch(Exception preOrderException){
             logInfo.add("getPreOrder Exception! Scanned Till --> " + node + " Exception Stack -" + getStackForException(preOrderException));
             addFinalStateInfoInTable();
        }
        }catch(StackOverflowError soError){
            System.out.println("stackOverflow Error ! Scanned Till --> " + node + " Left Child --> " + node.left + " Right Child --> " + node.right +"  Exception.");
            printNodesThatCauseStackOverflow(); 
            System.exit(0);
        }
        return Tmp;
    } 
   public void printNodesThatCauseStackOverflow()
    {
       String               tableInfo = "";
       BacktrackingBST.Node tempNode; 
       BacktrackingBST.Node parent;
       System.out.println("Tree Info  \r\n");
        for(int i = 0 ; i < treeNodes.size(); i++)
        {
            tempNode = treeNodes.get(i);
            parent   = getParentFieldByReflection(tempNode);
            
            if(tempNode != null)
            tableInfo=tempNode.getKey() + "|";
            if(parent != null) tableInfo+= "Parent - "    + parent.getKey();
            else
            tableInfo+= " Parent: Null";
            
            if(tempNode.left != null) tableInfo+= " Left - "+tempNode.left.getKey();
            else
            tableInfo+= " Left: Null";
            
             if(tempNode.right != null) tableInfo+=" Right - "+tempNode.right.getKey();
            else
            tableInfo+= " Right: Null";
             
            System.out.println(tableInfo); 
        }  
       System.out.println("----------------------------------------"); 
    }
     
    /*
    generatesTree - method to generate a fresh tree
    */
    public void generateTree()
    {
        for(int i = 0 ; i < treeSize ; i++)
            generateRandomNode();  
        printTree();  
    } 
    
    /*
    Hackish way to output the exception into a string (this string is saved to the proper .txt file upon exception)
    allows to easily pinpoint the problem without looking at the console.
    */
    public String getStackForException(Exception myThrownException)  
    {
    StringWriter sw   = new StringWriter();
    PrintWriter pw    = new PrintWriter(sw);
    
    myThrownException.printStackTrace(pw);
    String sStackTrace = sw.toString();
    return sStackTrace;
    }
    
    /*
    Saves tree state/ascii to the text file
    */
    public void saveTreeStateToLogFile()
    {
           BTreePrinter.clearTree();
           printTree();
           
           String treeLines [] = BTreePrinter.genertedTree.split("\n");
           for(int i = 0 ; i <treeLines.length; i++){
           logInfo.add(treeLines[i]);
       }
    } 
    /*
    Saves the entire tree information into a table~~ format
    */
    public void addFinalStateInfoInTable()
    {
       String               tableInfo = "";
       BacktrackingBST.Node tempNode; 
       BacktrackingBST.Node parent;
       logInfo.add("Tree Info After Backtracking : \r\n");
        for(int i = 0 ; i < treeNodes.size(); i++)
        {
            tempNode = treeNodes.get(i);
            parent   = getParentFieldByReflection(tempNode);
            
            if(tempNode != null)
            tableInfo=tempNode.getKey() + "|";
            if(parent != null) tableInfo+= "Parent - "    + parent.getKey();
            else
            tableInfo+= " Parent: Null";
            
            if(tempNode.left != null) tableInfo+= " Left - "+tempNode.left.getKey();
            else
            tableInfo+= " Left: Null";
            
             if(tempNode.right != null) tableInfo+=" Right - "+tempNode.right.getKey();
            else
            tableInfo+= " Right: Null";
             
            logInfo.add(tableInfo); 
        }  
       logInfo.add("----------------------------------------"); 
    }
     
    
    public void retrackTest()
    {
        try{
        System.out.println("\r\nRetracking Testing!\r\n");
        
        for(int i = 0 ; i < numberDeleteActionsDone ; i++ )
        {
            tree.retrack();
            logInfo.add("tree.retrack()");
        }
        }catch(Exception retrackException){
            logInfo.add("Exception Retracking -- > " + getStackForException(retrackException));
        }  
        
       afterRetrackOrder = getPreorder(tree.root);
       logInfo.add("\r\nPreOrder After Retrack ---> " + afterRetrackOrder);
       isRetrackValid = beforeBackTrackOrder.equals(afterRetrackOrder); 
       System.out.println("Before Order :"  + beforeBackTrackOrder + " "+ afterRetrackOrder);
       logInfo.add("\r\nAfter Retracking " + numberDeleteActionsDone+" Actions"); 
       saveTreeStateToLogFile();    
       logInfo.add("Finished Test -----> "+ runNumber + "");
       logInfo.add("-----------------------------------------------------------------------------------------------------------");
       
    } 
    public void startTest()
    {
        BacktrackingBST.Node nodeToDelete; 
      
        try{
      
        int     randNumDeleteOperations = (int) (Math.random() * (treeSize)) + 1;
        System.out.println("Number operations To Delete : " + randNumDeleteOperations);
        numberDeleteActionsDone               = 0;
       
        String originalTreeOrder       = getPreorder(backtrackTree.getRoot());  
        logInfo.add("\r\nPreOrder OriginalTree : ---> "  + originalTreeOrder);
        logInfo.add("------------------------\r\nGenerated Before Delete\r\n------------------------\n"); 
        saveTreeStateToLogFile();
          
        isValidBackTrack                = false;
        
        for(int i = 0 ;  i < randNumDeleteOperations; i ++)
        {
            Collections.shuffle(treeNodes) ;  //shuffle our nodes and get a random one
            nodeToDelete = treeNodes.get(0);  
            
            if(!removedNodes.contains(nodeToDelete) && !removedIntegerKeys.contains(nodeToDelete.getKey())){ //Sanity check
           
            tree.delete     (nodeToDelete);
            removedNodes.add(nodeToDelete);
            removedIntegerKeys.add(nodeToDelete.getKey());//Used for those who chose the "key" approach instead of the pointer one
            logInfo .add("tree.delete(node"+nodeToDelete.getKey()+");");
            numberDeleteActionsDone++; 
            }
            
        }  
        logInfo.add("------------------------\r\nGenerated Tree After Delete\r\n------------------------\r\n");  
        saveTreeStateToLogFile(); 
        beforeBackTrackOrder     = getPreorder(tree.getRoot());
        logInfo.add("\r\nPreOrder Before Back Track : ---> "  + beforeBackTrackOrder);
          
        for(int i = 0 ; i < numberDeleteActionsDone; i++)
        logInfo.add("tree.backtrack();");    
        
        for(int i = 0 ; i < numberDeleteActionsDone; i++)
           tree.backtrack();
         
        String afterBackTrackOrder     = getPreorder(tree.getRoot()         ); 
        logInfo.add("\r\nPreOrder after Back Track : ---> "  + afterBackTrackOrder); 
        isValidBackTrack = originalTreeOrder.equals(afterBackTrackOrder);
        logInfo.add("------------------------\r\nAfter BackTracking Action\r\n------------------------"); 
        
        saveTreeStateToLogFile();  
        addFinalStateInfoInTable();
        
        if(doRetrackCheck)
          retrackTest();
        
        logInfo.set(0,"\r\n| Run Number " + runNumber+ " | Backtrack Valid ---> " +" "+isValidBackTrack+" | Retrack Valid -" +isRetrackValid + " |");
        
        
        }catch(Exception backTrackException){
            System.out.println("Exception. Saving To Log File."); 
            logInfo.add("Exception - "+backTrackException.getMessage() + " ---> " + backTrackException.getLocalizedMessage() + " Stack Trace : " +getStackForException(backTrackException));
        } 
        
    } 
    
}
