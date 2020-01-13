/**************************************************************************
 * Project     : connect-4-player
 * File        : EvaluatorTest.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.connect4.Board;
import com.example.connect4.TestApplicationConfiguration;
import com.example.connect4.json.Stone;
import com.example.connect4.player.Player;

/**
 * EvaluatorTest.
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
public class EvaluatorTest {
  
  private static final Logger logger = LoggerFactory.getLogger(EvaluatorTest.class);

  @Autowired
  private Player player;

  private Board board;
  
  @Test
  public void testHasPlayer() {
    assertNotNull(player);
  }
  

  @PostConstruct
  public void init() {
    board = player.readBoard();
  }
  
  @Test
  public void testHasBoard() {
    assertNotNull(board);
    
  }
  
  @Test
  public void testGameIsFinished() {
    assertEquals("finished", board.getStatus());
  }
  
  @Test
  public void testGameHasWinner() {
    board.printBoard();
    Stone[] win = board.getWin();
    assertNotNull(win);
    assertNotEquals(0, win.length);
    List<String> winner= new ArrayList<>();
    for (Stone s: win) {
      winner.add(s.getPosition().toString());
    }
    logger.info("Winning stones: {}", String.join(", ", winner));
  }
  
}