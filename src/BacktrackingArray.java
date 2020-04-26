public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int lastIndex;//


    // TODO: implement your code here

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        lastIndex = -1;
    }

    @Override
    public Integer get(int index){
        return arr[index];
    }

    @Override
    public Integer search(int x) {

        for (int i = 0; i <= lastIndex; i++){
            if (arr[i] == x)
                return i;
        }
        return -1;
    }

    @Override
    public void insert(Integer x) {
        arr[lastIndex+1] = x;
        stack.push(lastIndex+1);
        lastIndex++;
    }

    @Override
    public void delete(Integer index) {
        if(index > lastIndex)
            //throw new IllegalArgumentException(); // only for tests
        stack.push(arr[index]);
        stack.push(index + arr.length);
        for (int i=index+1; i<arr.length; i++){ //not efficient
            arr[i-1] = arr[i];
        }
        lastIndex = lastIndex - 1;
    }

    @Override
    public Integer minimum() {
        Integer minIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if (arr[i] < arr[minIndex])
                minIndex = i;
        }
        return minIndex;
    }

    @Override
    public Integer maximum() {
        Integer maxIndex = 0;
        for(int i = 1; i < arr.length; i++){
            if (arr[i] > arr[maxIndex])
                maxIndex = i;
        }
        return maxIndex;
    }

    @Override
    public Integer successor(Integer index) {
        Integer output = -1;
        for (int i = 0; i < lastIndex+1; i++) {
            if (arr[i] > arr[index]) {
                if (output == -1 || arr[i] < arr[output])
                    output = i;
            }
        }
        return output;
    }

    @Override
    public Integer predecessor(Integer index) {
        Integer output = -1;
        for (int i = 0; i < lastIndex+1; i++) {
            if (arr[i] < arr[index]) {
                if (output == -1 || arr[i] > arr[output]) {
                    output = i;
                }
            }
        }
        return output;
    }


    @Override
    public void backtrack() {
        if (!stack.isEmpty()) {
            int index = (Integer)stack.pop();

            if (index >= arr.length) {
                index = index - arr.length;
                int toAddValue = (Integer) stack.pop();
                for (int i = lastIndex + 1; i > index; i--) //if index>lastIndex just add at arr[index]
                    arr[i] = arr[i - 1];
                arr[index] = toAddValue;
            } else
                lastIndex = lastIndex - 1;
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
            for (int i = 1; i < arr.length; i++)
                System.out.print(" "+arr[i]);
        }
    }
}
