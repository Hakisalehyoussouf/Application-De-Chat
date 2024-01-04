package ma.ensate.client.utils;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;
import java.security.PublicKey;
import javax.crypto.interfaces.DHPublicKey;

public class Conversation implements  Serializable {

	
    private String type_requette;//message or login or insrciption
    private String senderEmail;
    private String receiverEmail;
    private String message ;
    private String nom;
    private String type="txt";
    private Socket socket;
    private byte[] imageData;
    private String email;
    private String password;
    private String loginState = "";
    private String prenom;
    private String code;
    private DHPublicKey publicKey;
    
    
    private byte[] signature;
	private PublicKey publicKeyy;
    public byte[] getSignature() {
		return signature;
    }
	public void setSignature(byte[] signature) {
		this.signature = signature;
	}


	
        public PublicKey getPublicKeyy() {
            return publicKeyy;
		}
		public void setPublicKeyy(PublicKey publicKeyy) {
			this.publicKeyy = publicKeyy;
		}
    
    
    
    
    

    public Conversation (){
    }
    public Conversation ( String email, String password, String type_requette){
        this.email = email;
        this.password = password;
        this.type_requette = type_requette;
    }

    public Conversation(String nom1, String prenom1, String password1, String email1, String code1,String type_requette ) {
        
		this.nom = nom1;
		this.prenom = prenom1;
		this.password = password1;
		this.email = email1;
		this.code = code1;
                this.type_requette = "inscription";
	}

    
    public Conversation (String mes,String senderEmail,String receiverEmail,String type_requette ) {    
        
	this.message=mes;	
	this.receiverEmail=receiverEmail;
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;
		
    }
        
            
    public Conversation (String T,String mes,String senderEmail,String receiverEmail,String type_requette ) {
		
	this.message=mes;
	this.receiverEmail=receiverEmail;
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;	
    }




    public Conversation (String senderEmail ,String type_requette) {
		
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;		
    }



    public ObjectInputStream getInStream() throws IOException {
        return new ObjectInputStream(getSocket().getInputStream());
    }


    public String getType_requette() {
	return type_requette;
    }
    public void setType_requette(String type_requette) {
	this.type_requette = type_requette;
    }
		
    public String getMessage() {
	return message;
    }
    
    public void setMessage(String message) {
    	this.message = message;
    }

    public String getNom() {
	return nom;
    }
    
    public String getType() {
	return type;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getSenderEmail() {
	return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
	this.senderEmail = senderEmail;
    }
    
    public String getReceiverEmail() {
	return receiverEmail;
    }
    
    public void setReceiverEmail(String receiverEmail) {
	this.receiverEmail = receiverEmail;
    }
    
    public void setType(String type) {
	this.type = type;
    }
	
    //img 
    public byte[] getImageData() {
	return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.setImageData(imageData);
    }
	
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginState() {
        return loginState;
    }
    public void setLoginState(String loginState) {
        this.loginState = loginState;
    }
    
    
    


	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}




	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

    /**
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * @param socket the socket to set
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }


    
    /**
     * @return the publicKey
     */
    public DHPublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * @param publicKey the publicKey to set
     */
    public void setPublicKey(DHPublicKey publicKey) {
        this.publicKey = publicKey;
    }

}
