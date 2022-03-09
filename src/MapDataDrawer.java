import java.util.*;
import java.io.*;
import java.awt.*;

public class MapDataDrawer
{

    private int[][] grid;

    public MapDataDrawer(String filename, int rows, int cols) throws FileNotFoundException {
        Scanner scan = new Scanner(new File (filename));
        grid = new int[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                int current = scan.nextInt();
                grid[i][j]=current;
            }
        }
        scan.close();
        // initialize grid
        //read the data from the file into the grid

    }

    /**
     * @return the min value in the entire grid
     */
    public int findMinValue(){
        int lowest = grid[0][0];
        for (int[] innerArray : grid){
            for (int val: innerArray){
                if (val<lowest){
                    lowest=val;
                }
            }
        }
        return lowest;
    }
    /**
     * @return the max value in the entire grid
     */
    public int findMaxValue(){
        int highest = grid[0][0];
        for (int[] innerArray : grid){
            for(int val: innerArray){
                if (val>highest){
                    highest=val;
                }
            }
        }
        return highest;
    }

    /**
     * @param col the column of the grid to check
     * @return the index of the row with the lowest value in the given col for the grid
     */
    public  int indexOfMinInCol(int col){
        int lowest = grid[0][col];
        int index = 0;
        for (int i = 1; i <grid.length; i++){
            int current = grid[i][col];
            if (current < lowest){
                lowest = current;
                index = i;
            }

        }
        return index;
    }

    /**
     * Draws the grid using the given Graphics object.
     * Colors should be grayscale values 0-255, scaled based on min/max values in grid
     */
    public void drawMap(Graphics g){
        int highest = findMaxValue();
        int lowest = findMinValue();
        for (int i = 0; i<grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int current = grid[i][j];
                int currentScale = current - lowest;
                int highestScale = highest - lowest;
                double x1 = ((double) (currentScale)) / (highestScale);
                int c = (int) (x1 * 255);
                g.setColor(new Color(c, c, c));
                g.fillRect(j, i, 1, 1);

            }
        }

    }

    /**
     * Find a path from West-to-East starting at given row.
     * Choose a foward step out of 3 possible forward locations, using greedy method described in assignment.
     * @return the total change in elevation traveled from West-to-East
     */
    public int drawLowestElevPath(Graphics g, int row){
        g.fillRect(0, row, 1, 1);
        int elevation = 0;
        for (int i = 1; i <grid[0].length-1; i++) {

            if (row == 0) {
                int mid = grid[row][i];
                int high = grid[row + 1][i];
                int current = grid[row][i - 1];
                int y = Math.abs(current - mid);
                int z = Math.abs(current - high);

                if (y < z) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                }
                if (z < y) {
                    g.fillRect(i, row + 1, 1, 1);
                    row++;
                    elevation += z;
                }
                if (y == z) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                }

            }

            else if (row == grid.length - 1) {
                int low = grid[row - 1][i];
                int mid = grid[row][i];
                int current = grid[row][i - 1];
                int x = Math.abs(current - low);
                int y = Math.abs(current - mid);

                if (x < y) {
                    g.fillRect(i, row - 1, 1, 1);
                    row--;
                    elevation += x;
                }
                if (y < x) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                }
                if (y == x) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                }

            }

            else {

                int low = grid[row - 1][i];
                int mid = grid[row][i];
                int high = grid[row + 1][i];
                int current = grid[row][i - 1];
                int x = Math.abs(current - low);
                int y = Math.abs(current - mid);
                int z = Math.abs(current - high);

                if ((x < y) && (x < z)) {
                    g.fillRect(i, row - 1, 1, 1);
                    row--;
                    elevation += x;
                } else if ((y < x) && (y < z)) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                } else if ((z < y) && (z < x)) {
                    g.fillRect(i, row + 1, 1, 1);
                    row++;
                    elevation += z;
                } else if (x == z) {
                    int r = (int) (Math.random() * 2 + 1);
                    if (r == 1) {
                        g.fillRect(i, row - 1, 1, 1);
                        row--;
                    } else if (r == 2) {
                        g.fillRect(i, row + 1, 1, 1);
                        row++;
                    }

                } else if (y == z) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                } else if (y == x) {
                    g.fillRect(i, row, 1, 1);
                    elevation += y;
                }

            }
        }

        return elevation;
    }

    /**
     * @return the index of the starting row for the lowest-elevation-change path in the entire grid.
     */
    public int indexOfLowestElevPath(Graphics g){
        int lowest = drawLowestElevPath(g, 0);
        int index = 0;
        for (int i = 1; i<grid.length; i++){
            int current = drawLowestElevPath(g, i);
            if (current < lowest){
                lowest = current;
                index = i;
            }

        }
        return index;

    }


}