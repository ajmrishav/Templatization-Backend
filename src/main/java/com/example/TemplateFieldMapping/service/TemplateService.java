package com.example.TemplateFieldMapping.service;

import com.example.TemplateFieldMapping.Repository.TemplateRepository;
import com.example.TemplateFieldMapping.model.InputFieldsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class TemplateService {

    public static final Logger infoLog = Logger.getLogger(TemplateService.class.getName());

    @Autowired
    private TemplateRepository templateRepository;
    public Boolean schemaForData(InputFieldsTO inputFieldsTO) {
        String orgName;
        try {
            if (inputFieldsTO != null) {
                String query = createQueryForTable(inputFieldsTO);
                templateRepository.executeQuery(query);
            }
        } catch (Exception e) {
            infoLog.log(Level.INFO, "Exception in TemplateService.schemaForData()" + e.getMessage());
        }
        return true;
    }

    private String createQueryForTable(InputFieldsTO inputFieldsTO) {
    String query = null;
    StringBuilder queryBuilder = new StringBuilder();
    queryBuilder.append("Create TABLE `" + inputFieldsTO.getOrgName() + "`");
    queryBuilder.append("( Id INT not null auto_increment primary key ,");
        for (int i = 0; i < inputFieldsTO.getFields().length; i++) {
            if (i != inputFieldsTO.getFields().length - 1) {
                queryBuilder.append("`" + inputFieldsTO.getFields()[i] + "`" + " varchar(100) null ,");
            } else {
                queryBuilder.append("`" + inputFieldsTO.getFields()[i] + "`" + " varchar(100) null );");

            }
        }
        return queryBuilder.toString();
    }
}
