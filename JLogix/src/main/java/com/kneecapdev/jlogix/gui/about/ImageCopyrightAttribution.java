package com.kneecapdev.jlogix.gui.about;

import java.awt.Desktop;
import java.net.URL;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class ImageCopyrightAttribution extends CopyrightAttribution {

	private String imgCreator, imgCreatorURL;
	private String imgProvider, imgProviderURL;
	
	/**
	 * Use this constructor for image copyright attributions.
	 * 
	 * @param imgCreator Name of the image creator.
	 * @param imgCreatorURL URL of the image creator.
	 * @param imgProvider Name of the image provider.
	 * @param imgProviderURL URL of the image provider.
	 */
	public ImageCopyrightAttribution(String imgCreator, String imgCreatorURL, String imgProvider, String imgProviderURL) {
		this.imgCreator = imgCreator;
		this.imgCreatorURL = imgCreatorURL;
		this.imgProvider = imgProvider;
		this.imgProviderURL = imgProviderURL;
	}

	@Override
	public Node getNode() {
		HBox hbox = new HBox();
		
		Text txt1 = new Text("Icons made by ");
			txt1.setFont(AboutGUI.fontSmall);
			txt1.setFill(javafx.scene.paint.Color.GRAY);
		Hyperlink h1 = new Hyperlink(imgCreator);
		h1.setOnAction(linkEvent -> {
			try {
				Desktop.getDesktop().browse(new URL(imgCreatorURL).toURI());
			} catch (Exception ignored) {}
		});
		Text txt2 = new Text(" from ");
			txt2.setFont(AboutGUI.fontSmall);
			txt2.setFill(javafx.scene.paint.Color.GRAY);
		Hyperlink h2 = new Hyperlink(imgProvider);
		h2.setOnAction(linkEvent -> {
			try {
				Desktop.getDesktop().browse(new URL(imgProviderURL).toURI());
			} catch (Exception ignored) {}
		});

		hbox.getChildren().addAll(txt1, h1, txt2, h2);
		
		return hbox;
	}
	
}
