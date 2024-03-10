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
        recieverRepository.save(RecieverEntity.builder().registeredDate(date).location(locationEntity).email(recieverDto.getEmail()).name(recieverDto.getName()).organization(recieverDto.getOrganization()).phoneNumber(recieverDto.getPhoneNumber()).build());
        return recieverDto;
    }

    public RecieverDto getRecieverDetails(Integer id) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        LocationEntity locationEntity=recieverEntity.getLocation();
        return RecieverDto.builder().address(locationEntity.getDoorNo()+","+locationEntity.getStreet()+","+locationEntity.getArea()+","+locationEntity.getTown()+","+locationEntity.getDistrict()+","+locationEntity.getState()+","+locationEntity.getZipcode()).name(recieverEntity.getName()).email(recieverEntity.getEmail()).phoneNumber(recieverEntity.getPhoneNumber()).organization(recieverEntity.getOrganization()).posts(recieverEntity.getPostEntity().stream().map((post)->PostDto.builder().location(post.getLocation().getDoorNo()+","+post.getLocation().getStreet()+","+post.getLocation().getArea()+","+post.getLocation().getTown()+","+post.getLocation().getDistrict()+","+post.getLocation().getState()+","+post.getLocation().getZipcode()).served(post.getServed()).posts(post.getFoodEntities().stream().map((food)-> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build()).toList()).build()).toList()).build();
    }

    public PostDto acceptPost(Integer id, Integer postId) {
        PostEntity postEntity = postRepository.findById(postId).orElse(null);
        postEntity.setReciever_id(id);
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

    public RecieverDto updateName(Integer id, String name) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        recieverEntity.setName(name);
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }

    public RecieverDto updateAddress(Integer id, String updateaddress) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        String[] address=updateaddress.split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        recieverEntity.setLocation(locationEntity);
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }

    public RecieverDto updatePhoneNumber(Integer id, String number) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        recieverEntity.setPhoneNumber(number);
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }

    public RecieverDto updateOrganization(Integer id, String organization) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        recieverEntity.setOrganization(organization);
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }

    public RecieverDto updateEmail(Integer id, String email) {
        RecieverEntity recieverEntity=recieverRepository.findById(id).orElse(new RecieverEntity());
        recieverEntity.setEmail(email);
        recieverRepository.save(recieverEntity);
        return getRecieverDetails(id);
    }

    public RecieverDto deleteReciever(Integer id) {
        RecieverDto recieverDto=getRecieverDetails(id);
        recieverRepository.deleteById(id);
        return recieverDto;
    }

}
