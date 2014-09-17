package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.model.Credential;

import java.util.List;
import java.util.Optional;

public interface ICredentialService {

    List<Credential> findAll();

    void batchSave(List<Credential> credentials);

    void delete(Long credentialId);

    Optional<Credential> findOne(Long credentialId);

    void update(Credential credential);
}
