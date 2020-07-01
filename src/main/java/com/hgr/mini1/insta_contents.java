package com.hgr.mini1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class insta_contents {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "C:/dev/Workspace/IdeaProjects/mini1/chromedriver.exe");


        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-default-apps");

        ChromeDriver driver = new ChromeDriver(options);

        Map<Integer,String> result = new HashMap<>();

        for(int i=1;i<=1;i++) {
            for(int j=1;j<=3;j++){
                driver.get("https://www.instagram.com/explore/tags/%EA%BD%83%EC%A7%91/");
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                WebElement data = driver.findElementByXPath("//*[@id=\"react-root\"]/section/main/article/div[2]/div/div["+i+"]/div["+j+"]/a");
                String url = data.getAttribute("href");
                driver.get(url);
                driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
                List<WebElement> innerdata = driver.findElementsByXPath("//*[@id=\"react-root\"]/section/main/div/div/article/div[2]/div[1]/ul/div/li/div/div/div[2]/span");
                String text = innerdata.get(0).getText();
                System.out.println(text);
                result.put(j,text);
            }
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonStr = null;
        try {
            jsonStr = mapper.writeValueAsString(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }




        driver.close();
        driver.quit();
    }
}


