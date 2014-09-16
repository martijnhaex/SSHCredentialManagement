package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.fixture.CredentialFixture;
import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.hamcrest.core.IsSame;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    @Mock
    ICredentialService credentialService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(new IndexController(credentialService)).build();
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
        mockMvc.perform(get("/{id}/delete", 1L))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/"));
        verify(credentialService).delete(1L);
    }

    @Test
    public void edit() throws Exception {
        final Credential credential = CredentialFixture.CREATE(3L);
        when(credentialService.findOne(3L)).thenReturn(Optional.of(credential));

        mockMvc.perform(get("/{id}/edit", 3L))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("update"))
                .andExpect(model().attribute("credential", IsSame.sameInstance(credential)));

    }

    private ResultActions doGetIndex() throws Exception {
        return mockMvc.perform(get("/"));
    }
}