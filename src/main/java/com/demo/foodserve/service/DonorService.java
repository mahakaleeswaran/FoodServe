package com.demo.foodserve.service;

import com.demo.foodserve.Repository.DonorRepository;
import com.demo.foodserve.Repository.FoodRepository;
import com.demo.foodserve.Repository.LocationRepository;
import com.demo.foodserve.Repository.PostRepository;
import com.demo.foodserve.dto.DonorDto;
import com.demo.foodserve.dto.FoodDto;
import com.demo.foodserve.dto.LocationDto;
import com.demo.foodserve.dto.PostDto;
import com.demo.foodserve.entity.DonorEntity;
import com.demo.foodserve.entity.FoodEntity;
import com.demo.foodserve.entity.LocationEntity;
import com.demo.foodserve.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DonorService {
    @Autowired
    private DonorRepository donorRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private LocationRepository locationRepository;

    public DonorDto register(DonorDto donorDto) {
        String[] address=donorDto.getAddress().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        donorRepository.save(DonorEntity.builder().name(donorDto.getName()).email(donorDto.getEmail()).organization(donorDto.getOrganization()).location(locationEntity).phoneNumber(donorDto.getPhoneNumber()).build());
        return donorDto;
    }

    public DonorDto getDonorDetails(Integer id) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        LocationEntity locationEntity=donorEntity.getLocation();
        return DonorDto.builder().address(locationEntity.getDoorNo()+","+locationEntity.getStreet()+","+locationEntity.getArea()+","+locationEntity.getTown()+","+locationEntity.getDistrict()+","+locationEntity.getState()+","+locationEntity.getZipcode()).name(donorEntity.getName()).email(donorEntity.getEmail()).phoneNumber(donorEntity.getPhoneNumber()).organization(donorEntity.getOrganization()).posts(donorEntity.getPostEntity().stream().map((post)->PostDto.builder().location(LocationDto.builder().doorNo(post.getLocation().getDoorNo()).street(post.getLocation().getStreet()).area(post.getLocation().getArea()).town(post.getLocation().getTown()).district(post.getLocation().getDistrict()).state(post.getLocation().getState()).zipcode(post.getLocation().getZipcode()).build().toString()).served(post.getServed()).posts(post.getFoodEntities().stream().map((food)-> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build()).toList()).build()).toList()).build();
    }

    public PostDto putPost(Integer id, PostDto postdto) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        String[] address=postdto.getLocation().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        PostEntity postEntity = postRepository.save(PostEntity.builder().location(locationEntity).reciever_id(null).email(donorEntity.getEmail()).phoneNumber(donorEntity.getPhoneNumber()).served(false).donor_Id(id).build());
        foodRepository.saveAll(postdto.getPosts().stream().map((post)-> FoodEntity.builder().post_Id(postEntity.getPostId()).foodName(post.getFoodName()).quantity(post.getQuantity()).build()).toList());
        return postdto;
    }


    public DonorDto updateDetils(Integer id,DonorDto donorDto) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        donorEntity.setName(donorDto.getName());
        String[] address=donorDto.getAddress().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        donorEntity.setLocation(locationEntity);
        donorEntity.setEmail(donorDto.getEmail());
        donorEntity.setOrganization(donorDto.getOrganization());
        donorEntity.setPhoneNumber(donorDto.getPhoneNumber());
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto updateName(Integer id, String name) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        donorEntity.setName(name);
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto updateAddress(Integer id, String updateaddress) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        String[] address=updateaddress.split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        donorEntity.setLocation(locationEntity);
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto updatePhoneNumber(Integer id, String number) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        donorEntity.setPhoneNumber(number);
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto updateOrganization(Integer id, String organization) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        donorEntity.setOrganization(organization);
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto updateEmail(Integer id, String email) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        donorEntity.setEmail(email);
        donorRepository.save(donorEntity);
        return getDonorDetails(id);
    }

    public DonorDto deleteDonor(Integer id) {
        DonorDto donorDto=getDonorDetails(id);
        donorRepository.deleteById(id);
        return donorDto;
    }
}
