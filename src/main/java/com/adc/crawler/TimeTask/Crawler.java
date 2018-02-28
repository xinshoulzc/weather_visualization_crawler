package com.adc.crawler.TimeTask;

import com.adc.crawler.utils.HttpHelper;
import com.adc.crawler.utils.FileHelper;
import com.adc.crawler.utils.DateHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.TimerTask;

import com.adc.crawler.constant.JsonServiceURL;
import com.adc.crawler.constant.FileOutputDir;

import javax.annotation.PostConstruct;

/**
 * @author zhichengliu
 * @create -02-27-11:31
 **/

@Component
public class Crawler extends TimerTask{

    @PostConstruct
    @Scheduled(cron = "0 0 0 * * ?")
    public void run() {
//        System.out.println("I am running in Class com.adc.crawler.Crawler");
        String baseUrl = JsonServiceURL.AUTO_STATION_URL + "GetAutoStationDataByDatetime_Geliku/";
        getWind(baseUrl);
    }

    private void getWind(String baseUrl) {
        String stopDate = DateHelper.getNow().substring(0, 8) + "000000";
        String beginDate = DateHelper.getPostponeDateByYear(stopDate, -1);
        String endDate = DateHelper.getPostponeDateByDay(beginDate, 1);
        // TODO: filepath format edit (windows version)
        File baseFilePath = new File(FileOutputDir.OUTPUT_DIR + "wind\\");
        File[] downloadedfile =  baseFilePath.listFiles();
        if (downloadedfile != null && downloadedfile.length > 0) {
            beginDate = FileHelper.getDateByFileName(downloadedfile[downloadedfile.length - 1].getName())[0];
            endDate = FileHelper.getDateByFileName(downloadedfile[downloadedfile.length - 1].getName())[1];
        }
        while(endDate.compareTo(stopDate) < 0){
            String filePath = baseFilePath + "\\" + beginDate + "-" + endDate + ".csv";
            String data = HttpHelper.getCsvData(baseUrl + beginDate + "/" + endDate);
            FileHelper.save2file(data, filePath);
            beginDate = endDate;
            endDate = DateHelper.getPostponeDateByDay(beginDate, 1);
        }
    }
}
