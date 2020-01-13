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

import org.springframework.stereotype.Component;

/**
 * Evaluator.
 *
 */
@Component
public class Evaluator {

  private Random rnd = new Random();
  
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
  
  
  
  public String evaluateBoard(Board board) {
    for (int r = 0; r < Board.NUM_ROWS; r++) {
      for (int s = 0; s < Board.NUM_COLS - 4; s++) {
        if (horizontalFour(board, r, s,1))
          return "player_1";
        else if (horizontalFour(board, r, s,2))
          return "player_2";
      }
    }
    for (int r = 0; r < Board.NUM_ROWS - 4; r++) {
      for (int s = 0; s < Board.NUM_COLS; s++) {
        if (verticalFour(board, r,  s, 1))
          return "player_1";
        else if (verticalFour(board, r,  s, 2))
          return "player_2";
      }
    }
    for (int r = 0; r < Board.NUM_ROWS - 4; r++) {
      for (int s = 0; s < Board.NUM_COLS - 4; s++) {
        if (diagonalFourDown(board, r,  s, 1))
          return "player_1";
        else if (diagonalFourDown(board, r,  s, 2))
          return "player_2";
      }
    }
    for (int r = 3; r < Board.NUM_ROWS; r++) {
      for (int s = 0; s < Board.NUM_COLS - 4; s++) {
        if (diagonalFourUp(board, r,  s, 1))
          return "player_1";
        else if (diagonalFourUp(board, r,  s, 2))
          return "player_2";
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
      && board.get(r,  s + 3) == player)
      return true;
    else
      return false;
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
      && board.get(r + 3,  s) == player)
      return true;
    else
      return false;
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
      && board.get(r + 3,  s + 3) == player)
      return true;
    else
      return false;
  }  /**
   * @param r
   * @param s
   * @param player
   * @return
   */
  
  private boolean diagonalFourUp(Board board, int r, int s, int player) {
    if (board.get(r, s) == player 
      && board.get(r - 1,  s + 1) == player 
      && board.get(r - 2,  s + 2) == player 
      && board.get(r - 3,  s + 3) == player)
      return true;
    else
      return false;
  }
}
