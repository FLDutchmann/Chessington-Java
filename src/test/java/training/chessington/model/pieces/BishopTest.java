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
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(rookCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void whiteBishopCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece bishop = new Bishop(PlayerColour.WHITE);
        Coordinates rookCoords = new Coordinates(3, 4);
        board.placePiece(rookCoords, bishop);

        // Act
        List<Move> moves = bishop.getAllowedMoves(rookCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(rookCoords, rookCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void rookCannotMovePastPieceDiagonally() {
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
    public void rookCanTakePieceOfDifferentColor() {
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
    public void rookCannotTakePieceOfSameColor() {
        // Arrange
        Board board = Board.empty();
        Piece whiteBishop = new Bishop(PlayerColour.WHITE);
        Coordinates whiteBishopCoords = new Coordinates(3, 3);
        board.placePiece(whiteBishopCoords, whiteBishop);

        Piece blackBishop = new Bishop(PlayerColour.WHITE);
        Coordinates blackBishopCoords = new Coordinates(5, 5);
        board.placePiece(blackBishopCoords, blackBishop);

        // Act
        List<Move> whiteMoves = whiteBishop.getAllowedMoves(whiteBishopCoords, board);
        List<Move> blackMoves = blackBishop.getAllowedMoves(blackBishopCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whiteBishopCoords, blackBishopCoords));
        assertThat(blackMoves).doesNotContain(new Move(blackBishopCoords, whiteBishopCoords));
    }
}
