package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.Pawn;
import training.chessington.model.pieces.Piece;

import java.util.List;

import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    public void newBoardHasWhitePiecesAtBottom() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(7, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.WHITE);
    }

    @Test
    public void newBoardHasBlackPiecesAtTop() {
        // Arrange
        Board board = Board.forNewGame();

        // Act
        Piece piece = board.get(new Coordinates(0, 0));

        // Assert
        assertThat(piece).isColour(PlayerColour.BLACK);
    }

    @Test
    public void canMovePiecesOnBoard() {
        // Arrange
        Board board = Board.forNewGame();

        Coordinates from = new Coordinates(6, 0);
        Coordinates to = new Coordinates(4, 4);

        // Act
        board.move(from, to);

        // Assert
        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isColour(PlayerColour.WHITE).isPiece(PAWN);
    }

    @Test
    public void whiteTakenAfterEnPassant() {
        // Arrange
        Board board = Board.empty();

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(4, 4);
        board.placePiece(blackCoords, blackPawn);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(6, 5);
        board.placePiece(whiteCoords, whitePawn);

        // Act
        board.move(whiteCoords, whiteCoords.plus(-2, 0));
        board.move(blackCoords, blackCoords.plus(1, 1));

        // Assert
        assertThat(board.get(whiteCoords.plus(-2, 0))).isNull();
    }

    @Test
    public void blackTakenAfterEnPassant() {
        // Arrange
        Board board = Board.empty();

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(1, 4);
        board.placePiece(blackCoords, blackPawn);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(3, 5);
        board.placePiece(whiteCoords, whitePawn);

        // Act
        board.move(blackCoords, blackCoords.plus(2, 0));
        board.move(whiteCoords, whiteCoords.plus(-1, -1 ));

        // Assert
        assertThat(board.get(blackCoords.plus(2, 0))).isNull();
    }
}