package com.demo.game.service;

import com.demo.game.Constant;
import com.demo.game.api.model.Game;
import com.demo.game.api.model.PlayStatus;
import com.demo.game.exception.InvalidPlayerTurn;
import com.demo.game.model.KalahPlayer;
import com.demo.game.model.KalahaGame;
import com.demo.game.model.PlayerStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KalahaGameService {

  private Map<Integer, KalahaGame> kalahaGameMap = new LinkedHashMap<>();
  private AtomicInteger gameSequence = new AtomicInteger(1);

  public Game createGame() {
    int gameId = gameSequence.getAndIncrement();
    kalahaGameMap.put(gameId, new KalahaGame(gameId));
    return new Game().id(gameId).uri(String.format(Constant.uriGame, gameId));
  }

  public PlayStatus play(Integer gameId, Integer pitId) {
    KalahaGame kalahaGame = kalahaGameMap.get(gameId);
    PlayerStatus playerStatus = kalahaGame.getPlayerStatus();
    if (!PlayerStatus.isPlayerOne(playerStatus) && !PlayerStatus.isPlayerTwo(playerStatus)) {
      throw new InvalidPlayerTurn("Game Over and Winner player is  " + playerStatus);
    }
    int pit = pitId.intValue() - 1;
    if (!isValidMove(kalahaGame, pit))
      throw new InvalidPlayerTurn("For Player " + playerStatus + " not a valid pit " + pitId);
    if (PlayerStatus.isPlayerOne(playerStatus)) {
      play(kalahaGame, pit, kalahaGame.getPlayer1());
    } else if (PlayerStatus.isPlayerTwo(playerStatus)) {
      play(kalahaGame, pit, kalahaGame.getPlayer2());
    }
    kalahaGameMap.put(gameId, kalahaGame);
    return createPlayStatus(kalahaGame);
  }

  private PlayStatus createPlayStatus(KalahaGame kalahaGame) {
    Map<String, String> statusMap = new LinkedHashMap<>();
    int board[] = kalahaGame.getBoard();
    for (int i = 0; i < board.length; i++) {
      statusMap.put(String.valueOf(i + 1), String.valueOf(board[i]));
    }
    return new PlayStatus()
        .id(kalahaGame.getId())
        .uri(String.format(Constant.uriGame, kalahaGame.getId()))
        .status(statusMap);
  }

  private boolean isValidMove(KalahaGame kalahaGame, int pit) {
    PlayerStatus playerStatus = kalahaGame.getPlayerStatus();
    if (PlayerStatus.isPlayerOne(playerStatus)) {
      return 0 <= pit && pit <= 5;
    } else if (PlayerStatus.isPlayerTwo(playerStatus)) {
      return 7 <= pit && pit <= 12;
    }
    return false;
  }

  private int collectAllPeeblesStone(int[] board, int pit) {
    int noOfPeeblesInKalaha = board[pit];
    board[pit] = 0;
    return noOfPeeblesInKalaha;
  }

  private int next(int pit) {
    pit += 1;
    return pit % Constant.noOfPits;
  }

  private boolean checkIsKalah(int pit) {
    return pit == 6 || pit == 13;
  }

  private void updateNextTurn(KalahaGame kalahaGame) {
    PlayerStatus playerStatus = kalahaGame.getPlayerStatus();
    if (PlayerStatus.isPlayerOne(playerStatus)) {
      kalahaGame.setPlayerStatus(PlayerStatus.PLAYER_TWO_TURN);
    } else {
      kalahaGame.setPlayerStatus(PlayerStatus.PLAYER_ONE_TURN);
    }
  }

  private void play(KalahaGame kalahaGame, int pit, KalahPlayer player) {
    int[] board = kalahaGame.getBoard();
    int noOfPeeblesInPit = collectAllPeeblesStone(board, pit);
    int lastDropPit = pit;
    while (noOfPeeblesInPit > 0) {
      int dropPit = next(lastDropPit);
      if (checkIsKalah(dropPit) && !player.ownKalaha(dropPit)) {
        lastDropPit = dropPit;
        continue;
      }
      board[dropPit] += 1;
      noOfPeeblesInPit -= 1;
      lastDropPit = dropPit;
    }

    // If the last stone you drop is in an empty cup on your side,
    // capture that piece along with any pieces in the hole directly opposite.
    if (player.checkOwnPit(lastDropPit) && board[lastDropPit] == 1) {
      board[player.getKalaha()] += board[lastDropPit] + board[12 - lastDropPit];
      board[lastDropPit] = 0;
      board[12 - lastDropPit] = 0;
    }
    // If your last stone falls into your Mancala/house, take another turn.
    if (!player.ownKalaha(lastDropPit)) updateNextTurn(kalahaGame);

    findWinner(kalahaGame);
  }

  /**
   * When one player's six cups are completely empty, the game ends. The player who still has stones
   * left in their cups captures those stones and puts them in their Mancala. Players compare the
   * number of stones in their Mancala. The player with the most stones wins
   *
   * @param kalahaGame
   */
  private void findWinner(KalahaGame kalahaGame) {
    int[] board = kalahaGame.getBoard();
    KalahPlayer player1 = kalahaGame.getPlayer1();
    KalahPlayer player2 = kalahaGame.getPlayer2();
    int pitSumSideOne = 0;
    for (int i = 0; i < 6; i++) {
      pitSumSideOne += board[i];
    }
    int pitSumSideTwo = 0;
    for (int i = 7; i < 13; i++) {
      pitSumSideTwo += board[i];
    }
    if (pitSumSideOne == 0 || pitSumSideTwo == 0) {
      board[player1.getKalaha()] += pitSumSideOne;
      board[player2.getKalaha()] += pitSumSideTwo;
      if (board[player1.getKalaha()] > board[player2.getKalaha()]) {
        kalahaGame.setPlayerStatus(PlayerStatus.PLAYER_ONE_WIN);
      } else {
        kalahaGame.setPlayerStatus(PlayerStatus.PLAYER_TWO_WIN);
      }
    }
  }
}
