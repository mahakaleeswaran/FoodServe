package com.demo.foodserve.service;
import com.demo.foodserve.Repository.DonorRepository;
import com.demo.foodserve.Repository.PostRepository;
import com.demo.foodserve.Repository.RecieverRespository;
import com.demo.foodserve.dto.DonorDto;
import com.demo.foodserve.dto.FoodDto;
import com.demo.foodserve.dto.PostDto;
import com.demo.foodserve.dto.RecieverDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private DonorService donorService;
    @Autowired
    private RecieverRespository recieverRespository;
    @Autowired
    private RecieverService recieverService;
    public List<PostDto> getCreatedPostsForSpecificRange(Date startDate, Date endDate) {
        return postRepository.findAllByCreatedDateBetween(startDate, endDate).stream()
                .map(post -> PostDto.builder()
                        .served(post.getServed())
                        .posts(post.getFoodEntities().stream()
                                .map(food -> FoodDto.builder()
                                        .foodName(food.getFoodName())
                                        .quantity(food.getQuantity())
                                        .build())
                                .toList())
                        .location(post.getLocation().getDoorNo() + "," + post.getLocation().getStreet() + "," + post.getLocation().getArea() + "," + post.getLocation().getTown() + "," + post.getLocation().getDistrict() + "," + post.getLocation().getState() + "," + post.getLocation().getZipcode())
                        .build())
                .toList();
    }

    public List<PostDto> getAcceptedPostsForSpecificRange(Date startDate, Date endDate) {
        return postRepository.findAllByAcceptedDateBetween(startDate, endDate).stream()
                .map(post -> PostDto.builder()
                        .served(post.getServed())
                        .posts(post.getFoodEntities().stream()
                                .map(food -> FoodDto.builder()
                                        .foodName(food.getFoodName())
                                        .quantity(food.getQuantity())
                                        .build())
                                .toList())
                        .location(post.getLocation().getDoorNo() + "," + post.getLocation().getStreet() + "," + post.getLocation().getArea() + "," + post.getLocation().getTown() + "," + post.getLocation().getDistrict() + "," + post.getLocation().getState() + "," + post.getLocation().getZipcode())
                        .build())
                .toList();
    }

    public List<PostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .served(post.getServed())
                        .posts(post.getFoodEntities().stream()
                                .map(food -> FoodDto.builder()
                                        .foodName(food.getFoodName())
                                        .quantity(food.getQuantity())
                                        .build())
                                .toList())
                        .location(post.getLocation().getDoorNo() + "," + post.getLocation().getStreet() + "," + post.getLocation().getArea() + "," + post.getLocation().getTown() + "," + post.getLocation().getDistrict() + "," + post.getLocation().getState() + "," + post.getLocation().getZipcode())
                        .build())
                .toList();
    }

    public List<DonorDto> getAllDonors() {
        return  donorRepository.findAll().stream().map((donor)->donorService.getDonorDetails(donor.getDonor_Id())).toList();
    }

    public List<RecieverDto> getAllRecievers() {
        return  recieverRespository.findAll().stream().map((reciever)->recieverService.getRecieverDetails(reciever.getReciever_id())).toList();
    }
}
