package com.mvmlabs.springboot.domain;

import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {

    @Id
    private String          tag;

    @Column(nullable = false)
    private String          description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tag> implied;

    public Tag() {
    }

    public Tag(final String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Collection<Tag> getImplied() {
        if (implied == null) {
            implied = new HashSet<Tag>();
        }
        return implied;
    }

    public void setImplied(final Collection<Tag> implied) {
        this.implied = implied;
    }
}
