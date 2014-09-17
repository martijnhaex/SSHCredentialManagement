package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.repository.CredentialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CredentialService implements ICredentialService {

    private final CredentialRepository credentialRepository;

    @Autowired
    public CredentialService(CredentialRepository credentialRepository) {
        this.credentialRepository = credentialRepository;
    }

    @Override
    public List<Credential> findAll() {
        return credentialRepository.findAll();
    }

    @Override
    public void batchSave(final List<Credential> credentials) {
        credentialRepository.batchSave(credentials);
    }

    @Override
    public void delete(Long credentialId) {
        final List<Credential> credentials = findAll()
                .stream()
                .filter(credential -> !credential.getId().equals(credentialId))
                .collect(Collectors.toList());

        batchSave(credentials);
    }

    @Override
    public Optional<Credential> findOne(Long credentialId) {
        return findAll()
                .stream()
                .filter(credential -> credential.getId().equals(credentialId))
                .findFirst();
    }

    @Override
    public void update(Credential credential) {
        delete(credential.getId());

        final List<Credential> credentials = findAll();
        credentials.add(credential);
        batchSave(credentials);
    }

}
