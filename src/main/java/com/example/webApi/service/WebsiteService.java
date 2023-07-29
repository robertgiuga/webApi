package com.example.webApi.service;


import com.example.webApi.model.Website;
import com.example.webApi.repository.IWebsiteRepository;
import com.example.webApi.utils.DataNormalization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WebsiteService {


    private IWebsiteRepository websiteRepository;
    private DataNormalization dataNormalization;

    @Autowired
    public WebsiteService(IWebsiteRepository websiteRepository, DataNormalization dataNormalization) {
        this.websiteRepository = websiteRepository;
        this.dataNormalization = dataNormalization;
    }

    public Website findByDomain(String domain){
        domain= this.dataNormalization.domainNormalization(domain);
        return websiteRepository.findByDomain(domain);
    }

    public Website findByPhone(String phone){
        return websiteRepository.findByPhone(phone);
    }
}
