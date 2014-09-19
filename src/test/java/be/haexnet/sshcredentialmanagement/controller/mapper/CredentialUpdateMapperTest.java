package be.haexnet.sshcredentialmanagement.controller.mapper;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialUpdateCommand;
import be.haexnet.sshcredentialmanagement.fixture.CredentialFixture;
import be.haexnet.sshcredentialmanagement.model.Credential;
import org.junit.Test;

import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;

public class CredentialUpdateMapperTest {

    CredentialUpdateMapper mapper = new CredentialUpdateMapper();

    @Test
    public void mapToCommandHasNoEntity() throws Exception {
        final CredentialUpdateCommand result = mapper.map(Optional.empty());

        assertThat(result.getId()).isNull();
        assertThat(result.getServer()).isNull();
        assertThat(result.getPassword()).isNull();
        assertThat(result.getUsername()).isNull();
        assertThat(result.getUrl()).isNull();
    }

    @Test
    public void mapToCommandHasEntity() throws Exception {
        final CredentialUpdateCommand result = mapper.map(Optional.of(CredentialFixture.CREATE("99")));

        assertThat(result.getId()).isEqualTo("99");
        assertThat(result.getServer()).isEqualTo("SERVER99");
        assertThat(result.getPassword()).isEqualTo("PASSWORD99");
        assertThat(result.getUsername()).isEqualTo("USERNAME99");
        assertThat(result.getUrl()).isEqualTo("URL99");
    }

    @Test
    public void mapToEntity() throws Exception {
        final CredentialUpdateCommand command = new CredentialUpdateCommand();
        command.setId("72");
        command.setUsername("UPDATED");

        final Credential result = mapper.map(command);

        assertThat(result.getId()).isEqualTo("72");
        assertThat(result.getServer()).isNull();
        assertThat(result.getPassword()).isNull();
        assertThat(result.getUsername()).isEqualTo("UPDATED");
        assertThat(result.getUrl()).isNull();
    }
}