package drabat.elementrix.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import drabat.elementrix.screen.SplashScreen;

public class GameElementrix	extends Game 
{
	public Screen Splash;	
	@Override
	public void create() 
	{
		//Carga los Recursos al inicio del juego
		Assets.load();
		Splash=new SplashScreen(this);
		setScreen(Splash);
		
	}
	public void render() 
	{
		super.render();
	}
	public void dispose () {
		super.dispose();
		getScreen().dispose();
	}
}
