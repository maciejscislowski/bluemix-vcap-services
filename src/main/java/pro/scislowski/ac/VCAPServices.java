package pro.scislowski.ac;

import java.util.Map;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public interface VCAPServices {

    String CLOUDANT = "cloudantNoSQLDB";
    String CLEARDB = "clearDB";
    String ALCHEMY_API = "alchemy_api";

    String USERNAME = "username";
    String PASSWORD = "password";

    boolean contains(String type);

    Map<String, String> getCredentials(String type);

}
