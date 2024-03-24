package com.demo.foodserve.service;
import com.demo.foodserve.Repository.*;
import com.demo.foodserve.dto.*;
import com.demo.foodserve.entity.CredentialsEntity;
import com.demo.foodserve.entity.FeedBackEntity;
import com.demo.foodserve.entity.FoodEntity;
import com.demo.foodserve.entity.LocationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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
    @Autowired
    private CredentialsRepository credentialsRepository;
    @Autowired
    private FeedBackRepository feedBackRepository;
    @Autowired
    private FoodRepository foodRepository;

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

    public List<RecieverDto> getAllRecieversBasedOnLocation(LocationDto locationDto) {
        LocationEntity locationEntity = LocationEntity.builder()
                .location_id(locationDto.getLocationId())
                .doorNo(locationDto.getDoorNo())
                .street(locationDto.getStreet())
                .area(locationDto.getArea())
                .town(locationDto.getTown())
                .district(locationDto.getDistrict())
                .state(locationDto.getState())
                .zipcode(locationDto.getZipcode())
                .build();

        return recieverRespository.findAll()
                .stream()
                .filter(recieverEntity -> {
                    if (locationEntity.getLocation_id() != null && !locationEntity.getLocation_id().equals(recieverEntity.getLocation().getLocation_id())) {
                        return false;
                    }
                    if (locationEntity.getDoorNo() != null && !locationEntity.getDoorNo().equals(recieverEntity.getLocation().getDoorNo())) {
                        return false;
                    }
                    if (locationEntity.getStreet() != null && !locationEntity.getStreet().equals(recieverEntity.getLocation().getStreet())) {
                        return false;
                    }
                    if (locationEntity.getArea() != null && !locationEntity.getArea().equals(recieverEntity.getLocation().getArea())) {
                        return false;
                    }
                    if (locationEntity.getTown() != null && !locationEntity.getTown().equals(recieverEntity.getLocation().getTown())) {
                        return false;
                    }
                    if (locationEntity.getDistrict() != null && !locationEntity.getDistrict().equals(recieverEntity.getLocation().getDistrict())) {
                        return false;
                    }
                    if (locationEntity.getState() != null && !locationEntity.getState().equals(recieverEntity.getLocation().getState())) {
                        return false;
                    }
                    return locationEntity.getZipcode() == null || locationEntity.getZipcode().equals(recieverEntity.getLocation().getZipcode());
                })
                .map(recieverEntity -> recieverService.getRecieverDetails(recieverEntity.getReciever_id()))
                .toList();
    }

    public String register(CredentialsDto credentialsDto) {
        CredentialsEntity credentialsEntity = credentialsRepository.save(CredentialsEntity.builder().userName(credentialsDto.getUsername()).confirmPassword(credentialsDto.getConfirmPassword()).userRole(credentialsDto.getUserRole()).build());
        return  credentialsEntity.getUserName();
    }

    public List<CredentialsDto> getAllAccounts() {
        return credentialsRepository.findAll().stream().map((credentials)->CredentialsDto.builder().username(credentials.getUserName()).confirmPassword(credentials.getConfirmPassword()).userRole(credentials.getUserRole()).build()).toList();
    }

    public FeedBackDto postFeedBack(FeedBackDto feedBackDto) {
        feedBackRepository.save(FeedBackEntity.builder().feedBack(feedBackDto.getFeedBack()).build());
        return feedBackDto;
    }


    public List<FeedBackDto> getAllFeedBackDto() {
        return feedBackRepository.findAll().stream().map((feed)->FeedBackDto.builder().feedBack(feed.getFeedBack()).build()).toList();
    }

    public CountDto getAllCount() {
        List<FoodEntity> foodItems =foodRepository.findAll();
        Double totalQuantity = foodItems.stream()
                .mapToDouble(FoodEntity::getQuantity)
                .sum();
        return CountDto.builder().donorsCount(donorRepository.findAll().size()).recieversCount(recieverRespository.findAll().size()).foodCount(totalQuantity/2.0).build();
    }

    public LoginDto getUserId(LoginDto loginDto) {
        if(Objects.equals(loginDto.getUserRole(), "donor")){
            loginDto.setUserId(donorRepository.findByUserName(loginDto.getUsername()).getDonor_Id());
        } else{
            loginDto.setUserId(recieverRespository.findByUserName(loginDto.getUsername()).getReciever_id());
        }
        return loginDto;
    }
}
