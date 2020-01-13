/**************************************************************************
 * Project     : connect-4-player
 * File        : TurnRequest.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.json;

import org.springframework.stereotype.Component;

/**
 * TurnRequest.
 *
 */
@Component
public class TurnRequest {

  private String turn;
  
  private Integer position;

  
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
   * @return the position
   */
  public Integer getPosition() {
    return position;
  }

  
  /**
   * @param position the position to set
   */
  public void setPosition(Integer position) {
    this.position = position;
  }
  
  
}
