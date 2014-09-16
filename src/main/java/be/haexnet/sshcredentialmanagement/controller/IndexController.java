package be.haexnet.sshcredentialmanagement.controller;

import be.haexnet.sshcredentialmanagement.model.Credential;
import be.haexnet.sshcredentialmanagement.service.ICredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
        final List<Credential> allCredentials = findAllCredentials();
        modelMap.put("credentials", allCredentials);
        return "index";
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long credentialId) {
        credentialService.delete(credentialId);
        return "redirect:/";
    }

    private List<Credential> findAllCredentials() {
        return credentialService.findAll();
    }
}
