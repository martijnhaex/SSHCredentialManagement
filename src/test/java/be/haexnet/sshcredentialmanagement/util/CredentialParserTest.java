package be.haexnet.sshcredentialmanagement.util;

import be.haexnet.sshcredentialmanagement.model.Credential;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CredentialParserTest {
    CredentialParser credentialParser = new CredentialParser();

    @Test
    public void parseFromStringNull() throws Exception {
        assertThat(parseFromString(null)).isEmpty();
    }

    @Test
    public void parseFromStringEmpty() throws Exception {
        assertThat(parseFromString("")).isEmpty();
    }

    @Test
    public void parseFromStringToLowElement() throws Exception {
        assertThat(parseFromString("tomcat,tomcat,192.168.1.1")).isEmpty();
    }

    @Test
    public void parseFromStringToManyElement() throws Exception {
        final List<Credential> credentials = parseFromString("SERVER1,tomcat,tomcat,192.168.1.1,15");

        assertThat(credentials).hasSize(1);
        assertThat(credentials.get(0).getId()).isNotNull();
        assertThat(credentials.get(0).getServer()).isEqualTo("SERVER1");
        assertThat(credentials.get(0).getPassword()).isEqualTo("tomcat");
        assertThat(credentials.get(0).getUsername()).isEqualTo("tomcat");
        assertThat(credentials.get(0).getUrl()).isEqualTo("192.168.1.1");
    }

    @Test
    public void parseFromStringSingleLine() throws Exception {
        final List<Credential> credentials = parseFromString("SERVER2,root,tomcat,192.168.1.2");

        assertThat(credentials).hasSize(1);
        assertThat(credentials.get(0).getId()).isNotNull();
        assertThat(credentials.get(0).getServer()).isEqualTo("SERVER2");
        assertThat(credentials.get(0).getPassword()).isEqualTo("root");
        assertThat(credentials.get(0).getUsername()).isEqualTo("tomcat");
        assertThat(credentials.get(0).getUrl()).isEqualTo("192.168.1.2");
    }

    @Test
    public void parseFromStringMultipleLines() throws Exception {
        final List<Credential> credentials = parseFromString("SERVER3, password, user, 192.168.1.3\nSERVER4,tomcat_dev,tomcat,192.168.1.4");

        assertThat(credentials).hasSize(2);
        assertThat(credentials.get(0).getId()).isNotNull();
        assertThat(credentials.get(0).getServer()).isEqualTo("SERVER3");
        assertThat(credentials.get(0).getPassword()).isEqualTo("password");
        assertThat(credentials.get(0).getUsername()).isEqualTo("user");
        assertThat(credentials.get(0).getUrl()).isEqualTo("192.168.1.3");
        assertThat(credentials.get(1).getId()).isNotNull();
        assertThat(credentials.get(1).getServer()).isEqualTo("SERVER4");
        assertThat(credentials.get(1).getPassword()).isEqualTo("tomcat_dev");
        assertThat(credentials.get(1).getUsername()).isEqualTo("tomcat");
        assertThat(credentials.get(1).getUrl()).isEqualTo("192.168.1.4");
    }

    private List<Credential> parseFromString(final String value) {
        return credentialParser.fromString(value);
    }
}