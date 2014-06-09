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
import drabat.elementrix.Models.World;
import drabat.elementrix.game.Assets;
import drabat.elementrix.game.Settings;

public class PantallaJuego implements Screen{
	
	Game game;
	OrthographicCamera guiCam;
	SpriteBatch batcher;

	int OPC;			
	int tiempo=60; 
	static final int GAME_READY = 0;
	static final int GAME_PAUSED = 1;
	Vector3 touchPoint;	
	World world;
	public int state_Game=0;
	BoundingBox[][] Elementbound;
	private BoundingBox PauseBound;
	private BoundingBox PauseResumenBound;
	private BoundingBox PauseQuitBound;
	
	TextureRegion Fuego;
	TextureRegion Agua;
	TextureRegion Tierra;
	TextureRegion Viento;
	TextureRegion Trueno;
	
	TextureRegion Explosion;
	private float stateTime;
	public PantallaJuego(final Game game) 
	{
		/*Inicializa la ventana y las variables define el tamaño de la vista */
		this.game=game;
		guiCam=new OrthographicCamera(100,150);
		guiCam.position.set(100f/2, 150f/2, 0);
		batcher=new SpriteBatch();
		/*Crea un nuevo mundo*/
		world=new World();
		/*Crea zonas donde solo se podra tocar la pantalla*/
		Elementbound=new BoundingBox[World.FILAS][World.COLUMNAS];
		for (int i = 0; i < World.FILAS; i++) 
		{
			for (int j = 0; j < World.COLUMNAS; j++) 
			{
				Elementbound[i][j]=new BoundingBox(new Vector3(10+j*10, 30+i*10, 0), new Vector3(20+j*10,40+i*10,0));
			}
		}
		/*Variable donde se almacenaran la cordenada x,y,z donde se toco la pantalla*/
		touchPoint= new Vector3();
		/*Se establece el tamaño de la fuente*/
		Assets.font.setScale(.3f);
		/*Se restablece el Score en 0 */
		Score.SetScore();
		/*Se elige un background aleatorio*/
		OPC=(int) (Math.random()*4+1);
		/*se establece los puntos donde se tocara los botones */
		PauseBound=new BoundingBox(new Vector3(40, 0, 0), new Vector3(60,20,0));
		PauseQuitBound=new BoundingBox(new Vector3(70, 30, 0), new Vector3(90,50,0));
		PauseResumenBound=new BoundingBox(new Vector3(10, 30, 0), new Vector3(30,50,0));
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
	public void pause() 
	{
		/*Si se presion pausa pone el estado del juego en pausa*/
		if (state_Game==GAME_READY) 
		{
			state_Game=GAME_PAUSED;
		}
	}
	public void update(float delta)
	{
		/*Esta parte se encarga de verificar si se toco la ventana 
		 * la ultima vez que se mostro y apartir de ahi se dispara 
		 * un evento y solo será valida si se toco alguna de los cuadros, 
		 * si se toco lo primero que hace es que a la matriz de el 
		 * elementos en índices x, y se le asignara un valor 0*/
		if (Gdx.input.justTouched()) 
		{
			/*Verifica donde se toco la pantalla*/
			guiCam.unproject(touchPoint.set(
					Gdx.input.getX(),Gdx.input.getY(), 0));
			/*Verifica en cada pocision si se toco la pantalla*/
			for (int i = 0; i < World.FILAS; i++) 
			{
				for (int j = 0; j < World.COLUMNAS; j++) 
				{
					if (Elementbound[i][j].contains(touchPoint))
					{
						/*Si se toco la pantalla en la cordenada x,y entonces Elimina los blques que lo redean*/
						world.Destruccion0(i, j);
						world.Destruccion1();
					}
				}
			}
			/*Si hubo pausa Llama al emtodo pausa*/
			if(PauseBound.contains(touchPoint))
			{
				//Guardar
				pause();
				//return;
			}
			
		}
		/*Actualiza el mundo*/
		world.update(delta);
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0,1,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);
		
		stateTime+=Gdx.graphics.getDeltaTime();	
		batcher.disableBlending();
		batcher.begin();
		/*Dibuja el Background*/
		switch(OPC)
		{
			case 1:
				batcher.draw(Assets.background_fire,0,0,100,150);
				break;
			case 2:batcher.draw(Assets.background_whater,0,0,100,150);
				break;
			case 3:batcher.draw(Assets.background_earth,0,0,100,150);
				break;
			case 4:batcher.draw(Assets.background_wind,0,0,100,150);
				break;
		}
		batcher.end();
		/*Dibuja cada una de las esferas que se tienen en la matriz*/
		for (int i = 0; i < World.FILAS; i++) {
			for (int j = 0; j < World.COLUMNAS; j++) 
				{
				batcher.enableBlending();
				switch(World.elementos[i][j].getElement())
				{
					case World.FIRE  :
						Fuego=Assets.fire.getKeyFrame(stateTime, true);
						//batcher.enableBlending();
						batcher.begin();
						batcher.draw(Fuego,World.elementos[i][j].position.x*10,
								World.elementos[i][j].position.y*10, 10,10);
						batcher.end();
						break;
					case World.WHATER :
							Agua=Assets.whater.getKeyFrame(stateTime, true);
							batcher.begin();
							batcher.draw(Agua,World.elementos[i][j].position.x*10,
										World.elementos[i][j].position.y*10, 10,10);
							batcher.end();
						break;
					case World.EARTH :
							Tierra=Assets.earth.getKeyFrame(stateTime, true);
							batcher.begin();
							batcher.draw(Tierra,World.elementos[i][j].position.x*10,
								World.elementos[i][j].position.y*10, 10,10);
					batcher.end();
						break;
					case World.WIND  :
							Viento=Assets.find.getKeyFrame(stateTime, true);
							batcher.begin();
							batcher.draw(Viento,World.elementos[i][j].position.x*10,
										World.elementos[i][j].position.y*10, 10,10);
							batcher.end();
						
						break;
					case World.TRUENO  :
							Trueno=Assets.dark.getKeyFrame(stateTime, true);
							batcher.begin();
							batcher.draw(Trueno,World.elementos[i][j].position.x*10,
										World.elementos[i][j].position.y*10, 10,10);
							batcher.end();
						break;
				}
			}
		}
		/*Dibuja la barra horizontal y vertical*/
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.barra_h, 0, 20,100,10);
		batcher.end();
		
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.barra_v, 0, 20,10,100);
		batcher.draw(Assets.barra_v, 90, 20,10,100);
		batcher.end();
		/*Dibuja el Score*/
		batcher.enableBlending();
		batcher.begin();
		Assets.font.draw(batcher, "Score:0"+Score.getScore(), 00,150);
		batcher.end();
		/*Dibuja el tiempo transcurrido*/
		batcher.enableBlending();
		batcher.begin();
		Assets.font.draw(batcher, "Time: "+tiempo, 50,140);
		batcher.end();
		/*Dibuja el boton de Pausa*/
		batcher.enableBlending();
		batcher.begin();
		batcher.draw(Assets.Pause, 40, 0, 20, 20);
		batcher.end();
		
	}
	@Override
	public void render(float delta) 
	{
		/*Verifica si el estado del juego es Ready o Pause*/
		if (state_Game==GAME_READY) 
		{
			/*Si esta en Ready Decrementa el tiempo hasta que el tiempo se igual a 0*/
			tiempo=60-(int) (stateTime/2);
			if(tiempo==0)
			{
				/*Si es igual a 0 entonces muestra la pantalla de gameover*/
				Settings.addScore(Score.getScore());
				game.setScreen(new PantallaGameOver(game));
			}
			update(delta);
		}
		if (state_Game==GAME_PAUSED) 
		{
			/*Si el estado del juego es Pausa muestra la ventana de Pausa*/
			pauseGame(delta);
		}
		
	}

	private void pauseGame(float delta) 
	{
		/*Ventana de Pausa*/
		if (Gdx.input.justTouched()) 
		{
			guiCam.unproject(touchPoint.set(
					Gdx.input.getX(),Gdx.input.getY(), 0));
			/*si se toca el boton de Resume regresa al estado de ready el juego*/
			if(PauseResumenBound.contains(touchPoint))
			{
				//Guardar
				state_Game=GAME_READY;
				return;
			}
			/*Si se toco el boton de salir muestra la pantalla de menu*/
			if(PauseQuitBound.contains(touchPoint))
			{
				world.vector.removeAllElements();
				game.setScreen(new MainMenuScreen(game));
			}
			
		}
		GL10 gl = Gdx.graphics.getGL10();
		gl.glClearColor(0,1,0,1);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		/*Muestra el background de pausa*/
		guiCam.update();
		batcher.setProjectionMatrix(guiCam.combined);		
		batcher.begin();
		batcher.draw(Assets.backPause, 0, 0, 100, 150);
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