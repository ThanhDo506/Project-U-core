package com.thanhdo.game.tiles;

import com.thanhdo.game.graphics.Sprite;

import com.thanhdo.game.util.Camera;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.io.File;

public class TileManager
{
	public static ArrayList<TileMap> tm;
	public Camera cam;

	public TileManager(){
		tm = new ArrayList<TileMap>();
	}

	public TileManager(String path, Camera cam){
		tm = new ArrayList<TileMap>();
		this.cam = cam;
		addTileMap(path,64,64, cam);
	}

	public TileManager(String path, int blockWidth, int blockHeight, Camera cam){
		this.cam = cam;
		tm = new ArrayList<TileMap>();
		addTileMap(path, blockWidth, blockHeight, cam);
	}

	public void addTileMap(String path, int blockWidth, int blockHeight, Camera cam){
		String imagePath;

		int width = 0;
		int height = 0;
		int tileWidth;
		int tileHeight;
		int tileCount;
		int tileColumns;
		int layers = 0;
		Sprite sprite;

		String imagePathTSX;
		String[] data = new String[10];


		try{
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document doc = builder.parse(new File(getClass().getClassLoader().getResource(path).toURI()));
			doc.getDocumentElement().normalize();
			System.out.println("run");
			NodeList list = doc.getElementsByTagName("tileset");
			Node node = list.item(0);
			Element eElement = (Element) node;
			/*
			imagePathTSX = eElement.getAttribute("source");
			imagePath = imagePathTSX.substring(0, imagePathTSX.length() - 4);

			*/
			imagePath = eElement.getAttribute("name");
			tileWidth = Integer.parseInt(eElement.getAttribute("tilewidth"));
			tileHeight = Integer.parseInt(eElement.getAttribute("tileheight"));
			tileCount = Integer.parseInt(eElement.getAttribute("tilecount"));
			tileColumns = Integer.parseInt(eElement.getAttribute("columns"));
			sprite = new Sprite("tile/" + imagePath + ".png", tileWidth, tileHeight);
			/*
			tileColumns = sprite.getSpriteSheetWidth() / tileWidth;
			tileCount = tileColumns * (sprite.getSpriteSheetHeight() / tileHeight);
			*/

			list = doc.getElementsByTagName("layer");
			layers = list.getLength();

			for (int i = 0; i < layers; i++)
			{
				node = list.item(i);
				eElement = (Element) node;
				if(i <= 0){
					width = Integer.parseInt(eElement.getAttribute("width"));
					height = Integer.parseInt(eElement.getAttribute("height"));
				}

				data[i] = eElement.getElementsByTagName("data").item(0).getTextContent();
				/*
				if(i >= 1){
					tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}else{
					tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}*/


				if(i >= 1){
					tm.add(new TileMapNorm(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}else {
					tm.add(new TileMapObj(data[i], sprite, width, height, blockWidth, blockHeight, tileColumns));
				}

				cam.setLimit(width * blockWidth, height * blockHeight);

			}
		}catch (Exception e){
			System.out.println(e.getMessage());
			System.out.println("ERROR - TILEMANAGER: can not read tilemap");
		}

	}


	public void render(Graphics2D g){
		for (int i = 0; i < tm.size(); i++)
		{
			tm.get(i).render(g);
		}
	}	
}
