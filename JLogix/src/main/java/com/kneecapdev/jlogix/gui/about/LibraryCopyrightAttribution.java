package com.kneecapdev.jlogix.gui.about;

import java.awt.Desktop;
import java.net.URL;

import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class LibraryCopyrightAttribution extends CopyrightAttribution {

	private String license;
	private String libraryName, libraryURL;
	
	/**
	 * Use this constructor for library copyright attributions.
	 * @param libraryName Name of the library.
	 * @param libraryURL URL of the Library.
	 */
	public LibraryCopyrightAttribution(String license, String libraryName, String libraryURL) {
		this.license = license;
		this.libraryName = libraryName;
		this.libraryURL = libraryURL;
	}

	@Override
	public Node getNode() {
		VBox vbox = new VBox(0);
		
		Text license = new Text(this.license);
		license.setFont(AboutGUI.fontSmall);
		license.setFill(javafx.scene.paint.Color.GRAY);
		
		Hyperlink hl = new Hyperlink(libraryName);
		hl.setOnAction(linkEvent -> {
			try {
				Desktop.getDesktop().browse(new URL(libraryURL).toURI());
			} catch (Exception ignored) {}
		});
		
		vbox.getChildren().addAll(license, hl);

		return vbox;
	}
	
}
