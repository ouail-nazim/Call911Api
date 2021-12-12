package com.example.fireapiv1.Service;

import com.example.fireapiv1.Model.Post;
import com.example.fireapiv1.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }
     public Post savePost(Post post){
        return postRepository.save(post);
     }
}
