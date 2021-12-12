package com.example.fireapiv1.Controller;

import com.example.fireapiv1.Model.Client;
import com.example.fireapiv1.Model.Fire;
import com.example.fireapiv1.Model.Post;
import com.example.fireapiv1.Repository.ClientRepository;
import com.example.fireapiv1.Repository.FireRepository;
import com.example.fireapiv1.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/posts")
public class PostController {

    private final PostService postService;
    private final ClientRepository clientRepository;
    private final FireRepository fireRepository;

    @Autowired
    public PostController(PostService postService, ClientRepository clientRepository, FireRepository fireRepository) {
        this.postService = postService;
        this.clientRepository = clientRepository;
        this.fireRepository = fireRepository;
    }

    @GetMapping
    public List<Post> getFirs() {
        return postService.getAllPosts();
    }

    @PostMapping
    public Post saveFire(
            @RequestBody Post post,
            @RequestParam("user_id") long uid,
            @RequestParam("fire_id") long fid
    ) {
        Optional<Client> client = clientRepository.findById(uid);
        Optional<Fire> fire = fireRepository.findById(fid);
        if (client.isPresent() && fire.isPresent()) {
            return postService.savePost(client.get().comment(post, fire.get()));
        } else {
            return null;
        }
    }


}
