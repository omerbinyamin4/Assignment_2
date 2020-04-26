import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class MainTest {
    public static void main(String[] args) {
        testPrint();
    }

    private static void testPrint() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        System.out.println("Array:");
        BacktrackingArray array = new BacktrackingArray(new Stack(), 100);
        array.insert(4);
        array.insert(8);
        array.insert(2);
        array.insert(3);
        array.print();
        System.out.println();

        System.out.println("Sorted Array:");
        BacktrackingSortedArray sortedArray = new BacktrackingSortedArray(new Stack(), 100);
        sortedArray.insert(4);
        sortedArray.insert(8);
        sortedArray.insert(2);
        sortedArray.insert(3);
        sortedArray.print();
        System.out.println();

        System.out.println("BST:");
        BacktrackingBST bst = new BacktrackingBST(new Stack(), new Stack());
        bst.insert(new BacktrackingBST.Node(4, null));
        bst.insert(new BacktrackingBST.Node(8, null));
        bst.insert(new BacktrackingBST.Node(2, null));
        bst.insert(new BacktrackingBST.Node(3, null));
        bst.print();

        System.out.flush();
        System.setOut(old);
        String expected ="Array:\n" +
                "4 8 2 3\n" +
                "Sorted Array:\n" +
                "2 3 4 8\n" +
                "BST:\n" +
                "4 2 3 8";
        String actual = baos.toString()
                .replaceAll("\r", "")
                .replaceAll(" \n", "\n")
                .trim();

        System.out.println("actual: \n" + actual);
        System.out.println("expected: \n" + expected);

        if (expected.equals(actual)){
            System.out.println("Success!");
        } else {
            System.out.println("Failed!");
        }
    }
}
