package com.example.TemplateFieldMapping.controller;


import com.example.TemplateFieldMapping.model.InputFieldsTO;
import com.example.TemplateFieldMapping.model.UploadDataTO;
import com.example.TemplateFieldMapping.service.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RestController()
public class TemplateController {

  @Autowired
  private TemplateService templateService;

    @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
    @RequestMapping(value = "/create/orgtable" ,method = RequestMethod.POST)
  public Boolean createOrganisationTable(@RequestBody InputFieldsTO inputFieldsTO){
   return templateService.schemaForData(inputFieldsTO);
  }

  @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
  @RequestMapping("/put/data")
  public void  putDataInOrgTable(@RequestBody UploadDataTO data){
      templateService.uploadData(data);
  }
}
