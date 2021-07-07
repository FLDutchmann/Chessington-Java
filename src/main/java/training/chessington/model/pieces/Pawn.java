package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();
        if (from.getRow() != 0 && getColour().equals(PlayerColour.WHITE)) {
            if (board.get(from.plus(-1, 0)) == null) {
                moves.add(new Move(from, from.plus(-1, 0)));
                if (board.get(from.plus(-2, 0)) == null && from.getRow() == 6)
                    moves.add(new Move(from, from.plus(-2, 0)));
            }

            Coordinates upLeft = from.plus(-1, -1);
            if (from.getCol() != 0 && board.get(upLeft) != null &&
                    board.get(upLeft).getColour().equals(PlayerColour.BLACK)) {
                moves.add(new Move(from, upLeft));
            }
            Coordinates upRight = from.plus(-1, +1);
            if (from.getCol() != 7 && board.get(upRight) != null &&
                    board.get(upRight).getColour().equals(PlayerColour.BLACK)) {
                moves.add(new Move(from, upRight));
            }
        }

        if (from.getRow() != 7 && getColour().equals(PlayerColour.BLACK)) {
            if (board.get(from.plus(+1, 0)) == null) {
                moves.add(new Move(from, from.plus(+1, 0)));
                if (board.get(from.plus(+2, 0)) == null && from.getRow() == 1)
                    moves.add(new Move(from, from.plus(+2, 0)));
            }

            Coordinates downLeft = from.plus(+1, -1);
            if (from.getCol() != 0 && board.get(downLeft) != null &&
                    board.get(downLeft).getColour().equals(PlayerColour.WHITE)) {
                moves.add(new Move(from, downLeft));
            }
            Coordinates downRight = from.plus(+1, +1);
            if (from.getCol() != 7 && board.get(downRight) != null &&
                    board.get(downRight).getColour().equals(PlayerColour.WHITE)) {
                moves.add(new Move(from, downRight));
            }
        }

        return moves;
    }
}
