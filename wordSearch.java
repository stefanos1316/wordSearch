import java.lang.System;; 
import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;  

class wordSearch {
    
    public static boolean exist(char[][] grid, String word) {
        boolean found = false;
        for(int i = 0; i < grid.length; i++){
            for(int j=0; j < grid[0].length; j++){
               if(dfs(grid, word, i, j, 0)){
                   found = true;
               }
            }
        }
     
        return found;
    }
     
    // The basic DFS function
    public static boolean dfs(char[][] grid, String word, int i, int j, int k) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length){
            return false;
        }
     
        if (grid[i][j] == word.charAt(k)) {
            char temp = grid[i][j];
            grid[i][j]='#';
            if (k == word.length() -1) {
                return true;
            } else if(dfs(grid, word, i-1, j, k+1)
            || dfs(grid, word, i+1, j, k+1)
            || dfs(grid, word, i, j-1, k+1)
            || dfs(grid, word, i, j+1, k+1)) {
                return true;
            }
            grid[i][j] = temp;
        }
     
        return false;
    }
  
    public static void main(String args[]) throws IOException { 

        // Print help if no command-line argumnets were given
        if (args.length < 1) {
            System.out.println("No command-line argument!");
            System.out.println("Please provide a word to search in the array.");
            System.out.println("For example: java wordSearch queens");
            System.out.println();
            System.exit(1);
        }

        // If a path is given then execute the following function
        if (args.length > 1) {
            try (BufferedReader br = new BufferedReader(new FileReader(args[1]))) {
                String line;
                ArrayList<String> mylist = new ArrayList<>();
                while ((line = br.readLine()) != null) {
                    line = line.replaceAll("\\s+",""); 
                    mylist.add(line);
                }
               
                int rowSize = mylist.get(0).length();
                System.out.println(rowSize);
                char[][] newGrid = new char[mylist.size()][rowSize];
                for (int i = 0; i < mylist.size(); ++i) {
                    char[] splitter = mylist.get(i).toCharArray();
                    for (int j = 0; j < splitter.length; ++j)
                        newGrid[i][j] = splitter[j];
                }

                System.out.println("Given word ''" + args[0] + "'' ==> " + exist(newGrid, args[0].toUpperCase()));
                System.exit(0);
            }
        }

        // A default 2D greeting array for Guoliang
        char[][] grid = { { 'H', 'E', 'L', 'L', 'O', ',', 'G', 'U', 'O', 'L', 'I', 'A', 'N', 'G' }, 
             { 'T', 'H', 'I', 'S', 'I', 'S', 'S', 'T', 'E', 'F', 'A', 'N', 'O', 'S' }, 
             { 'H', 'A', 'V', 'E', 'A', 'N', 'I', 'C', 'E', 'W', 'E', 'E', 'K', '!' } }; 

        System.out.println("Given word ''" + args[0] + "'' ==> " + exist(grid, args[0].toUpperCase()));
    } 
} 
  
