package com.example.mybatisxml.mapper;

import com.example.mybatisxml.domain.City;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CityMapper {

    City selectCityById(int id);
}
