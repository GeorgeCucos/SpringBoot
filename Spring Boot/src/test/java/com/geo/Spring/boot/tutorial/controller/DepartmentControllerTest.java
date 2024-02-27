package com.geo.Spring.boot.tutorial.controller;

import com.geo.Spring.boot.tutorial.entity.Department;
import com.geo.Spring.boot.tutorial.error.DepartmentNotFoundException;
import com.geo.Spring.boot.tutorial.service.DepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;

    private Department department;

    @BeforeEach
    void setUp() {
        department=Department.builder()
                .departmentAddress("asdas")
                .departmentCode("it-07")
                .departmentName("it")
                .departmentId(1L)
                .build();
    }

    @Test
    void saveDepartment() throws Exception {
       Department inputdepartment=Department.builder()
                .departmentAddress("asdas")
                .departmentCode("it-07")
                .departmentName("it")
                .build();

        Mockito.when(departmentService.saveDepartment(inputdepartment))
                .thenReturn(department);

        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"departmentName\":\"it\",\n" +
                        "    \"departmentAddress\":\"asdas\",\n" +
                        "    \"departmentCode\":\"it-07\"\n" +
                        "}")).andExpect(status().isOk());
    }

    @Test
    void fetchDepartmentById() throws Exception {
        Mockito.when(departmentService.fetchDepartmentById(1L))
                .thenReturn(Optional.ofNullable(department));

        mockMvc.perform(get("/departments/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName")
                        .value(department.getDepartmentName()));
    }
}