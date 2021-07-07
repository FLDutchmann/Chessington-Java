package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class QueenTest {
    @Test
    public void blackQueenCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void whiteQueenCanMoveDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(diff, diff)));
        }
        for(int diff = 1; diff < 5; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(diff, -diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-diff, diff)));
        }
        for(int diff = 1; diff < 4; diff++) {
            assertThat(moves).contains(new Move(queenCoords, queenCoords.plus(-diff, -diff)));
        }
    }

    @Test
    public void queenCannotMovePastPieceDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(3, 3);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(5, 5);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        for(int diff = 3; diff < 5; diff++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteQueenCoords, whiteQueenCoords.plus(diff, diff)));
        for(int diff = 3; diff < 6; diff++)
            assertThat(blackMoves).doesNotContain(new Move(blackQueenCoords, blackQueenCoords.plus(-diff, -diff)));
    }


    @Test
    public void queenCanTakePieceOfDifferentColorDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(3, 3);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(5, 5);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteQueenCoords, blackQueenCoords));
        assertThat(blackMoves).contains(new Move(blackQueenCoords, whiteQueenCoords));
    }

    @Test
    public void queenCannotTakePieceOfSameColorDiagonally() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new Queen(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 3);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Queen(PlayerColour.WHITE);
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
    public void blackQueenCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int col = 0; col < 8; col++) if(col != queenCoords.getCol())
            assertThat(moves).contains(new Move(queenCoords, new Coordinates(3, col)));
    }

    @Test
    public void whiteQueenCanMoveHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int col = 0; col < 8; col++) if(col != queenCoords.getCol())
            assertThat(moves).contains(new Move(queenCoords, new Coordinates(3, col)));
    }

    @Test
    public void blackQueenCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.BLACK);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int row = 0; row < 8; row++) if(row != queenCoords.getRow())
            assertThat(moves).contains(new Move(queenCoords, new Coordinates(row, 4)));
    }

    @Test
    public void whiteQueenCanMoveVertically() {
        // Arrange
        Board board = Board.empty();
        Piece queen = new Queen(PlayerColour.WHITE);
        Coordinates queenCoords = new Coordinates(3, 4);
        board.placePiece(queenCoords, queen);

        // Act
        List<Move> moves = queen.getAllowedMoves(queenCoords, board);

        // Assert
        for(int row = 0; row < 8; row++) if(row != queenCoords.getRow())
            assertThat(moves).contains(new Move(queenCoords, new Coordinates(row, 4)));
    }

    @Test
    public void queenCannotMovePastPieceHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(3, 3);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(3, 5);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        for(int col = 6; col < 8; col++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteQueenCoords, new Coordinates(3, col)));
        for(int col = 0; col < 3; col++)
            assertThat(blackMoves).doesNotContain(new Move(blackQueenCoords, new Coordinates(3, col)));
    }

    @Test
    public void queenCannotMovePastPieceVertically() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(3, 1);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(5, 1);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        for(int row = 6; row < 8; row++)
            assertThat(whiteMoves).doesNotContain(new Move(whiteQueenCoords, new Coordinates(row, 1)));
        for(int row = 0; row < 3; row++)
            assertThat(blackMoves).doesNotContain(new Move(blackQueenCoords, new Coordinates(row, 1)));
    }

    @Test
    public void queenCanTakePieceOfDifferentColorHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece whiteQueen = new Queen(PlayerColour.WHITE);
        Coordinates whiteQueenCoords = new Coordinates(3, 1);
        board.placePiece(whiteQueenCoords, whiteQueen);

        Piece blackQueen = new Queen(PlayerColour.BLACK);
        Coordinates blackQueenCoords = new Coordinates(5, 1);
        board.placePiece(blackQueenCoords, blackQueen);

        // Act
        List<Move> whiteMoves = whiteQueen.getAllowedMoves(whiteQueenCoords, board);
        List<Move> blackMoves = blackQueen.getAllowedMoves(blackQueenCoords, board);

        // Assert
        assertThat(whiteMoves).contains(new Move(whiteQueenCoords, blackQueenCoords));
        assertThat(blackMoves).contains(new Move(blackQueenCoords, whiteQueenCoords));
    }

    @Test
    public void queenCannotTakePieceOfSameColorHorizontally() {
        // Arrange
        Board board = Board.empty();
        Piece whitePiece = new Queen(PlayerColour.WHITE);
        Coordinates whitePieceCoords = new Coordinates(3, 1);
        board.placePiece(whitePieceCoords, whitePiece);

        Piece otherPiece = new Queen(PlayerColour.WHITE);
        Coordinates otherPieceCoords = new Coordinates(5, 1);
        board.placePiece(otherPieceCoords, otherPiece);

        // Act
        List<Move> whiteMoves = whitePiece.getAllowedMoves(whitePieceCoords, board);
        List<Move> othersMoves = otherPiece.getAllowedMoves(otherPieceCoords, board);

        // Assert
        assertThat(whiteMoves).doesNotContain(new Move(whitePieceCoords, otherPieceCoords));
        assertThat(othersMoves).doesNotContain(new Move(otherPieceCoords, whitePieceCoords));
    }
}
