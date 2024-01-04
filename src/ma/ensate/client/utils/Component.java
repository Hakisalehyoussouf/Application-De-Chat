package ma.ensate.client.utils;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.interfaces.DHPrivateKey;
import javax.crypto.interfaces.DHPublicKey;
import javax.crypto.spec.DHParameterSpec;
import ma.ensate.client.controllers.Controller;


public class Component {
    
    //pour cient1

  
    private  KeyPair keyPairC1;      
    private  DHPrivateKey privatekeyC1;
    private  DHPublicKey publicKeyC1;
    
    //pour cient2
    private KeyPair keyPairC2;      
    private DHPrivateKey privatekeyC2;
    private DHPublicKey publicKeyC2;
    
    public  Component() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException{
            
            keyPairC1 = Component.generateDH();
            privatekeyC1 = (DHPrivateKey) keyPairC1.getPrivate();
            publicKeyC1 = (DHPublicKey) keyPairC1.getPublic();

            keyPairC2 = Component.generateDH();
            privatekeyC2 = (DHPrivateKey) keyPairC2.getPrivate();
            publicKeyC2 = (DHPublicKey) keyPairC2.getPublic();
        
    }
    
    
    //les valeurs des cles prives et public
  /* static{
        
        try{
            keyPairC1 = Component.generateDH();
            privatekeyC1 = (DHPrivateKey) keyPairC1.getPrivate();
            publicKeyC1 = (DHPublicKey) keyPairC1.getPublic();

            keyPairC2 = Component.generateDH();
            privatekeyC2 = (DHPrivateKey) keyPairC2.getPrivate();
            publicKeyC2 = (DHPublicKey) keyPairC2.getPublic();

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    

        
    //Methode generant la cle
    public static KeyPair generateDH() throws NoSuchAlgorithmException, InvalidAlgorithmParameterException {
    BigInteger p = new BigInteger("136854320559533254631972018531648239055288867669838985125502705391274711330406592653790443182467551247301520514103701130874609730106273069268727590757391070255621645776765781386523946345350918195084812219134514091090275008257105213430625202483783179540364043610785892382208799852881617211445730232577907466911");
		    
        BigInteger g = BigInteger.valueOf(2);

        DHParameterSpec dhSpec = new DHParameterSpec(p, g);
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
        keyGen.initialize(dhSpec);
        KeyPair keyPair=keyGen.generateKeyPair();
		    
        return keyPair;
    }

    /**
     * @return the keyPairC1
     */
    public KeyPair getKeyPairC1() {
        return keyPairC1;
    }

    /**
     * @param keyPairC1 the keyPairC1 to set
     */
    public void setKeyPairC1(KeyPair keyPairC1) {
        this.keyPairC1 = keyPairC1;
    }

    /**
     * @return the privatekeyC1
     */
    public DHPrivateKey getPrivatekeyC1() {
        return privatekeyC1;
    }

    /**
     * @param privatekeyC1 the privatekeyC1 to set
     */
    public void setPrivatekeyC1(DHPrivateKey privatekeyC1) {
        this.privatekeyC1 = privatekeyC1;
    }

    /**
     * @return the publicKeyC1
     */
    public DHPublicKey getPublicKeyC1() {
        return publicKeyC1;
    }

    /**
     * @param publicKeyC1 the publicKeyC1 to set
     */
    public void setPublicKeyC1(DHPublicKey publicKeyC1) {
        this.publicKeyC1 = publicKeyC1;
    }

    /**
     * @return the keyPairC2
     */
    public KeyPair getKeyPairC2() {
        return keyPairC2;
    }

    /**
     * @param keyPairC2 the keyPairC2 to set
     */
    public void setKeyPairC2(KeyPair keyPairC2) {
        this.keyPairC2 = keyPairC2;
    }

    /**
     * @return the privatekeyC2
     */
    public DHPrivateKey getPrivatekeyC2() {
        return privatekeyC2;
    }

    /**
     * @param privatekeyC2 the privatekeyC2 to set
     */
    public void setPrivatekeyC2(DHPrivateKey privatekeyC2) {
        this.privatekeyC2 = privatekeyC2;
    }

    /**
     * @return the publicKeyC2
     */
    public DHPublicKey getPublicKeyC2() {
        return publicKeyC2;
    }

    /**
     * @param publicKeyC2 the publicKeyC2 to set
     */
    public void setPublicKeyC2(DHPublicKey publicKeyC2) {
        this.publicKeyC2 = publicKeyC2;
    }
    
    
}


/*
public class Component {
    
    // Key pair for client 1
    public static final KeyPair keyPairC1;
    public static final DHPrivateKey privateKeyC1;
    public static final DHPublicKey publicKeyC1;
    
    // Key pair for client 2
    public static final KeyPair keyPairC2;
    public static final DHPrivateKey privateKeyC2;
    public static final DHPublicKey publicKeyC2;
    
    static {
        try {
            // Generate the DH key pair once
            BigInteger p = new BigInteger("136854320559533254631972018531648239055288867669838985125502705391274711330406592653790443182467551247301520514103701130874609730106273069268727590757391070255621645776765781386523946345350918195084812219134514091090275008257105213430625202483783179540364043610785892382208799852881617211445730232577907466911");
            BigInteger g = BigInteger.valueOf(2);
            DHParameterSpec dhSpec = new DHParameterSpec(p, g);
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DH");
            keyGen.initialize(dhSpec);
            
            // Generate key pair for client 1
            keyPairC1 = keyGen.generateKeyPair();
            privateKeyC1 = (DHPrivateKey) keyPairC1.getPrivate();
            publicKeyC1 = (DHPublicKey) keyPairC1.getPublic();
            
            // Generate key pair for client 2
            keyPairC2 = keyGen.generateKeyPair();
            privateKeyC2 = (DHPrivateKey) keyPairC2.getPrivate();
            publicKeyC2 = (DHPublicKey) keyPairC2.getPublic();
        } catch (NoSuchAlgorithmException | InvalidAlgorithmParameterException ex) {
            // Handle exception
        }
    }
}*/