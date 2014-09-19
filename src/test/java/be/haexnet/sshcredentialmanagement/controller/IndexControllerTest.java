package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialUpdateCommand;
import be.haexnet.sshcredentialmanagement.controller.mapper.CredentialUpdateMapper;
import be.haexnet.sshcredentialmanagement.fixture.CredentialFixture;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {

    @Mock
    ICredentialService credentialService;
    @Mock
    CredentialUpdateMapper credentialUpdateMapper;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(new IndexController(credentialService, credentialUpdateMapper)).build();
    }

    @Test
    public void indexStatus() throws Exception {
        doGetIndex()
                .andExpect(status().isOk());
    }

    @Test
    public void indexForward() throws Exception {
        doGetIndex()
                .andExpect(forwardedUrl("index"));
    }

    @Test
    public void indexData() throws Exception {
        final List<Credential> credentials = Collections.emptyList();
        when(credentialService.findAll()).thenReturn(credentials);
        doGetIndex()
                .andExpect(model().attribute("credentials", sameInstance(credentials)));
    }

    @Test
    public void delete() throws Exception {
        mockMvc.perform(get("/{id}/delete", "1"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/"));
        verify(credentialService).delete("1");
    }

    @Test
    public void edit() throws Exception {
        final Optional<Credential> credential = Optional.of(CredentialFixture.CREATE("3"));
        final CredentialUpdateCommand command = new CredentialUpdateCommand();

        when(credentialService.findOne("3")).thenReturn(credential);
        when(credentialUpdateMapper.map(credential)).thenReturn(command);

        mockMvc.perform(get("/{id}/edit", "3"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("update"))
                .andExpect(model().attribute("credential", sameInstance(command)));
    }

    @Test
    public void update() throws Exception {
        final String credentialId = "15";
        final String server = "SERVER UPDATE";
        final String password = "password";
        final String username = "USER UPDATE";
        final String url = "192.168.1.15";
        final Credential credential = Credential.of(credentialId, server, password, "USER UPDATE", url);

        when(credentialUpdateMapper.map(any(CredentialUpdateCommand.class))).thenReturn(credential);

        mockMvc.perform(post("/edit")
                .param("id", credentialId.toString())
                .param("server", server)
                .param("password", password)
                .param("username", username)
                .param("url", url))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/"));

        verify(credentialService).update(credential);
    }

    private ResultActions doGetIndex() throws Exception {
        return mockMvc.perform(get("/"));
    }
}