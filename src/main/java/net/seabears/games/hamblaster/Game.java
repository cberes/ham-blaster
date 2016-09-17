package net.seabears.games.hamblaster;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

  public static final String GAME_NAME = "Winnie Land";

  public static final int MENU = 0;
  public static final int PLAY = 1;

  public Game() {
    super(GAME_NAME);
    addState(new Menu(MENU));
    addState(new Play(PLAY));
  }

  @Override
  public void initStatesList(GameContainer container) throws SlickException {
    getState(MENU).init(container, this);
    getState(PLAY).init(container, this);
    enterState(MENU);
  }

  public static void main(String[] args) {
    AppGameContainer container;
    try {
      container = new AppGameContainer(new Game());
      container.setDisplayMode(640, 360, false);
      container.start();
    } catch (SlickException e) {
      e.printStackTrace();
    }
  }
}
