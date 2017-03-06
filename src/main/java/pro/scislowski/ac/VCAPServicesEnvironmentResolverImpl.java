package pro.scislowski.ac;

/**
 * @author Maciej.Scislowski@gmail.com
 */
public class VCAPServicesEnvironmentResolverImpl implements VCAPServicesResolver {

    @Override
    public String getVCAPServices() {
        String vcapServices = System.getenv("VCAP_SERVICES");
        if (vcapServices == null) {
            throw new RuntimeException("VCAP_SERVICES cannot be null");
        }
        return vcapServices;
    }

}
