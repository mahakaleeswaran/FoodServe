package com.demo.foodserve.service;
import com.demo.foodserve.Repository.LocationRepository;
import com.demo.foodserve.Repository.PostRepository;
import com.demo.foodserve.Repository.RecieverRespository;
import com.demo.foodserve.dto.*;
import com.demo.foodserve.dto.RecieverDto;
import com.demo.foodserve.entity.*;
import com.demo.foodserve.entity.RecieverEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Component
public class RecieverService {

    @Autowired
    private RecieverRespository recieverRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private LocationRepository locationRepository;

    public RecieverDto register(RecieverDto recieverDto) {
        String[] address=recieverDto.getAddress().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        Date date = new Date();
        recieverRepository.save(RecieverEntity.builder().userName(recieverDto.getUsername()).registeredDate(date).location(locationEntity).email(recieverDto.getEmail()).name(recieverDto.getName()).organization(recieverDto.getOrganization()).phoneNumber(recieverDto.getPhoneNumber()).build());
        return recieverDto;
    }

    public RecieverDto getRecieverDetails(Integer id) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        LocationEntity locationEntity=recieverEntity.getLocation();
        return RecieverDto.builder().address(locationEntity.getDoorNo()+","+locationEntity.getStreet()+","+locationEntity.getArea()+","+locationEntity.getTown()+","+locationEntity.getDistrict()+","+locationEntity.getState()+","+locationEntity.getZipcode()).name(recieverEntity.getName()).email(recieverEntity.getEmail()).phoneNumber(recieverEntity.getPhoneNumber()).organization(recieverEntity.getOrganization()).posts(recieverEntity.getPostEntity().stream().map((post)->PostDto.builder().location(post.getLocation().getDoorNo()+","+post.getLocation().getStreet()+","+post.getLocation().getArea()+","+post.getLocation().getTown()+","+post.getLocation().getDistrict()+","+post.getLocation().getState()+","+post.getLocation().getZipcode()).served(post.getServed()).posts(post.getFoodEntities().stream().map((food)-> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build()).toList()).build()).toList()).build();
    }

    public PostDto acceptPost(Integer id, Integer postId) {
        PostEntity postEntity = postRepository.findById(postId).orElse(null);
        assert postEntity != null;
        postEntity.setReciever(recieverRepository.findById(id).orElse(new RecieverEntity()));
        Date date=new Date();
        postEntity.setAcceptedDate(date);
        postEntity.setServed(true);
        postRepository.save(postEntity);
        LocationEntity locationEntity=postEntity.getLocation();
        return PostDto.builder().location(locationEntity.getDoorNo()+","+locationEntity.getStreet()+","+locationEntity.getArea()+","+locationEntity.getTown()+","+locationEntity.getDistrict()+","+locationEntity.getState()+","+locationEntity.getZipcode()).served(postEntity.getServed()).posts(postEntity.getFoodEntities().stream().map((food)->FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build()).toList()).build();
    }

    public RecieverDto updateDetails(Integer id, RecieverDto recieverDto) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        recieverEntity.setName(recieverDto.getName());
        String[] address=recieverDto.getAddress().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        recieverEntity.setLocation(locationEntity);
        recieverEntity.setEmail(recieverDto.getEmail());
        recieverEntity.setOrganization(recieverDto.getOrganization());
        recieverEntity.setPhoneNumber(recieverDto.getPhoneNumber());
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }


    public RecieverDto deleteReciever(Integer id) {
        RecieverDto recieverDto=getRecieverDetails(id);
        recieverRepository.deleteById(id);
        return recieverDto;
    }

    public List<PostDto> getAllPostsBasedOnLocation(Integer id,LocationDto locationDto) {
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
        return postRepository.findAll()
                .stream().filter((postEntity)-> !postEntity.getServed())
                .filter(postEntity -> {
                    if (locationEntity.getLocation_id() != null && !locationEntity.getLocation_id().equals(postEntity.getLocation().getLocation_id())) {
                        return false;
                    }
                    if (locationEntity.getDoorNo() != null && !locationEntity.getDoorNo().equals(postEntity.getLocation().getDoorNo())) {
                        return false;
                    }
                    if (locationEntity.getStreet() != null && !locationEntity.getStreet().equals(postEntity.getLocation().getStreet())) {
                        return false;
                    }
                    if (locationEntity.getArea() != null && !locationEntity.getArea().equals(postEntity.getLocation().getArea())) {
                        return false;
                    }
                    if (locationEntity.getTown() != null && !locationEntity.getTown().equals(postEntity.getLocation().getTown())) {
                        return false;
                    }
                    if (locationEntity.getDistrict() != null && !locationEntity.getDistrict().equals(postEntity.getLocation().getDistrict())) {
                        return false;
                    }
                    if (locationEntity.getState() != null && !locationEntity.getState().equals(postEntity.getLocation().getState())) {
                        return false;
                    }
                    return locationEntity.getZipcode() == null || locationEntity.getZipcode().equals(postEntity.getLocation().getZipcode());
                })
                .map(post -> PostDto.builder().id(post.getPostId()).donorEmail(post.getDonor().getEmail()).donorName(post.getDonor().getName()).donorPhoneNumber(post.getDonor().getPhoneNumber()).location(post.getLocation().getDoorNo()+","+post.getLocation().getStreet()+","+post.getLocation().getArea()+","+post.getLocation().getTown()+","+post.getLocation().getDistrict()+","+post.getLocation().getState()+","+post.getLocation().getZipcode()).served(post.getServed()).posts(post.getFoodEntities().stream().map((food)-> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build()).toList()).build()).toList();
    }

    public List<PostDto> getAllAcceptedPosts(Integer id) {
        return postRepository.findAll().stream()
                .filter(post -> post.getReciever() != null && Objects.equals(post.getReciever().getReciever_id(), id))
                .map(post -> PostDto.builder().donorEmail(post.getDonor().getEmail()).donorName(post.getDonor().getName()).donorPhoneNumber(post.getDonor().getPhoneNumber())
                        .id(post.getPostId())
                        .location(post.getLocation().getDoorNo() + "," + post.getLocation().getStreet() + "," + post.getLocation().getArea() + "," +
                                post.getLocation().getTown() + "," + post.getLocation().getDistrict() + "," + post.getLocation().getState() + "," +
                                post.getLocation().getZipcode())
                        .served(post.getServed())
                        .posts(post.getFoodEntities().stream()
                                .map(food -> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build())
                                .toList())
                        .build())
                .toList();
    }

}
