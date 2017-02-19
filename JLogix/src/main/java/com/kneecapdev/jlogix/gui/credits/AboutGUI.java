package com.kneecapdev.jlogix.gui.credits;

import java.util.ArrayList;

import com.kneecapdev.jlogix.gui.LogixGUI;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;

public class AboutGUI extends Alert {

	public ArrayList<CopyrightAttribution> libraries = new ArrayList<>();
	public ArrayList<CopyrightAttribution> images = new ArrayList<>();
	
	public static Font fontSmall = new Font("Segoe UI", 13);
	public static Font fontBig = javafx.scene.text.Font.font("Segoe UI", FontWeight.BOLD, 16);
	
	public AboutGUI() {
		super(AlertType.INFORMATION);
		
		ImageView icon = new ImageView(new Image(LogixGUI.class.getResourceAsStream("/images/logix_logo.png")));
		icon.setFitHeight(64);
		icon.setFitWidth(64);
		this.setGraphic(icon);
		
		this.initModality(Modality.APPLICATION_MODAL);
		this.initOwner(LogixGUI.instance.stage);
		this.setTitle("About");
		this.setHeaderText(LogixGUI.TITLE + " " + LogixGUI.VERSION + " " + LogixGUI.COPYRIGHT_YEAR);

		TextFlow tf = new TextFlow();
		tf.setMaxWidth(600);
		tf.setPrefWidth(600);

		this.init();
		
		Text headerLibraries = new Text("Used libraries: \n");
			headerLibraries.setFont(fontBig);
			headerLibraries.setFill(javafx.scene.paint.Color.GRAY);
        
		ArrayList<Node> libraryNodes = new ArrayList<>();
		libraries.forEach((l) -> {	
			libraryNodes.add(new Text("\n"));
			libraryNodes.add(l.getNode());
		});
			
        Text headerImages = new Text("\nUsed images: \n");
	        headerImages.setFont(fontBig);
	        headerImages.setFill(javafx.scene.paint.Color.GRAY);
	        
	    ArrayList<Node> imageNodes = new ArrayList<>();
	    images.forEach((l) -> {
	    	imageNodes.add(new Text("\n"));
	    	imageNodes.add(l.getNode());
	    });
		
		Text txtLicenseNotice = new Text("\n" + LogixGUI.TITLE + " is licensed under The MIT License (MIT)\n");
	        txtLicenseNotice.setFont(fontSmall);
	        txtLicenseNotice.setFill(javafx.scene.paint.Color.GRAY);

        Text txtLicense = new Text("THE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.");
	        txtLicense.setFill(javafx.scene.paint.Color.GRAY.darker());
	        txtLicense.setFont(javafx.scene.text.Font.font("Segoe UI", 11));

        Text txtCopyright = new Text("\nCopyright © " + LogixGUI.COPYRIGHT_YEAR + " KneecapDev");
	        txtCopyright.setFill(javafx.scene.paint.Color.GRAY);
	        txtCopyright.setFont(fontSmall);
		
	    tf.getChildren().add(headerLibraries);   
	    tf.getChildren().addAll(libraryNodes);
	    tf.getChildren().add(headerImages);
	    tf.getChildren().addAll(imageNodes);
	    tf.getChildren().addAll(txtLicenseNotice, txtLicense, txtCopyright);
	    
		this.getDialogPane().setContent(tf);
		
		this.showAndWait();
	}
	
	private void init() {
		libraries.add(new LibraryCopyrightAttribution("(Apache License 2.0)", "Guava", "https://github.com/google/guava"));
		libraries.add(new LibraryCopyrightAttribution("(Apache License 2.0)", "Json-Simple", "https://github.com/fangyidong/json-simple"));
		libraries.add(new LibraryCopyrightAttribution("(Apache License 2.0)", "Apache Log4J", "https://logging.apache.org/log4j/2.x/"));
		libraries.add(new LibraryCopyrightAttribution("(Apache License 2.0)", "Apache Commons Configuration", "https://logging.apache.org/log4j/2.x/"));
		
		images.add(new ImageCopyrightAttribution("TutsPlus", "https://tutsplus.com/", "Flaticon", "www.flaticon.com "));
	}
	
}
