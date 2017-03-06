package pro.scislowski.ac;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class VCAPServicesImpl implements VCAPServices {

    private static final Logger LOG = Logger.getLogger(VCAPServicesImpl.class.getName());
    private static final String CREDENTIALS_FIELD = "credentials";
    private JsonObject vcapServices;

    public VCAPServicesImpl(VCAPServicesResolver resolver) {
        String vcapServicesEnv = resolver.getVCAPServices();

        if (vcapServicesEnv == null) {
            throw new RuntimeException("VCAP_SERVICES cannot be null");
        }

        JsonObject vcapServices = null;
        try {
            JsonParser e = new JsonParser();
            vcapServices = (JsonObject) e.parse(vcapServicesEnv);
        } catch (JsonSyntaxException e) {
            LOG.log(Level.INFO, "Cannot parse VCAP_SERVICES", e);
        }

        this.vcapServices = vcapServices;
    }

    @Override
    public boolean contains(String type) {
        return vcapServices.get(type) != null;
    }

    @Override
    public Map<String, String> getCredentials(String type) {
        JsonObject obj = vcapServices.getAsJsonArray(type).get(0).getAsJsonObject().getAsJsonObject(CREDENTIALS_FIELD);
        Set<Map.Entry<String, JsonElement>> entries = obj.entrySet();
        Map<String, String> credentials = new HashMap<>();
        for (Map.Entry<String, JsonElement> entry : entries) {
            credentials.put(entry.getKey(), entry.getValue().getAsString());
        }
        return credentials;
    }

}
