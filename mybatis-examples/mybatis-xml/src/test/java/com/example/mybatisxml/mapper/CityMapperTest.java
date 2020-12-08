package com.example.mybatisxml.mapper;

import com.example.mybatisxml.domain.City;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
class CityMapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    void selectCityById() {
        City city = cityMapper.selectCityById(1);
        assertThat(city.getId()).isEqualTo(1);
        assertThat(city.getName()).isEqualTo("San Francisco");
        assertThat(city.getState()).isEqualTo("CA");
        assertThat(city.getCountry()).isEqualTo("US");
    }
}