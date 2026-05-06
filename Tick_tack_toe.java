import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    static char[][] board = new char[3][3];
    static char player1Symbol;
    static char player2Symbol;
    static int  currentPlayer;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();
    static boolean gameOver = false;
    static boolean isDraw   = false;

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

    static int getRow(int slot) { return (slot - 1) / 3; }
    static int getCol(int slot) { return (slot - 1) % 3; }

    static boolean isValidMove(int row, int col) {
        if (row < 0 || row > 2 || col < 0 || col > 2) return false;
        if (board[row][col] != '-') return false;
        return true;
    }

    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    static void computerMove() {
        System.out.println("Computer is making a move...");
        int row, col;
        do {
            int slot = random.nextInt(9) + 1;
            row = getRow(slot);
            col = getCol(slot);
        } while (!isValidMove(row, col));
        placeMove(row, col, player2Symbol);
        System.out.println("Computer placed '" + player2Symbol
                + "' at Row: " + row + ", Col: " + col);
    }

    static boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol)
                return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)
                return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol)
            return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)
            return true;
        return false;
    }

    // ── UC10: Detect draw condition ───────────────────────────────
    static boolean checkDraw() {
        int emptyCellCount = 0;   // counting logic

        // loop traversal — scan every cell on the board
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (board[row][col] == '-') {
                    emptyCellCount++;  // found an empty cell
                }
            }
        }

        // boolean flag — draw only when zero empty cells remain
        if (emptyCellCount == 0) {
            isDraw = true;   // set global draw flag
            return true;
        }
        return false;
    }

    static void switchTurn() {
        currentPlayer = (currentPlayer == 1) ? 2 : 1;
    }

    static void startGameLoop() {
        while (!gameOver) {
            System.out.println("\n--- Player " + currentPlayer + "'s Turn ---");
            printBoard();

            if (currentPlayer == 1) {
                int slot, row, col;
                do {
                    slot = getUserInput();
                    row  = getRow(slot);
                    col  = getCol(slot);
                    if (!isValidMove(row, col))
                        System.out.println("Invalid move! Try again.");
                } while (!isValidMove(row, col));
                placeMove(row, col, player1Symbol);

                if (checkWin(player1Symbol)) {
                    printBoard();
                    System.out.println("Player 1 wins!");
                    gameOver = true;
                } else if (checkDraw()) {        // UC10 called here
                    printBoard();
                    System.out.println("It's a draw! No more moves left.");
                    gameOver = true;
                }

            } else {
                computerMove();

                if (checkWin(player2Symbol)) {
                    printBoard();
                    System.out.println("Computer wins!");
                    gameOver = true;
                } else if (checkDraw()) {        // UC10 called here
                    printBoard();
                    System.out.println("It's a draw! No more moves left.");
                    gameOver = true;
                }
            }

            if (!gameOver) switchTurn();
        }
    }

    public static void main(String[] args) {
        initializeBoard();
        tossAndAssignSymbols();
        System.out.println();
        startGameLoop();
    }
}