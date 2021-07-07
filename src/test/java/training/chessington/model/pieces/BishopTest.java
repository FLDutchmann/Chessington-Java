package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BishopTest {
    @Test
    public void blackBishopCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.BLACK);
        Coordinates bishopCoords = new Coordinates(3, 4);
        board.placePiece(bishopCoords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void whiteBishopCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates bishopCoords = new Coordinates(3, 4);
        board.placePiece(bishopCoords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(bishopCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(bishopCoords, bishopCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void bishopCannotMovePastPieceDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteBishop = new Bishop(PlayerColour.WHITE);
        Coordinates whiteBishopCoords = new Coordinates(3, 3);
        board.placePiece(whiteBishopCoords, whiteBishop);

        Piece blackBishop = new Bishop(PlayerColour.BLACK);
        Coordinates blackBishopCoords = new Coordinates(5, 5);
        board.placePiece(blackBishopCoords, blackBishop);

        // Act
        List<Move> whiteMoves = whiteBishop.getAllowedMoves(whiteBishopCoords, board);
        List<Move> blackMoves = blackBishop.getAllowedMoves(blackBishopCoords, board);

        // Assert
        for(int diff = 3; diff < 5; diff++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteBishopCoords, whiteBishopCoords.plus(diff, diff)));
        for(int diff = 3; diff < 6; diff++)
            assertThat(blackMoves).doesNotContain(new Move(blackBishopCoords, blackBishopCoords.plus(-diff, -diff)));
    }

    @Test
    public void bishopCanTakePieceOfDifferentColor() {
        // Arrange
        Board board = Board.empty();
        Piece whiteBishop = new Bishop(PlayerColour.WHITE);
        Coordinates whiteBishopCoords = new Coordinates(3, 3);
        board.placePiece(whiteBishopCoords, whiteBishop);

        Piece blackBishop = new Bishop(PlayerColour.BLACK);
        Coordinates blackBishopCoords = new Coordinates(5, 5);
        board.placePiece(blackBishopCoords, blackBishop);

        // Act
        List<Move> whiteMoves = whiteBishop.getAllowedMoves(whiteBishopCoords, board);
        List<Move> blackMoves = blackBishop.getAllowedMoves(blackBishopCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteBishopCoords, blackBishopCoords));
        assertThat(blackMoves).contains(new Move(blackBishopCoords, whiteBishopCoords));
    }

    @Test
    public void bishopCannotTakePieceOfSameColor() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new Bishop(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 3);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Bishop(PlayerColour.WHITE);
        Coordinates otherPieceCoords = new Coordinates(5, 5);
        board.placePiece(otherPieceCoords, otherPiece);

        // Act
        List<Move> whiteMoves = whitePiece.getAllowedMoves(whitePieceCoords, board);
        List<Move> othersMoves = otherPiece.getAllowedMoves(otherPieceCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whitePieceCoords, otherPieceCoords));
        assertThat(othersMoves).doesNotContain(new Move(otherPieceCoords, whitePieceCoords));
    }

    @Test
    public void bishopCannotMoveOffBoard() {
        // Arrange
        Board board = Board.empty();
        Piece whiteBishop = new Bishop(PlayerColour.WHITE);
        Coordinates whiteBishopCoords = new Coordinates(0, 0);
        board.placePiece(whiteBishopCoords, whiteBishop);

        Piece blackBishop = new Bishop(PlayerColour.BLACK);
        Coordinates blackBishopCoords = new Coordinates(7, 7);
        board.placePiece(blackBishopCoords, blackBishop);

        // Act
        List<Move> whiteMoves = whiteBishop.getAllowedMoves(whiteBishopCoords, board);
        List<Move> blackMoves = blackBishop.getAllowedMoves(blackBishopCoords, board);

        // Assert
        assertThat(whiteMoves).allMatch( (move) -> move.getTo().isInBounds() );
        assertThat(blackMoves).allMatch( (move) -> move.getTo().isInBounds() );
    }
}
