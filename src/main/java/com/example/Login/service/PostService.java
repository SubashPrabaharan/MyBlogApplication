package com.example.Login.service;

import com.example.Login.model.Post;
import com.example.Login.repo.PostRepo;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

@Autowired
PostRepo postRepo;


    public boolean addpost(String title, String content, HttpSession session) {

       String chk = (String) session.getAttribute("username");
       if(chk == null){
           return false;
       }
       else{
           Post post = new Post(title,content,chk);
           postRepo.save(post);
           return true;
       }
    }
    public boolean editPost(long id, String title, String content, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) return false;

        Optional<Post> postOpt = postRepo.findById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (post.getAuthor().equals(username)) {
                post.setTitle(title);
                post.setContent(content);
                postRepo.save(post);
                return true;
            }
        }
        return false;
    }
    public boolean deletePost(long id, HttpSession session) {
        String username = (String) session.getAttribute("username");
        if (username == null) return false;

        Optional<Post> postOpt = postRepo.findById(id);
        if (postOpt.isPresent()) {
            Post post = postOpt.get();
            if (post.getAuthor().equals(username)) {
                postRepo.delete(post);
                return true;
            }
        }
        return false;
    }



}
