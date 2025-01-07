package de.g10f.keycloak.credential.hash;

import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.PasswordPolicy;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

public class SSHAPasswordHashProvider implements PasswordHashProvider {
    private final String providerId;

    public SSHAPasswordHashProvider(String providerId) {
        this.providerId = providerId;
    }

    @Override
    public boolean policyCheck(PasswordPolicy policy, PasswordCredentialModel credential) {
        return true;
    }

    @Override
    public PasswordCredentialModel encodedCredential(String rawPassword, int iterations) {
        LdapShaPasswordEncoder ldapShaPasswordEncoder = new LdapShaPasswordEncoder();
        String encodedPassword = ldapShaPasswordEncoder.encode(rawPassword);
        return PasswordCredentialModel.createFromValues(providerId, null, iterations, encodedPassword);
    }

    @Override
    public boolean verify(String rawPassword, PasswordCredentialModel credential) {
        String encodedPassword = credential.getPasswordSecretData().getValue();
        return (new LdapShaPasswordEncoder()).matches(rawPassword, encodedPassword);
    }

    @Override
    public void close() {
    }
}
