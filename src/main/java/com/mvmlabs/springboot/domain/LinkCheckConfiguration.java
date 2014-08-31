package com.mvmlabs.springboot.domain;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
public class LinkCheckConfiguration {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long     id;

    @Version
    private Long     version;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Tag> tags;

    @ManyToOne
    private User     createdBy;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar createdDate;

    @ManyToOne
    private User     updatedBy;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(final Long version) {
        this.version = version;
    }

    public Set<Tag> getTags() {
        if (tags == null) {
            tags = new HashSet<Tag>();
        }
        return tags;
    }

    public void setTags(final Set<Tag> tags) {
        this.tags = tags;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final User createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(final Calendar createdDate) {
        this.createdDate = createdDate;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(final User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Calendar getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(final Calendar updatedDate) {
        this.updatedDate = updatedDate;
    }
}
