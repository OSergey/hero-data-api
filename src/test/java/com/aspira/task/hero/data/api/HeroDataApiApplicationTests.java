package com.aspira.task.hero.data.api;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {HeroDataApiApplication.class})
@AutoConfigureMockMvc
@Slf4j
public class HeroDataApiApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Before
  public void initCache() throws Exception {
    mockMvc.perform(post("/api/hero-management/heroes").content(String.valueOf(1))
        .contentType(MediaType.APPLICATION_JSON))
    .andReturn();
  }

  @Test
  public void getHeroes() throws Exception {
    mockMvc.perform(get("/api/hero-management/heroes")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(e -> log.info(e.getResponse().getContentAsString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andReturn();
  }

  @Test
  public void getHeroById() throws Exception {
    mockMvc.perform(get("/api/hero-management/heroes/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(e -> log.info(e.getResponse().getContentAsString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(1))
        .andExpect(jsonPath("level").value(1))
        .andExpect(jsonPath("experience").value(0))
        .andReturn();
  }

  @Test
  public void updateExperienceHeroById() throws Exception {
    mockMvc.perform(put("/api/hero-management/heroes/1/experience").content(String.valueOf(200))
        .contentType(MediaType.APPLICATION_JSON))
        .andDo(e -> log.info(e.getResponse().getContentAsString()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("id").value(1))
        .andExpect(jsonPath("level").value(3))
        .andExpect(jsonPath("experience").value(0))
        .andReturn();
  }

}
