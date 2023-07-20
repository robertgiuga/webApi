package com.example.webApi.service;


import com.example.webApi.model.Website;
import com.example.webApi.repository.IWebsiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {


    private IWebsiteRepository websiteRepository;

    @Autowired
    public WebsiteService(IWebsiteRepository websiteRepository) {
        this.websiteRepository = websiteRepository;
    }

    public Website findByDomain(String domain){
        return websiteRepository.findByDomain(domain);
    }

    public Website findByPhone(String phone){
        return websiteRepository.findByPhone(phone);
    }
}
