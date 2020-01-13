/**************************************************************************
 * Project     : connect-4-game
 * File        : gameTest.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.game;

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
 * gameTest.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class GameTest {

  @Autowired
  private Game game;

  
  @Test
  public void testHasgame() {
    assertNotNull(game);
  }
  
  @Test
  public void canStartNewGame() {
    String status = game.startNewGame();
    assertEquals("running", status);
  }
  
  @Test
  public void canReadBoard() {
    Board board = game.readBoard();
    assertNotNull(board);
    board.printBoard();
  }
  
  
}
