package com.demo.game.controller;

import com.demo.game.api.GamesApi;
import com.demo.game.api.model.Game;
import com.demo.game.api.model.PlayStatus;
import com.demo.game.service.KalahaGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GamesApi {

    @Autowired
    private KalahaGameService kalahaGameService;

    @Override
    public ResponseEntity<Game> createGame() {
        return new ResponseEntity(kalahaGameService.createGame(), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<PlayStatus> makePitMove(@PathVariable("game_id") Integer gameId, @PathVariable("pit_id") Integer pitId) {
        return new ResponseEntity(kalahaGameService.play(gameId, pitId), HttpStatus.OK);
    }
}