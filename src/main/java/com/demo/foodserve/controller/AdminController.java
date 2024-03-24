package com.demo.foodserve.controller;
import com.demo.foodserve.dto.*;
import com.demo.foodserve.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequestMapping("/admin")
@RestController
public class AdminController {


    @Autowired
    private AdminService adminService;

    @PostMapping("/getCreatedPosts")
    public List<PostDto> getCreatedPostsForSpecificRange(@RequestBody DateDto dateDto) {
        return adminService.getCreatedPostsForSpecificRange(dateDto.getStartDate(), dateDto.getEndDate());
    }

    @PostMapping("/getAcceptedPosts")
    public List<PostDto> getAcceptedPostsForSpecificRange(@RequestBody DateDto dateDto){
        return adminService.getAcceptedPostsForSpecificRange(dateDto.getStartDate(), dateDto.getEndDate());
    }
    @GetMapping("/getAllPosts")
    public List<PostDto> getAllPosts(){
        return adminService.getAllPosts();
    }

    @GetMapping("/getAllDonors")
    public List<DonorDto> getAllDonors(){
        return adminService.getAllDonors();
    }

    @GetMapping("/getAllRecievers")
    public List<RecieverDto> getAllRecievers(){
        return adminService.getAllRecievers();
    }

    @GetMapping("/RecieverOnLocation")
    public List<RecieverDto> getAllRecieversBasedOnLocation(@RequestBody LocationDto locationDto){
        return adminService.getAllRecieversBasedOnLocation(locationDto);
    }

    @PostMapping("/register")
    public String register(@RequestBody CredentialsDto credentialsDto){
        return adminService.register(credentialsDto);
    }

    @GetMapping("get")
    public List<CredentialsDto> getAllAccounts(){
        return adminService.getAllAccounts();
    }

    @PostMapping("/feedBack")
    public  FeedBackDto postFeedBack(@RequestBody FeedBackDto feedBackDto){
        return adminService.postFeedBack(feedBackDto);
    }

    @GetMapping("/getAllfeedBacks")
    public List<FeedBackDto> getAllFeedBackDto(){
        return adminService.getAllFeedBackDto();
    }

    @GetMapping("/getAllCounts")
    public CountDto getAllCounts(){
        return adminService.getAllCount();
    }

    @PostMapping("/getUserId")
    public LoginDto getUserId(@RequestBody LoginDto loginDto){return adminService.getUserId(loginDto);}

}
