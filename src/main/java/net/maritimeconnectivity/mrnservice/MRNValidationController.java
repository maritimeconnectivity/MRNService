package net.maritimeconnectivity.mrnservice;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
public class MRNValidationController  {

    @Autowired
    private MRNValidationService mrnValidationService;


    @RequestMapping(value = "validate/", method=RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String isValid(){
        return Boolean.TRUE.toString();
    }

    @RequestMapping(value = "validate/", method=RequestMethod.POST) //, produces = MediaType.TEXT_PLAIN_VALUE)
    public String validateMRN(@RequestBody MRNInput input){
        return mrnValidationService.getMrnMask(input.nameSpace, input.organizationMrn);
    }

    /*
    @RequestMapping("/articles")
    public List<Article> getAllArticles(){
        return articleService.getAllArticles();
    }

    @RequestMapping("/articles/{id}")
    public Article getArticle(@PathVariable String id){
        return articleService.getArticle(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/articles")
    public void addArticle(@RequestBody Article article){
        articleService.addArticle(article);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/articles/{id}")
    public void updateArticle(@RequestBody Article article, @PathVariable String id){
        articleService.updateArticle(article, id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    public void deleteArticle(@PathVariable String id){
        articleService.deleteArticle(id);
    }
    */
}
