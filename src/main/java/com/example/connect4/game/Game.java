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


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.connect4.json.BoardResponse;
import com.example.connect4.json.StartResponse;
import com.example.connect4.json.Stone;
import com.example.connect4.json.TurnRequest;

/**
 * Player.
 *
 */
@Component
public class Game {
  
  private static final Logger logger = LoggerFactory.getLogger(Game.class);

  @Autowired
  private RestTemplate rest;
  
  @Autowired
  private Evaluator evaluator;
  
  @Value("${application.player}")
  private String thisPlayer;
  
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
      Player player = Player.fromName(board.toMove());
      logger.info("Player {} to move ", board.toMove());
      if (thisPlayer == null || thisPlayer.trim().length() == 0 || thisPlayer.equals(board.toMove())) {
        Integer nextMove = evaluator.findMove(board, player);
        logger.info("Player plays slot {} ", nextMove);
        turn(player, board.slotPosition(nextMove));
      }
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        logger.info("Interrupted");
      }
      board = readBoard();      
      status = board.getStatus();
      board.printBoard();
    }
    Stone[] win = board.getWin();
    List<String> winner= new ArrayList<>();
    for (Stone s: win) {
      winner.add(s.getPosition().toString());
    }
    logger.info("Winner is: {}", evaluator.findWinner(board));
    logger.info("Winning stones: {}", String.join(", ", winner));
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
    logger.info("Game is {}", response.getStatus());
    Board board = new Board(response);
    return board;
  }
  
  public void turn(Player player, Integer nextMove) {
    logger.info("Player {} moves to position {}", player, nextMove);
    TurnRequest request = new TurnRequest();
    request.setTurn(player.getName());
    request.setPosition(nextMove);
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    HttpEntity<TurnRequest> entity = new HttpEntity<TurnRequest>(request ,headers);
    String response = rest.postForObject(turnUrl, request, String.class);
    logger.info("Turn is {}", response);
  }

}
