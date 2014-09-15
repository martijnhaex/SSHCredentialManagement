package be.haexnet.sshcredentialmanagement.model;

import java.io.Serializable;

public class Credential implements Serializable {

    private Long id;
    private String server;
    private String username;
    private String password;
    private String url;

    public Credential() {
    }

    public Credential(Long id, String server, String username, String password, String url) {
        this.id = id;
        this.server = server;
        this.username = username;
        this.password = password;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
