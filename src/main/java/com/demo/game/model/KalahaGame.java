package com.demo.game.model;

import com.demo.game.Constant;
import lombok.Data;

@Data
public class KalahaGame {
  private int id;
  private int[] board;
  private PlayerStatus playerStatus;
  private KalahPlayer player1;
  private KalahPlayer player2;

  public KalahaGame(int gameId) {
    this.id = gameId;
    int[] board = new int[Constant.noOfPits];
    for (int i = 0; i < board.length; i++) {
      if (i == 6 || i == 13) {
        continue;
      }
      board[i] = Constant.noOfPeebles;
    }
    this.board = board;
    this.player1 = new KalahPlayer(1, 6);
    this.player2 = new KalahPlayer(2, 13);
    this.playerStatus = PlayerStatus.PLAYER_ONE_TURN;
  }
}
