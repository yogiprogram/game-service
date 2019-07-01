package com.demo.game.service;

import com.demo.game.api.model.Game;
import com.demo.game.api.model.PlayStatus;
import com.demo.game.exception.InvalidPlayerTurnException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class KalahaGameServiceTest {

  @InjectMocks private KalahaGameService kalahaGameService;
  private Game game;

  @Before
  public void setup() {
    game = kalahaGameService.createGame();
  }

  @Test
  public void createGame_success() {
    assertNotNull(game);
    assertNotNull(game.getId());
    assertTrue(game.getUri().endsWith(game.getId().toString()));
  }

  @Test
  public void play_success() {
    Integer gameId = game.getId();
    int pitId = 1;
    PlayStatus playStatus = kalahaGameService.play(gameId, pitId);
    assertNotNull(game);
    assertTrue(playStatus.getUri().endsWith(gameId.toString()));
    assertEquals(gameId, playStatus.getId());
    assertEquals(playStatus.getStatus().get(String.valueOf(pitId)), String.valueOf(0));
  }

  @Test
  public void play_failedWrongPebeels() {
    Integer gameId = game.getId();
    int pitId = 8;
    assertThatThrownBy(() -> kalahaGameService.play(gameId, pitId))
        .isInstanceOf(InvalidPlayerTurnException.class)
        .hasMessageContaining("For Player PLAYER_ONE_TURN not a valid pit 8");
  }
}
