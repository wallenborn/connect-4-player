/**************************************************************************
 * Project     : connect-4-player
 * File        : Board.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.connect4.json.BoardResponse;
import com.example.connect4.json.Stone;

/**
 * Board.
 *
 */
public class Board {

  private static final int NUM_COLS = 7;
  private static final int NUM_ROWS = 6;
  
  private Integer[][] board = new Integer[NUM_ROWS][NUM_COLS];
  
  BoardResponse currentBoard;
  
  Random rnd = new Random();
  
  /**
   * @param boardResponse
   */
  public Board(BoardResponse boardResponse) {
    this.currentBoard = boardResponse;
    int i = 0;
    Integer[] field = boardResponse.getField();
    for (int r = 0 ; r < NUM_ROWS; r++) {
      for (int s = 0; s < NUM_COLS; s++) {
        board[r][s] = field[i++];
      }
    }
   }

  public void printBoard() {
    System.out.println("-----------------------------");
    for (int r = 0 ; r < NUM_ROWS; r++) {
      System.out.print('|');
      for (int s = 0; s < NUM_COLS; s++) {
        System.out.print(" " + board[r][s] + " |");
      }
      System.out.println();
    }
    System.out.println("-----------------------------");
    
  }

  /**
   * @return
   */
  public String toMove() {
    return currentBoard.getTurn();
  }

  /**
   * @return
   */
  public Integer randomMove() {
    List<Integer> availableSlots = new ArrayList<>();
    for (int s = 0; s < NUM_COLS; s++) {
      if (board[0][s] == 0) {
        availableSlots.add(s);
      }
    }
    if (availableSlots.size() > 0) {
      Integer i = rnd.nextInt(availableSlots.size());
      return slot(availableSlots.get(i));
    }
    return null;
  }

  /**
   * @param integer
   * @return
   */
  private Integer slot(Integer s) {
    for (int r = NUM_ROWS - 1; r >= 0; r--) {
      if (board[r][s] == 0) {
        return position(r,s);
      }
    }
    return null;
  }

  /**
   * @param numRows
   * @param s
   * @return
   */
  private Integer position(int r, int s) {
    return r * NUM_COLS + s;
  }

  /**
   * @return
   */
  public String getStatus() {
    return currentBoard.getStatus();
  }

  /**
   * @return
   */
  public Stone[] getWin() {
    return currentBoard.getWin();
  }
  
}
