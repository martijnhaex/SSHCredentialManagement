package be.haexnet.sshcredentialmanagement.controller.mapper;

import be.haexnet.sshcredentialmanagement.controller.command.CredentialUpdateCommand;
import be.haexnet.sshcredentialmanagement.model.Credential;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CredentialUpdateMapper {
    public CredentialUpdateCommand map(final Optional<Credential> credential) {
        CredentialUpdateCommand command = new CredentialUpdateCommand();
        if (credential.isPresent()) {
            command = mapData(credential.get(), command);
        }
        return command;
    }

    public Credential map(final CredentialUpdateCommand command) {
        return Credential.of(
                command.getId(),
                command.getServer(),
                command.getPassword(),
                command.getUsername(),
                command.getUrl()
        );
    }

    private CredentialUpdateCommand mapData(final Credential credential, final CredentialUpdateCommand command) {
        command.setId(credential.getId());
        command.setServer(credential.getServer());
        command.setPassword(credential.getPassword());
        command.setUsername(credential.getUsername());
        command.setUrl(credential.getUrl());
        return command;
    }
}
