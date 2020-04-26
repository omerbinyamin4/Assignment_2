public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    BacktrackingBST.Node root = null;

    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
    }


    public Node getRoot() {
        return root;
    }
	
    public Node search(int x) {
        if (root == null)  //for tests
            return null;
        else
            return root.search(x);
    }


    public void insert(BacktrackingBST.Node z) {

        BacktrackingBST.Node toInsert = new BacktrackingBST.Node(z.getKey(), z.getValue());
        stack.push(z);
        stack.push(null);

        insert_2(root, z);

        if (!redoStack.isEmpty())
            redoStack.clear();
    }

    public void insert_2(BacktrackingBST.Node curr, BacktrackingBST.Node toInsert){

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

        BacktrackingBST.Node toDelete = new BacktrackingBST.Node(x.getKey(), x.getValue());
        toDelete.right = x.right;
        toDelete.left = x.left;
        toDelete.parent = x.parent;
        stack.push(toDelete);

        if (x.left == null & x.right == null){
            if (x.parent.getKey() > x.getKey())
                x.parent.left = null;
            else
                x.parent.right = null;
        }
        else if (x. parent != null & x.left == null & x.right != null){
            if (x.parent.getKey() > x.getKey())
                x.parent.left = x.right;
            else
                x.parent.right = x.right;
        }
        else if (x.parent != null & x.left != null & x.right == null){
            if (x.parent.getKey() > x.getKey())
                x.parent.left = x.left;
            else
                x.parent.right = x.left;
        }
        else{
            Node succ = successor(x);
            Node poped = (Node)stack.pop();
            delete(succ);
            stack.push(poped);

            succ.right = x.right; //replace right child
            if (x.right != null)
                x.right.parent = succ;

            succ.left = x.left; //replace left child
            if (x.left != null)
                x.left.parent = succ;

            succ.parent = x.parent; //replace parent
            if (x.parent == null) //in case deleted node is original root
                root = succ;
        }

        if (!redoStack.isEmpty())
            redoStack.clear();
    }

    public Node minimum() {
        return root.minimum();
    }

    public Node maximum() {
        return root.maximum();
    }

    public Node successor(Node x) {
        return x.successor();
    }

    public Node predecessor(Node x) {
        return x.predecessor();
    }

    @Override
    public void backtrack() {
        if (!stack.isEmpty()) {
            Node indicator = (Node) stack.pop();
            if (indicator == null) {
                delete((Node) stack.pop());
                redoStack.push(stack.pop());
            }
            else {
                if (indicator.parent == null){
                    root = indicator;
                    if (indicator.right!=null)
                        indicator.right.parent = indicator;
                    if (indicator.left!=null)
                        indicator.left.parent = indicator;
                }

                else if (indicator.parent.getKey() > indicator.getKey())
                    indicator.parent.left = indicator;
                else
                    indicator.parent.right = indicator;
                if (indicator.parent == null || indicator.right != null & indicator.left != null)
                    backtrack();

                BacktrackingBST.Node toDelete = new BacktrackingBST.Node(indicator.getKey(), indicator.getValue());
                redoStack.push(toDelete);
                redoStack.push(null);
            }
        }

    }

    @Override
    public void retrack() {
        if (!redoStack.isEmpty()) {
            Node indicator = (Node)stack.pop();
            if (indicator == null) {
                delete((Node) stack.pop());
                redoStack.pop();
            }
            else {
                if (indicator.parent.getKey() > indicator.getKey())
                    indicator.parent.left = indicator;
                else
                    indicator.parent.right = indicator;
                if (indicator.right != null & indicator.left != null) //??
                    backtrack();
            }
        }
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

    public static void main(String[] args) {


        Stack backTrackingStack     = new Stack();
        Stack retrackStack            = new Stack();
        BacktrackingBST tree = new BacktrackingBST(backTrackingStack, retrackStack);
        BacktrackingBST.Node node232 = new BacktrackingBST.Node(232,null);
        tree.insert(node232);
        BacktrackingBST.Node node715 = new BacktrackingBST.Node(715,null);
        tree.insert(node715);
        BacktrackingBST.Node node282 = new BacktrackingBST.Node(282,null);
        tree.insert(node282);

        tree.print();
        System.out.println();

        tree.delete(node232);

        tree.print();
        System.out.println();

        tree.backtrack();
        tree.print();
        System.out.println();
        tree.delete(node715);
        tree.print();
        System.out.println();
        tree.backtrack();
        tree.print();
        System.out.println();



    }
}


