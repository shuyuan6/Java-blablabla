import java.util.LinkedList;
import java.util.List;

class Solution {
    public static boolean isValidChessboard(List<String> chessboard, int n) {
        for (int r = 0; r < n; r++) {
            boolean qFoundR = false;
            for (int c = 0; c < n; c++) {
                if (chessboard.get(r).charAt(c) == 'Q') {
                    if (qFoundR) {
                        return false;
                    } else {
                        qFoundR = true;
                    }
                }
            }
        }

        for (int c = 0; c < n; c++) {
            boolean qFoundC = false;
            for (int r = 0; r < n; r++) {
                if (chessboard.get(r).charAt(c) == 'Q') {
                    if (qFoundC) {
                        return false;
                    } else {
                        qFoundC = true;
                    }
                }
            }
        }

        // from top right to bottom left
        for (int sum = 0; sum <= (n - 1) * 2; sum ++) {
            boolean qFound = false;
            for (int r = 0; r < n; r++) {
                int c = sum - r;
                if (c < 0 || c >= n) {
                    continue;
                }
                if (chessboard.get(r).charAt(c) == 'Q') {
                    if (qFound) {
                        return false;
                    } else {
                        qFound = true;
                    }
                }
            }
        }

        // from top left to bottom right
        for (int diff = n - 1; diff > -n; diff--) {
            boolean qFoundLR = false;
            for (int r = 0; r < n; r++) {
                int c = r - diff;
                if (c < 0 || c >= n) {
                    continue;
                }
                if (chessboard.get(r).charAt(c) == 'Q') {
                    if (qFoundLR) {
                        return false;
                    } else {
                        qFoundLR = true;
                    }
                }
            }
        }
        return true;
    }


    public static void main(String[] args){
        List<String> input = new LinkedList<>();
        input.add("....");
        input.add("....");
        input.add("....");
        input.add("....");

        System.out.println("input is valid: " + isValidChessboard(input, 4));
    }
}
