public class MainTests {
    public static void main(String[] args) {

        //*****Array Tests*******
        Stack st = new Stack();
        BacktrackingArray backarr1 = new BacktrackingArray(st,20);

       //Insert Test

        System.out.println("Insert Tests:\n");
        for(int i=0;i<10;i=i+1){
            backarr1.insert((i*20)%17);
            System.out.print("Insert test num " +i+": ");
            backarr1.print();
            System.out.println();
        }
        backarr1.print();


        //Delete Tests
        System.out.println("\n\nDelete Tests:\n");
        //Delete 1
        System.out.println("Delete Tests num 1 - index to big - do nothing");
        System.out.print("Before: ");
        backarr1.print();
        backarr1.delete(15);
        System.out.print(" After: ");
        backarr1.print();
        System.out.println();
        //Delete 2
        System.out.println("Delete Tests num 2 - index is 0");
        System.out.print("Before: ");
        backarr1.print();
        backarr1.delete(0);
        System.out.print(" After: ");
        backarr1.print();
        System.out.println();
        backarr1.insert(9);
        //Delete 3
        System.out.println("Delete Tests num 3 - index is 5");
        System.out.print("Before: ");
        backarr1.print();
        backarr1.delete(5);
        System.out.print(" After: ");
        backarr1.print();
        System.out.println();
        backarr1.insert(1);
        //Delete 4
        System.out.println("Delete Tests num 4 - index is last(9)");
        System.out.print("Before: ");
        backarr1.print();
        backarr1.delete(9);
        System.out.print(" After: ");
        backarr1.print();
        backarr1.insert(6);


        //getTests
        System.out.println("\n\nGet Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        backarr1.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i*2);
        }
        System.out.println("Get Test num 1 - index exists: Expected: 1000"+ " . Function Output:" + backarr1.get(0));
        System.out.println("Get Test num 2 - index exists: Expected: 0"+ " . Function Output:" + backarr1.get(1));
        System.out.println("Get Test num 3 - index exists last: Expected: 16"+ " . Function Output:" + backarr1.get(9));
        System.out.println("Get Test num 4 - index doesn't exist: Expected: null"+ " . Function Output:" +backarr1.get(17));


        //searchTests
        System.out.println("\n\nSearch Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        backarr1.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i);
        }
        backarr1.insert(20);
        backarr1.insert(15);
        System.out.println("Search Test num 1 - value exists first: Expected: 0"+ " . Function Output:" +backarr1.search(1000));
        System.out.println("Search Test num 2 - value exists last: Expected: 11"+ " . Function Output:" + backarr1.search(15));
        System.out.println("Search Test num 3 - value exists mid: Expected: 9"+ " . Function Output:" + +backarr1.search(8));
        System.out.println("Search Test num 4 - value exists last: Expected: -1"+ " . Function Output:" + +backarr1.search(17));


        //Minimum Tests
        System.out.println("\n\nMinimum Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        for(int i=0;i<10;i=i+1){
            backarr1.insert(i*2);
        }
        backarr1.print();
        System.out.println();
        //Minimum 1
        System.out.println("Minimum Test num 1 - minimum is first: Expected: 0. Function Output:" + backarr1.minimum());
        //Minimum 2
        backarr1.insert(-1);
        backarr1.print();
        System.out.println();
        System.out.println("Minimum Test num 2 - minimum is last: Expected: 10 .Function Output:" + backarr1.minimum());
        //Minimum 3
        backarr1.insert(10);
        backarr1.print();
        System.out.println();
        System.out.println("Minimum Test num 3 - minimum is in the middle: Expected: 10 .Function Output:" + backarr1.minimum());


        //Maximum Tests
        System.out.println("\n\nMaximum Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        backarr1.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i*2);
        }
        //Maximum 1
        System.out.println("Maximum Test num 1 - maximum is first: Expected: 0. Function Output:" + backarr1.maximum());
        //Maximum 2
        backarr1.insert(10000);
        System.out.println("Maximum Test num 2 - maximum is last: Expected: 10. Function Output:" + backarr1.maximum());
        //Maximum 3
        backarr1.insert(10);
        System.out.println("Maximum Test num 3 - maximum is in the middle: Expected: 10. Function Output:" + backarr1.maximum());


        //Predecessor Tests
        System.out.println("\n\nPredecessor Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        backarr1.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i);
        }
        backarr1.insert(20);
        backarr1.insert(15);
        System.out.println("Predecessor Test num 1 - value is smallest: Expected: -1"+ " . Function Output:" + backarr1.predecessor(1));
        System.out.println("Predecessor Test num 2 - value is biggest but first in array : Expected: 10"+ " . Function Output:" + backarr1.predecessor(0));
        System.out.println("Predecessor Test num 3 - value is last in the array: Expected: 9"+ " . Function Output:" + backarr1.predecessor(11));
        System.out.println("Predecessor Test num 4 - value is in the middle: Expected: 4"+ " . Function Output:" + backarr1.predecessor(5));


        //Successor Tests
        System.out.println("\n\nSuccessor Tests: \n");
        backarr1 = new BacktrackingArray(st,20);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i);
        }
        backarr1.insert(1000);
        backarr1.insert(20);
        backarr1.insert(15);
        System.out.println("Successor Test num 1 - value is biggest: Expected: -1"+ " . Function Output:" + backarr1.successor(9));
        System.out.println("Successor Test num 2 - value is smallest but first in array : Expected: 1"+ " . Function Output:" + backarr1.successor(0));
        System.out.println("Successor Test num 3 - value is last in the array: Expected: 10"+ " . Function Output:" + backarr1.successor(11));
        System.out.println("Successor Test num 4 - value is in the middle: Expected: 4"+ " . Function Output:" + backarr1.successor(3));


        //Backtracking Tests

        System.out.println("\n\n\nBacktracking Tests: \n");
        //BackTrack Text:
        System.out.println("BackTrack Operation Printed message:");
        backarr1.backtrack();
        System.out.println("\n");

        //Backtracking Tests A
        System.out.println("Backtracking Tests A: undo Insert \n");
        st = new Stack();
        backarr1 = new BacktrackingArray(st,20);
        //Test A1 - insert 1 and remove 1
        System.out.print("Test A1: insert 1 value. Before:");
        backarr1.insert(1000);
        backarr1.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        //Test A2 - insert 2 and remove 2
        System.out.print("Test A2: insert 2 values, remove twice. before: ");
        backarr1.insert(1000);
        backarr1.insert(5);
        backarr1.print();
        System.out.println();
        System.out.print("undo once: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println();
        System.out.print("undo again:");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        //Test A3 - undo empty
        System.out.print("Test A3: undo empty. before:");
        backarr1.print();
        System.out.println();
        System.out.print("undo:");
        System.out.println();
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");


        //Test B - undo delete
        st = new Stack();
        backarr1 = new BacktrackingArray(st,20);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i+1);
        }
        System.out.println("Backtracking Tests B: undo delete \n");
        //Test B1 - Delete once from end
        System.out.print("Test B1: deleted value (9) from end. Before deletion: ");
        backarr1.print();
        System.out.println();
        System.out.print(" after deletion: ");
        backarr1.delete(8);
        backarr1.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");

        //Test B2 - Delete once from middle
        System.out.print("Test B2: deleted 1 value(6) from middle. Before deletion: ");
        backarr1.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr1.delete(5);
        backarr1.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");
        //Test B3 - Delete twice
        System.out.print("Test B3: deleted 2 values (9,6). Before deletion: ");
        backarr1.print();
        System.out.println();
        System.out.print("after deletion 1: ");
        backarr1.delete(8);
        backarr1.print();
        System.out.println();
        System.out.print("after deletion 2: ");
        backarr1.delete(5);
        backarr1.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");

        //Test B - undo mix

        System.out.println("Backtracking Tests C: undo mix \n");
        st = new Stack();
        backarr1 = new BacktrackingArray(st,20);
        for(int i=0;i<9;i=i+1){
            backarr1.insert(i+1);
        }
        //Test C1 - Delete and Insert
        System.out.print("Test C1: deleted 1 value(6) from middle. later insert 6. Before deletion: ");
        backarr1.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr1.delete(5);
        backarr1.print();
        System.out.println();
        System.out.print("after insertion:");
        backarr1.insert(6);
        backarr1.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");

        //Test C2 - insert and delete
        System.out.print("Test C2: insert 20 and later deleted 1 value(6) from middle. insertion: ");
        backarr1.print();
        System.out.println();
        System.out.print("after insertion:");
        backarr1.insert(20);
        backarr1.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr1.delete(5);
        backarr1.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr1.backtrack();
        System.out.print("arr values:"); backarr1.print();
        System.out.println("\n");


        //***************Sorted Array Tests**************

        Stack st2 = new Stack();
        BacktrackingSortedArray backarr2 = new BacktrackingSortedArray(st2,20);
        System.out.println(" ");
        System.out.println("-------------------------Sorted Array tests-----------------------");


        //Insert Test

        System.out.println("Insert Tests:\n");
        for(int i=0;i<10;i=i+1){
            backarr2.insert((i*20)%17);
            System.out.print("Insert test num " +i+": ");
            backarr2.print();
            System.out.println();
        }
        backarr2.print();
        /*System.out.print("Insert largest" +": ");
        backarr2.insert(25);
        backarr2.print();
        System.out.println();
        System.out.print("Insert smallest" +": ");
        backarr2.insert(-3);
        backarr2.print();
        System.out.println();
        */

        //Delete Tests


        System.out.println("\n\nDelete Tests:\n");
        //Delete 1
        System.out.println("Delete Tests num 1 - index to big - do nothing");
        System.out.print("Before: ");
        backarr2.print();
        backarr2.delete(15);
        System.out.print(" After: ");
        backarr2.print();
        System.out.println();
        //Delete 2
        System.out.println("Delete Tests num 2 - index is 0");
        System.out.print("Before: ");
        backarr2.print();
        backarr2.delete(0);
        System.out.print(" After: ");
        backarr2.print();
        System.out.println();
        backarr2.insert(0);
        //Delete 3
        System.out.println("Delete Tests num 3 - index is 5");
        System.out.print("Before: ");
        backarr2.print();
        backarr2.delete(5);
        System.out.print(" After: ");
        backarr2.print();
        System.out.println();
        backarr2.insert(7);
        //Delete 4
        System.out.println("Delete Tests num 4 - index is last(9)");
        System.out.print("Before: ");
        backarr2.print();
        backarr2.delete(9);
        System.out.print(" After: ");
        backarr2.print();
        backarr2.insert(15);

        //getTests
        System.out.println("\n\nGet Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        backarr2.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i*2);
        }

        System.out.println("Get Test num 1 - index exists: Expected: 0"+ " . Function Output:" + backarr2.get(0));
        System.out.println("Get Test num 2 - index exists: Expected: 2"+ " . Function Output:" + backarr2.get(1));
        System.out.println("Get Test num 3 - index exists last: Expected: 1000"+ " . Function Output:" + backarr2.get(9));
        System.out.println("Get Test num 4 - index doesn't exist: Expected: null"+ " . Function Output:" +backarr2.get(17));

       //searchTests
        System.out.println("\n\nSearch Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        backarr2.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i);
        }
        backarr2.insert(20);
        backarr2.insert(15);


        System.out.println("Search Test num 1 - value exists first: Expected: 11"+ " . Function Output:" +backarr2.search(1000));
        System.out.println("Search Test num 2 - value exists last: Expected: 9"+ " . Function Output:" + backarr2.search(15));
        System.out.println("Search Test num 3 - value exists mid: Expected: 8"+ " . Function Output:" + +backarr2.search(8));
        System.out.println("Search Test num 4 - value exists last: Expected: -1"+ " . Function Output:" + +backarr2.search(17));

        //Minimum Tests
        System.out.println("\n\nMinimum Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        for(int i=0;i<10;i=i+1)
            backarr2.insert(i*2);



        //Minimum 1
        System.out.println("Minimum Test num 1 : Expected: 0. Function Output:" + backarr2.minimum());
        //Minimum 2
        backarr2.insert(-1);

        System.out.println("Minimum Test num 2 : Expected: 10 .Function Output:" + backarr2.minimum());
        //Minimum 3
        backarr2.insert(9);

        System.out.println("Minimum Test num 3 : Expected: 10 .Function Output:" + backarr2.minimum());

        System.out.println("Noa is the Queen");

        //Maximum Tests
        System.out.println("\n\nMaximum Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        backarr2.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i*2);
        }

        //Maximum 1
        System.out.println("Maximum Test num 1 : Expected: 9. Function Output:" + backarr2.maximum());
        //Maximum 2
        backarr2.insert(10000);
        System.out.println("Maximum Test num 2 : Expected: 10. Function Output:" + backarr2.maximum());
        //Maximum 3
        backarr2.insert(10);
        System.out.println("Maximum Test num 3 : Expected: 11. Function Output:" + backarr2.maximum());

        //Predecessor Tests

        System.out.println("\n\nPredecessor Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        backarr2.insert(1000);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i);
        }
        backarr2.insert(20);
        backarr2.insert(15);

        System.out.println("Predecessor Test num 1  Expected: 0"+ " . Function Output:" + backarr2.predecessor(1));
        System.out.println("Predecessor Test num 2  Expected: -1"+ " . Function Output:" + backarr2.predecessor(0));
        System.out.println("Predecessor Test num 3  Expected: 10"+ " . Function Output:" + backarr2.predecessor(11));
        System.out.println("Predecessor Test num 4  Expected: 4"+ " . Function Output:" + backarr2.predecessor(5));


        //Successor Tests
        System.out.println("\n\nSuccessor Tests: \n");
        backarr2 = new BacktrackingSortedArray(st2,20);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i);
        }
        backarr2.insert(1000);
        backarr2.insert(20);
        backarr2.insert(15);

        backarr2.print();
        System.out.println();
        System.out.println("Successor Test num 1  Expected: 10"+ " . Function Output:" + backarr2.successor(9));
        System.out.println("Successor Test num 2  Expected: 1"+ " . Function Output:" + backarr2.successor(0));
        System.out.println("Successor Test num 3  Expected: -1"+ " . Function Output:" + backarr2.successor(11));
        System.out.println("Successor Test num 4  Expected: 4"+ " . Function Output:" + backarr2.successor(3));


//Backtracking Tests

        System.out.println("\n\n\nBacktracking Tests: \n");
        //BackTrack Text:
        System.out.println("BackTrack Operation Printed message:");
        backarr2.backtrack();
        System.out.println("\n");

        //Backtracking Tests A
        System.out.println("Backtracking Tests A: undo Insert \n");
        st2 = new Stack();
        backarr2 = new BacktrackingSortedArray(st2,20);
        //Test A1 - insert 1 and remove 1
        System.out.print("Test A1: insert 1 value. Before:");
        backarr2.insert(1000);
        backarr2.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        //Test A2 - insert 2 and remove 2
        System.out.print("Test A2: insert 2 values, remove twice. before: ");
        backarr2.insert(1000);
        backarr2.insert(5);
        backarr2.print();
        System.out.println();
        System.out.print("undo once: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println();
        System.out.print("undo again:");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        //Test A3 - undo empty
        System.out.print("Test A3: undo empty. before:");
        backarr2.print();
        System.out.println();
        System.out.print("undo:");
        System.out.println();
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");


        //Test B - undo delete
        st2 = new Stack();
        backarr2 = new BacktrackingSortedArray(st2,20);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i+1);
        }
        System.out.println("Backtracking Tests B: undo delete \n");
        //Test B1 - Delete once from end
        System.out.print("Test B1: deleted value (9) from end. Before deletion: ");
        backarr2.print();
        System.out.println();
        System.out.print(" after deletion: ");
        backarr2.delete(8);
        backarr2.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");

        //Test B2 - Delete once from middle
        System.out.print("Test B2: deleted 1 value(6) from middle. Before deletion: ");
        backarr2.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr2.delete(5);
        backarr2.print();
        System.out.println();
        System.out.print("After undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");
        //Test B3 - Delete twice
        System.out.print("Test B3: deleted 2 values (9,6). Before deletion: ");
        backarr2.print();
        System.out.println();
        System.out.print("after deletion 1: ");
        backarr2.delete(8);
        backarr2.print();
        System.out.println();
        System.out.print("after deletion 2: ");
        backarr2.delete(5);
        backarr2.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");

        //Test B - undo mix

        System.out.println("Backtracking Tests C: undo mix \n");
        st2 = new Stack();
        backarr2 = new BacktrackingSortedArray(st2,20);
        for(int i=0;i<9;i=i+1){
            backarr2.insert(i+1);
        }
        //Test C1 - Delete and Insert
        System.out.print("Test C1: deleted 1 value(6) from middle. later insert 6. Before deletion: ");
        backarr2.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr2.delete(5);
        backarr2.print();
        System.out.println();
        System.out.print("after insertion:");
        backarr2.insert(6);
        backarr2.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");

        //Test C2 - insert and delete
        System.out.print("Test C2: insert 20 and later deleted 1 value(6) from middle. insertion: ");
        backarr2.print();
        System.out.println();
        System.out.print("after insertion:");
        backarr2.insert(20);
        backarr2.print();
        System.out.println();
        System.out.print("after deletion:");
        backarr2.delete(5);
        backarr2.print();
        System.out.println();
        System.out.print("After first undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println();
        System.out.print("After second undo: ");
        backarr2.backtrack();
        System.out.print("arr values:"); backarr2.print();
        System.out.println("\n");




    }


}
