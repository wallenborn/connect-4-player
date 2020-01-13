/**************************************************************************
 * Project     : connect-4-player
 * File        : Evaluator.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Evaluator.
 *
 */
@Component
public class Evaluator {

  private static final Logger logger = LoggerFactory.getLogger(Evaluator.class);
  
  private Random rnd = new Random();
  
  
  public Integer findMove(Board board, Player player) {
    List<Integer> moves = new ArrayList<>();
    for (int s = 0; s < Board.NUM_COLS; s++) {
      if (!slotIsFree(board, s)) {
        moves.add(-100);
      } else {
        Board bb = new Board(board);
        bb.addStone(s, player);
        Player winner = findWinner(bb);
        if (winner == player) {
          logger.info("Evaluator sees a winning move for Player {}: slot {}", player.getName(), s);
          moves.add(100);
        } else if (winner == null) {
          moves.add(0);
        } else {
          logger.info("Evaluator sees a losing move for Player {}: slot {}", player.getName(), s);
          moves.add(-100);
        }
      }
    }    
    return bestMove(moves);
  }
  
  
  /**
   * @param moves
   * @return
   */
  private Integer bestMove(List<Integer> moves) {
    int score = 0;
    for (Integer i: moves) {
      if (i > score) {
        score = i;
      }
    }
    List<Integer> candidates = new ArrayList<>();
    for (int i = 0; i < moves.size(); i++) {
      if (moves.get(i) == score) {
        candidates.add(i);
      }
    }
    Integer i = rnd.nextInt(candidates.size());
    return candidates.get(i);
    }
  


  /**
   * @param s
   * @return
   */
  private boolean slotIsFree(Board board, int s) {
    return (board.get(0, s) == 0);
  }


  /**
   * @return
   */
  public Integer randomMove(Board board) {
    List<Integer> availableSlots = new ArrayList<>();
    for (int s = 0; s < Board.NUM_COLS; s++) {
      if (board.get(0, s) == 0) {
        availableSlots.add(s);
      }
    }
    if (availableSlots.size() > 0) {
      Integer i = rnd.nextInt(availableSlots.size());
      return board.slotPosition(availableSlots.get(i));
    }
    return null;
  }
  
  
  
  public Player findWinner(Board board) {
    //board.printBoard();
    for (int r = 0; r < Board.NUM_ROWS; r++) {
      for (int s = 0; s < Board.NUM_COLS - 3; s++) {
        if (horizontalFour(board, r, s, Player.ONE.getTag()))
          return Player.ONE;
        else if (horizontalFour(board, r, s, Player.TWO.getTag()))
          return Player.TWO;
      }
    }
    for (int r = 0; r < Board.NUM_ROWS - 3; r++) {
      for (int s = 0; s < Board.NUM_COLS; s++) {
        if (verticalFour(board, r,  s, Player.ONE.getTag()))
          return Player.ONE;
        else if (verticalFour(board, r,  s, Player.TWO.getTag()))
          return Player.TWO;
      }
    }
    for (int r = 0; r < Board.NUM_ROWS - 3; r++) {
      for (int s = 0; s < Board.NUM_COLS - 3; s++) {
        if (diagonalFourDown(board, r,  s, Player.ONE.getTag()))
          return Player.ONE;
        else if (diagonalFourDown(board, r,  s, Player.TWO.getTag()))
          return Player.TWO;
      }
    }
    for (int r = 3; r < Board.NUM_ROWS; r++) {
      for (int s = 0; s < Board.NUM_COLS - 3; s++) {
        if (diagonalFourUp(board, r,  s, Player.ONE.getTag()))
          return Player.ONE;
        else if (diagonalFourUp(board, r,  s, Player.TWO.getTag()))
          return Player.TWO;
      }
    }
    return null; // undecided
  }


  
  /**
   * @param r
   * @param s
   * @param player
   * @return
   */
  private boolean horizontalFour(Board board, int r, int s, int player) {
    if (board.get(r, s) == player 
      && board.get(r,  s + 1) == player 
      && board.get(r,  s + 2) == player 
      && board.get(r,  s + 3) == player) {
     // logger.info("Found horizontal 4 at {},{} for player {}", r, s, player);
      return true;
    } else {
      return false;
    }
  }
  
  /**
   * @param r
   * @param s
   * @param player
   * @return
   */
  private boolean verticalFour(Board board, int r, int s, int player) {
    if (board.get(r, s) == player 
      && board.get(r + 1,  s) == player 
      && board.get(r + 2,  s) == player 
      && board.get(r + 3,  s) == player) {
    //  logger.info("Found vertical 4 at {},{} for player {}", r, s, player);
      return true;
    } else {
      return false;
    }
    }
  
  /**
   * @param r
   * @param s
   * @param player
   * @return
   */
  private boolean diagonalFourDown(Board board, int r, int s, int player) {
    if (board.get(r, s) == player 
      && board.get(r + 1,  s + 1) == player 
      && board.get(r + 2,  s + 2) == player 
      && board.get(r + 3,  s + 3) == player) {
  //    logger.info("Found diagonal 4 down at {},{} for player {}", r, s, player);
      return true;
    } else {
      return false;
    } 
  }
  
  /**
   * @param r
   * @param s
   * @param player
   * @return
   */
  
  private boolean diagonalFourUp(Board board, int r, int s, int player) {
    if (board.get(r, s) == player 
      && board.get(r - 1,  s + 1) == player 
      && board.get(r - 2,  s + 2) == player 
      && board.get(r - 3,  s + 3) == player) {
    //  logger.info("Found diagonal 4 up at {},{} for player {}", r, s, player);
      return true;
    } else {
      return false;
    } 
  }
}
