package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    public King(PlayerColour colour) {
        super(PieceType.KING, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        for(int row = -1; row < 2; row++) {
            for(int col = -1; col < 2; col++) {
                Coordinates to = from.plus(row, col);
                if (!from.equals(to) && to.isInBounds()) moves.add(new Move(from, to));
            }
        }
    }
}
