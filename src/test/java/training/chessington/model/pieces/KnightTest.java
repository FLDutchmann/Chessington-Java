package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class KnightTest {
    @Test
    public void blackKnightCanMoveInAnL() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.BLACK);
        Coordinates knightCoords = new Coordinates(3, 4);
        board.placePiece(knightCoords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, board);

        // Assert
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(4, 6)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(4, 2)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(5, 5)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(5, 3)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(2, 6)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(2, 2)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(1, 5)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(1, 3)));
    }

    @Test
    public void whiteKnightCanMoveInAnL() {
        // Arrange
        Board board = Board.empty();
        Piece knight = new Knight(PlayerColour.WHITE);
        Coordinates knightCoords = new Coordinates(3, 4);
        board.placePiece(knightCoords, knight);

        // Act
        List<Move> moves = knight.getAllowedMoves(knightCoords, board);

        // Assert
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(4, 6)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(4, 2)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(5, 5)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(5, 3)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(2, 6)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(2, 2)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(1, 5)));
        assertThat(moves).contains(new Move(knightCoords, new Coordinates(1, 3)));
    }

    @Test
    public void knightCanTakePieceOfDifferentColor() {
        // Arrange
        Board board = Board.empty();
        Piece whiteKnight = new Knight(PlayerColour.WHITE);
        Coordinates whiteKnightCoords = new Coordinates(3, 1);
        board.placePiece(whiteKnightCoords, whiteKnight);

        Piece blackKnight = new Knight(PlayerColour.BLACK);
        Coordinates blackKnightCoords = new Coordinates(4, 3);
        board.placePiece(blackKnightCoords, blackKnight);

        // Act
        List<Move> whiteMoves = whiteKnight.getAllowedMoves(whiteKnightCoords, board);
        List<Move> blackMoves = blackKnight.getAllowedMoves(blackKnightCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteKnightCoords, blackKnightCoords));
        assertThat(blackMoves).contains(new Move(blackKnightCoords, whiteKnightCoords));
    }

    @Test
    public void knightCannotTakePieceOfSameColor() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new Knight(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 1);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Knight(PlayerColour.WHITE);
        Coordinates otherPieceCoords = new Coordinates(4, 3);
        board.placePiece(otherPieceCoords, otherPiece);

        // Act
        List<Move> whiteMoves = whitePiece.getAllowedMoves(whitePieceCoords, board);
        List<Move> othersMoves = otherPiece.getAllowedMoves(otherPieceCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whitePieceCoords, otherPieceCoords));
        assertThat(othersMoves).doesNotContain(new Move(otherPieceCoords, whitePieceCoords));
    }
}
