package be.haexnet.sshcredentialmanagement.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CredentialServiceTest {
    ICredentialService credentialService = new CredentialService();

    @Test
    public void findAll() throws Exception {
        assertThat(credentialService.findAll()).isEqualTo(Collections.emptyList());
    }
}