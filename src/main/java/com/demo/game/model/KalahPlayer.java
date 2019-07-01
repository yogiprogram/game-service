package com.demo.game.model;

import lombok.Data;

@Data
public class KalahPlayer {
    private int id;

    //its Mancala or house
    private int kalaha;

    public KalahPlayer() {
    }

    public KalahPlayer(int id, int kalaha) {
        this.id = id;
        this.kalaha = kalaha;
    }

    public void setKalaha(int kalaha) {
        this.kalaha = kalaha;
    }

    public boolean ownKalaha(int pit) {
        return kalaha == pit;
    }

    public boolean checkOwnPit(int pit) {
        if (id == 1)
            return 0 <= pit && pit <= 5;
        else
            return 7 <= pit && pit <= 12;
    }

}
