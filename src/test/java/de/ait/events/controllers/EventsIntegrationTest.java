package de.ait.events.controllers;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("Endpoint/events is works:")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
class EventsIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Nested
    @DisplayName("GET /events:")
    public class GetEvents {

        @Test
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_empty_list_of_events_for_empty_database() throws Exception {
            mockMvc.perform(get("/api/events"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(0)));
        }

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_list_of_events_for_not_empty_database() throws Exception{
            mockMvc.perform(get("/api/events"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.size()", is(4)))
                    .andExpect(jsonPath("$.[0].id", is(1)))
                    .andExpect(jsonPath("$.[1].id", is(2)))
                    .andExpect(jsonPath("$.[2].id", is(3)))
                    .andExpect(jsonPath("$.[3].id", is(4)));
        }
    }

    @Nested
    @DisplayName("POST /events:")
    public class PostEvent {

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_created_event() throws Exception {
            mockMvc.perform(post("/api/events")
                    .contentType("application/json")
                    .content("{\n" +
                            "  \"name\": \"Новое событие\",\n" +
                            "  \"description\": \"Описание нового события\"\n" +
                            "}"))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(5)));
        }

    }

    @Nested
    @DisplayName("GET /events/{event-id}:")
    public class GetEvent {

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_existed_event() throws Exception {
            mockMvc.perform(get("/api/events/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.name", is("Событие 1")))
                    .andExpect(jsonPath("$.description", is("Описание события 1")));
        }

        @Test
        @Sql(scripts = "/sql/data.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void return_404_for_not_existed_event() throws Exception {
            mockMvc.perform(get("/api/events/5"))
                    .andExpect(status().isNotFound());
        }
    }
}