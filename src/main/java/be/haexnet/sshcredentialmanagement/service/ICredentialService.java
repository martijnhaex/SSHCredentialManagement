package be.haexnet.sshcredentialmanagement.service;

import be.haexnet.sshcredentialmanagement.model.Credential;

import java.util.List;

public interface ICredentialService {

    List<Credential> findAll();

    void batchSave(List<Credential> credentials);

}
