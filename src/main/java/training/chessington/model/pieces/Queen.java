package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Queen extends AbstractPiece {
    public Queen(PlayerColour colour) {
        super(PieceType.QUEEN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        moves.addAll(getMovesInDirection(from, board,1,1));
        moves.addAll(getMovesInDirection(from, board,1,-1));
        moves.addAll(getMovesInDirection(from, board,-1,1));
        moves.addAll(getMovesInDirection(from, board,-1,-1));

        moves.addAll(getMovesInDirection(from, board,0,1));
        moves.addAll(getMovesInDirection(from, board,0,-1));
        moves.addAll(getMovesInDirection(from, board,1,0));
        moves.addAll(getMovesInDirection(from, board,-1,0));

        return moves;
    }
}
