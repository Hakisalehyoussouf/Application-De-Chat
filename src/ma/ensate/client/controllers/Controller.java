package ma.ensate.client.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;
import java.util.Base64;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import ma.ensate.client.utils.CompomentInter;
import ma.ensate.client.utils.Component;
import ma.ensate.client.utils.Conversation;

public class Controller implements Initializable {
    
    //security parameterz
    private String secret = "haki_saleh_youss";
    //CompomentInter compomentInter = new CompomentInter();
    
    private static KeyPair keyPair;      
    private static DHPrivateKey privatekey;
    private static DHPublicKey publicKey;
     private static DHPublicKey ReceiverpublicKey;
     
     private static PublicKey publicKeyy;
   
      private static byte[] signatureBytes;
    
    
    
    private static final String PRIVATE_KEY_FILE = "private_key_salim.key";
    private static final String PUBLIC_KEY_FILE = "public_key_salim.key";
     private static final String RECEIVER_PUBLIC_KEY_FILE = "public_key_adnan.key";
    

    
  
    ObjectOutputStream outStream ;
    /**
     *  C'est juste pour affichier le message envoyer par moi meme
     * Sa valeur est defini lors du premier evenemet et se change selon les evenement 
     */
    String currentContact; 
                        
        
   
  
    @FXML
    private TextField sendWritingLabel;
    @FXML
    private Button btnSend;
     
    
    
    
    @FXML
    private Button btnChat; // C'est le bouton d'initialisation de la page____________________
    @FXML
    private Button btnGroup;
    @FXML
    private Button btnProfileManaging;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnLogout;

   
    
    

   
    
     //la partie notification + message + contact______________________________________________________
    @FXML
    private VBox contactBox;
    @FXML
    private TextField searchTxtField;
    
    int sizetab = 10;
    
    Button[] buttonTable = new Button[sizetab];
    //Label[] nameTable = new Button[sizetab];
    //Label[] messageTable = new Button[sizetab];
    //Label[] buttonTable = new Button[sizetab];
    //Peut etre que je creerai le tableau de chacun des ces composants?????????????????????????????????????????????????????????
    @FXML
    private ImageView profileImage;
    @FXML
    private Label nameLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Pane notificationPane;
    @FXML
    private Label notificationNumber;
    

    
    
     //la partie centrale____________________________________________________________________________________
        
    @FXML
    private Button closebtn;
    
    
    @FXML
    private VBox main_form;
    @FXML
    private HBox main_form_head;
    @FXML
    private ImageView profileImage_head;
    @FXML
    private Label nameLabel_head;
    @FXML
    private Circle onlineSign;
    @FXML
    private Label OnlineLabel;
    @FXML
    private Button btnCall;
    @FXML
    private Button btnVideoCall;
    @FXML
    private Button btnMenu;
    
    @FXML
    private ScrollPane main_form_center_pane;
    @FXML
    private VBox main_form_center;
    @FXML
    private HBox main_form_bottom;
    @FXML
    private Button btnSticker;
    @FXML
    private Button btnAttach;
    @FXML
    private ListView<String> listView;
    
    
    

    
    //Preparation de structure d'affcihage
    //private ObservableList<String> listModel = FXCollections.observableArrayList();
    //private ListView<String> listView ;
    //istModel = FXCollections.observableArrayList();
    //listView = new ListView<>(listModel);
    
    
    //public Controller(){
    //    listModel = FXCollections.observableArrayList();
     //   listView = new ListView<>(listModel);
     //   main_form_center.getChildren().add(listView);
    //}

    
    

 
    //**********************************************L'initialisation de la page********************************************************/
            
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        //les valeurs des cles prives et public
        /*try {
            keyPair= generateDH();
            privatekey=( DHPrivateKey)keyPair.getPrivate();
            publicKey=(DHPublicKey)keyPair.getPublic();
        
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        

        
        //Le Chargement des des contacts deja existants______________________________________________________________________________________________
        String FILE_NAME = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/contacts.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            String username;
            int i=0;
            while ((line = reader.readLine()) != null) {
                username = line;                
        
                // Créer un ImageView avec une image(L'mage doit etre reuperez depuis le server : Le programme est avec adnan)
                //??????????????????????????????????????????????????????????????????????????????????????????????????????????
                Image image = new Image("/ma/ensate/client/resources/images/profileimage.png");
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(70);
                imageView.setFitHeight(60);
                HBox.setMargin(imageView, new Insets(0, 0, 0, 20));
                
     
                
                // Créer un Label pour le texte
                /**
                 * -Le message doit etre recuperez depuis la base des donnees(Le programme est avec adnan)!!!!!!!!!
                 * -Si plusieurs messages exites dans la base des donnees, ils seront transferes et sotockes dans une liste et
                 * le dernier sera stocke dans le label
                 */
                Label name = new Label(username);
                Label message = new Label("Bonsoir! C'est un membre de l'equipe!");
                
                Color nameColor = Color.WHITE; 
                Font namefont = Font.font("Ebrima", FontWeight.BOLD, 18); 
                name.setTextFill(nameColor);
                name.setFont(namefont);
                name.setAlignment(Pos.CENTER_LEFT);
                
                Color messageColor = Color.web("#9da7a7"); 
                Font messagefont = Font.font("Ebrima", 15); 
                message.setTextFill(messageColor);
                message.setFont(messagefont);
                name.setAlignment(Pos.CENTER_LEFT);
                
                VBox vbox = new VBox(name, message);
                vbox.setPrefWidth(116);
                vbox.setPrefHeight(60);
                vbox.setAlignment(Pos.CENTER_LEFT);
                VBox.setMargin(vbox, new Insets(0, 0, 0, 10));
                
          
                // Créer un cercle avec une couleur de remplissage
                //Circle circle = new Circle(10, Color.GREEN);
                //Label messageNumber = new Label("2");
                //messageNumber.setPrefSize(7, 11);
                
                //circle.setLayoutX(28);
                //circle.setLayoutY(41);
                //circle.setFill(Color.web("#50c984"));
                
                //messageNumber.setLayoutX(24);
                //messageNumber.setLayoutY(32);
                //messageNumber.setTextFill(Color.WHITE);
                
                //Pane pane = new Pane(circle, messageNumber);
                //pane.setPrefWidth(45);
                //pane.setPrefHeight(70);
                
                
                //Creation de Hbox
                HBox hbox = new HBox(imageView,vbox );
                hbox.setPrefSize(267, 78);
                hbox.setAlignment(Pos.CENTER_LEFT);
                
         
   
                   
                // Creation du button d'un conatct
               
                Button button = new Button();
                button.getStyleClass().add("gray-background");
                button.setStyle("-fx-stylesheet: \"@../vues/main.css\";");
                button.setPrefSize(267, 80);
                button.setGraphic(hbox);
                button.setOnAction(this::handleButtonAction);
                
                buttonTable[i]=button;
                
                //HBox h = (HBox)buttonTable[i].getGraphic();
                //System.out.println("Test de :::::::::::::::::::::::::::::::" + i);//C'est a efaacer apre????????????????????????????????????
                ++i;
                
                
                
                
                
                
                
                //Ajout du button au contactBox
                contactBox.getChildren().add(button);
            }
            
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    
        
        
        //**********************************************La connection et la creation du thread ********************************************************/
        String host = "localhost";
        int port = 1234;
        
            try {
                Socket socket = new Socket(host,port);
                outStream = new ObjectOutputStream(socket.getOutputStream());
                
                //La recherche de Senderusername depuis le fichier
                String usernamefile2 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/username.txt";     
                String usernameline2= "";
                try {
                    BufferedReader usernameReader2 = new BufferedReader(new FileReader(usernamefile2));
                    usernameline2 = usernameReader2.readLine();
                    usernameReader2.close();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }

                Conversation con = new Conversation( usernameline2, "username");
                
                if (loadKeys()) {
                    System.out.println("Keys loaded successfully.");
                } else {
                    System.out.println("Generating new keys...");
                    keyPair= generateDH();
                    privatekey=(DHPrivateKey)keyPair.getPrivate();
                    publicKey=(DHPublicKey)keyPair.getPublic();
                    saveKeys();
                }
                
                /*this.keyPair = Component.generateDH();
                this.privatekey = (DHPrivateKey) this.keyPair.getPrivate();
                this.publicKey = (DHPublicKey) this.keyPair.getPublic();*/
                
                
                con.setPublicKey(publicKey);
                outStream.writeObject(con);
                
              
                
                new Thread(()->  {
                    //System.out.println("thread cree??????????????????????????????");

                    try {
        	        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        	        while (true) {
        	            Conversation conn1 = (Conversation) in.readObject();
                            //STOCKER LE DESTINATAIRE ET LE MESSAGE DANS UN FICHIER
                            /**
                             * Le cas de plusieurs mesaages(C'est forcement le cas), je dois lire depuis une liste des messages
                             * Supposons pour le moment que j'ai un seul messsage
                            **/
                            String user = conn1.getSenderEmail();
                            String msg = conn1.getMessage(); 
                            
                            
                            System.out.println("Message crypte recu est : " + msg);
                            
                            if(verifySignature(msg, conn1)) {
	          				System.out.println("Meme signatutre!");
                                                System.out.println("Meme signatutre!");
                                                System.out.println("Meme signatutre!");
                                                System.out.println("Meme signatutre!");
                                                System.out.println("Meme signatutre!");
	          			}else {
	          				System.out.println("Message car la signature ne correspond pas!");
	          			}
                            
                            //Decryptage et decodage
                            /*byte[] MessageDecodeEtEncrypte = Base64.getDecoder().decode(msg);
                            SecretKey secretKey=new SecretKeySpec(this.secret.getBytes(),0,this.secret.length(),"AES");
                            Cipher cipher=Cipher.getInstance("AES");
                            cipher.init(Cipher.DECRYPT_MODE,secretKey);
                            byte[] MessageDecrypteEtDecoteBytes = cipher.doFinal(MessageDecodeEtEncrypte);
                            String MessageDecrypteEtDecote = new String(MessageDecrypteEtDecoteBytes);*/
                            String MessageDecrypteEtDecote = Controller.decryptet(msg, this.privatekey, this.ReceiverpublicKey);
                           
     
                            
                            System.out.println("Message decrypte  est : " + MessageDecrypteEtDecote);

                            //Le stockage dans le fichier temp(Tres Important pour le message en stock!!!!!!!!!!!!!!!!!!!!!!!)
                            String tempfilename1 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/temp.txt";
                            try (FileWriter tempwriter1 = new FileWriter(tempfilename1, true)) {
                                tempwriter1.write(user + "=>" + MessageDecrypteEtDecote + "\n");
                                tempwriter1.close();
                            } catch (IOException e) {
                                System.out.println(e.getMessage());
                            } 
                            
                            
                            /**
                            * Apres l'ajout je dois aussi afficher tout le contenu de ce fichier dans le listview
                            * Je dois egalement lire le contenu du fichier avec l'evenement de contact
                            * */
                            
                            /**
                             * Quand la liste remplacera le msg, je remplirai par la 
                             */
                            if(currentContact.equals(user)){
                                
                                listView.getItems().clear();
                                String tempfilename2 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/temp.txt";
                                try (BufferedReader tempreader2 = new BufferedReader(new FileReader(tempfilename2))) {
                                    String line2 = tempreader2.readLine();
                                    while (line2 != null) {
                                        String[] lineContent2 = line2.split("=>");//C'est un cas a revoir?????????????????????????????????????????????????????????????
                                        if(lineContent2.length==1){
                                            break;
                                        }
                                        
                                        String user2 = lineContent2[0];
                                        String msg2 = lineContent2[1];
                                        if(currentContact.equals(user2)){
                                            Platform.runLater(()->{
                                                listView.getItems().add(msg2);
                                            });  
                                        }
                                        line2 = tempreader2.readLine();
                                    }
                                }
                            }              
        	        }
        	    } catch (ClassNotFoundException e) {
        	        e.printStackTrace();
        	    } catch (IOException e) {
        	        e.printStackTrace();
        	    } catch (NoSuchAlgorithmException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (NoSuchPaddingException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (InvalidKeyException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalBlockSizeException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (BadPaddingException ex) {
                        Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }
        	}).start();
                
                
                
                
                
                
                
                //InputStreamReader isr = new InputStreamReader(is);
                //BufferedReader br =  new BufferedReader(isr);
                //pw = new PrintWriter(socket.getOutputStream(),true);
               
                
                //Ecrire tout message dans le socket nous devons le convertir en notre objet avnt de l'ecrire??????????
                //pw.println(email);
        
                     
                //la creation du thread__________
               /* new Thread(()->{
                    
                    try {
                        InputStream is = socket.getInputStream();
                        ObjectInputStream ois = new ObjectInputStream(is);
                        while(true){
                            //C'est a remplacer par notre objet
                            Conversation conn1 = (Conversation) ois.readObject();
                            //String response = br.readLine();
                            Platform.runLater(()->{
                                listView.getItems().add(response);
                            });
                        }
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                }).start();*/
                    
                //}
            } catch (IOException ex) {
                 System.out.println(ex.getMessage());
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

    //C'est pour buutonclose(minimize utton)
    Stage stage;
    @FXML
    public  void handleButtonAction(ActionEvent event) {
              
        if(event.getSource()==closebtn){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        
        
        
        
        
        
        if(event.getSource()==btnLogout){
  
            
            //La recherche de Senderusername depuis le fichier
            String usernamefile1 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/username.txt";     
            String usernameline= "";
            try {
                BufferedReader usernameReader1 = new BufferedReader(new FileReader(usernamefile1));
                usernameline = usernameReader1.readLine();
                usernameReader1.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
    
            
            //Signalement au serveur
            
            String host = "localhost";
            int port = 1234;
            Socket socket ;
            Conversation conversation=new Conversation( usernameline,"deconnecter");
                Conversation conversation_rec;
                ObjectInputStream entreeDepuisServeur;
                try {
                    socket = new Socket(host,port);
                    outStream = new ObjectOutputStream(socket.getOutputStream());   
                    outStream.writeObject(conversation);
            } catch (IOException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }   
            
            

            
            //Fermeture de l'ancienne fenetre
            try {
                
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
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
        
        
        if(event.getSource()==btnChat){    
            //Tous le traitememnt necessaires sont ici!!!!!!!!!!!!!!!!!!!
            System.out.println("Bonjour! C'est btnChat");
       
            
        }
        
        
        
        
        
        
        
        
        
        
        if(event.getSource()==btnGroup){     
           // Afficher la page des gestion des groupes dans notre cas(Ce n'est pas important)
        }
        
        if(event.getSource()==btnProfileManaging){ 
            
            
            //Tous le traitememnt necessaires sont ici!!!!!!!!!!!!!!!!!!!
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("/ma/ensate/client/vues/ProfileAndContact.fxml"));
                Node node = (Node)event.getSource();
                Scene currentScene = (Scene)node.getScene();
                currentScene.setRoot(newRoot);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
        }
        
        if(event.getSource()==btnSettings){      
            // Afficher la page des gestion des parametres dans notre cas(Ce n'est pas important)
        }
        
        
        for (Button button : buttonTable) {
            if (event.getSource() == button) {
                listView.getItems().clear();
     
                
                
                // Récupérer le HBox qui est le Graphic du bouton
                HBox hb = (HBox) button.getGraphic();
  
                for (Node enfant : hb.getChildren()) {
                    
                    if (enfant instanceof ImageView) {
                        profileImage_head = (ImageView) enfant;
                    }
    
                    if (enfant instanceof VBox) {
                        VBox  vb = (VBox) enfant;
                        nameLabel_head.setText(((Label)vb.getChildren().get(0)).getText());
                        currentContact = nameLabel_head.getText();
                        //System.out.println("C'est " + currentContact + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                 
                        String tempfilename3 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/temp.txt";     
                        try {
                            BufferedReader tempreader3 = new BufferedReader(new FileReader(tempfilename3));
                            String line3 = tempreader3.readLine();
                            while (line3 != null) {
                                
                                String[] lineContent3 = line3.split("=>");
                                if(lineContent3.length==1){
                                   break;
                                }
                                //System.out.println(Arrays.toString(lineContent3)); 
                                String user3 = lineContent3[0];
                                String msg3 = lineContent3[1];
                                if(currentContact.equals(user3)){
                                    Platform.runLater(()->{
                                        listView.getItems().add(msg3);
                                    });  
                                }
                                line3 = tempreader3.readLine();
                            }
                            tempreader3.close();
                        } catch (IOException ex) {
                            System.err.println(ex.getMessage());
                        }
                       
                        
                        
               
                        
                        //String ms = ((Label)vb.getChildren().get(1)).getText();
                        //listView.getItems().clear();
                        //listView.getItems().add(ms);
                        
                        //System.out.println(nameLabel_head.getText());
                        //System.out.println(nameLabel_head.getText());
                        //System.out.println(nameLabel_head.getText());
                        //System.out.println(nameLabel_head.getText());
                    }
                    
               
                    
                    
                    //L'autre insatnce est une pane dont je dois traiter apres???????????????????????
                    //if (enfant instanceof VBox) {
                       // VBox  vb = (VBox) enfant;
                       // nameLabel_head = (Label)vb.getChildren().get(0);
                    //}
                }

                
                           
                //La demande se statut au server!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!A azmizam!!!!!!!!!!!!!!!!!!!!!!!
                
                String host = "localhost";
                int port = 1234;
                Socket socket ;
                Conversation conversation=new Conversation( currentContact,"statut");
                Conversation conversation_rec;
                ObjectInputStream entreeDepuisServeur;
                try {
                    socket = new Socket(host,port);
                    outStream = new ObjectOutputStream(socket.getOutputStream());   
                    outStream.writeObject(conversation);
                    entreeDepuisServeur = new ObjectInputStream(socket.getInputStream());
                    conversation_rec = (Conversation) entreeDepuisServeur.readObject();
                               
                    if(conversation_rec.getLoginState().equals("N")){
                       OnlineLabel.setVisible(false);
                       onlineSign.setVisible(false);
                    }else{
                        OnlineLabel.setVisible(true);
                        onlineSign.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
                  
           
                
 
                
                
                
                //la gestion d'apparition
                main_form_head.setVisible(true);
                main_form_center.setVisible(true);
                main_form_bottom.setVisible(true);
                //System.out.println("Test Reussie!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            }
        }
        
      
        
        if(event.getSource()==btnSend){
            listView.getItems().clear();
            
            String host = "localhost";
            int port = 1234;
            Socket socket;
            try {
                socket = new Socket(host,port);
                outStream = new ObjectOutputStream(socket.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            
            //La recuperation du message
            String message = sendWritingLabel.getText();
            
            
            
            //La recherche de Senderusername depuis le fichier
            String usernamefile1 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/username.txt";     
            String usernameline= "";
            try {
                BufferedReader usernameReader1 = new BufferedReader(new FileReader(usernamefile1));
                usernameline = usernameReader1.readLine();
                usernameReader1.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            //L'ajout du message au fichier temp
           String tempfilename4 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/temp.txt";
           try (FileWriter tempwriter2 = new FileWriter(tempfilename4, true)) {
               tempwriter2.write(currentContact + "=>" + message + "\n");
               tempwriter2.close();
           } catch (IOException e) {
               System.out.println(e.getMessage());
              // System.out.println("Ecriture dans le fichier  depuis sendbtn a echouee!!!!!!!!!");
           } 
           //System.out.println("Ecriture dans le fichier depuis sendbtn reussie!");
                            

           //Ecriture sur le listView         
            String tempfilename5 = "C:/Users/hp/Desktop/Chat_App/src/ma/ensate/client/utils/temp.txt";     
            try {
                BufferedReader tempreader5 = new BufferedReader(new FileReader(tempfilename5));
                String line5 = tempreader5.readLine();
                while (line5 != null) {
                    String[] lineContent5 = line5.split("=>");
                    
                    if(lineContent5.length==1){
                        break;
                    }
                    //System.out.println("Verification du fichier");
                    String user5 = lineContent5[0];
                    String msg5 = lineContent5[1];
                    if(currentContact.equals(user5)){
                        Platform.runLater(()->{
                            listView.getItems().add(msg5);
                        });  
                    }
                    line5 = tempreader5.readLine();
                }
                tempreader5.close();
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }           
           
            
            //Crypter le message
            String MessageCrypteEtCode ="";
            try {
                MessageCrypteEtCode = Controller.encrypt(message, this.privatekey, this.ReceiverpublicKey);
                //System.out.println("La cle public du client 1 : " + Component.getPublicKeyC1().getY());
                System.out.println("Le message Original est :" + message );
                System.out.println("Le message crypte est :" + MessageCrypteEtCode );
                
                
                
                
                
                /*
                byte[] MessageCrypte;
                try {
                Cipher cipher = Cipher.getInstance("AES");
                SecretKey secretKey = new SecretKeySpec(this.secret.getBytes(),0,this.secret.length(),"AES");
                cipher.init(Cipher.ENCRYPT_MODE,secretKey);
                MessageCrypte = cipher.doFinal(message.getBytes());
                MessageCrypteEtCode = Base64.getEncoder().encodeToString(MessageCrypte);
                System.out.println("Le message Original est :" + message );
                System.out.println("Le message crypte est :" + MessageCrypteEtCode );
                } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (BadPaddingException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvalidKeyException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchPaddingException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalBlockSizeException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadPaddingException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            //L'envoir du message au serveur
            Conversation conversation=new Conversation(MessageCrypteEtCode,usernameline,currentContact, "message");
            try {
                
                signature(MessageCrypteEtCode);
		conversation.setPublicKeyy(publicKeyy);
		conversation.setSignature(signatureBytes);
                outStream.writeObject(conversation);
                //System.out.println("EMessage  : " + conversation.getMessage());
                //System.out.println("Sender : " + conversation.getSenderEmail());
                //System.out.println("Receiver : " + conversation.getReceiverEmail());
                //System.out.println("Type  : mesaage" );
                //System.out.println("Envoie au serveur de puis haki reussie!!");
            } catch (IOException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (InvalidKeyException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SignatureException ex) {
                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            sendWritingLabel.clear();
        }
    
    }

   // @FXML
    //private void handleButtonAction(MouseEvent event) {
            
    //}
    
    

    

    
    //la methode de cryptage
    
    public static  String encrypt(String message,DHPrivateKey privateKey,DHPublicKey publicKey) throws NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
		 
		   
        KeyAgreement keyAgree = KeyAgreement.getInstance("DH");
	        
        keyAgree.init(privateKey);
	keyAgree.doPhase(publicKey, true);
	     

        // Generate the shared secret
	        byte[] sharedSecret = keyAgree.generateSecret();
	        
	        byte[] truncatedSecret = Arrays.copyOf(sharedSecret, 16); 
	        SecretKey secretKey = new SecretKeySpec(truncatedSecret, "AES");
	        
	      //SecretKey secretKey = new SecretKeySpec(sharedSecret,"AES");
          Cipher cipher = Cipher.getInstance("AES");
        
          cipher.init(Cipher.ENCRYPT_MODE, secretKey);
          byte[] messageCipher = cipher.doFinal(message.getBytes());
           String MessageCrypteEtCode = Base64.getEncoder().encodeToString(messageCipher);
		 
		   return  MessageCrypteEtCode;
		 
	 }
    
    
    
    

    
    
    //la methode de decryptage
    
    public static  String decryptet(String cipherM,DHPrivateKey privateKey,DHPublicKey publicKey ) throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, NoSuchPaddingException {
		byte[] cipherBytes = Base64.getDecoder().decode(cipherM);
		 KeyAgreement keyAgree = KeyAgreement.getInstance("DH");
	        keyAgree.init(privateKey);
	        keyAgree.doPhase(publicKey, true);
	     // Generate the shared secret
	        byte[] sharedSecret = keyAgree.generateSecret();
	        byte[] truncatedSecret = Arrays.copyOf(sharedSecret, 16); 
	        SecretKey secretKey = new SecretKeySpec(truncatedSecret, "AES");
	        
	      //SecretKey secretKey = new SecretKeySpec(sharedSecret,"AES");
        Cipher cipher = Cipher.getInstance("AES");
      
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
       
        byte[] messageCipher = cipher.doFinal(cipherBytes);
		   return  new String(messageCipher);
        

    }
    
    
    
    
    
    
    
    
    //les dernieres methodes
    
    private static void saveKeys() throws IOException {
        try (ObjectOutputStream privateKeyOut = new ObjectOutputStream(new FileOutputStream(PRIVATE_KEY_FILE));
             ObjectOutputStream publicKeyOut = new ObjectOutputStream(new FileOutputStream(PUBLIC_KEY_FILE))) {
            privateKeyOut.writeObject(privatekey);
            publicKeyOut.writeObject(publicKey);
        }
    }
	 
	 private static boolean loadKeys() {
	        if (!Files.exists(Paths.get(PRIVATE_KEY_FILE)) || !Files.exists(Paths.get(PUBLIC_KEY_FILE))) {
	            return false;
	        }

	        try (ObjectInputStream privateKeyIn = new ObjectInputStream(Files.newInputStream(Paths.get(PRIVATE_KEY_FILE)));
	             ObjectInputStream publicKeyIn = new ObjectInputStream(Files.newInputStream(Paths.get(PUBLIC_KEY_FILE)));
                     ObjectInputStream ReceiverpublicKeyIn = new ObjectInputStream(Files.newInputStream(Paths.get(RECEIVER_PUBLIC_KEY_FILE)))){
                    
	            privatekey = (DHPrivateKey) privateKeyIn.readObject();
	            publicKey = (DHPublicKey) publicKeyIn.readObject();
                    ReceiverpublicKey = (DHPublicKey) ReceiverpublicKeyIn.readObject();
	            return true;
	        } catch (IOException | ClassNotFoundException e) {
	            e.printStackTrace();
	            return false;
	        }
    
         }
         
         
         
         static private KeyPair generateDH() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
		 BigInteger p = new BigInteger("136854320559533254631972018531648239055288867669838985125502705391274711330406592653790443182467551247301520514103701130874609730106273069268727590757391070255621645776765781386523946345350918195084812219134514091090275008257105213430625202483783179540364043610785892382208799852881617211445730232577907466911");
		    BigInteger g = BigInteger.valueOf(2);

		    DHParameterSpec dhSpec = new DHParameterSpec(p, g);
		    KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
		    keyGen.initialize(dhSpec);
		    KeyPair keyPair=keyGen.generateKeyPair();
		    
		     return keyPair;
	}
         
         
         
         
         private static void signature(String message) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
		 
		 KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
	        keyGen.initialize(2048);
                
                
	        KeyPair keyPair = keyGen.generateKeyPair();

	        // Get private and public keys
	        PrivateKey privateKey = keyPair.getPrivate();
	        publicKeyy = keyPair.getPublic();
                
                
	      Signature  signature = Signature.getInstance("SHA256withRSA");
	        signature.initSign(privateKey);
                
                
	     // Compute the hash value of the message
	        byte[] messageBytes = message.getBytes();
	        signature.update(messageBytes);
                
	       signatureBytes = signature.sign();
	      
		 
	 }
         
         private static boolean verifySignature(String message, Conversation conversation) {
		    try {
		        Signature sig = Signature.getInstance("SHA256withRSA");
		        sig.initVerify(conversation.getPublicKeyy());
		        sig.update(message.getBytes());

		        return sig.verify(conversation.getSignature());
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
		}

 
}

