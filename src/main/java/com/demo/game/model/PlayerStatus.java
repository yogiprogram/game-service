package com.demo.game.model;

public enum PlayerStatus {
  PLAYER_ONE_TURN,
  PLAYER_TWO_TURN,
  PLAYER_ONE_WIN,
  PLAYER_TWO_WIN;

  public static boolean isPlayerOne(PlayerStatus playerPlayerStatus) {
    return PlayerStatus.PLAYER_ONE_TURN.equals(playerPlayerStatus);
  }

  public static boolean isPlayerTwo(PlayerStatus playerPlayerStatus) {
    return PlayerStatus.PLAYER_TWO_TURN.equals(playerPlayerStatus);
  }
}
