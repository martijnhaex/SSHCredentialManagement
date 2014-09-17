package be.haexnet.sshcredentialmanagement.fixture;

import be.haexnet.sshcredentialmanagement.model.Credential;

import java.util.ArrayList;
import java.util.List;

public final class CredentialFixture {
    private CredentialFixture() {
    }

    public static List<Credential> LIST() {
        final List<Credential> credentials = new ArrayList<>();
        credentials.add(CREATE(2L));
        credentials.add(CREATE(3L));
        credentials.add(CREATE(1L));
        return credentials;
    }

    public static Credential CREATE(final Long id) {
        return Credential.of(
                id,
                "SERVER" + id,
                "PASSWORD" + id,
                "USERNAME" + id,
                "URL" + id
        );
    }
}
