package training.chessington.model;

import training.chessington.model.pieces.*;

public class Board {

    private Piece[][] board = new Piece[8][8];
    private Move lastMove;

    private Board() {
    }

    public static Board forNewGame() {
        Board board = new Board();
        board.setBackRow(0, PlayerColour.BLACK);
        board.setBackRow(7, PlayerColour.WHITE);

        for (int col = 0; col < 8; col++) {
            board.board[1][col] = new Pawn(PlayerColour.BLACK);
            board.board[6][col] = new Pawn(PlayerColour.WHITE);
        }

        return board;
    }

    public static Board empty() {
        return new Board();
    }

    private void setBackRow(int rowIndex, PlayerColour colour) {
        board[rowIndex][0] = new Rook(colour);
        board[rowIndex][1] = new Knight(colour);
        board[rowIndex][2] = new Bishop(colour);
        board[rowIndex][3] = new Queen(colour);
        board[rowIndex][4] = new King(colour);
        board[rowIndex][5] = new Bishop(colour);
        board[rowIndex][6] = new Knight(colour);
        board[rowIndex][7] = new Rook(colour);
    }

    public Piece get(Coordinates coords) {
        return board[coords.getRow()][coords.getCol()];
    }

    public void move(Coordinates from, Coordinates to) {
        if(isEnPassant(from, to)) {
            if(get(from).getColour() == PlayerColour.BLACK)
                board[to.getRow()-1][to.getCol()] = null;
            if(get(from).getColour() == PlayerColour.WHITE)
                board[to.getRow()+1][to.getCol()] = null;
        }

        board[to.getRow()][to.getCol()] = board[from.getRow()][from.getCol()];
        board[from.getRow()][from.getCol()] = null;
        lastMove = new Move(from, to);
    }

    public boolean isEnPassant(Coordinates from, Coordinates to) {
        // A pawn moves to an empty square in a different column if and only if it's attacking en passant
        return get(from).getType().equals(Piece.PieceType.PAWN) && get(to) == null && to.getCol() != from.getCol();
    }

    public void placePiece(Coordinates coords, Piece piece) {
        board[coords.getRow()][coords.getCol()] = piece;
    }

    public Move getLastMove() {
        return lastMove;
    }
}
