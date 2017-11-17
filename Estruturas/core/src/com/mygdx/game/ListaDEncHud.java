package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.TextInputListener;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ListaDEncHud implements Disposable, TextInputListener{
	private int elementos; //Nos ajudar� na l�gica de adicionar e remover
	private boolean delete;//bollean e merda
	private int opcao;
	
	public Stage stage;
	private Viewport port;
	
	private Executor game;	
	
	
    TextButtonStyle textButtonStyleAdd;
    BitmapFont fontAdd;
    Skin skinAdd;
    TextureAtlas buttonAtlasAdd;
    
    TextButtonStyle textButtonStyleRemove;
    BitmapFont fontRemove;
    Skin skinRemove;
    TextureAtlas buttonAtlasRemove;
    
    TextButtonStyle textButtonStyleMenu;
    BitmapFont fontMenu;
    Skin skinMenu;
    TextureAtlas buttonAtlasMenu;
    
    TextButtonStyle textButtonStylePesq;
    BitmapFont fontPesq;
    Skin skinPesq;
    TextureAtlas buttonAtlasPesq;
    
	
	public ListaDEncHud(SpriteBatch sb, final Executor game) {
		elementos = 0;
		delete = false;
		opcao = 0;
		
		this.game = game;
		
		port = new FitViewport(Executor.V_WIDTH, Executor.V_HEIGHT, new OrthographicCamera());
		stage = new Stage(port, sb);
		
		 Gdx.input.setInputProcessor(stage);
		 fontAdd = new BitmapFont();
	     skinAdd = new Skin();
	     buttonAtlasAdd = new TextureAtlas("Bot�es/AddImg.pack");
	     skinAdd.addRegions(buttonAtlasAdd);
	     textButtonStyleAdd = new TextButtonStyle();
	     textButtonStyleAdd.font = fontAdd;
	     textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarNormal");
	     textButtonStyleAdd.down = skinAdd.getDrawable("AdicionarPressionado");
	     textButtonStyleAdd.checked = skinAdd.getDrawable("AdicionarNormal");
	     Button buttonAdd = new TextButton(" ", textButtonStyleAdd);
	     
	     buttonAdd.addListener(new ClickListener() {
	    	 
	    	 @Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleAdd.up = skinAdd.getDrawable("AdicionarSelecionado");
					return super.mouseMoved(event, x, y);
				}

			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				System.out.println("chegou no clique");
				if(elementos == 0) {
					Gdx.input.getTextInput(ListaDEncHud.this, "Adicionar", "", "Conteudo");
				}
				else {
					Gdx.input.getTextInput(ListaDEncHud.this, "Adicionar", "", "Posi��o-Conteudo");
				}
				}	    	
	     	});
	     stage.addActor(buttonAdd);
	     
	     fontRemove = new BitmapFont();
	     skinRemove = new Skin();
	     buttonAtlasRemove = new TextureAtlas("Bot�es/RemoveImg.pack");
	     skinRemove.addRegions(buttonAtlasRemove);
	     textButtonStyleRemove = new TextButtonStyle();
	     textButtonStyleRemove.font = fontRemove;
	     textButtonStyleRemove.up = skinRemove.getDrawable("RemoverNormal");
	     textButtonStyleRemove.down = skinRemove.getDrawable("RemoverPressionado");
	     textButtonStyleRemove.checked = skinRemove.getDrawable("RemoverNormal");
	     Button buttonRemove = new TextButton(" ", textButtonStyleRemove);
	     buttonRemove.addListener(new ClickListener() {	    	 
	    	 	@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleRemove.up = skinRemove.getDrawable("RemoverNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleRemove.up = skinRemove.getDrawable("RemoverSelecionado");
					return super.mouseMoved(event, x, y);
				}
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					elementos--; //Permitir� excluir
					delete = true;
					Gdx.input.getTextInput(ListaDEncHud.this, "Remover", "", "Posi��o");
				}    	 
	     	});
	     stage.addActor(buttonRemove);
	     
	     fontMenu = new BitmapFont();
	     skinMenu = new Skin();
	     buttonAtlasMenu = new TextureAtlas("Bot�es/MenuImg.pack");
	     skinMenu.addRegions(buttonAtlasMenu);
	     textButtonStyleMenu = new TextButtonStyle();
	     textButtonStyleMenu.font = fontMenu;
	     textButtonStyleMenu.up = skinMenu.getDrawable("MenuNormal");
	     textButtonStyleMenu.down = skinMenu.getDrawable("MenuPressionado");
	     textButtonStyleMenu.checked = skinMenu.getDrawable("MenuNormal");
	     Button buttonMenu = new TextButton(" ", textButtonStyleMenu);
	     buttonMenu.addListener(new ClickListener() {	    	 
	    	 	@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStyleMenu.up = skinMenu.getDrawable("MenuNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStyleMenu.up = skinMenu.getDrawable("MenuSelecionado");
					return super.mouseMoved(event, x, y);
				}
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					game.setScreen(new MenuScreen(game));
				}    	 
	     	});
	     stage.addActor(buttonMenu);
	     

	     fontPesq = new BitmapFont();
	     skinPesq = new Skin();
	     buttonAtlasPesq = new TextureAtlas("Bot�es/RemoveImg.pack");
	     skinPesq.addRegions(buttonAtlasPesq);
	     textButtonStylePesq = new TextButtonStyle();
	     textButtonStylePesq.font = fontPesq;
	     textButtonStylePesq.up = skinPesq.getDrawable("RemoverNormal");
	     textButtonStylePesq.down = skinPesq.getDrawable("RemoverPressionado");
	     textButtonStylePesq.checked = skinPesq.getDrawable("RemoverNormal");
	     Button buttonPesq = new TextButton(" ", textButtonStylePesq);
	     buttonPesq.addListener(new ClickListener() {	    	 
	    	 	@Override
				public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
	    		 textButtonStylePesq.up = skinPesq.getDrawable("RemoverNormal");
					super.exit(event, x, y, pointer, toActor);
				}
				@Override
				public boolean mouseMoved(InputEvent event, float x, float y) {
					textButtonStylePesq.up = skinPesq.getDrawable("RemoverSelecionado");
					return super.mouseMoved(event, x, y);
				}
				@Override
				public void clicked(InputEvent event, float x, float y) {
					super.clicked(event, x, y);
					opcao = 1;
					Gdx.input.getTextInput(ListaDEncHud.this, "Pesquisar", "", "Conteudo");
					
				}    	 
	     	});
	     stage.addActor(buttonPesq);
	     
	     
		Table table = new Table();
		table.top();
		table.setFillParent(true);
						
		table.add(buttonAdd).expandX().pad(10);
		table.add(buttonRemove).expandX().pad(10);
		table.add(buttonMenu).expandX().pad(10);
		table.add(buttonPesq).expandX().pad(10);
				
		
		stage.addActor(table);
		
	}
	
	/*
	 * Aqui quando o usu�rio clicar pela primeira vez
	 * ele adicionar� automaticamente na posi��o 0,
	 * quando for em alguma outra, ele adicionar� nas posi��es subsequentes
	 */
	@Override
	public void input(String text) {
		int pos;
		if( (elementos == 0) && (delete == false) && (opcao == 0)) {
			elementos++;
			ListaDEncScreen.insereTela(1, text);
		}
		else if( (elementos > 0) && (delete == false) && (opcao == 0)) {
			elementos++;
			String entrada [] = text.split("-"); //------Exce��o quando coloca errado
			pos = Integer.parseInt(entrada[0]); //-------Exce��o elemento diferente de numero
			ListaDEncScreen.insereTela(pos, entrada[1]);
		}
		else if((delete == true) && (opcao == 0)) {
			ListaDEncScreen.removeTela(Integer.parseInt(text)); //------Exce��o quando coloca diferente de numero
			delete = false;
		}
		if(opcao == 1){
			ListaDEncScreen.Pesquisa(text);
			opcao = 0;
		}
	}

	@Override
	public void canceled() {
				
	}
	
	public void dispose() {
		stage.dispose();
		skinAdd.dispose();
		buttonAtlasAdd.dispose();
		fontAdd.dispose();
		
		skinRemove.dispose();
		buttonAtlasRemove.dispose();
		fontRemove.dispose();
		
		skinMenu.dispose();
		buttonAtlasMenu.dispose();
		fontMenu.dispose();
		
		skinPesq.dispose();
		buttonAtlasPesq.dispose();
		fontPesq.dispose();
		
		
	}


}
