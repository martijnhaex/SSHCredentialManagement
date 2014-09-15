package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    private final ICredentialService credentialService;

    @Autowired
    public IndexController(final ICredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @RequestMapping("/")
    public String index(final ModelMap modelMap) {
        modelMap.put("credentials", findAllCredentials());
        return "index";
    }

    private List<Credential> findAllCredentials() {
        return credentialService.findAll();
    }
}
