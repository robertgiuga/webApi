package com.example.webApi.controller;

import com.example.webApi.model.Website;
import com.example.webApi.service.WebsiteService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RequestMapping("api/website")
@RestController
public class WebsiteController {


    private final WebsiteService websiteService;

    @Autowired
    public WebsiteController(WebsiteService websiteService) {
        this.websiteService = websiteService;
    }

    @GetMapping("/{domain}")
    @ResponseBody
    public Website findByDomain(@PathVariable String domain){
        return websiteService.findByDomain(domain);
    }
    @GetMapping()
    @ResponseBody
    public Website findByPhone(@RequestParam String phone){
        return websiteService.findByPhone(phone);
    }
}
