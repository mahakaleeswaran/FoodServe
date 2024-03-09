package com.demo.foodserve.controller;

import com.demo.foodserve.dto.DonorDto;
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

    @PatchMapping("/{id}/{postId}/accept")
    public PostDto acceptPost(@PathVariable Integer id, @PathVariable Integer postId){
        return recieverService.acceptPost(id,postId);
    }

    @PutMapping("/{id}/updateDetails")
    public RecieverDto updateDetails(@PathVariable Integer id, @RequestBody RecieverDto recieverDto){
        return recieverService.updateDetails(id,recieverDto);
    }

    @PatchMapping("/{id}/updateName")
    public RecieverDto updateName(@PathVariable Integer id,@RequestBody String name){
        return recieverService.updateName(id,name);
    }

    @PatchMapping("/{id}/updateOrganization")
    public RecieverDto updateOrganization(@PathVariable Integer id,@RequestBody String organization){
        return recieverService.updateOrganization(id,organization);
    }

    @PatchMapping("/{id}/updateAddress")
    public RecieverDto  updateAddress(@PathVariable Integer id,@RequestBody String address){
        return recieverService.updateAddress(id,address);
    }

    @PatchMapping("/{id}/updatePhoneNumber")
    public RecieverDto  updatePhoneNumber(@PathVariable Integer id,@RequestBody String number){
        return recieverService.updatePhoneNumber(id,number);
    }

    @PatchMapping("/{id}/updateEmail")
    public RecieverDto  updateEmail(@PathVariable Integer id,@RequestBody String email){
        return recieverService.updateEmail(id,email);
    }

    @GetMapping("/{id}")
    public RecieverDto getRecieverDetails(@PathVariable Integer id){
        return recieverService.getRecieverDetails(id);
    }


    @DeleteMapping("/{id}/delete")
    public RecieverDto  deleteDonor(@PathVariable Integer id){
        return recieverService.deleteReciever(id);
    }
}
