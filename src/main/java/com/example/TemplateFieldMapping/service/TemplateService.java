package com.example.TemplateFieldMapping.service;

import com.example.TemplateFieldMapping.Repository.TemplateRepository;
import com.example.TemplateFieldMapping.model.InputFieldsTO;
import com.example.TemplateFieldMapping.model.UploadDataTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
                queryBuilder.append("`" + inputFieldsTO.getFields()[i] + "`" + " text null ,");
            } else {
                queryBuilder.append("`" + inputFieldsTO.getFields()[i] + "`" + " text null );");

            }
        }
        return queryBuilder.toString();
    }

    public void uploadData(UploadDataTO uploadDataTO) {

        try {
            if (uploadDataTO != null) {
                templateRepository.executeQuery(createQueryForInsert(uploadDataTO));
            }
        } catch (Exception e) {
            infoLog.log(Level.INFO, "Exception in TemplateService.uploadData()" + e.getMessage());
        }
    }

    private String createQueryForInsert(UploadDataTO uploadDataTO) {
        String query = null, combinedString = "", concatenatedString = "",finalResult = "";
        StringBuilder queryBuilder = new StringBuilder();
        if (uploadDataTO.getData() != null && uploadDataTO.getData().length > 0) {
            String[] fieldsName = uploadDataTO.getData()[0];
            if (fieldsName != null && fieldsName.length > 0) {
                for(int i=0;i<fieldsName.length;i++){
                    if(i< fieldsName.length-1) {
                        concatenatedString = concatenatedString + "`" + fieldsName[i] + "` ,";
                    }
                    else{
                        concatenatedString = concatenatedString + "`" + fieldsName[i] + "`";
                    }
                }
//                concatenatedString = Arrays.stream(fieldsName)
//                        .collect(Collectors.joining(", "));
                concatenatedString = "(" + concatenatedString + ")";
            }
        }
        for (int i = 1; i < uploadDataTO.getData().length; i++) {
//            String commaString = Arrays.stream(uploadDataTO.getData()[i])
//                    .collect(Collectors.joining(", "));
            combinedString = "";
            String[] value = uploadDataTO.getData()[i];
            for (int j = 0; j < value.length; j++) {
                if (j < value.length - 1) {
                    combinedString = combinedString + "'" + value[j] + "',";
                } else {
                    combinedString = "(" + combinedString + "'" + value[j] + "' )";
                }
            }
            if(i<uploadDataTO.getData().length-1) {
                finalResult = finalResult + combinedString + ",";
            }
            else{
                finalResult = finalResult + combinedString;
            }
        }
        finalResult  = "VALUES" + finalResult;
        queryBuilder.append("Insert into " + uploadDataTO.getOrgName());
        queryBuilder.append(concatenatedString);
        queryBuilder.append(finalResult);
        return queryBuilder.toString();
    }

}
