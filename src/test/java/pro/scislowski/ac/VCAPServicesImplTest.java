package pro.scislowski.ac;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class VCAPServicesImplTest {

    private static final String VCAP_SERVICES_VALUE = "{'alchemy_api': [{'credentials': {'url': 'URL_VALUE', 'note': 'note', 'apikey': 'API_KEY_VALUE'}}]," +
            "'cloudantNoSQLDB': [{'credentials': {'username': 'USERNAME_VALUE', 'password': 'PASSWORD_VALUE', 'host': 'HOST_VALUE', 'port': 443, 'url': 'URL_VALUE'}}]}";
    private VCAPServices vcapServices;

    @Before
    public void setUp() throws Exception {
        vcapServices = new VCAPServicesImpl(() -> VCAP_SERVICES_VALUE);
    }

    @Test
    public void whenCheckIfContainsCloudantCredentialsThenReturnsTrue() throws Exception {
        boolean containsCloudantCredentials = vcapServices.contains(VCAPServices.CLOUDANT);
        Assert.assertTrue(containsCloudantCredentials);
    }

    @Test
    public void whenCheckIfContainsClearDBCredentialsThenReturnsFalse() throws Exception {
        boolean containsClearDBCredentials = vcapServices.contains(VCAPServices.CLEARDB);
        Assert.assertFalse(containsClearDBCredentials);
    }

    @Test
    public void whenGetCloudantCredentialsThenReturnThatCredentials() throws Exception {
        Map<String, String> credentials = vcapServices.getCredentials(VCAPServices.CLOUDANT);
        String username = credentials.get(VCAPServices.USERNAME);
        String password = credentials.get(VCAPServices.PASSWORD);
        Assert.assertEquals("USERNAME_VALUE", username);
        Assert.assertEquals("PASSWORD_VALUE", password);
    }
}
