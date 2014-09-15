package be.haexnet.sshcredentialmanagement.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ImportControllerTest {
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(new ImportController()).build();
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

    private ResultActions doGetImport() throws Exception {
        return mockMvc.perform(get("/batch-import"));
    }
}