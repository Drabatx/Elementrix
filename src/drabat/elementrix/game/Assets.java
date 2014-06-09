package drabat.elementrix.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Music;

public class Assets 
{
	public static TextureAtlas atlas;
	//Fondos de Pantalla
	public static AtlasRegion background;
	public static AtlasRegion background_fire;
	public static AtlasRegion background_whater;
	public static AtlasRegion background_earth;
	public static AtlasRegion background_wind;
	public static AtlasRegion backMadera;
	public static AtlasRegion backScore;
	public static AtlasRegion backPause;
	
	//Barra Horizontal y Vertical del juego
	public static AtlasRegion barra_h;
	public static AtlasRegion barra_v;
	
	//Boton de Atras
	public static AtlasRegion Atras;
	
	//Letreros
	public static AtlasRegion inicio;
	public static AtlasRegion sound;
	public static AtlasRegion score;
	//Fuente
	public static BitmapFont font; 
	//Tamaño de Columnas y Filas
	public static int FRAME_COL;
	public static int FRAME_FIL;
	
	//Variable que nos ayudara a las animaciones
	public static AtlasRegion SplashBack;
	public static Texture ExpSheet;
	public static TextureRegion[] Expframes;
	
	//Musica
	public static Music music;
	public static Music bip;
	//Boton de Pause
	public static AtlasRegion Pause;
	//Lista de Efectos
	public static Animation EfectoTitle;
	//Lista de Esferas de Elementos
	public static Animation fire;
	public static Animation whater;
	public static Animation find;
	public static Animation earth;
	public static Animation Explosion;
	public static Animation dark;
	
	public static void load()
	{
		atlas=new TextureAtlas(Gdx.files.internal("data/pack"));
		background=atlas.findRegion("Background");
		
		background_fire=atlas.findRegion("Backgroundfi");
		background_earth=atlas.findRegion("Backgrounde");
		background_whater=atlas.findRegion("Backgroundw");
		background_wind=atlas.findRegion("Backgroundwi");
		backScore=atlas.findRegion("BackScore");
		backMadera=atlas.findRegion("BackMadera");
		backPause=atlas.findRegion("PauseBack");
		SplashBack=atlas.findRegion("Splash");
		barra_h=atlas.findRegion("barrah");
		barra_v=atlas.findRegion("barrav");
		
		font=new BitmapFont(Gdx.files.internal("data/hiero.fnt"),false);
		
		Atras=atlas.findRegion("Atras");
		inicio=atlas.findRegion("inicio");
		sound=atlas.findRegion("sonido");
		score=atlas.findRegion("score");
		Pause=atlas.findRegion("pause");
		
		music=Gdx.audio.newMusic(Gdx.files.internal("data/soundb.mp3"));
		bip=Gdx.audio.newMusic(Gdx.files.internal("data/bip.mp3"));
		
		EfectoTitle=loadSprites("data/Efecto1.png", 3, 3);
		
		fire=loadSprites("data/Fire.png", 3, 3);
		whater=loadSprites("data/Agua.png", 3, 3);
		find=loadSprites("data/Viento.png", 3, 3);
		earth=loadSprites("data/Tierra.png", 3, 3);
		dark=loadSprites("data/trueno.png", 3, 3);
	}

	//Metodo se encarga de obtener la animasion de cada Sprite 
	public static Animation loadSprites(String nombre,int col, int fil)
	{
		FRAME_COL=col;
		FRAME_FIL=fil;
		int index = 0;
		ExpSheet=new 
		
		Texture(nombre);
		TextureRegion[][] tmp=TextureRegion.split(ExpSheet, 
							ExpSheet.getWidth()/FRAME_COL, 
							ExpSheet.getHeight()/FRAME_FIL);
		Expframes = new TextureRegion[FRAME_COL*FRAME_FIL];
		for (int i = 0; i < FRAME_FIL; i++) {
			for (int j = 0; j < FRAME_COL; j++) {
				Expframes[index++]=tmp[i][j];
			}
		}
		
		return new Animation(.1f, Expframes);
	}
	
	
	public static void dispose(){
		atlas.dispose();
	}
}
