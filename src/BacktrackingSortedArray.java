public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private Integer lastIndex;
    // TODO: implement your code here

    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        lastIndex = -1;
    }
    @Override
    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer search(int x) {//binary search

        Integer low = 0;
        Integer high = lastIndex;

        while (low <= high){
            Integer middle = (low+high)/2;
            if(arr[middle] == x){
                return middle;
            }
            if (x < arr[middle])
                high = middle - 1;
            else
                low = middle + 1;
        }
        return -1;
    }

    @Override
    public void insert(Integer x) {//insertion sort
        boolean isAdded = false;
        if(lastIndex==-1) {
            arr[0] = x;
            stack.push(0);
            lastIndex++;
            isAdded = true;
        }
        Integer low = 0;
        Integer high = lastIndex;
        while (!isAdded){
            Integer middle = (low+high)/2;
            if (x >= arr[middle] & (middle+1>lastIndex || x <= arr[middle + 1])){
                for (int i = lastIndex+1; i > middle+1 ; i--)
                    arr[i] = arr[i - 1];
                    arr[middle + 1]=x;
                    isAdded = true;
                    stack.push(middle + 1);
                    lastIndex++;
            }
                 if (x < arr[middle]) {
                     if (middle == 0) {
                         for (int i = lastIndex+1; i > 0 ; i--)
                             arr[i] = arr [i - 1];
                             arr[0] = x ;
                             isAdded = true;
                             stack.push(0);
                             lastIndex++;
                     }
                     high = middle;
                 }

            else
                low = middle+1;
        }
    }


    @Override
    public void delete(Integer index) {//move one index backwards all values in the bigger indexes than index
        if(index > lastIndex)
            throw new IllegalArgumentException(); // only for tests
        stack.push(arr[index]);
        stack.push(index + arr.length+1);
        for (int i=index+1; i<lastIndex + 1; i++){
            arr[i-1] = arr[i];
        }
        lastIndex = lastIndex - 1;
    }

    @Override
    public Integer minimum() {
        return 0; //check if empty
    }

    @Override
    public Integer maximum() {
        return lastIndex; //check if empty
    }

    @Override
    public Integer successor(Integer index) {
        return index+1;
    }

    @Override
    public Integer predecessor(Integer index) {
        return index-1;
    }

    @Override
    public void backtrack() {//if index bigger than array length than delete backtracked action is being else insert
        if (!stack.isEmpty()) {
            int index = (Integer) stack.pop();

            if (index > arr.length) {//popping the insert function unneeded push
                insert((Integer) stack.pop());
                stack.pop();
            }
            if (index < arr.length) {//popping the delete function unneeded push
                delete(index);
                stack.pop();
                stack.pop();
            }
            System.out.println("backtracking performed");
        }
    }


    @Override
    public void retrack() {
        // Do not implement anything here!!
    }

    @Override
    public void print() {
        if (lastIndex != -1){
            System.out.print(arr[0]);
            for (int i = 1; i <= lastIndex; i++)
                System.out.print(" "+arr[i]);
        }
    }
}
