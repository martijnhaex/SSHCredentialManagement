package be.haexnet.sshcredentialmanagement.model;

import java.io.Serializable;

public class Credential implements Serializable {

    private static final Long serialVersionUID = 141605814607823206L;

    private final Long id;
    private final String server;
    private final String username;
    private final String password;
    private final String url;

    private Credential(Long id, String server, String password, String username, String url) {
        this.id = id;
        this.server = server;
        this.password = password;
        this.username = username;
        this.url = url;
    }

    public static final Credential of(Long id, String server, String password, String username, String url) {
        return new Credential(id, server, password, username, url);
    }

    public Long getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return "Credential{" +
                "id=" + id +
                ", server='" + server + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Credential that = (Credential) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (server != null ? !server.equals(that.server) : that.server != null) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (server != null ? server.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }
}
