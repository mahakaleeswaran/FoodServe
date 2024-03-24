package com.demo.foodserve.controller;
import com.demo.foodserve.dto.LocationDto;
import com.demo.foodserve.dto.PostDto;
import com.demo.foodserve.dto.RecieverDto;
import com.demo.foodserve.service.RecieverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reciever")
public class RecieverController {

    @Autowired
    private RecieverService recieverService;

    @PostMapping("/register")
    public RecieverDto register(@RequestBody RecieverDto recieverDto){
        return recieverService.register(recieverDto);
    }

    @GetMapping("/{id}/{postId}/accept")
    public PostDto acceptPost(@PathVariable Integer id, @PathVariable Integer postId){
        return recieverService.acceptPost(id,postId);
    }

    @PutMapping("/{id}/updateDetails")
    public RecieverDto updateDetails(@PathVariable Integer id, @RequestBody RecieverDto recieverDto){
        return recieverService.updateDetails(id,recieverDto);
    }

    @GetMapping("/{id}")
    public RecieverDto getRecieverDetails(@PathVariable Integer id){
        return recieverService.getRecieverDetails(id);
    }

    @PostMapping("/{id}/getPostsBasedOnLocation")
    public List<PostDto> getPostsBasedOnLocation(@PathVariable Integer id, @RequestBody LocationDto locationDto){
        return recieverService.getAllPostsBasedOnLocation(id,locationDto);
    }

    @GetMapping("/{id}/getAcceptedPosts")
    public List<PostDto> getAcceptedPosts(@PathVariable Integer id){
        return recieverService.getAllAcceptedPosts(id);
    }

    @DeleteMapping("/{id}/delete")
    public RecieverDto  deleteDonor(@PathVariable Integer id){
        return recieverService.deleteReciever(id);
    }
}
