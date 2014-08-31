package com.mvmlabs.springboot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

@Entity
public class LinkCheckResult {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;
        
    @ManyToOne
    @JoinColumn(name="linkcheck_id")
    private LinkCheck linkCheck;
    
    @ManyToOne
    @JoinColumn(name="link_id")
    private Link link;
    
    @Column(nullable = false)
    private Integer attempt;
    
    @Column(nullable = true)
    private Integer responseCode;
    
    @Column(nullable = true, length=4000)
    @Lob
    private String responseHeader;
    
    @Column(nullable = true, length=50000)
    @Lob
    private String responseBody;
    
    @Column(nullable = true, length=4000)
    private Integer responseSize;
    
    @Column(nullable = false)
    private Long timeTakenMillis;
    
    @Column(nullable = false)
    private Boolean success;

    @Column(nullable = true, length=4000)
    @Lob
    private String errorMessage;

    public LinkCheckResult() {
    }

    public LinkCheckResult(final Link link) {
        this.link = link;
    }

    public LinkCheckResult(final Link link, final Exception exception) {
        this.link = link;
        success = Boolean.FALSE;
        errorMessage = exception.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public LinkCheck getLinkCheck() {
        return linkCheck;
    }

    public void setLinkCheck(LinkCheck linkCheck) {
        this.linkCheck = linkCheck;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Integer getAttempt() {
        return attempt;
    }

    public void setAttempt(Integer attempt) {
        this.attempt = attempt;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseHeader() {
        return responseHeader;
    }

    public void setResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public void setResponseBody(String responseBody) {
        if (responseBody != null) {
            this.responseBody = responseBody.substring(0, 49999);
            this.responseSize = this.responseBody.length();
        } else {
            this.responseBody = null;
            this.responseSize = 0;
        }
    }

    public Integer getResponseSize() {
        return responseSize;
    }

    public void setResponseSize(Integer responseSize) {
        this.responseSize = responseSize;
    }

    public Long getTimeTakenMillis() {
        return timeTakenMillis;
    }

    public void setTimeTakenMillis(Long timeTakenMillis) {
        this.timeTakenMillis = timeTakenMillis;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
