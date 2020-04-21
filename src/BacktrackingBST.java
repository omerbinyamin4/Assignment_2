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
        return search_2(root, x);
    }

    public static Node search_2 (BacktrackingBST.Node node, int x){

        if (node.getKey() == x)
            return node;
        else if(x > node.getKey() & node.right != null)
            return search_2(node.right, x);
        else if (x < node.getKey() & node.left != null)
            return search_2(node.left,x);

        return null;
    }

    public void insert(BacktrackingBST.Node z) {

        BacktrackingBST.Node toInsert = new BacktrackingBST.Node(z.getKey(), z.getValue());
        stack.push(toInsert);
        stack.push(null);
        insert_2(root, toInsert);

        if (!redoStack.isEmpty())
            redoStack.clear();
    }

    public static void insert_2(BacktrackingBST.Node curr, BacktrackingBST.Node toInsert){

        if (curr == null)
            curr = toInsert;

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
        else if (x.left == null & x.right != null){
            if (x.parent.getKey() > x.getKey())
                x.parent.left = x.right;
            else
                x.parent.right = x.right;
        }
        else if (x.left != null & x.right == null){
            if (x.parent.getKey() > x.getKey())
                x.parent.left = x.left;
            else
                x.parent.right = x.left;
        }
        else{
            Node temp = successor(x); //non- static?
            Node poped = (Node)stack.pop();
            delete(temp); //non static?
            stack.push(poped);
            temp.right = x.right;
            temp.left = x.left;
            x = temp;
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
                if (indicator.parent.getKey() > indicator.getKey())
                    indicator.parent.left = indicator;
                else
                    indicator.parent.right = indicator;
                if (indicator.right != null & indicator.left != null)
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
    }

}
