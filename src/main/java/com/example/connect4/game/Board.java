/**************************************************************************
 * Project     : connect-4-player
 * File        : Board.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.game;

import com.example.connect4.json.BoardResponse;
import com.example.connect4.json.Stone;

/**
 * Board.
 *
 */
public class Board {

  protected static final int NUM_COLS = 7;
  protected static final int NUM_ROWS = 6;
  
  private Integer[][] field = new Integer[NUM_ROWS][NUM_COLS];
  
  BoardResponse currentBoard;
  
  /**
   * @param boardResponse
   */
  public Board(Board board) {
    int i = 0;
    for (int r = 0 ; r < NUM_ROWS; r++) {
      for (int s = 0; s < NUM_COLS; s++) {
       field[r][s] = board.get(r, s);
      }
    }
   }
  
  /**
   * @param boardResponse
   */
  public Board(BoardResponse boardResponse) {
    this.currentBoard = boardResponse;
    int i = 0;
    Integer[] responseField = boardResponse.getField();
    for (int r = 0 ; r < NUM_ROWS; r++) {
      for (int s = 0; s < NUM_COLS; s++) {
        field[r][s] = responseField[i++];
      }
    }
   }

  public void printBoard() {
    System.out.println("-----------------------------");
    for (int r = 0 ; r < NUM_ROWS; r++) {
      System.out.print('|');
      for (int s = 0; s < NUM_COLS; s++) {
        System.out.print(" " + field[r][s] + " |");
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
   * @param integer
   * @return
   */
  protected Integer slotPosition(Integer s) {
    for (int r = NUM_ROWS - 1; r >= 0; r--) {
      if (field[r][s] == 0) {
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
  
  public Integer get(int r, int s) {
    return field[r][s];
  }

  /**
   * @param s
   */
  public void addStone(int s, Player player) {
    for (int r = NUM_ROWS - 1; r >= 0; r--) {
      if (field[r][s] == 0) {
        field[r][s] = player.getTag();
        return;
      }
    }   
  }
}
