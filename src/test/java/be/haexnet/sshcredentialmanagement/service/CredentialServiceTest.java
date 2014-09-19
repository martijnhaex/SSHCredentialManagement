package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.fixture.CredentialFixture;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.repository.CredentialRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CredentialServiceTest {

    @Mock
    CredentialRepository credentialRepository;

    ICredentialService credentialService;

    List<Credential> credentials;

    @Before
    public void setUp() throws Exception {
        credentialService = new CredentialService(credentialRepository);
        credentials = CredentialFixture.LIST();

        when(credentialRepository.findAll()).thenReturn(credentials);
    }

    @Test
    public void findAll() throws Exception {
        final List<Credential> credentials = Collections.emptyList();
        when(credentialRepository.findAll()).thenReturn(credentials);
        assertThat(credentialService.findAll()).isSameAs(credentials);
    }

    @Test
    public void batchSave() throws Exception {
        final List<Credential> credentials = Collections.emptyList();
        credentialService.batchSave(credentials);
        verify(credentialRepository).batchSave(credentials);
    }

    @Test
    public void delete() throws Exception {
        credentialService.delete("1");

        credentials.remove(2);
        verify(credentialRepository).batchSave(credentials);
    }

    @Test
    public void findOneHappyPath() throws Exception {
        final Optional<Credential> credential = credentialService.findOne("3");

        assertThat(credential.isPresent()).isTrue();
        assertThat(credential.get()).isSameAs(credentials.get(1));
    }

    @Test
    public void findOneUnhappyPath() throws Exception {
         assertThat(credentialService.findOne("6").isPresent()).isFalse();
    }

}