package com.AshghalIntegration.FileManagement.Service;


import com.AshghalIntegration.FileManagement.Entity.ApiLogHistory;
import com.AshghalIntegration.FileManagement.Repository.ApiLogHistoryRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.logging.LogLevel;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gaurav Mam
 */

@Service
@Slf4j
public class LogService {

    private final String logPrefix = "AshghalIntegration >> \n";

    @Autowired
    private ApiLogHistoryRepo apiLogHistoryRepo;

    @Autowired
    private Environment environment;

    @Autowired
    private Environment env;


    public void saveLogFile(String logFile, ApiLogHistory apiLogHistory) {
        try {
            apiLogHistory.setLogFile(logFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        apiLogHistoryRepo.save(apiLogHistory);
    }

    public void writeToFile(String file, String data, LogLevel logLevel) {

        if (file == null || data == null || file.isEmpty()) {
            return;
        }
        data = new Date() + " " + logPrefix + " " + data;
        data += "\n";

        if (logLevel == LogLevel.DEBUG) {
            log.debug(data);
        } else {
            log.info(data);
        }

        if (logLevel.equals(LogLevel.INFO) ||
                logLevel.equals(LogLevel.DEBUG) && "DEBUG".equalsIgnoreCase(environment.getProperty("sepm.log.file.level"))) {
            BufferedWriter writer = null;
            try {
                if (new File(file).exists()) {
                    writer = new BufferedWriter(new FileWriter(file, true));
                    writer.append(data);
                } else {
                    writer = new BufferedWriter(new FileWriter(file));
                    writer.write(data);
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    public String getLogFileAbsolutePath(String ApiRefId) {

        String logFilePath = null;

        if (null != ApiRefId) {
            logFilePath = env.getProperty("ApiLogHistory.path") + "/" + ApiRefId + "/";
            try {
                Files.createDirectories(Paths.get(logFilePath).toAbsolutePath().normalize());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logFilePath = env.getProperty("ApiLogHistory.path");
        }

        File f = new File(logFilePath + new SimpleDateFormat("ddMMyyyyHHmmss").format(new Date()) + ".txt");
//        UUID.randomUUID().toString()
        return f.getAbsolutePath();
    }
}
