/**************************************************************************
 * Project     : connect-4-player
 * File        : Player.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.game;


/**
 * Player.
 *
 */
public enum Player {
 
  ONE("player_1", 1),
  TWO("player_2", 2);

  private String name;
  
  private Integer tag;
  
  
  private Player(String name, int tag) {
    this.name = name;
    this.tag = tag;
  }


  
  /**
   * @return the name
   */
  public String getName() {
    return name;
  }


  
  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }


  
  /**
   * @return the tag
   */
  public Integer getTag() {
    return tag;
  }


  
  /**
   * @param tag the tag to set
   */
  public void setTag(Integer tag) {
    this.tag = tag;
  }
   
  public static Player fromName(String s) {
    for (Player p: Player.values()) {
      if (p.getName().equals(s))
        return p;
    }
    return null;
  }

}
