
package ma.ensate.client.utils;

import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CompomentInter {
    
    public static Component component;
    
    static{
        try {
            component = new Component();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(CompomentInter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAlgorithmParameterException ex) {
            Logger.getLogger(CompomentInter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
