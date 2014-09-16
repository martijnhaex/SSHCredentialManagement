package be.haexnet.sshcredentialmanagement.fixture;

import be.haexnet.sshcredentialmanagement.model.Credential;

import java.util.ArrayList;
import java.util.List;

public final class CredentialFixture {
    private CredentialFixture() {
    }

    public static List<Credential> LIST() {
        final List<Credential> credentials = new ArrayList<>();
        credentials.add(create(2L));
        credentials.add(create(3L));
        credentials.add(create(1L));
        return credentials;
    }

    private static Credential create(final Long id) {
        return Credential.of(
                id,
                "SERVER" + id,
                "PASSWORD" + id,
                "USERNAME" + id,
                "URL" + id
        );
    }
}
