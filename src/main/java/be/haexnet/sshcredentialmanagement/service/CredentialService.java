package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.model.Credential;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CredentialService implements ICredentialService {

    @Override
    public List<Credential> findAll() {
        return Collections.emptyList();
    }

}
