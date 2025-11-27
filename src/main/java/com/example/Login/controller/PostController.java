package com.example.Login.controller;

import com.example.Login.model.Post;
import com.example.Login.repo.PostRepo;
import com.example.Login.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    @Autowired
    PostService postService;
    @Autowired
    PostRepo postRepo;

    //add post
@PostMapping("/add-post")
    public String addPost(@RequestParam String title,
                          @RequestParam String content,
                          HttpSession session){
    boolean saved= postService.addpost(title,content,session);

    if(!saved){
        return "redirect:/Login.html";
    }
    return "redirect:/main.html";

}
@ResponseBody
@GetMapping("/get-posts")
public List<Post> feed(){
    return postRepo.findAll();
}
    // Edit post
    @PostMapping("/edit-post/{id}")
    public String editPost(@PathVariable long id,
                           @RequestParam String title,
                           @RequestParam String content,
                           HttpSession session) {
        boolean updated = postService.editPost(id, title, content, session);
        return updated ? "redirect:/main.html" : "redirect:/Login.html";
    }
    // Delete post
    @GetMapping("/delete-post/{id}")
    public String deletePost(@PathVariable long id, HttpSession session) {
        boolean deleted = postService.deletePost(id, session);
        return deleted ? "redirect:/main.html" : "redirect:/Login.html";
    }

}
