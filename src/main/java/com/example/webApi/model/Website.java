package com.example.webApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Website {
    private String domain;
    private String name;
    private String legalName;
    private List<String> availableNames;
    private List<String> telephones;
    private List<String> mediaLinks;

    @JsonProperty("_source")
    private void unpackNested(Map<String,Object> source) {
        this.domain = (String)source.get("domain");
        this.name = (String)source.get("company_commercial_name");
        this.legalName = (String)source.get("company_legal_name");
        this.availableNames=  Arrays.stream(source.get("company_all_available_names").toString().split("\\|")).toList();
        this.telephones=  Arrays.stream(source.get("telephone").toString().split("\\|")).toList();
        this.mediaLinks=  Arrays.stream(source.get("socialLinks").toString().split("\\|")).toList();
    }
    @JsonProperty("hits")
    private void unpackTripleNested(Map<String,Object> hits) {
        Map<String,String> source =(Map<String,String>)((List<Map<String,Object>>)hits.get("hits")).get(0).get("_source");
        this.domain = (String)source.get("domain");
        this.name = (String)source.get("company_commercial_name");
        this.legalName = (String)source.get("company_legal_name");
        this.availableNames=  Arrays.stream(source.get("company_all_available_names").split("\\|")).toList();
        this.telephones=  Arrays.stream(source.get("telephone").split("\\|")).toList();
        this.mediaLinks=  Arrays.stream(source.get("socialLinks").split("\\|")).toList();
    }

    public Website() {
    }

    public Website(String domain, String name, String legalName, List<String> availableNames, List<String> telephones, List<String> mediaLinks) {
        this.domain = domain;
        this.name = name;
        this.legalName = legalName;
        this.availableNames = availableNames;
        this.telephones = telephones;
        this.mediaLinks = mediaLinks;
    }

    public Website(Builder builder) {
        this.domain = builder.domain;
        this.name = builder.name;
        this.legalName = builder.legalName;
        this.availableNames = builder.availableNames;
        this.telephones = builder.telephones;
        this.mediaLinks = builder.mediaLinks;
    }

    public Website(String s, String s1, String s2, List<String> toList) {
        this.domain = s;
        this.name = s1;
        this.legalName =s2;
        this.availableNames = toList;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public List<String> getAvailableNames() {
        return availableNames;
    }

    public void setAvailableNames(List<String> availableNames) {
        this.availableNames = availableNames;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }

    public List<String> getMediaLinks() {
        return mediaLinks;
    }

    public void setMediaLinks(List<String> mediaLinks) {
        this.mediaLinks = mediaLinks;
    }

    @Override
    public String toString() {
        StringBuilder b= new StringBuilder();
        b.append(domain).append(",").append(name).append(",").append(legalName).append(",");
        if(availableNames!=null)  b.append(String.join("|", availableNames));
        b.append(",");
        if(telephones!=null)  b.append(String.join("|", telephones));
        b.append(",");
        if(mediaLinks!=null)  b.append(String.join("|", mediaLinks));
        return b.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Website website = (Website) o;
        return domain.equals(website.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(domain);
    }

    public static class Builder{
        private String domain;
        private String name;
        private String legalName;
        private List<String> availableNames;
        private List<String> telephones;
        private List<String> mediaLinks;

        public Builder() {
        }

        public Builder setDomain(String domain) {
            this.domain = domain;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setLegalName(String legalName) {
            this.legalName = legalName;
            return this;
        }

        public Builder setAvailableNames(List<String> availableNames) {
            this.availableNames = availableNames;
            return this;
        }

        public Builder setTelephones(List<String> telephones) {
            this.telephones = telephones;
            return this;
        }

        public Builder setMediaLinks(List<String> mediaLinks) {
            this.mediaLinks = mediaLinks;
            return this;
        }
        public Website build(){
            return new Website(this);
        }
    }
}
