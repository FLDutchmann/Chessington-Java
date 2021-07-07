package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements Piece {

    protected final Piece.PieceType type;
    protected final PlayerColour colour;

    protected AbstractPiece(Piece.PieceType type, PlayerColour colour) {
        this.type = type;
        this.colour = colour;
    }

    @Override
    public Piece.PieceType getType() {
        return type;
    }

    @Override
    public PlayerColour getColour() {
        return colour;
    }

    @Override
    public String toString() {
        return colour.toString() + " " + type.toString();
    }

    public List<Move> getMovesInDirection(Coordinates from, Board board, int rowDiff, int colDiff) {
        List<Move> moves = new ArrayList<>();
        Coordinates to = from.plus(rowDiff, colDiff);
        while(to.isInBounds()){
            if(board.get(to) != null){
                if(!board.get(to).getColour().equals(this.colour))
                    moves.add(new Move(from, to));
                break;
            }
            moves.add(new Move(from, to));
            to = to.plus(rowDiff, colDiff);
        }
        return moves;
    }
}
