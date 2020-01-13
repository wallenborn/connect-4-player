/**************************************************************************
 * Project     : connect-4-player
 * File        : Move.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.game;


/**
 * Move.
 *
 */
public class Move {
  
  private Integer slot;
  
  private Integer value;

  
  public Move(Integer slot, Integer value) {
    this.slot = slot;
    this.value = value;
  }
  /**
   * @return the slot
   */
  public Integer getSlot() {
    return slot;
  }

  
  /**
   * @param slot the slot to set
   */
  public void setSlot(Integer slot) {
    this.slot = slot;
  }

  
  /**
   * @return the value
   */
  public Integer getValue() {
    return value;
  }

  
  /**
   * @param value the value to set
   */
  public void setValue(Integer value) {
    this.value = value;
  }
  
  

}
