package be.haexnet.sshcredentialmanagement.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class IndexControllerTest {
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = standaloneSetup(new IndexController()).build();
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

    private ResultActions doGetIndex() throws Exception {
        return mockMvc.perform(get("/"));
    }
}