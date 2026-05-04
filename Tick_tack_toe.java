public class TicTacToe {

    // Step 1: Declare the 3x3 board
    static char[][] board = new char[3][3];

    // Step 2: Initialize all cells with '-'
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    // Step 3: Print the board in a readable format
    static void printBoard() {
        System.out.println("Tic-Tac-Toe Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" "); // space between columns
            }
            System.out.println(); // new line after each row
        }
    }

    // Step 4: Main method — entry point
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
    }
}