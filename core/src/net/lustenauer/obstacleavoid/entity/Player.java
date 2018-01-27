package net.lustenauer.obstacleavoid.entity;


import net.lustenauer.obstacleavoid.config.GameConfig;

/**
 * Created by Patric Hollenstein on 05.01.18.
 *
 * @author Patric Hollenstein
 */
public class Player extends GameObjectBase {


    public Player() {
        super(GameConfig.PLAYER_BOUNDS_RADIUS);
        setSize(GameConfig.PLAYER_SIZE, GameConfig.PLAYER_SIZE);
    }
}
