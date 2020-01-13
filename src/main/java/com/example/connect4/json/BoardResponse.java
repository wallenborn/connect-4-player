/**************************************************************************
 * Project     : connect-4-player
 * File        : BoardResponse.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.json;

import org.springframework.stereotype.Component;

/**
 * BoardResponse.
 *
 */
@Component
public class BoardResponse {

  private Integer[] field;
  private String turn;
  private Integer moves;
  private String status;
  private Stone[] win;
  
  /**
   * @return the field
   */
  public Integer[] getField() {
    return field;
  }
  
  /**
   * @param field the field to set
   */
  public void setField(Integer[] field) {
    this.field = field;
  }
  
  /**
   * @return the turn
   */
  public String getTurn() {
    return turn;
  }
  
  /**
   * @param turn the turn to set
   */
  public void setTurn(String turn) {
    this.turn = turn;
  }
  
  /**
   * @return the moves
   */
  public Integer getMoves() {
    return moves;
  }
  
  /**
   * @param moves the moves to set
   */
  public void setMoves(Integer moves) {
    this.moves = moves;
  }
  
  /**
   * @return the status
   */
  public String getStatus() {
    return status;
  }
  
  /**
   * @param status the status to set
   */
  public void setStatus(String status) {
    this.status = status;
  }

  
  /**
   * @return the win
   */
  public Stone[] getWin() {
    return win;
  }

  
  /**
   * @param win the win to set
   */
  public void setWin(Stone[] win) {
    this.win = win;
  }
  


}
