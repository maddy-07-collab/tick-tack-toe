import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static char player1Symbol;
    static char player2Symbol;
    static int  currentPlayer;

    // UC3: new scanner
    static Scanner scanner = new Scanner(System.in);

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

    // UC3: new method
    static int getUserInput() {
        System.out.print("Enter a slot number (1-9): ");
        int slot = scanner.nextInt();
        return slot;
    }

    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        System.out.println();
        tossAndAssignSymbols();
        System.out.println();
        int slot = getUserInput();
        System.out.println("You entered slot: " + slot);
    }
}