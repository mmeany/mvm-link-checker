package com.mvmlabs.springboot.web;

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
import org.springframework.web.servlet.ModelAndView;

import com.mvmlabs.springboot.domain.Link;
import com.mvmlabs.springboot.domain.LinkCheck;
import com.mvmlabs.springboot.domain.Tag;
import com.mvmlabs.springboot.service.LinkService;
import com.mvmlabs.springboot.service.TagService;
import com.mvmlabs.springboot.service.UserService;
import com.mvmlabs.springboot.web.form.LinkForm;

/**
 * Controller for querying Links as stand-alone entities.
 * 
 * @author Mark Meany
 */
@Controller
public class LinkController {

    private static final String LIST_LINKS_VIEW_NAME = "link.list";

    private static final String VIEW_LINK_VIEW_NAME = "link.view";

    private static final String LINK_FORM_VIEW_NAME = "link.form";

    private static final String SUCCESS_REDIRECT_TEMPLATE = "redirect:/admin/link/view/%d";

    private static final String LINK_CHECK_REDIRECT_TEMPLATE = "redirect:/admin/linkcheck/view/%d";

    private static final String LINK_CHECK_VIEW_NAME = "linkcheck.view";
    
    /** Logger implementation. */
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LinkService linkService;

    private final TagService tagService;
    
    private final UserService userService;

    @Autowired
    public LinkController(final LinkService linkService, final TagService tagService, final UserService userService) {
        this.linkService = linkService;
        this.tagService = tagService;
        this.userService = userService;
    }
    
    @RequestMapping(value = "/admin/link/list", method=RequestMethod.GET)
    public ModelAndView listAllLinks(@PageableDefault(page = 0, value = 10) final Pageable pageable) {
        logger.debug("Listing all links");
        final ModelAndView mv = new ModelAndView(LIST_LINKS_VIEW_NAME);
        
        // Obtain all tagged links
        mv.addObject("page", linkService.allLinks(pageable));
        return mv;
    }
    
    @RequestMapping(value = "/admin/link/list/tagged/{tag}", method=RequestMethod.GET)
    public ModelAndView listLinksForTag(@PageableDefault(page = 0, value = 10) final Pageable pageable, @PathVariable("tag") final Tag tag) {
        logger.debug("Listing links for tag {}", tag.getTag());
        final ModelAndView mv = new ModelAndView(LIST_LINKS_VIEW_NAME);
        
        // Obtain all tagged links
        mv.addObject("page", linkService.allLinksWithTag(pageable, tag));
        mv.addObject("tag", tag);
        return mv;
    }
    
    @RequestMapping(value = "/admin/link/view/{id}", method = RequestMethod.GET)
    public ModelAndView view(@PathVariable("id") final Link link) {
        logger.debug("Viewing link '{}'.", link.getName());
        logger.debug("This link has {} tags.", link.getTags().size());
        return new ModelAndView(VIEW_LINK_VIEW_NAME, "link", link);
    }
    
    @RequestMapping(value="/admin/link/add", method = RequestMethod.GET)
    public ModelAndView addForm() {
        logger.debug("Add link");
        final ModelAndView mv = new ModelAndView(LINK_FORM_VIEW_NAME);

        final Link link = new Link();
        link.setId(0L);
        link.setVersion(0L);
        final LinkForm linkForm = new LinkForm(link);
        mv.addObject("linkForm", linkForm);
        mv.addObject("tagNames", tagService.getAllTagNames());
        
        return mv;
    }
    
    @RequestMapping(value="/admin/link/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editForm(@PathVariable("id") final Link link) {
        logger.debug("Edit link {}", link.getId());
        final ModelAndView mv = new ModelAndView(LINK_FORM_VIEW_NAME);
        
        final LinkForm linkForm = new LinkForm(link);
        mv.addObject("linkForm", linkForm);
        mv.addObject("tagNames", tagService.getAllTagNames());

        return mv;
    }
    
    @RequestMapping(value="/admin/link/add", method=RequestMethod.POST)
    public String saveAddForm(@Valid final LinkForm linkForm, final BindingResult binding) {
        logger.debug("Save add link {}");

        Link link = new Link();
        linkForm.update(link, userService.currentUser(), tagService);
        link = linkService.save(link);
        
        return getViewLinkRedirectUrl(link.getId());
    }

    @RequestMapping(value="/admin/link/edit/{id}", method=RequestMethod.POST)
    public String saveEditForm(@Valid final LinkForm linkForm, final BindingResult binding) {
        logger.debug("Save edit link {}", linkForm.getId());

        final Link link = linkService.loadLinkById(linkForm.getId());
        linkForm.update(link, userService.currentUser(), tagService);
        linkService.save(link);
        
        return getViewLinkRedirectUrl(linkForm.getId());
    }

    @RequestMapping(value="/admin/link/check/{id}", method = RequestMethod.GET)
    public String checkLinkImmediate(@PathVariable("id") final Link link) {
        logger.debug("Check link {}", link.getId());
        
        final LinkCheck linkCheck = linkService.addHocLinkCheck(link, userService.currentUser());
        
        return getViewLinkCheckRedirectUrl(linkCheck.getId());
    }
    
    @RequestMapping(value="/admin/linkcheck/view/{id}", method = RequestMethod.GET)
    public ModelAndView checkResult(@PathVariable("id") final LinkCheck linkCheck) {
        logger.debug("Check link result {}", linkCheck.getId());
        return new ModelAndView(LINK_CHECK_VIEW_NAME, "linkCheck", linkCheck);
    }
    
    private String getViewLinkRedirectUrl(final Long id) {
        logger.debug("Building view link redirect for link {}.", id);
        return String.format(SUCCESS_REDIRECT_TEMPLATE, id);
    }
    
    private String getViewLinkCheckRedirectUrl(final Long id) {
        logger.debug("Building view link check redirect for link check {}.", id);
        return String.format(LINK_CHECK_REDIRECT_TEMPLATE, id);
    }

}
