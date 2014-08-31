package com.mvmlabs.springboot.web.form;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.domain.User;
import com.mvmlabs.springboot.service.NotFoundException;
import com.mvmlabs.springboot.service.TagService;

public class LinkForm {
    
    @Digits(integer=8, fraction=0)
    private Long id;

    @Digits(integer=8, fraction=0)
    private Long version;
    
    @NotNull
    @Size(min = 3, max = 80)
    private String name;

    @NotNull
    @Size(min = 3, max = 200)
    private String description;

    @NotNull
    @Size(min = 6, max = 100)
    private String url;

    private String createDate;

    private User createdBy;

    private String lastUpdatedDate;

    private User updatedBy;

    private List<String> tags;
    
    public LinkForm() {
    }

    public LinkForm(final Link link) {
        final SimpleDateFormat sdf = new SimpleDateFormat();
        this.id = link.getId();
        this.version = link.getVersion();
        this.name = link.getName();
        this.description = link.getDescription();
        this.url = link.getUrl();
        if (link.getCreateDate() != null) {
            this.createDate = sdf.format(link.getCreateDate().getTime());
        }
        this.createdBy = link.getCreatedBy();
        if (link.getLastUpdatedDate() != null) {
            this.lastUpdatedDate = sdf.format(link.getLastUpdatedDate().getTime());
        }
        this.updatedBy = link.getUpdatedBy();
        
        if (link.getTags().size() > 0) {
            for(final Tag tag : link.getTags()) {
                getTags().add(tag.getTag());
            }
        }
    }
    
    public void update(final Link link, final User user, final TagService tagService) {
        final Calendar now = Calendar.getInstance();
        if (link.getCreatedBy() == null) {
            link.setCreatedBy(user);
            link.setCreateDate(now);
        }
        link.setName(name);
        link.setDescription(description);
        link.setUrl(url);
        link.setUpdatedBy(user);
        link.setLastUpdatedDate(now);
        link.setVersion(version);
        
        if(getTags().size() > 0) {
            for(final String name : getTags()) {
                try {
                    link.getTags().add(tagService.getTag(name.trim()));
                } catch (NotFoundException e) {
                    // Could add this as a new tag
                }
            }
        }
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public List<String> getTags() {
        if (tags == null) {
            tags = new ArrayList<String>();
        }
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
    
}
