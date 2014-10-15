package be.haexnet.sshcredentialmanagement.config;

import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ApplicationIntegrationTest {
    @Autowired
    ICredentialService credentialService;

    @Test
    public void canAutowire() throws Exception {
        assertThat(credentialService).isNotNull();
    }
}