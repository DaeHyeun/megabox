package com.teamproject.megabox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


@Data
@AllArgsConstructor
public class UploadResultDTO {
    private String fileName;
    private String uuid;
    private String folderPath;

    // 파입업로드시 한개의 파일을 바로 출력하기 위한 메소드
    public String getImageURL(){
        try {
            //url을 utf-8로 인코딩하는 과정
            return URLEncoder.encode(folderPath+"/"+uuid+"_"+fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    //thumbNaile
    public String getThumbNaileURL(){
        try {
            //url을 utf-8로 인코딩하는 과정
            return URLEncoder.encode(folderPath+"/"+"s_"+uuid+"_"+fileName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
