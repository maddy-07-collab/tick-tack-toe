import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static char player1Symbol;
    static char player2Symbol;
    static int  currentPlayer;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    static void printBoard() {
        System.out.println("Tic-Tac-Toe Board:");
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(board[row][col]);
                if (col < 2) System.out.print(" ");
            }
            System.out.println();
        }
    }

    static void tossAndAssignSymbols() {
        int tossResult = random.nextInt(2);
        if (tossResult == 0) {
            currentPlayer = 1;
            player1Symbol = 'X';
            player2Symbol = 'O';
            System.out.println("Toss Result: Player 1 goes first!");
        } else {
            currentPlayer = 2;
            player2Symbol = 'X';
            player1Symbol = 'O';
            System.out.println("Toss Result: Player 2 (Computer) goes first!");
        }
        System.out.println("Player 1 symbol : " + player1Symbol);
        System.out.println("Player 2 symbol : " + player2Symbol);
        System.out.println("First turn       : Player " + currentPlayer);
    }

    static int getUserInput() {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    static int getRow(int slot) {
        return (slot - 1) / 3;
    }

    static int getCol(int slot) {
        return (slot - 1) % 3;
    }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            System.out.println("Invalid move! Slot is out of bounds.");
            return false;
        }
        if (board[row][col] != '-') {
            System.out.println("Invalid move! That slot is already taken.");
            return false;
        }
        return true;
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    // ── UC7: Computer makes a random valid move ───────────────────
    static void computerMove() {
        System.out.println("Computer is making a move...");
        int row, col;
        do {
            int slot = random.nextInt(9) + 1;   // random slot 1–9
            row = getRow(slot);                  // UC4 reused
            col = getCol(slot);                  // UC4 reused
        } while (!isValidMove(row, col));        // UC5 reused — loop until valid

        placeMove(row, col, player2Symbol);      // UC6 reused
        System.out.println("Computer placed '" + player2Symbol
                + "' at Row: " + row + ", Col: " + col);
    }

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        System.out.println();
        tossAndAssignSymbols();
        System.out.println();

        // Human move
        int slot = getUserInput();
        int row  = getRow(slot);
        int col  = getCol(slot);
        if (isValidMove(row, col)) {
            placeMove(row, col, player1Symbol);
            System.out.println("\nBoard after Player 1's move:");
            printBoard();
        }

        System.out.println();

        // Computer move
        computerMove();
        System.out.println("\nBoard after Computer's move:");
        printBoard();
    }
}