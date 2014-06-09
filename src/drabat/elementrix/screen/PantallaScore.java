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

public class PantallaScore implements Screen{

	Game game;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	private BoundingBox AtrasBound;
	private Vector3 touchPoint;
	String[] highScore= new String[5];
	public PantallaScore(Game game) 
	{
		this.game=game;
		// TODO Auto-generated constructor stub
		guiCam=new OrthographicCamera(100,150);
		guiCam.position.set(100f/2, 150f/2, 0);
		batcher=new SpriteBatch();
		Assets.font.setScale(.3f);
		AtrasBound=new BoundingBox(new Vector3(80, 0, 0), new Vector3(100,20,0));
		touchPoint= new Vector3();
		
		for (int i = 0; i < 5; i++) 
		{
			highScore[i] = i + 1 + ". " + drabat.elementrix.game.Settings.highscores[i];
		
		}
		
		
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
			if(AtrasBound.contains(touchPoint))
			{
				Assets.font.scale(1);
				game.setScreen(new MainMenuScreen(game));
				return;
			}
		}
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(1,0,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		
		batcher.begin();
		batcher.draw(Assets.backScore, 0, 0, 100, 150);
		batcher.end();
		batcher.begin();
		batcher.draw(Assets.Atras, 80, 0, 20, 20);
		batcher.end();
		batcher.begin();
		float y = 60;
		for (int i = 4; i >= 0; i--) {
			Assets.font.draw(batcher, highScore[i],20 , y);
			y += Assets.font.getLineHeight();
		}
		
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