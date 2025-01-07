package de.g10f.keycloak.credential.hash;

import org.junit.Test;
import org.keycloak.credential.hash.PasswordHashProvider;
import org.keycloak.models.credential.PasswordCredentialModel;

import static org.junit.Assert.*;

public class SSHAPasswordHashProviderTest {

    @Test
    public void policyCheck() {
    }

    @Test
    public void encodedCredential() {
        SSHAPasswordHashProviderFactory sshaPasswordHashProviderFactory = new SSHAPasswordHashProviderFactory();
        PasswordHashProvider sshaPasswordHashProvider = sshaPasswordHashProviderFactory.create(null);
        PasswordCredentialModel credentialModel = sshaPasswordHashProvider.encodedCredential("Ich esse gerne Bratwurst", -1);
        String secret = credentialModel.getPasswordSecretData().getValue();
        assertEquals("{SSHA}", secret.substring(0, 6));

    }

    @Test
    public void verify() {
        SSHAPasswordHashProviderFactory sshaPasswordHashProviderFactory = new SSHAPasswordHashProviderFactory();
        PasswordHashProvider sshaPasswordHashProvider = sshaPasswordHashProviderFactory.create(null);
        PasswordCredentialModel pwdCredentialModel = PasswordCredentialModel.createFromValues("ssha", null, -1, "{SSHA}A95jbMDX8Mi3YC2MpZ8IlTIVu6QOka/mM7/XiQ==");
        boolean isPwdValid = sshaPasswordHashProvider.verify("Ich esse gerne Bratwurst", pwdCredentialModel);
        assertTrue(isPwdValid);
    }

    @Test
    public void close() {
    }
}