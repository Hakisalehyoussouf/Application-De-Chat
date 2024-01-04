package ma.ensate.server.controllers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.interfaces.DHPublicKey;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import ma.ensate.client.utils.Conversation; // C'est un grand probleme??????????????????????????????????



public class ServerController extends Thread  {
    
        static final String DRIVER = "com.mysql.cj.jdbc.Driver";
        static final String DATABASEURL = "jdbc:mysql://localhost:3306/chat";
        static final String USERNAME = "root";
        static final String PASSWORD = "";

    
	public static ArrayList<Conver>  ActiveUser=new ArrayList<>(); 
        public static Map<String,DHPublicKey> contact=new HashMap();
	ObjectInputStream inStream;
	
	public static void main(String[] args) throws Exception {	
            new ServerController().start();
	}
        
        
	@Override
	public void run() {
            
            try {
		ServerSocket serverSocket =new ServerSocket(1234);
                System.out.println("Server is  running on port 1234");
                while(true) {
                    Socket socket =serverSocket.accept();//keeps the prog running
                    System.out.println("Connection reussie!");
                    //traitemant suivant le type de requette
                    //extrair
                    // verify sontype
                    //
                    Conver conversation=new Conver(socket);
                    
                    //Comment savoir le temps d'initialisation des  variables pour appeller des conditions sur ce thread (Important pour le projet du java??????????????????????????)??????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    
                    if(conversation.conversation.getType_requette().equals("inscription")) {
                        
                        String username = conversation.getConversation().getNom();
                        String email = conversation.getConversation().getEmail();
                        String password = conversation.getConversation().getPassword();
                        
                        
                        Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
                        
                        String query = "INSERT INTO client (username, email, password, statut)  VALUES('"+ username + "','" + email + "','" + password + "','N')";
                        Statement stmt = con.createStatement();
                        stmt.executeUpdate(query);
                        
                        conversation.getConversation().setLoginState("success");
                        
                        ObjectOutputStream sortieVersClient = conversation.getoutStream();
                        sortieVersClient.writeObject(conversation.getConversation());
                        
                        
                    
                    }
                    
                    Conver c = new Conver();
                    if(conversation.conversation.getType_requette().equals("deconnecter")) { 
                        System.out.println("Juste decconnexion!!!!!!!!!!!!!");
                        for(Conver C: ActiveUser) {			            			
                            if(C.getConversation().getSenderEmail().equals(conversation.getConversation().getSenderEmail())) {                              
                                //ActiveUser.remove(C);
                                c = C;
                            }    
                        }
                        ActiveUser.remove(c);
                        System.out.println("Juste decconnexion 2!!!!!!!!!!!!!");
                    }
                    
                    
                    if(conversation.conversation.getType_requette().equals("statut")) {
                        
                        boolean isActive = true;
                        System.out.println("Just verification!!!!!!!!!!!!!!!!!!!!!!!");
                        for(Conver C: ActiveUser) {
                            
                            Socket s=C.getSocket();	            			
                            ObjectInputStream inStream1 = C.getInStream();
	            	    ObjectOutputStream  outStream1 = C.getoutStream();
                            //InputStream inStream1 = s.getInputStream();
                            //ObjectInputStream inStream1 = C.getInStream();
                           
                            //Conversation conversation=new Conversation("Haki ce message est pour vous : "+conn.getMessage(),"haki","adnan", "message");
                            Conversation conn2 = C.getConversation();
                            if(conn2.getSenderEmail().equals(conversation.getConversation().getSenderEmail())) {
                                //conn2.setMessage(conn.getSenderEmail()+":  "+conn.getMessage());
                                conn2.setLoginState("Y");
                                isActive = false;
                                ObjectOutputStream sortieVersClient = conversation.getoutStream();
                                sortieVersClient.writeObject(conn2);
                            }   
                            
                            
                        }
                        if(isActive){
                            ObjectOutputStream sortieVersClient = conversation.getoutStream();
                            conversation.getConversation().setLoginState("N");
                            sortieVersClient.writeObject(conversation.getConversation());
                        }
               
                    }
      
                    if(conversation.conversation.getType_requette().equals("login")) {
                    
                
                        String email = conversation.getConversation().getEmail();
                        String password = conversation.getConversation().getPassword();
                        
                        conversation.getConversation().setLoginState(ServerController.login(email, password));
                        
                        ObjectOutputStream sortieVersClient = conversation.getoutStream();
                        sortieVersClient.writeObject(conversation.getConversation());
                    
                    }
                    
                    //   inStream = conversation.getInStream();
                    // Conversation conn = conversation.getConversation();
                    /*if(conversation.getConversation().getType_requette().equals("username")){
                        ActiveUser.add(conversation);
                        
                       String Q="select * from conversations where receiverEmail ='"+conversation.conversation.getSenderEmail()+"' order by date_envoi";
                        String Q2="delete from conversations where receiverEmail ='"+conversation.conversation.getSenderEmail()+"'";
                	Connection conex=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
                        Statement stmt = conex.createStatement();
                        ResultSet resultSet=stmt.executeQuery(Q);
                        while (resultSet.next()) {
                            Conversation message = new Conversation(resultSet.getString("Message"), resultSet.getString("senderEmail"), resultSet.getString("receiverEmail"), resultSet.getString("type_message"));
                            // Send message to client
                            conversation.getoutStream().writeObject(message);
				       
                            //objectOutputStream.flush();
				            
                        }
                        stmt.executeUpdate(Q2);
                        
                        
                        
                    }*/
                    if(conversation.getConversation().getType_requette().equals("message") || conversation.getConversation().getType_requette().equals("username")){
 
                        conversation.start();
   
                    }				 
		}
            }catch (SocketException e) {
	        // client disconnected
                System.out.println("Client disconnected.");
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }	
			
	}
	


	class Conver extends Thread {
	    	
            protected Socket socket;
            protected  ObjectInputStream inStream1;
            protected  ObjectOutputStream  outStream1;
            protected Conversation  conversation;
            
            public Conver(){};
            public Conver(Socket socket) throws SQLException, IOException, ClassNotFoundException {
                this.socket = socket;
	        this.inStream1 = new ObjectInputStream(socket.getInputStream());
	        this.outStream1=new  ObjectOutputStream(socket.getOutputStream());
                this.conversation=(Conversation) this.inStream1.readObject();
	            
            }
            
            
            public Conversation getConversation() {
                return conversation;
            }
            
            public void setConversation(Conversation conversation) {
                this.conversation = conversation;
            }
            
            public ObjectInputStream getInStream() {
                return inStream1;
            }
            public ObjectOutputStream getoutStream() {
                return  outStream1;
            }
            public void setInStream(ObjectInputStream inStream) {
                this.inStream1 = inStream;
            }
            
            public Socket getSocket() {
                return socket;
            }

            public void setSocket(Socket socket) throws IOException {
                this.socket = socket;
            }

			
	    	
	    //Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
       
            
            
	        
            @Override
	    public void run() {
                try{ 	
                    
                    if(this.conversation.getType_requette().equals("username")){
                        
                        contact.put(this.conversation.getSenderEmail(), this.conversation.getPublicKey());
                        ActiveUser.add(this);
                        
                       String Q="select * from conversations where receiverEmail ='"+conversation.getSenderEmail()+"' order by date_envoi";
                        String Q2="delete from conversations where receiverEmail ='"+conversation.getSenderEmail()+"'";
                	Connection conex=DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
                        Statement stmt = conex.createStatement();
                        ResultSet resultSet=stmt.executeQuery(Q);
                        while (resultSet.next()) {
                            Conversation message = new Conversation(resultSet.getString("Message"), resultSet.getString("senderEmail"), resultSet.getString("receiverEmail"), resultSet.getString("type_message"));
                            // Send message to client
                            this.getoutStream().writeObject(message);
		
                            //objectOutputStream.flush();
				            
                        }
                        stmt.executeUpdate(Q2);
                        
                        
                        
                    }else{
                        
                    
                        
            
	            Conversation conn = this.conversation;
                     Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/chat","root","");
	            // System.out.println(" Le mesage a envoyer :"+ conn.getMessage());
                    //inStreamm.writeObject(conn);
                    
                    //String query = "SELECT *  FROM client WHERE statut='Y' AND email='" + conn.getSenderEmail() + "';";
                    String query1 = "INSERT INTO conversations (senderEmail, receiverEmail, type_message, message, date_envoi) VALUES ('"
	            	                + conn.getSenderEmail() + "','" + conn.getReceiverEmail() + "','" + conn.getType() + "','" 
	            	                + conn.getMessage() + "', NOW());";

	            Statement stmt = con.createStatement();
                    
	            //if(res.next()) {
                    
                        boolean isActive=false;
                        //System.out.println(this.conversation.getMessage());
                       
                        for(Conver C: ActiveUser) {
                            Socket s=C.getSocket();	            			
                            ObjectInputStream inStream1 = C.getInStream();
	            	    ObjectOutputStream  outStream1 = C.getoutStream();
                            //InputStream inStream1 = s.getInputStream();
                            //ObjectInputStream inStream1 = C.getInStream();
                           
                            //Conversation conversation=new Conversation("Haki ce message est pour vous : "+conn.getMessage(),"haki","adnan", "message");
                            Conversation conn2 = C.getConversation();
                            if(conn2.getSenderEmail().equals(conn.getReceiverEmail())) {
                                //conn2.setMessage(conn.getSenderEmail()+":  "+conn.getMessage());
                                C.getoutStream().writeObject(conn);
                                isActive = true;
                            }	            			
                        }
                    //}else{
	            	if(!isActive) {                       
                            stmt.executeUpdate(query1);
                        }
                    //}
	            		
                    }} catch (SocketException e) {
	                 // client disconnected
	                 System.out.println("Client disconnected.");
	             } catch (IOException e) {
	                 e.printStackTrace();
	             } catch (SQLException ex) {
                    Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
                } /*catch (SQLException e) {
	                 e.printStackTrace();
	             } */
	          } 
            }
      
	
        
        
        
    //Les fonctions e login
        
    public static Connection getConnection(){
        try{
            // load the driver
            Class.forName(DRIVER);
            //
            Connection conn = DriverManager.getConnection(DATABASEURL, USERNAME, PASSWORD);
            //  System.out.println("Database connected !");
            return conn;
        } catch (Exception e){
            System.out.println(e);
        }
        return null;  //  ??
    }


    public static String login(String email, String password){
        String success="" ;
        String error="" ;
        String username="";
      
        try(Connection conn = ServerController.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select username, email, password from client where email='"+email+"' and password='"+password+"'");
        ) {
            if(rs.next()){
                
              /*
                KeyStore keyStore = KeyStore.getInstance("JKS");
                FileInputStream keyStoreFile = new FileInputStream("C:\\Users\\hp\\Desktop\\Chat_App\\lib\\lacalkey.jks");
                keyStore.load(keyStoreFile, "99919632".toCharArray());

                // Create a TrustManager that trusts the certificates in the TrustStore
                KeyStore trustStore = KeyStore.getInstance("PKCS12");
                FileInputStream trustStoreFile = new FileInputStream("C:\\Users\\hp\\Desktop\\Chat_App\\lib\\truststore");
                trustStore.load(trustStoreFile, "99919632".toCharArray());
                
                
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(trustStore);

                // Create an SSLContext with the necessary protocols, key, and trust managers
                SSLContext sslContext = SSLContext.getInstance("TLS");
                KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                keyManagerFactory.init(keyStore, "99919632".toCharArray());
                sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

                // Create an SSLServerSocket using the SSLContext
                SSLServerSocketFactory sslServerSocketFactory = sslContext.getServerSocketFactory();
                SSLServerSocket serverSocket = (SSLServerSocket) sslServerSocketFactory.createServerSocket(1234);
            */
            
                
             
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                
                username = rs.getString("username");
                success += "success" + " " + username;
                return success; 
            }else {
                error += "error";
                return error;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }   /*catch (KeyStoreException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (CertificateException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnrecoverableKeyException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (KeyManagementException ex) {
                Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
            }*/
        return null;
    }
    
    
    
    
    
}        
        
        
        
    





        
       /*
            
        /*public void broadcastMessage(String message){
      
                try {
                    for(Conver conv:ActiveUser){
                        PrintWriter pw2 = new PrintWriter(conv.socketClient.getOutputStream(), true);
                        pw2.println(message);
                    }
                } catch (IOException ex) {
                    System.err.println(ex.getMessage()); 
                }
                
        }
        
        
        
        
        @Override
        public void run() {
            try {
                InputStream is = socketClient.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader br =  new BufferedReader(isr);
                PrintWriter pw = new PrintWriter(socketClient.getOutputStream(),true);
                
                
                String ipClient = socketClient.getRemoteSocketAddress().toString();
                System.out.println("Conversation du client d'adresse IP= " + ipClient);
                
                while(true){
                    String req = br.readLine(); //Elle sear remplace npar notre obj
                    broadcastMessage(req); //C'est la cible
                }
            } catch (IOException ex) {
                 System.err.println(ex.getMessage()); 
            } 
      
        }*/
        
        
    
    
    
    
    
    