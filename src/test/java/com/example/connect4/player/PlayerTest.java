/**************************************************************************
 * Project     : connect-4-player
 * File        : PlayerTest.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.connect4.TestApplicationConfiguration;
import com.example.connect4.game.Board;

/**
 * PlayerTest.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class PlayerTest {

  @Autowired
  private Player player;

  
  @Test
  public void testHasPlayer() {
    assertNotNull(player);
  }
  
  @Test
  public void canStartNewGame() {
    String status = player.startNewGame();
    assertEquals("running", status);
  }
  
  @Test
  public void canReadBoard() {
    Board board = player.readBoard();
    assertNotNull(board);
    board.printBoard();
  }
  
  
}
