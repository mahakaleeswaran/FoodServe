package com.demo.foodserve.controller;

import com.demo.foodserve.dto.DonorDto;
import com.demo.foodserve.dto.PostDto;
import com.demo.foodserve.service.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("donor")
public class DonorController {

    @Autowired
    private DonorService donorService;

    @PostMapping("/register")
    public DonorDto register(@RequestBody DonorDto donorDto){
        return donorService.register(donorDto);
    }

    @GetMapping("/{id}")
    public DonorDto getDonorDetails(@PathVariable Integer id){
        return donorService.getDonorDetails(id);
    }

    @PostMapping("/{id}/post")
    public PostDto putPost(@PathVariable Integer id,@RequestBody PostDto postdto){
        return donorService.putPost(id,postdto);
    }

    @PutMapping("/{id}/updateDetails")
    public DonorDto updateDetails(@PathVariable Integer id,@RequestBody DonorDto donorDto){
        return donorService.updateDetils(id,donorDto);
    }

    @PatchMapping("/{id}/updateName")
    public DonorDto updateName(@PathVariable Integer id,@RequestBody String name){
        return donorService.updateName(id,name);
    }

    @PatchMapping("/{id}/updateOrganization")
    public DonorDto updateOrganization(@PathVariable Integer id,@RequestBody String organization){
        return donorService.updateOrganization(id,organization);
    }

    @PatchMapping("/{id}/updateAddress")
    public DonorDto updateAddress(@PathVariable Integer id,@RequestBody String address){
        return donorService.updateAddress(id,address);
    }

    @PatchMapping("/{id}/updatePhoneNumber")
    public DonorDto updatePhoneNumber(@PathVariable Integer id,@RequestBody String number){
        return donorService.updatePhoneNumber(id,number);
    }

    @PatchMapping("/{id}/updateEmail")
    public DonorDto updateEmail(@PathVariable Integer id,@RequestBody String email){
        return donorService.updateEmail(id,email);
    }

    @DeleteMapping("/{id}/delete")
    public DonorDto deleteDonor(@PathVariable Integer id){
        return donorService.deleteDonor(id);
    }
}
