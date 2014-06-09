package drabat.elementrix.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import drabat.elementrix.game.Assets;
import drabat.elementrix.game.GameElementrix;

public class SplashScreen implements Screen
{
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	private GameElementrix game;
	
	public SplashScreen(GameElementrix game) 
	{
		this.game=game;
		guiCam=new OrthographicCamera(10,15);
		guiCam.position.set(10f/2, 15f/2, 0);
		batcher=new SpriteBatch();
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
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
	public void render(float delta) 
	{
		handleInput();
		 GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0,0,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		batcher.begin();
		batcher.draw(Assets.SplashBack, 0, 0, 10, 15);
		batcher.end();
	}

	private void handleInput() {
		// TODO Auto-generated method stub
		if(Gdx.input.justTouched())
		{
			game.setScreen(new MainMenuScreen(game));
		}
	}
	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() 
	{
	}

}
