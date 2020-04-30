public class Warmup {
    public static int backtrackingSearch(int[] arr, int x, int fd, int bk, Stack myStack) {
        Integer index = 0;
       while (index < arr.length) {
               for (int i = 0; i < fd & index < arr.length; i = i + 1) {
                   if (arr[index] == x)
                       return index;
                   if (index == arr.length-1)
                       return -1;
                   myStack.push(index);
                   index = index + 1; //'moving forward' fd times
               }

            for (int i = 0; i < bk; i = i + 1)
                myStack.pop();                  //'moving backward' bk times

            index = (Integer)myStack.pop(); //starting next loop iteration from the index after going backward bk times
        }

       return -1;
    }

    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {

        int low = 0;
        int high = arr.length-1;

        while (low <= high){

            int bk = isConsistent(arr);
            for (int i = 0 ; i < bk & !myStack.isEmpty(); i = i+1){  //at the end of this loop, stack will contain current low and high after bk times steps back
                high = (Integer)myStack.pop();
                low = (Integer)myStack.pop();
            }

            int middle = (low+high)/2;
            if(arr[middle] == x){
                return middle;
            }
            myStack.push(low);
            myStack.push(high);

            if (x < arr[middle])
                high = middle-1;
            else
                low = middle+1;
        }

        return -1;

    }

    private static int isConsistent(int[] arr) {
        double res = Math.random() * 100 - 75;

        if (res > 0) {
            return (int)Math.round(res / 10);
        } else {
            return 0;
        }
    }

}
