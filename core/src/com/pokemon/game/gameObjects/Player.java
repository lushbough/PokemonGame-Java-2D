package com.pokemon.game.gameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;
import com.pokemon.game.GameConstants;

public class Player extends Sprite implements InputProcessor {
    private Vector2 velocity = new Vector2();
    private float speed = 140;
    private float currentSpeed;
    private float weight = 40;
    private int row, col;

    private TiledMapTileLayer collisionSpot;
//    private static TextureRegion playerLook = mainActor[0][0];


    public Player(Sprite batch, TiledMapTileLayer collisionLayer) {
        super(batch);
        this.collisionSpot = collisionLayer;
//        this.currentSpeed = playerSpeed;
    }

    @Override
    public void draw(Batch batch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(batch);


    }


    public void update(float delta) {
        float oldX = getX();
        float oldY = getY();
        float tileWidth = collisionSpot.getTileWidth();
        float tileHeight = collisionSpot.getTileHeight();
        boolean collisionX = false;
        boolean collisionY = false;

        setX(getX() + this.velocity.x * delta); //reposition with frames


        if(velocity.x < 0) {

            if (!collisionX) {

                try {

                    collisionX = collisionSpot.getCell((int) (getX() / tileWidth), (int) (getY() + getHeight() / tileHeight)).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");

                } catch (Exception e) {
                    System.out.println("null tileeee");
                }
            }


            //left
            if (!collisionX) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / 2 / tileHeight)).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");

                } catch (Exception e) {
                    System.out.println("null tileeee");
                }


            }

            }

            //diagnal bot left
            if (!collisionX) {
                try {
                    collisionY = collisionSpot.getCell((int) (getX() / tileWidth), (int) ((getY() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");

                } catch (Exception e) {
                    System.out.println("null tileeee");
                }


            } else if (velocity.x > 0) {
            //top right

            if(!collisionX) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() + getWidth() / tileWidth), (int) (getY() + getHeight() / tileHeight)).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");

                } catch (Exception e) {
                    System.out.println("null tileeee");


                }
            }
            //middle right
            if (!collisionX) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() + getWidth() / tileWidth), (int) ((getY() + getWidth() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");

                } catch (Exception e) {
                    System.out.println("null tileeee");
                }

            }
            if (!collisionX) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() + getWidth() / tileWidth), (int) ((getY() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");
                } catch (Exception e) {
                    System.out.println("null tileeee");
                }

            }

        }

        if(collisionX) {
            setX(oldX);
            this.velocity.x = 0;
        }

        setY(getY() + this.velocity.y * delta);


        if (velocity.y < 0) {

            if(!collisionY) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() / tileWidth), (int) (getY() / tileHeight)).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");
                } catch (Exception e) {
                    System.out.println("null tileeee");
                }

                //bot left
            } //bot mid
            if (!collisionY) {

                try {
                    collisionY = collisionSpot.getCell((int) ((getX() + getWidth()) / 2 / tileWidth), (int) ((getY() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    System.out.println("collision");
                } catch (Exception e) {
                    System.out.println("null tileeee");
                }

            }

            //bot right
            if (!collisionY) {
                try {
                    collisionY = collisionSpot.getCell((int) (getX() + getWidth() / tileWidth), (int) ((getY() / tileHeight))).getTile().getProperties().containsKey("blocked");
                } catch (Exception e) {
                    System.out.println("null tileeee");
                }

            }
        }else if (velocity.y > 0) {
                //top left

            if(!collisionY) {

                try {
                    collisionY = collisionSpot.getCell((int) (getX() / tileWidth), (int) ((getY() + getHeight()) / tileHeight)).getTile().getProperties().containsKey("blocked");
                } catch (Exception e) {
                    System.out.println("null tileeee");
                }
            }  //top mid
                if (!collisionY) {
                    try {
                        collisionY = collisionSpot.getCell((int) ((getX() + getWidth()) / 2 / tileWidth), (int) ((getY() + getHeight() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    } catch (Exception e) {
                        System.out.println("null tileeee");
                    }

                }

                //top right
                if (!collisionY) {
                    try {
                        collisionY = collisionSpot.getCell((int) ((getX() + getWidth() / 2 / tileWidth)), (int) ((getY() / tileHeight))).getTile().getProperties().containsKey("blocked");
                    } catch (Exception e) {
                        System.out.println("null tileeee");
                    }
                }

            }


        if(collisionY) {
            setY(oldY);
            this.velocity.x = 0;
        }
    }


    public TiledMapTileLayer getCollisionLayer() {
        return collisionSpot;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionSpot = collisionLayer;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public float getWeight() {
        return weight;
    }



    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.W:
                this.setRegion(GameConstants.defaultUpStance);
//                setCurrentSpeed(-getCurrentSpeed());
                this.velocity.y = speed;
                break;
            case Input.Keys.S:
                this.setRegion(GameConstants.defaultDownStance);
                this.velocity.y = -speed;
                break;
            case Input.Keys.A:
                this.setRegion(GameConstants.defaultLeftStance);
                this.velocity.x = -speed;
                break;
            case Input.Keys.D:
                this.setRegion(GameConstants.defaultRightStance);
                this.velocity.x = speed;
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) { switch (keycode) {
        case Input.Keys.W:
        case Input.Keys.S:
            this.velocity.y = 0;
            break;
        case Input.Keys.A:
        case Input.Keys.D:
            this.velocity.x = 0;
            break;
    }
        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
