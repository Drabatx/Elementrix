package drabat.elementrix.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import drabat.elementrix.Models.Score;
import drabat.elementrix.game.Assets;
import drabat.elementrix.game.Settings;

public class MainMenuScreen implements Screen
{
	Game game;
	OrthographicCamera guiCam;
	SpriteBatch batcher;
	float stateTime;
	TextureRegion Explosion;
	BoundingBox Iniciobound;
	BoundingBox Sonidobound;
	BoundingBox Scorebound;
	BoundingBox Exitbound;
	Vector3 touchPoint;
	public MainMenuScreen(Game game) {
		// TODO Auto-generated constructor stub
		this.game=game;
		guiCam=new OrthographicCamera(10,15);
		guiCam.position.set(10f/2, 15f/2, 0);
		batcher=new SpriteBatch();
		stateTime=0f;
		Iniciobound=new BoundingBox(new Vector3(2, 7, 0), new Vector3(8,9,0));
		Sonidobound=new BoundingBox(new Vector3(2, 5, 0), new Vector3(8,7,0));
		Scorebound=new BoundingBox(new Vector3(2, 3, 0), new Vector3(8,5,0));
		Exitbound=new BoundingBox(new Vector3(2, 1, 0), new Vector3(8,3,0));
		touchPoint= new Vector3();
		if(PantallaSound.Sonido=="ON")
		Assets.music.play();
	}
	@Override
	public void dispose() 
	{
		batcher.dispose();
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() 
	{
		Settings.save();
	}

	@Override
	public void render(float delta) 
	{
		if (Gdx.input.justTouched()) 
		{
			guiCam.unproject(touchPoint.set(
					Gdx.input.getX(),Gdx.input.getY(), 0));
		if (Iniciobound.contains(touchPoint)) 
			{
				Score.SetScore();
				game.setScreen(new PantallaJuego(game));
				return;
			}
		if (Sonidobound.contains(touchPoint)) 
		{
			game.setScreen(new PantallaSound(game));
			return;
		}
		if (Scorebound.contains(touchPoint)) 
		{
			game.setScreen(new PantallaScore(game));
			return;
		}
		}
		
		
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0,0,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
	
		batcher.disableBlending();
		batcher.begin();
		batcher.draw(Assets.background,0,0,10,15);
		batcher.end();
		
//		Dibujamos el Sprite
		
		
		stateTime+=Gdx.graphics.getDeltaTime();
		Explosion=Assets.EfectoTitle.getKeyFrame(stateTime, true);
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Explosion,0,0,10,9);
		batcher.end();
		
		
		
//Dibujamos los titulos

		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.inicio, 2,7,6,2);
		batcher.end();
		
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.sound, 2,5,6,2);
		batcher.end();
		
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.score, 2,3,6,2);
		batcher.end();

	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void show() {
		
	}

}
