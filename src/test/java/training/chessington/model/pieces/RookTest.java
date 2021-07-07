package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {
    @Test
    public void blackRookCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // Assert
        for(int col = 0; col < 8; col++) if(col != rookCoords.getCol())
            assertThat(moves).contains(new Move(rookCoords, new Coordinates(3, col)));
    }

    @Test
    public void whiteRookCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // Assert
        for(int col = 0; col < 8; col++) if(col != rookCoords.getCol())
            assertThat(moves).contains(new Move(rookCoords, new Coordinates(3, col)));
    }

    @Test
    public void blackRookCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.BLACK);
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // Assert
        for(int row = 0; row < 8; row++) if(row != rookCoords.getRow())
            assertThat(moves).contains(new Move(rookCoords, new Coordinates(row, 4)));
    }

    @Test
    public void whiteRookCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece rook = new Rook(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, rook);

        // Act
        List<Move> moves = rook.getAllowedMoves(rookCoords, board);

        // Assert
        for(int row = 0; row < 8; row++) if(row != rookCoords.getRow())
            assertThat(moves).contains(new Move(rookCoords, new Coordinates(row, 4)));
    }

    @Test
    public void rookCannotMovePastPieceHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteRook = new Rook(PlayerColour.WHITE);
        Coordinates whiteRookCoords = new Coordinates(3, 3);
        board.placePiece(whiteRookCoords, whiteRook);

        Piece blackRook = new Rook(PlayerColour.BLACK);
        Coordinates blackRookCoords = new Coordinates(3, 5);
        board.placePiece(blackRookCoords, blackRook);

        // Act
        List<Move> whiteMoves = whiteRook.getAllowedMoves(whiteRookCoords, board);
        List<Move> blackMoves = blackRook.getAllowedMoves(blackRookCoords, board);

        // Assert
        for(int col = 6; col < 8; col++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteRookCoords, new Coordinates(3, col)));
        for(int col = 0; col < 3; col++)
            assertThat(blackMoves).doesNotContain(new Move(blackRookCoords, new Coordinates(3, col)));
    }

    @Test
    public void rookCannotMovePastPieceVertically() {
        // Arrange
        Board board = Board.empty();
        Piece whiteRook = new Rook(PlayerColour.WHITE);
        Coordinates whiteRookCoords = new Coordinates(3, 1);
        board.placePiece(whiteRookCoords, whiteRook);

        Piece blackRook = new Rook(PlayerColour.BLACK);
        Coordinates blackRookCoords = new Coordinates(5, 1);
        board.placePiece(blackRookCoords, blackRook);

        // Act
        List<Move> whiteMoves = whiteRook.getAllowedMoves(whiteRookCoords, board);
        List<Move> blackMoves = blackRook.getAllowedMoves(blackRookCoords, board);

        // Assert
        for(int row = 6; row < 8; row++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteRookCoords, new Coordinates(row, 1)));
        for(int row = 0; row < 3; row++)
            assertThat(blackMoves).doesNotContain(new Move(blackRookCoords, new Coordinates(row, 1)));
    }

    @Test
    public void rookCanTakePieceOfDifferentColor() {
        // Arrange
        Board board = Board.empty();
        Piece whiteRook = new Rook(PlayerColour.WHITE);
        Coordinates whiteRookCoords = new Coordinates(3, 1);
        board.placePiece(whiteRookCoords, whiteRook);

        Piece blackRook = new Rook(PlayerColour.BLACK);
        Coordinates blackRookCoords = new Coordinates(5, 1);
        board.placePiece(blackRookCoords, blackRook);

        // Act
        List<Move> whiteMoves = whiteRook.getAllowedMoves(whiteRookCoords, board);
        List<Move> blackMoves = blackRook.getAllowedMoves(blackRookCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteRookCoords, blackRookCoords));
        assertThat(blackMoves).contains(new Move(blackRookCoords, whiteRookCoords));
    }

    @Test
    public void rookCannotTakePieceOfSameColor() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new Rook(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 1);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Rook(PlayerColour.WHITE);
        Coordinates otherPieceCoords = new Coordinates(5, 1);
        board.placePiece(otherPieceCoords, otherPiece);

        // Act
        List<Move> whiteMoves = whitePiece.getAllowedMoves(whitePieceCoords, board);
        List<Move> othersMoves = otherPiece.getAllowedMoves(otherPieceCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whitePieceCoords, otherPieceCoords));
        assertThat(othersMoves).doesNotContain(new Move(otherPieceCoords, whitePieceCoords));
    }

    @Test
    public void rookCannotMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(0, 0);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(7, 7);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        assertThat(whiteMoves).allMatch( (move) -> move.getTo().isInBounds() );
        assertThat(blackMoves).allMatch( (move) -> move.getTo().isInBounds() );

    }
}

