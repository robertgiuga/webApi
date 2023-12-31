package com.example.webApi.repository;

import com.example.webApi.model.Website;

public interface IWebsiteRepository {

    Website findByDomain(String domain);

    Website findByPhone(String phone);
}
