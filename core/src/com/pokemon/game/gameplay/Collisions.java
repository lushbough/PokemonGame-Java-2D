package com.pokemon.game.gameplay;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.pokemon.game.GameConstants;
import com.pokemon.game.gameObjects.Player;

public class Collisions {

    public static void checkBorders(Player player){
        if(player.getX() < 0 ) {
            player.setX(player.getX() + player.getSpeed());
        }
        if(player.getX() > GameConstants.MAPWIDTH) {
            player.setX(player.getX() - player.getSpeed());
        }
        if(player.getY() > GameConstants.MAPHEIGHT) {
            player.setY(player.getY() - player.getSpeed());
        }
        if(player.getY() < 0) {
            player.setY(player.getY() + player.getSpeed());
        }
    }

    public static void checkBlockedCollisions(Player player){

        float oldX = player.getX();
        float oldY = player.getY();
        float tileWidth = player.getCollisionLayer().getTileWidth();
        float tileHeight = player.getCollisionLayer().getTileHeight();
        boolean collisionX = false;
        boolean collisionY = false;
        int playerX = (int) (player.getX() / tileWidth);
        int playerY = (int) (player.getY() / tileHeight);
        boolean speedStatus;

        player.setX(player.getX() + player.getVelocity().x * Gdx.graphics.getDeltaTime()); //reposition with frames
        player.setY(player.getY() + player.getVelocity().y * Gdx.graphics.getDeltaTime());

        //Because I'm using if statements, it prioritzes up and left in checking collision

        if(player.getVelocity().x < 0) {
            collisionX = isCellBlocked(playerX - 1, playerY , player);
            if(!collisionX) collisionX = isCellBlocked(playerX - 1, playerY -1, player);
            if(!collisionX) collisionX = isCellBlocked(playerX - 1, playerY + 1, player);

        } else if(player.getVelocity().x > 0) {
            collisionX = isCellBlocked(playerX + 1, playerY, player);
            if(!collisionX) collisionX = isCellBlocked(playerX + 1, playerY -1, player);
            if(!collisionX) collisionX = isCellBlocked(playerX + 1, playerY + 1, player);
        }

        if(collisionX) {
            player.setX(oldX);
            player.setY(oldY);
            player.getVelocity().x = 0;
        }

        if (player.getVelocity().y < 0) {
            collisionY = isCellBlocked(playerX , playerY - 1, player);
            if(!collisionY) collisionY = isCellBlocked(playerX - 1, playerY -1, player);
            if(!collisionY) collisionY = isCellBlocked(playerX + 1, playerY - 1, player);



        }else if (player.getVelocity().y > 0) {
            //top left
            collisionY = isCellBlocked(playerX , playerY + 1, player);
            if(!collisionY) collisionY = isCellBlocked(playerX + 1, playerY + 1, player);
            if(!collisionX) collisionY = isCellBlocked(playerX - 1, playerY + 1, player);
        }

        if(collisionY) {
            player.setY(oldY);
            player.setX(oldX);
            player.getVelocity().y = 0;
        }

    }

    public static boolean isCellBlocked(float x, float y, Player player){
        TiledMapTileLayer.Cell cell = player.getCollisionLayer().getCell((int)x, (int) y);
        if (cell == null) return false;            // not sure if this is needed
        if (cell.getTile() == null) return false;  // probably only tiles can be null

        MapProperties properties = cell.getTile().getProperties();
        if (properties == null) return false;      // also not sure if it can be null

        return properties.containsKey("blocked");    }

}
