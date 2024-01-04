package ma.ensate.client.controllers;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ma.ensate.client.utils.Conversation_T;
import ma.ensate.client.utils.Verification_T;
import ma.ensate.client.utils.generer_T;

public class SignUpController_T {
	
	@FXML private Button sign_up;
	@FXML private TextField nom;
	@FXML private TextField prenom;
	@FXML private TextField password;
	@FXML private TextField email;
	@FXML private TextField code;
	private String type="inscription";
	
	
	public void verifier(ActionEvent event) {
		Verification_T v= new Verification_T();
		v.verify(email.getText());	
	}

	
	public void sign_up(ActionEvent event) throws Exception {
            
            String nom1 = nom.getText();
            String prenom1 = prenom.getText();
            String password1 = password.getText();
            String email1 = email.getText();
            String  code1 = code.getText();
		 
            
            Conversation_T conversation = new Conversation_T(nom1, prenom1, password1,email1,code1,type);
            Socket socketClient = new Socket("localhost",1234);
            ObjectOutputStream sortieVersServeur = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream entreeDepuisServeur = new ObjectInputStream(socketClient.getInputStream());
            Conversation_T conversation_rec ;

            
            sortieVersServeur.writeObject(conversation);
            conversation_rec = (Conversation_T) entreeDepuisServeur.readObject();
            
            //On chage la page !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            if(conversation_rec.getLoginState().equals("success")){
                
                  //l'envoire du ertificat 
                    generer_T v= new generer_T();
                    v.verify(email.getText());
                
                  //Fermeture de l'ancienne fenetre
                    Node node = (Node)event.getSource();
                    Scene currentScene = (Scene)node.getScene();
                    Stage currentStage = (Stage) currentScene.getWindow();
                    currentStage.close();
           
               
                    //Ouverture de la nouvelle fenetre
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ma/ensate/client/vues/Login.fxml"));
                    primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

 
               

                    //primaryStage.getIcons().add(new Image("/images/logo ENSA.png")); //C'esl le logo????????????????????????
                    //primaryStage.setTitle("SendMe");
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();

            }
            
            
            
            
            
            
            
            
            socketClient.close();
	}

	public Button getSign_up() {
		return sign_up;
	}

	

	public TextField getNom() {
		return nom;
	}

	

	public TextField getPrenom() {
		return prenom;
	}

	

	public TextField getPassword() {
		return password;
	}

	

	public TextField getEmail() {
		return email;
	}

	

	public TextField getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

}
