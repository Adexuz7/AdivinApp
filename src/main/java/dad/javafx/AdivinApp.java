package dad.javafx;

import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AdivinApp extends Application {
	private Label label;
	private Button checkButton;
	private TextField inputTextField;
	private int numeroAAdivinar = generateRandomNumber();
	private int numeroIntroducido;
	private int numeroIntentos = 0;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		// Etiqueta que pide introducir un número
		label = new Label("Introduce un número entre 1 y 100");

		// Cuadro en el que introducir el número
		inputTextField = new TextField();
		inputTextField.setMaxWidth(150);

		// Botón para comprobar el resultado
		checkButton = new Button("Voy a tener suerte");
		checkButton.setDefaultButton(true);
		checkButton.setOnAction(event -> showResult(event));

		// Presentación de la interfaz de la aplicación
		VBox root = new VBox();
		root.setSpacing(5); // Espacio entre elementos
		root.setAlignment(Pos.CENTER); // Alineación de la VBox "root" dentro de la ventana de la app
		root.getChildren().addAll(label, inputTextField, checkButton); // Elementos que hay dentro de la VBox "root"

		Scene scene = new Scene(root, 320, 200);

		primaryStage.setTitle("AdivinApp");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void showResult(ActionEvent event) {

		try {
			numeroIntroducido = Integer.parseInt(inputTextField.getText());
			
			if (numeroIntroducido == numeroAAdivinar) {
				numeroIntentos++;
				generateDialog("Correcto. F E L I C I D A D E S ");
			} else {
				if (numeroIntroducido > numeroAAdivinar) {
					generateWarning("El número es menor");
					numeroIntentos++;
				} else {
					generateWarning("El número es mayor");
					numeroIntentos++;
				}
			}
			
		} catch (NumberFormatException error) {
			generateError("Introduce un número");
		}

	}

	private int generateRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(100) + 1;
	}

	private void generateDialog(String message) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Muy biiieeeeennn");
		alert.setHeaderText(message);
		alert.setContentText("Nº de intentos: " + numeroIntentos);
		alert.showAndWait();
	}

	private void generateWarning(String message) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Prueba otra vez");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}
	
	private void generateError(String message) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Parece que alguien no sabe leer");
		alert.setHeaderText(null);
		alert.setContentText(message);
		alert.showAndWait();
	}

}
