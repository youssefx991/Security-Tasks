package Security;
import java.util.*;

public class ColumnarCipher {

    public List<Integer> analyse(String plainText, String cipherText) {
        // TODO: Analyze the plainText and cipherText to determine the key(s)
        int ctSize = cipherText.length();
        int ptSize = plainText.length();
        char[][] grid;
        List<Integer> key = new ArrayList<>();
        boolean foundKey = false;
        for(int rows=2, cols; rows<=ctSize/2; ++rows)
        {
            if(ctSize % rows != 0)
                continue;

            int count = 0;
            cols = ctSize / rows;
            grid = new char[rows][cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (count >= ptSize) {
                        grid[i][j] = 'x';
                    } else {
                        grid[i][j] = plainText.charAt(count++);
                    }
                }
            }

            // print grid
            // for (int i = 0; i < rows; i++) {
            //     for (int j = 0; j < cols; j++) {
            //         System.out.print(grid[i][j]);
            //     }
            //     System.out.println();
            // }
            // key = new ArrayList();
            key.clear();
            StringBuilder colString = new StringBuilder();
            foundKey = true;

            for (int j = 0; j < cols; j++) {
                for (int i = 0; i < rows; i++) {
                    colString.append(grid[i][j]);
                }

                // print colString
                // System.out.println("ColsString: " + colString.toString());
                int index = cipherText.indexOf(colString.toString());

                if (index != -1) {
                    // System.out.println("Found at index: " + index + "\t rows: " + rows + "\t number of column: " + ((index/rows) + 1));
                    key.add((index/rows) + 1);
                }
                else {
                    foundKey = false;
                    break;
                }

                colString.setLength(0); // Clear the StringBuilder for the next column
            }

            if(foundKey)
                break;

        }

        // System.out.print("key is: ");
        // for(int i=0; i<key.size(); ++i) {
        //     System.out.print(key.get(i) + " ");
        // }
        return key; // Placeholder return
    }

    public String decrypt(String cipherText, List<Integer> key) {
        int cipherSize = cipherText.length();
        int rows = (int) Math.ceil((double) cipherSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        int remainingCols = cipherSize % key.size();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                if (remainingCols != 0 && j == rows - 1 && keyMap.get(i) >= remainingCols) continue;
                grid[j][keyMap.get(i)] = cipherText.charAt(count++);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                result.append(grid[i][j]);
            }
        }
        return result.toString().toUpperCase().trim();
    }

    public String encrypt(String plainText, List<Integer> key) {
        int ptSize = plainText.length();
        int rows = (int) Math.ceil((double) ptSize / key.size());
        char[][] grid = new char[rows][key.size()];
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key.size(); j++) {
                if (count >= ptSize) {
                    grid[i][j] = 'x';
                } else {
                    grid[i][j] = plainText.charAt(count++);
                }
            }
        }

        Map<Integer, Integer> keyMap = new HashMap<>();
        for (int i = 0; i < key.size(); i++) {
            keyMap.put(key.get(i) - 1, i);
        }

        StringBuilder cipherText = new StringBuilder();
        for (int i = 0; i < key.size(); i++) {
            for (int j = 0; j < rows; j++) {
                cipherText.append(Character.toUpperCase(grid[j][keyMap.get(i)]));
            }
        }
        return cipherText.toString();
    }
}
