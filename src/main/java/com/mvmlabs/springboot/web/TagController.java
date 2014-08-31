package com.mvmlabs.springboot.web;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.service.TagService;
import com.mvmlabs.springboot.web.form.TagForm;

@Controller
public class TagController {

    private static final String LIST_TAGS_VIEW_NAME       = "tag.list";

    private static final String TAG_FORM_VIEW_NAME        = "tag.form";

    private static final String VIEW_TAG_VIEW_NAME        = "tag.view";

    private static final String SUCCESS_REDIRECT_TEMPLATE = "redirect:/admin/tag/view/%s";

    /** Logger implementation. */
    private final Logger        logger                    = LoggerFactory.getLogger(this.getClass());

    private final TagService    tagService;

    @Autowired
    public TagController(final TagService tagService) {
        this.tagService = tagService;
    }

    @RequestMapping(value = "/admin/tag/search/{criteria}", method = RequestMethod.GET)
    public @ResponseBody List<Tag> search(@PathVariable("criteria") final String criteria) {
        return tagService.findAllStartingWith(criteria);
    }

    @RequestMapping(value = "/admin/tag/all", method = RequestMethod.GET)
    public @ResponseBody Iterable<Tag> allTags() {
        return tagService.findAll();
    }

    @RequestMapping(value = "/admin/tag/list", method = RequestMethod.GET)
    public ModelAndView list(@PageableDefault(page = 0, value = 10) final Pageable pageable) {
        logger.debug("List requested.");
        return new ModelAndView(LIST_TAGS_VIEW_NAME, "page", tagService.getAllTags(pageable));
    }

    @RequestMapping(value = "/admin/tag/view/{tag}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("tag") final Tag tag) {
        logger.debug("View requested for tag %s.", tag.getTag());
        return new ModelAndView(VIEW_TAG_VIEW_NAME, "tag", tag);
    }

    @RequestMapping(value = "/admin/tag/edit/{tag}", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable("tag") final Tag tag) {
        logger.debug("Edit tag form requested for tag %s.", tag.getTag());

        final ModelAndView mv = new ModelAndView(TAG_FORM_VIEW_NAME);
        final TagForm form = new TagForm(tag);
        mv.addObject("tagForm", form);
        mv.addObject("tagNames", tagService.getAllTagNames());
        return mv;
    }

    @RequestMapping(value = "/admin/tag/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        logger.debug("Add tag form requested.");
        return new ModelAndView(TAG_FORM_VIEW_NAME, "tagForm", new TagForm());
    }

    @RequestMapping(value = "/admin/tag/add", method = RequestMethod.POST)
    public String saveAddTag(@Valid final TagForm form, final BindingResult binding) {
        logger.debug("New tag form has been submitted.");

        final Tag tag = new Tag();
        form.update(tag, tagService);
        tagService.save(tag);

        return getViewTagRedirectUrl(form.getTag());
    }

    @RequestMapping(value = "/admin/tag/edit/{tag}", method = RequestMethod.POST)
    public String saveEditTag(@Valid final TagForm form, final BindingResult binding) {
        logger.debug("Edit tag form has been submitted for tag %s.", form.getTag());

        final Tag tag = new Tag();
        form.update(tag, tagService);
        if (form.getTag().equals(form.getOriginalTag())) {
            logger.debug("Updating tag '%s'.", form.getTag());
            tagService.save(tag);
        } else {
            logger.debug("Renaming tag from '%s' to '%s'.", form.getOriginalTag(), form.getTag());
            tagService.rename(form.getOriginalTag(), tag);
        }
        return getViewTagRedirectUrl(form.getTag());
    }

    private String getViewTagRedirectUrl(final String tag) {
        logger.debug("Building view tag redirect for tag %s.", tag);
        return String.format(SUCCESS_REDIRECT_TEMPLATE, tag);
    }
}
