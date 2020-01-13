/**************************************************************************
 * Project     : connect-4-player
 * File        : Player.java
 * Language    : Java
 * Platform    : J2EE
 * Author      : wallenborn
 * Created     : 13.01.2020
 * Synopsis    : 
 **************************************************************************/

package com.example.connect4.player;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.connect4.Board;
import com.example.connect4.json.BoardResponse;
import com.example.connect4.json.StartResponse;
import com.example.connect4.json.TurnRequest;

/**
 * Player.
 *
 */
@Component
public class Player {
  
  private static final Logger logger = LoggerFactory.getLogger(Player.class);

  @Autowired
  private RestTemplate rest;
  
  private String player;
  
  @Value("${application.startUrl}")
  private String startUrl;

  @Value("${application.boardUrl}")
  private String boardUrl;
  
  @Value("${application.turnUrl}")
  private String turnUrl;

  /**
   * 
   */
  public void runGame() {
    String status = startNewGame();
    Board board = readBoard();
    status = board.getStatus();
    board.printBoard();
    while ("running".equals(status)) {
      String player = board.toMove();
      Integer nextMove = board.randomMove();
      turn(player, nextMove);
      board = readBoard();      
      status = board.getStatus();
      board.printBoard();
      try {
        Thread.sleep(500);
      } catch (InterruptedException e) {
        logger.info("Interrupted");
      }
    }
  }

  /**
   * 
   */
  public String startNewGame() {
    logger.info("New game");
    logger.info("Connecting to {}", startUrl);
    StartResponse response = rest.getForObject(startUrl, StartResponse.class);
    logger.info("Status is {}", response.getStatus());
    return response.getStatus();
  }

  /**
   * @return
   */
  public Board readBoard() {
    BoardResponse response = rest.getForObject(boardUrl, BoardResponse.class);
    logger.info("Response is {}", response.getStatus());
    Board board = new Board(response);
    return board;
  }
  
  public void turn(String player, Integer nextMove) {
    logger.info("Player {} moves to position {}", player, nextMove);
    TurnRequest request = new TurnRequest();
    request.setTurn(player);
    request.setPosition(nextMove);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<TurnRequest> entity = new HttpEntity<TurnRequest>(request ,headers);
    String response = rest.postForObject(turnUrl, request, String.class);
    logger.info("Response is {}", response);
  }

}
