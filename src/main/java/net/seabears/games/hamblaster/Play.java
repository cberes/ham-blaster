package net.seabears.games.hamblaster;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {
  private static final float SPEED_X = 0.1f;
  private static final float SPEED_Y = 0.1f;

  private final int id;

  private Animation winnieCurrent, winnieUp, winnieDown, winnieLeft, winnieRight;
  private Image map;
  private boolean quit;
  private int[] duration = new int[] {200, 200};
  private float winnieX, winnieY;
  // put Winnie in the middle of the map
  private float shiftX = winnieX + 320 - 50;
  private float shiftY = winnieY + 180 - 50;

  public Play(int id) {
    this.id = id;
  }

  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
    final String imageRoot = "src/main/resources/";
    map = new Image(imageRoot + "map.png");

    // animations
    Image[] walkUp = new Image[] {new Image(imageRoot + "winnie-up.png"), new Image(imageRoot + "winnie-up.png")};
    Image[] walkDown = new Image[] {new Image(imageRoot + "winnie-down.png"), new Image(imageRoot + "winnie-down.png")};
    Image[] walkLeft = new Image[] {new Image(imageRoot + "winnie-left.png"), new Image(imageRoot + "winnie-left.png")};
    Image[] walkRight = new Image[] {new Image(imageRoot + "winnie-right.png"), new Image(imageRoot + "winnie-right.png")};
    winnieUp = new Animation(walkUp, duration, false);
    winnieDown = new Animation(walkDown, duration, false);
    winnieLeft = new Animation(walkLeft, duration, false);
    winnieRight = new Animation(walkRight, duration, false);
    winnieCurrent = winnieDown;
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g)
      throws SlickException {
    g.drawImage(map, winnieX, winnieY);
    g.drawAnimation(winnieCurrent, shiftX, shiftY);

    if (quit) {
      g.drawString("Resume (R)", 250, 100);
      g.drawString("Main Menu (M)", 250, 150);
      g.drawString("Quit Game (Q)", 250, 200);
      if (!quit) {
        g.clear();
      }
    }
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta)
      throws SlickException {
    final Input input = container.getInput();
    if (!quit) {
      /*
       * Movement
       */
      if (input.isKeyDown(Input.KEY_UP)) {
        winnieCurrent = winnieUp;
        if (winnieY < 130f) {
          winnieY += delta * SPEED_Y;
        }
      }
      if (input.isKeyDown(Input.KEY_DOWN)) {
        winnieCurrent = winnieDown;
        if (winnieY > -970f) {
          winnieY -= delta * SPEED_Y;
        }
      }
      if (input.isKeyDown(Input.KEY_LEFT)) {
        winnieCurrent = winnieLeft;
        if (winnieX < 270f) {
          winnieX += delta * SPEED_X;
        }
      }
      if (input.isKeyDown(Input.KEY_RIGHT)) {
        winnieCurrent = winnieRight;
        if (winnieX > -1230f) {
          winnieX -= delta * SPEED_X;
        }
      }

      /*
       * Show Menu
       */
      if (input.isKeyPressed(Input.KEY_ESCAPE)) {
        quit = true;
      }
    } else {
      /*
       * Menu Options
       */
      if (input.isKeyPressed(Input.KEY_R)) {
        quit = false;
      } else if (input.isKeyPressed(Input.KEY_M)) {
        quit = false;
        game.enterState(Game.MENU);
      } else if (input.isKeyPressed(Input.KEY_Q)) {
        System.exit(0);
      }
    }
  }

  @Override
  public int getID() {
    return id;
  }
}
