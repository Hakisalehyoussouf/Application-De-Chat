package ma.ensate.client.controllers;

import java.io.FileWriter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import javafx.stage.StageStyle;
import ma.ensate.client.utils.Conversation;

public class LoginController_T implements Serializable {
    //@FXML
  //  private Button loginButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordPasswordField;

    
    private double xOffset = 0;
    private double yOffset = 0;

    public void loginButtonOnAction(ActionEvent e) throws IOException, ClassNotFoundException {

                     
   
                    

        if (emailTextField.getText().isEmpty() == false && passwordPasswordField.getText().isEmpty() == false){
            
            //loginMessageLabel.setText("You try to login!");
            //Sending to server
            Conversation conversation_env = new Conversation(emailTextField.getText(), passwordPasswordField.getText(), "login");
            Conversation conversation_rec ;

            

            Socket socketClient = new Socket("localhost",1234);
            ObjectOutputStream sortieVersServeur = new ObjectOutputStream(socketClient.getOutputStream());
            ObjectInputStream entreeDepuisServeur = new ObjectInputStream(socketClient.getInputStream());


            sortieVersServeur.writeObject(conversation_env);
            conversation_rec = (Conversation) entreeDepuisServeur.readObject();


        //   System.out.println(conversation_rec.getLoginState());
            loginMessageLabel.setText(conversation_rec.getLoginState());
            
            String falseresult = "error";
            String correctresult = loginMessageLabel.getText();
            if(!correctresult.equals(falseresult)){
                
                try { 
                    
                    
                    //Fermeture de l'ancienne fenetre
                    Node node = (Node)e.getSource();
                    Scene currentScene = (Scene)node.getScene();
                    Stage currentStage = (Stage) currentScene.getWindow();
                    currentStage.close();
                    
                    //Stockage de username dans fichier username.txt
                    String[] tab = conversation_rec.getLoginState().split(" ");
                    String s1 = tab[0];
                    String s2 = tab[1];
                    System.out.println(s1);
                    System.out.println(s2);
                    
                    String tempfilename1 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/username_T.txt";
                    try (FileWriter tempwriter1 = new FileWriter(tempfilename1)){
                        tempwriter1.write(s2+"\n");
                        tempwriter1.close();
                    }
                    
                    //System.out.println("Jusqu'a ici deja!!!!!!!!!!!");
                            
                    
                    //Ouverture de la nouvelle fenetre
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ma/ensate/client/vues/Dashboard_T.fxml"));
                    primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

                    
                    root.setOnMousePressed(event->{
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();  
                    });

                    root.setOnMouseDragged(event->{
                        primaryStage.setX(event.getScreenX()-xOffset);
                        primaryStage.setY(event.getScreenY()-yOffset);
                    });


                    //primaryStage.getIcons().add(new Image("/images/logo ENSA.png")); //C'esl le logo????????????????????????
                    //primaryStage.setTitle("SendMe");
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
           
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
  
            }
            

            socketClient.close();


        } else {
            loginMessageLabel.setText("Please enter an email and password");
        }
    }

    /**
     * Pour le test
     */
   /* private Stage stage;
    private Scene scene;
    private Parent root;

    public void signUpOnAction(ActionEvent event) throws IOException {

       // String username =  nameTextField.getText();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Scene4.fxml"));
        root = loader.load();

        // instantiate Scene4 Controller
      //  Scene4Controller scene4Controller = loader.getController();
     //   scene4Controller.displayName(username);


        // Parent root = FXMLLoader.load(getClass().getResource("Scene4.fxml"));   //
        stage = (Stage)((Node)event.getSource()).getScene().getWindow(); // getting  the Stage of the event source
        scene = new Scene(root); // adding the root node we already have (FXML)
        stage.setScene(scene);
        stage.show();
    }*/

    
    
    
    
    
    
        @FXML
    private Button signUpButton;

    public void signUpOnAction(ActionEvent event) throws IOException {

                //Fermeture de l'ancienne fenetre
                    Node node = (Node)event.getSource();
                    Scene currentScene = (Scene)node.getScene();
                    Stage currentStage = (Stage) currentScene.getWindow();
                    currentStage.close();
           
               
                    //Ouverture de la nouvelle fenetre
                    Stage primaryStage = new Stage();
                    Parent root = FXMLLoader.load(getClass().getResource("/ma/ensate/client/vues/SignUp_T.fxml"));
                    primaryStage.initStyle(StageStyle.DECORATED.UNDECORATED);

 
               

                    //primaryStage.getIcons().add(new Image("/images/logo ENSA.png")); //C'esl le logo????????????????????????
                    //primaryStage.setTitle("SendMe");
                    Scene scene = new Scene(root);
                    primaryStage.setScene(scene);
                    primaryStage.show();
        
        
        
        
    }

}
