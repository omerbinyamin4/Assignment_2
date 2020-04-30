public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    BacktrackingBST.Node root = null;
    private boolean isRetracking; //a field we added, being used it 'retrack' method


    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
        this.isRetracking = false;
    }


    public Node getRoot() {
        return root;
    }
	
    public Node search(int x) {
        if (root == null)  //empty tree
            return null;
        else
            return root.search(x); //using method we added to Node class
    }


    public void insert(BacktrackingBST.Node z) {

        stack.push(z);
        stack.push(null); //will differ later between insert and delete

        insert_2(root, z);

        if (!isRetracking) //clearing retrack history
            redoStack.clear();
    }

    public void insert_2(BacktrackingBST.Node curr, BacktrackingBST.Node toInsert){ //seld- aid method

        if (curr == null)
            root = toInsert;

        else if (toInsert.getKey() < curr.getKey()){
            if (curr.left == null) {
                curr.left = toInsert;
                toInsert.parent = curr;
            }
            else
                insert_2(curr.left, toInsert);
        }
        else{
            if (curr.right == null) {
                curr.right = toInsert;
                toInsert.parent = curr;
            }
            else
                insert_2(curr.right, toInsert);
        }

    }

    public void delete(Node x) {

        stack.push(x);

        if (x.left == null & x.right == null){ //case 1
            if (x.parent == null)
                root = null;
            else if (x.parent.getKey() > x.getKey())
                x.parent.left = null;
            else
                x.parent.right = null;
        }

        else if (x.left == null & x.right != null){ //case 2 option 1 (only one right son)
            if (x.parent == null){
                root = x.right;
                x.right.parent =  null;
            }
            else if (x.parent.getKey() > x.getKey()) {
                x.parent.left = x.right;
                x.right.parent = x.parent;
            }
            else {
                x.parent.right = x.right;
                x.right.parent = x.parent;
            }
        }
        else if (x.left != null & x.right == null){ //case 2 option 2 (only one left son)
            if (x.parent == null) {
                root = x.left;
                x.left.parent = null;
            }
            else if (x.parent.getKey() > x.getKey()) {
                x.parent.left = x.left;
                x.left.parent = x.parent;
            }
            else {
                x.parent.right = x.left;
                x.left.parent = x.parent;
            }
        }
        else{ //case 3 (two children)
            Node succ = successor(x);
            stack.pop();
            delete(succ);
            stack.push("case 3");
            stack.push(x);
            swap(x, succ);
        }

        if (!isRetracking)//clearing retrack history
            redoStack.clear();
    }

    public void swap(Node original, Node replacer){  //self- aid method, swapping between two nodes.

        Node origLeft = original.left;
        Node origRight = original.right;
        Node origParent = original.parent;

        original.left = replacer.left;
        replacer.left = origLeft;
        if (origLeft != null)
            origLeft.parent = replacer;

        original.right = replacer.right;
        replacer.right = origRight;
        if (origRight != null)
            origRight.parent = replacer;

        original.parent = replacer.parent;
        replacer.parent = origParent;
        if (origParent != null && origParent.getKey() > replacer.getKey())
            origParent.left = replacer;
        else if (origParent != null && origParent.getKey() < replacer.getKey())
            origParent.right = replacer;
        else if (origParent == null)
            root = replacer;

    }

    public Node minimum() {
        return root.minimum(); //using method we added to Node class
    }

    public Node maximum() {
        return root.maximum(); //using method we added to Node class
    }

    public Node successor(Node x) {
        return x.successor(); //using method we added to Node class
    }

    public Node predecessor(Node x) {
        return x.predecessor(); //using method we added to Node class
    }

    @Override
    public void backtrack() {
        if (!stack.isEmpty()) {
            Node indicator = (Node) stack.pop();
            if (indicator == null) { //null indicates we need to backtrack insert
                delete((Node) stack.pop());
                redoStack.push(stack.pop());
            }

            else { //delete
                Object poped = stack.pop();
                if (poped instanceof String) {   // string indicates last action was case 3 of deletion
                    Node succ = (Node)stack.pop();
                    swap(succ ,indicator);
                    stack.push(succ);
                    backtrack();
                    redoStack.pop();
                    redoStack.pop();
                }

                else { //case 1 or 2
                    stack.push(poped);
                    if (indicator.parent == null) { //originally was a root
                        root = indicator;
                        if (indicator.left != null)
                            indicator.left.parent = indicator;
                        else if (indicator.right != null)
                            indicator.right.parent = indicator;
                    }
                    else {
                        adopt(indicator.parent, indicator);
                        if (indicator.right != null)
                            adopt(indicator, indicator.right);
                        else if (indicator.left != null)
                            adopt(indicator, indicator.left);
                    }

                }

                redoStack.push(indicator);
                redoStack.push(null);
            }
            System.out.println("backtracking performed");
        }

    }

    public void adopt (Node father, Node son){ //self- aid method: putting 'son' as 'father' left\right son (according to key). assuming 'son' and 'father' are not null

        if (father.getKey() > son.getKey()) {
            father.left = son;
            son.parent = father;
        }
        else {
            father.right = son;
            son.parent = father;
        }
    }

    @Override
    public void retrack() {
        isRetracking = true;
        if (!redoStack.isEmpty()) {
            Node indicator = (Node)redoStack.pop();
            if (indicator == null) {
                delete((Node)redoStack.pop());
                stack.pop();
            }
            else {
                if (indicator.parent.getKey() > indicator.getKey())
                    indicator.parent.left = indicator;
                else
                    indicator.parent.right = indicator;

            }
        }
        isRetracking = false;
    }

    public void printPreOrder(){
       this.print();
    }

    @Override
    public void print() {
        if (root != null) {
            System.out.print(root.getKey());
            print(root.left);
            print(root.right);
        }
    }

    public void print(Node root){
        if (root == null)
            System.out.print("");
        else{
            System.out.print(" "+root.getKey());
            print(root.left);
            print(root.right);
        }
    }

    public static class Node{
    	//These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        
        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public Node search(int x){
                if (key == x)
                    return this;
                else if (x > key & right != null)
                    return right.search(x);
                else if (x < key & left != null)
                    return left.search(x);

            return null;
        }

        public Node minimum(){
           Node curr = this;
            while (curr.left != null)
                curr = curr.left;
            return curr;
        }

        public Node maximum(){
            Node curr = this;
            while (curr.right != null)
                curr = curr.right;
            return curr;
        }

        public Node successor(){
            if (this.right != null)
                return this.right.minimum();
            else{
                Node father = this.parent;
                while (father.right != this)
                    father = father.parent;
                return father;
            }
        }

        public Node predecessor(){
            if (this.left != null)
                return this.left.maximum();
            else{
                Node father = this.parent;
                while (father.left != this)
                    father = father.parent;
                return father;
            }
        }

        @Override
        public String toString()
        {
            return ""+key;
        }
    }


}


