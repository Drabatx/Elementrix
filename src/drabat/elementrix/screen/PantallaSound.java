package drabat.elementrix.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;
import drabat.elementrix.game.Assets;

public class PantallaSound implements Screen{

	Game game;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	BoundingBox SonidoBound;

	Vector3 touchPoint;
	public static String Sonido="ON";
	private BoundingBox AtrasBound;
	//boolean ban=true;
	public PantallaSound(Game game) 
	{
		this.game=game;
		guiCam=new OrthographicCamera(100,150);
		guiCam.position.set(100f/2, 150f/2, 0);
		batcher=new SpriteBatch();
		Assets.font.setScale(.3f);
		SonidoBound=new BoundingBox(new Vector3(40, 55, 0), new Vector3(60,80,0));
		AtrasBound=new BoundingBox(new Vector3(80, 0, 0), new Vector3(100,20,0));
		touchPoint= new Vector3();

	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		batcher.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float arg0) {
		
		if (Gdx.input.justTouched()) 
		{
			guiCam.unproject(touchPoint.set(Gdx.input.getX(),Gdx.input.getY(), 0));
			if (SonidoBound.contains(touchPoint)) 
			{
				if(Sonido.equals("ON"))
				{
					Assets.music.pause();
					Sonido="OFF";
					drabat.elementrix.game.Settings.soundEnabled=false;
					return;
				}
				if(Sonido.equals("OFF"))
				{
					Assets.music.play();
					Sonido="ON";
					drabat.elementrix.game.Settings.soundEnabled=true;
					return;
				}

			}
			if(AtrasBound.contains(touchPoint))
			{
				Assets.font.scale(1);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
		
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0,0,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		
		
		
		batcher.begin();
		batcher.draw(Assets.backMadera, 0, 0, 100, 150);
		batcher.end();
		
		
		batcher.begin();
		Assets.font.draw(batcher, "SONIDO ", 0, 150);
		batcher.end();
		
		batcher.begin();
		Assets.font.draw(batcher, ""+Sonido, 40, 80);
		batcher.end();
		
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.Atras, 80, 0, 20, 20);
		batcher.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}