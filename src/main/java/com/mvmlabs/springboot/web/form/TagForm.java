package com.mvmlabs.springboot.web.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.service.NotFoundException;
import com.mvmlabs.springboot.service.TagService;

public class TagForm {

    private String originalTag;

    @NotNull
    @Size(min = 3, max = 40)
    private String tag;
    
    @NotNull
    @Size(min = 3, max = 200)
    private String description;
    
    private List<String> implied;
    
    public TagForm() {
    }

    public TagForm(final Tag tag) {
        this.tag = tag.getTag();
        this.originalTag = tag.getTag();
        this.description = tag.getDescription();
        if (tag.getImplied().size() > 0) {
            for (Tag itag : tag.getImplied()) {
                getImplied().add((itag.getTag()));
            }
        }
    }

    public void update(final Tag tag, final TagService tagService) {
        tag.setTag(this.tag);
        tag.setDescription(this.description);
        if (getImplied().size() > 0) {
            for(final String name : getImplied()) {
                try {
                    tag.getImplied().add(tagService.getTag(name.trim()));
                } catch(NotFoundException e) {
                    // Could add this as a new tag
                }
            }
        }
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImplied() {
        if (implied == null) {
            implied = new ArrayList<String>();
        }
        return implied;
    }

    public void setImplied(List<String> implied) {
        this.implied = implied;
    }

    public String getOriginalTag() {
        return originalTag;
    }

    public void setOriginalTag(String originalTag) {
        this.originalTag = originalTag;
    }
}
