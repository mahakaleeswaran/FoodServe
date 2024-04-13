package com.demo.foodserve.service;

import com.demo.foodserve.Repository.*;
import com.demo.foodserve.dto.DonorDto;
import com.demo.foodserve.dto.FoodDto;
import com.demo.foodserve.dto.PostDto;
import com.demo.foodserve.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    @Autowired
    private CoordinatesRepository coordinatesRepository;

    public DonorDto register(DonorDto donorDto) {
        String[] address=donorDto.getAddress().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        coordinatesRepository.save(CoordinateEntity.builder().latitude(donorDto.getLatitude()).longitude(donorDto.getLongitude()).location(locationEntity).build());
        Date date = new Date();
        DonorEntity donorEntity = donorRepository.save(DonorEntity.builder().userName(donorDto.getUsername()).registeredDate(date).name(donorDto.getName()).email(donorDto.getEmail()).organization(donorDto.getOrganization()).location(locationEntity).phoneNumber(donorDto.getPhoneNumber()).build());
        donorDto.setUsername(donorEntity.getUserName());
        donorDto.setId(donorEntity.getDonor_Id());
        return donorDto;
    }

    public DonorDto getDonorDetails(Integer id) {
        DonorEntity donorEntity = donorRepository.findById(id).orElse(new DonorEntity());
        LocationEntity locationEntity = donorEntity.getLocation();

        return DonorDto.builder()
                .username(donorEntity.getUserName())
                .id(donorEntity.getDonor_Id())
                .address(locationEntity != null ? locationEntity.getDoorNo() + "," + locationEntity.getStreet() + "," + locationEntity.getArea() + "," +
                        locationEntity.getTown() + "," + locationEntity.getDistrict() + "," + locationEntity.getState() + "," + locationEntity.getZipcode() : "")
                .name(donorEntity.getName())
                .email(donorEntity.getEmail())
                .phoneNumber(donorEntity.getPhoneNumber())
                .organization(donorEntity.getOrganization())
                .posts(donorEntity.getPostEntity().stream()
                        .map(post -> {
                            RecieverEntity receiver = post.getReceiver();
                            if (receiver == null) {
                                receiver = new RecieverEntity();
                            }
                            return PostDto.builder()
                                    .receiverName(receiver.getName())
                                    .receiverEmail(receiver.getEmail())
                                    .receiverPhoneNumber(receiver.getPhoneNumber())
                                    .createdDate(post.getCreatedDate())
                                    .location(post.getLocation().getDoorNo() + "," + post.getLocation().getStreet() + "," + post.getLocation().getArea() + "," +
                                            post.getLocation().getTown() + "," + post.getLocation().getDistrict() + "," + post.getLocation().getState() + "," +
                                            post.getLocation().getZipcode())
                                    .served(post.getServed())
                                    .posts(post.getFoodEntities().stream()
                                            .map(food -> FoodDto.builder().foodName(food.getFoodName()).quantity(food.getQuantity()).build())
                                            .toList())
                                    .build();
                        })
                        .toList())
                .build();
    }


    public PostDto putPost(Integer id, PostDto postdto) {
        DonorEntity donorEntity=donorRepository.findById(id).orElse(new DonorEntity());
        String[] address=postdto.getLocation().split(",");
        LocationEntity locationEntity=LocationEntity.builder().doorNo(address[0]).street(address[1]).area(address[2]).town(address[3]).district(address[4]).state(address[5]).zipcode(address[6]).build();
        locationRepository.save(locationEntity);
        coordinatesRepository.save(CoordinateEntity.builder().latitude(postdto.getLatitude()).longitude(postdto.getLongitude()).location(locationEntity).build());
        Date date = new Date();
        PostEntity postEntity = postRepository.save(PostEntity.builder().createdDate(date).location(locationEntity).receiver(null).email(donorEntity.getEmail()).phoneNumber(donorEntity.getPhoneNumber()).served(false).donor(donorEntity).build());
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

    public DonorDto deleteDonor(Integer id) {
        DonorDto donorDto=getDonorDetails(id);
        donorRepository.deleteById(id);
        return donorDto;
    }
}
