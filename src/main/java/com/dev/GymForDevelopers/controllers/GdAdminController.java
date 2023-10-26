package com.dev.GymForDevelopers.controllers;

import com.dev.GymForDevelopers.exceptions.ErrorsResponse;
import com.dev.GymForDevelopers.exceptions.ExceptionConst;
import com.dev.GymForDevelopers.exceptions.GdNotFoundException;
import com.dev.GymForDevelopers.models.DTO.GdAdminDTO;
import com.dev.GymForDevelopers.models.entity.GdAdmin;
import com.dev.GymForDevelopers.services.GdAdminService;
import com.dev.GymForDevelopers.converters.GdConverterAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class GdAdminController {
    private final GdAdminService adminService;
    private final GdConverterAdmin gdConverterAdmin;

    @Autowired
    public GdAdminController(GdAdminService adminService, GdConverterAdmin gdConverterAdmin) {
        this.adminService = adminService;
        this.gdConverterAdmin = gdConverterAdmin;
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody GdAdminDTO adminDTO) {
        adminService.save(gdConverterAdmin.convertToEntity(adminDTO));
        return ResponseEntity.ok("Админ успешно создан");
    }


    @GetMapping("/{name}")
    public GdAdmin getAdmin(@PathVariable ("name") String name){
        return adminService.findByName(name);
    }

//  @ExceptionHandler
//  private ResponseEntity<ErrorsResponse> handleException(GdRuntimeException e){
//          ErrorsResponse response = new ErrorsResponse(
//                  "NULL_CANNOT_BE_PASSED_AS_A_PARAMETER",
//                  "В качестве параметра был передан null"
//          );
//      return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
//  }

    @ExceptionHandler
    private ResponseEntity<ErrorsResponse> handleException(GdNotFoundException e){
       ErrorsResponse response = new ErrorsResponse(
               ExceptionConst.ERRORS_CODE_NF,
               ExceptionConst.MESSAGE_NF
       );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
