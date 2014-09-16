package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CredentialService implements ICredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<Credential> findAll() {
        return Collections.emptyList();
    }

    @Override
    public void batchSave(final List<Credential> credentials) {
        credentialRepository.batchSave(credentials);
    }

}
