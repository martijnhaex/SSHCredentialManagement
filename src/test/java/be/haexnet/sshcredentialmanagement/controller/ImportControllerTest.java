package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import be.haexnet.sshcredentialmanagement.util.CredentialParser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ImportControllerTest {

    @Mock
    ICredentialService credentialService;
    @Mock
    CredentialParser credentialParser;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(new ImportController(credentialService, credentialParser)).build();
    }

    @Test
    public void showBatchImportStatus() throws Exception {
        doGetBatchImport()
                .andExpect(status().isOk());
    }

    @Test
    public void showBatchImportForward() throws Exception {
        doGetBatchImport()
                .andExpect(forwardedUrl("batchImport"));
    }

    @Test
    public void showBatchImportHasCommand() throws Exception {
        doGetBatchImport()
                .andExpect(model().attributeExists("command"));
    }

    @Test
    public void doBatchImportStatus() throws Exception {
        doPostBatchImport()
                .andExpect(status().isMovedTemporarily());
    }

    @Test
    public void doBtachImportRedirect() throws Exception {
        doPostBatchImport()
                .andExpect(redirectedUrl("/"));
    }

    @Test
    public void showImportStatus() throws Exception {
        doGetImport()
                .andExpect(status().isOk());
    }

    @Test
    public void showImportForward() throws Exception {
        doGetImport()
                .andExpect(forwardedUrl("import"));
    }

    @Test
    public void showImportHasCommand() throws Exception {
        doGetImport()
                .andExpect(model().attributeExists("command"));
    }

    private ResultActions doGetBatchImport() throws Exception {
        return mockMvc.perform(get("/batch-import"));
    }

    private ResultActions doPostBatchImport() throws Exception {
        return mockMvc.perform(post("/batch-import").param("credentials", "SERVER1,tomcat,tomcat,192.168.1.1\nSERVER2,root,tomcat,192.168.1.2"));
    }

    private ResultActions doGetImport() throws Exception {
        return mockMvc.perform(get("/create"));
    }
}