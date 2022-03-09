public class ArrayCalculations {

    public static int rowSum(int[][] arr, int row) {
        int total = 0;
        if (row>arr.length-1) {
            return -1;
        }
        else {
            for (int i : arr[row]){
                total += i;
            }
        }
        // create a variable to keep track of the total


        /* Check to see if the given row variable is valid. If the row is less than 0
        or is greater than he number of rows it is not valid. Return -1 if not valid.
         */


        /* if row is valid, create a loop to calculate the sum. hint: elements in a 2D array are found like this:
        arr[row][column]. If you are finding the sum of a row, the [row] number will not change
        only the [column] part will.
         */


        // return the total
        return 0;
    }

    public static int columnSum(int[][] arr, int col) {
        int total = 0;
        if(col>arr.length-1){
            return -1;
        }
        else {
            for (int i =0; i<arr.length; i++){
                total+= arr[i][col];
            }
        }
        // create a variable to keep track of the total


        /* check to see if the given col variable is valid. If col is less than 0 or is greater than
        the number of columns (the length of one array) it is not valid. Return -1 if not valid.
         */


        /* if col is valid, create a loop to calculate the sum. hint: elements in a 2D array are found like this:
        arr[row][column]. If you are finding the sum of a col, the [column] number will not change,
        only the [row] part will.
         */

        // return the total
        return total;
    }

    public static int diagonalSum(int[][] arr, int direction) {
        int total = 0;
        if (direction==0){
            for (int i = 0; i<arr.length; i++){
                total += arr[i][i];
            }
        }
        if (direction==1){
            for (int i = arr.length-1; i>= 0; i--){
                total += arr[i][i];
            }
        }

        return total;

    }



}
