package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knight extends AbstractPiece {
    public Knight(PlayerColour colour) {
        super(PieceType.KNIGHT, colour);
    }

    private static List<Diffs> moveDiffs = Arrays.asList(
            new Diffs(1, 2),
            new Diffs(1, -2),
            new Diffs(2, 1),
            new Diffs(2, -1),
            new Diffs(-1, 2),
            new Diffs(-1, -2),
            new Diffs(-2, 1),
            new Diffs(-2, -1)
    );

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> moves = new ArrayList<>();

        for(Diffs diffs: moveDiffs) {
            Coordinates to = from.plus(diffs.rowDiff, diffs.colDiff);
            if(to.isInBounds() && (board.get(to) == null || !board.get(to).getColour().equals(colour)))
                moves.add(new Move(from, to));
        }

        return moves;
    }

    private static class Diffs {
        int rowDiff;
        int colDiff;

        public Diffs(int rowDiff, int colDiff) {
            this.rowDiff = rowDiff;
            this.colDiff = colDiff;
        }
    }
}
