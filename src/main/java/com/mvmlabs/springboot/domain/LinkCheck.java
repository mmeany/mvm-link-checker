package com.mvmlabs.springboot.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class LinkCheck {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Version
    private Long version;
    
    @ManyToOne
    private User launchedBy;
    
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar launchedDate;
    
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @Column(nullable = true)
    private Integer totalLinksChecked;
    
    @Column(nullable = true)
    private Integer totalInitialErrors;
    
    @Column(nullable = true)
    private Integer totalFinalErrors;

    @OneToMany
    private List<LinkCheckResult> results;

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

    public User getLaunchedBy() {
        return launchedBy;
    }

    public void setLaunchedBy(User launchedBy) {
        this.launchedBy = launchedBy;
    }

    public Calendar getLaunchedDate() {
        return launchedDate;
    }

    public void setLaunchedDate(Calendar launchedDate) {
        this.launchedDate = launchedDate;
    }

    public Set<Tag> getTags() {
        if (tags == null) {
            tags = new HashSet<Tag>();
        }
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Integer getTotalLinksChecked() {
        return totalLinksChecked;
    }

    public void setTotalLinksChecked(Integer totalLinksChecked) {
        this.totalLinksChecked = totalLinksChecked;
    }

    public Integer getTotalInitialErrors() {
        return totalInitialErrors;
    }

    public void setTotalInitialErrors(Integer totalInitialErrors) {
        this.totalInitialErrors = totalInitialErrors;
    }

    public Integer getTotalFinalErrors() {
        return totalFinalErrors;
    }

    public void setTotalFinalErrors(Integer totalFinalErrors) {
        this.totalFinalErrors = totalFinalErrors;
    }

    public List<LinkCheckResult> getResults() {
        if (results == null) {
            results = new ArrayList<LinkCheckResult>();
        }
        return results;
    }

    public void setResults(List<LinkCheckResult> results) {
        this.results = results;
    }
}
