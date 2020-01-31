package utilidades;

import org.apache.commons.codec.digest.DigestUtils;

public class Utilidades {
    public static String Encriptar(String cad){
        return DigestUtils.sha256Hex(cad);
    }
}
