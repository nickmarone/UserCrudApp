package com.example.usercrudapp.util;

import com.example.usercrudapp.model.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVProcessor {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<User> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<User> users = new ArrayList<User>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                User user = new User();
                user.setName(csvRecord.get(Constants.CSV_HEAD_NAME));
                user.setSurname(csvRecord.get(Constants.CSV_HEAD_SURNAME));
                user.setEmail(csvRecord.get(Constants.CSV_HEAD_EMAIL));
                user.setAddress(csvRecord.get(Constants.CSV_HEAD_ADDRESS));
                user.setPhoneNumber(csvRecord.get(Constants.CSV_HEAD_PHONE_NUMBER));

                users.add(user);
            }

            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
