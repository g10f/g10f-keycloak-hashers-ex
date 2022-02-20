package de.g10f.keycloak.credential.hash;

import org.keycloak.Config;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.credential.hash.PasswordHashProviderFactory;
import org.keycloak.credential.hash.Pbkdf2PasswordHashProvider;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

/**
 * PBKDF2 Password Hash provider with HMAC using SHA1
 *
 * @author <a href"mailto:mail@g10f.de">Gunnar Scherf</a>
 */
public class Pbkdf2Sha1PasswordHashProviderFactory implements PasswordHashProviderFactory {

    public static final String ID = "pbkdf2-sha1";

    public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA1";

    public static final int DEFAULT_ITERATIONS = 27500;

    @Override
    public PasswordHashProvider create(KeycloakSession session) {
        return new Pbkdf2PasswordHashProvider(ID, PBKDF2_ALGORITHM, DEFAULT_ITERATIONS);
    }

    @Override
    public void init(Config.Scope config) {
    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void close() {
    }
}
