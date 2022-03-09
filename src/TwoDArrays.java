public class TwoDArrays {

    public static int maxValue(int[][] arr) {
        int highest = arr[0][0];
        for (int[] innerArray : arr){
            for (int val: innerArray){
                if (val>highest){
                    highest=val;
                }
            }
        }

        return highest;
    }

    public static int sumAll(int[][] arr) {

        return 0;
    }

    /*
    public static int[][] squareArray(int num) {

    }

     */


}
