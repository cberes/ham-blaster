package net.seabears.games.hamblaster;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {
  private final int id;
  private Image play, exit;

  public Menu(int id) {
    this.id = id;
  }

  @Override
  public void init(GameContainer container, StateBasedGame game) throws SlickException {
    play = new Image("src/main/resources/play.png");
    exit = new Image("src/main/resources/exit.png");
  }

  @Override
  public void render(GameContainer container, StateBasedGame game, Graphics g)
      throws SlickException {
    g.drawString("Welcome to Winnie Land!", 100, 50);
    g.drawImage(play, 100, 100);
    g.drawImage(exit, 100, 200);
  }

  @Override
  public void update(GameContainer container, StateBasedGame game, int delta)
      throws SlickException {
    final int mouseX = Mouse.getX();
    final int mouseY = Mouse.getY();
    if (Mouse.isButtonDown(0)) {
      if (mouseX >= 100 && mouseX <= 300 && mouseY >= 210 && mouseY <= 260) {
        game.enterState(Game.PLAY);
      } else if (mouseX >= 100 && mouseX <= 300 && mouseY >= 110 && mouseY <= 160) {
        System.exit(0);
      }
    }
  }

  @Override
  public int getID() {
    return id;
  }
}
