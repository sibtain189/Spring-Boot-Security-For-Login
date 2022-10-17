package com.jwt.tinyURL;

import java.util.List;

public class DataTinyResponse {

    public String domain;
    public String alias;
    public boolean deleted;
    public boolean archived;
    public Analytics analytics;
    public List<Tags> tags;
    public String created_at;
    public String expires_at;
    public String tiny_url;
    public String url;
    
    public String getDomain() {
        return domain;
    }
    public void setDomain(String domain) {
        this.domain = domain;
    }
    public String getAlias() {
        return alias;
    }
    public void setAlias(String alias) {
        this.alias = alias;
    }
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public boolean isArchived() {
        return archived;
    }
    public void setArchived(boolean archived) {
        this.archived = archived;
    }
    public Analytics getAnalytics() {
        return analytics;
    }
    public void setAnalytics(Analytics analytics) {
        this.analytics = analytics;
    }
    public List<Tags> getTags() {
        return tags;
    }
    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }
    public String getCreated_at() {
        return created_at;
    }
    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
    public String getExpires_at() {
        return expires_at;
    }
    public void setExpires_at(String expires_at) {
        this.expires_at = expires_at;
    }
    public String getTiny_url() {
        return tiny_url;
    }
    public void setTiny_url(String tiny_url) {
        this.tiny_url = tiny_url;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    

    
}
