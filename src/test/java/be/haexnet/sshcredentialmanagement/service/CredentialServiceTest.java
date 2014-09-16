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

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CredentialServiceTest {
    @Mock
    CredentialRepository credentialRepository;

    ICredentialService credentialService;

    @Before
    public void setUp() throws Exception {
        credentialService = new CredentialService(credentialRepository);
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
        final List<Credential> credentials = CredentialFixture.LIST();

        when(credentialRepository.findAll()).thenReturn(credentials);

        credentialService.delete(1L);

        credentials.remove(2);
        verify(credentialRepository).batchSave(credentials);
    }
}