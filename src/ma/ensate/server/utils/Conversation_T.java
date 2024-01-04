package ma.ensate.server.utils;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class Conversation_T implements  Serializable {

	
    private String type_requette;//message or login or insrciption
    private String senderEmail;
    private String receiverEmail;
    private String message ;
    private String nom;
    private String type="txt";
    private Socket socket;
    private byte[] imageData;
    
    public Conversation_T (String mes,String senderEmail,String receiverEmail,String type_requette ) {    
        
	this.message=mes;	
	this.receiverEmail=receiverEmail;
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;
		
    }
        
            
    public Conversation_T (String T,String mes,String senderEmail,String receiverEmail,String type_requette ) {
		
	this.message=mes;
	this.receiverEmail=receiverEmail;
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;	
    }




    public Conversation_T (String senderEmail ,String type_requette) {
		
	this.senderEmail=senderEmail;
	this.type_requette=type_requette;		
    }



    public ObjectInputStream getInStream() throws IOException {
        return new ObjectInputStream(socket.getInputStream());
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
        this.imageData = imageData;
    }
	
}
