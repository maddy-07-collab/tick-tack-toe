import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    // ── UC1 ───────────────────────────────────────────────────────
    static char[][] board = new char[3][3];

    // ── UC2 ───────────────────────────────────────────────────────
    static char player1Symbol;
    static char player2Symbol;
    static int  currentPlayer;

    // ── UC3 ───────────────────────────────────────────────────────
    static Scanner scanner = new Scanner(System.in);

    // ── UC1: Initialize board ─────────────────────────────────────
    static void initializeBoard() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    // ── UC1: Print board ──────────────────────────────────────────
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

    // ── UC2: Toss and assign symbols ──────────────────────────────
    static void tossAndAssignSymbols() {
        Random random = new Random();
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

    // ── UC3: Accept user slot input ───────────────────────────────
    static int getUserInput() {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    // ── UC4: Convert slot → row & column ─────────────────────────
    static int getRow(int slot) {
        return (slot - 1) / 3;
    }

    static int getCol(int slot) {
        return (slot - 1) % 3;
    }

    // ── UC5: Validate the move ────────────────────────────────────
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

    // ── UC6: Place symbol on the board ────────────────────────────
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;   // State update — write symbol into the 2D array
    }

    // ── Main ──────────────────────────────────────────────────────
    public static void main(String[] args) {
        initializeBoard();          // UC1
        printBoard();               // UC1
        System.out.println();
        tossAndAssignSymbols();     // UC2
        System.out.println();

        int slot = getUserInput();  // UC3
        int row  = getRow(slot);   // UC4
        int col  = getCol(slot);   // UC4

        if (isValidMove(row, col)) {                     // UC5
            char symbol = (currentPlayer == 1)           // decide whose symbol
                          ? player1Symbol
                          : player2Symbol;
            placeMove(row, col, symbol);                 // UC6
            System.out.println("\nBoard after move:");
            printBoard();                                // UC1 — show updated board
        } else {
            System.out.println("Please try again with a valid slot.");
        }
    }
}