package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KingTest {
    @Test
    public void blackKingCanMoveInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.BLACK);
        Coordinates kingCoords = new Coordinates(3, 4);
        board.placePiece(kingCoords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        for(int row = -1; row < 2; row++) {
            for(int col = -1; col < 2; col++) {
                Coordinates to = kingCoords.plus(row, col);
                if (!kingCoords.equals(to)) assertThat(moves).contains(new Move(kingCoords, to));
            }
        }
    }

    @Test
    public void whiteKingCanMoveInAllDirections() {
        // Arrange
        Board board = Board.empty();
        Piece king = new King(PlayerColour.WHITE);
        Coordinates kingCoords = new Coordinates(3, 4);
        board.placePiece(kingCoords, king);

        // Act
        List<Move> moves = king.getAllowedMoves(kingCoords, board);

        // Assert
        for(int row = -1; row < 2; row++) {
            for(int col = -1; col < 2; col++) {
                Coordinates to = kingCoords.plus(row, col);
                if (!kingCoords.equals(to)) assertThat(moves).contains(new Move(kingCoords, to));
            }
        }
    }

    @Test
    public void kingCanTakePieceOfDifferentColor() {
        // Arrange
        Board board = Board.empty();
        Piece whiteKing = new King(PlayerColour.WHITE);
        Coordinates whiteKingCoords = new Coordinates(3, 3);
        board.placePiece(whiteKingCoords, whiteKing);

        Piece otherPiece = new Pawn(PlayerColour.BLACK);
        Coordinates otherPieceCoordinates = new Coordinates(3, 4);
        board.placePiece(otherPieceCoordinates, otherPiece);

        // Act
        List<Move> whiteMoves = whiteKing.getAllowedMoves(whiteKingCoords, board);
        List<Move> blackMoves = otherPiece.getAllowedMoves(otherPieceCoordinates, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteKingCoords, otherPieceCoordinates));
        assertThat(blackMoves).contains(new Move(otherPieceCoordinates, whiteKingCoords));
    }

    @Test
    public void kingCannotTakePieceOfSameColor() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new King(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 3);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Pawn(PlayerColour.WHITE);
        Coordinates otherPieceCoords = new Coordinates(3, 4);
        board.placePiece(otherPieceCoords, otherPiece);

        // Act
        List<Move> whiteMoves = whitePiece.getAllowedMoves(whitePieceCoords, board);
        List<Move> othersMoves = otherPiece.getAllowedMoves(otherPieceCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whitePieceCoords, otherPieceCoords));
        assertThat(othersMoves).doesNotContain(new Move(otherPieceCoords, whitePieceCoords));
    }
}
