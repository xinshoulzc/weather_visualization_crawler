package com.adc.crawler;

import utils.HttpHelper;
import utils.FileHelper;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.util.TimerTask;
import utils.DateHelper;

/**
 * @author zhichengliu
 * @create -02-27-11:31
 **/

public class Crawler extends TimerTask{
    private final String AUTO_STATION_URL = "http://61.152.126.152/JsonService_V2/AutoStationJsonService.svc/";
    private final String OUTPUT_DIR = "src\\output\\";
    public void run() {
//        System.out.println("I am running in Class com.adc.crawler.Crawler");
        String baseUrl = AUTO_STATION_URL + "GetAutoStationDataByDatetime_Geliku/";
        getWind(baseUrl);
    }
    private void getWind(String baseUrl) {
        String stopDate = DateHelper.getNow().substring(0, 8) + "000000";
        String beginDate = DateHelper.getPostponeDateByYear(stopDate, -1);
        String endDate = DateHelper.getPostponeDateByDay(beginDate, 1);
        File baseFilePath = new File(OUTPUT_DIR + "wind\\");
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
