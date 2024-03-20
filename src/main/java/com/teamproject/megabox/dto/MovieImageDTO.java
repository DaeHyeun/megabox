package com.teamproject.megabox.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieImageDTO {
    private String uuid ;
    private String imgName;
    private String path;

    // 파입업로드시 한개의 파일을 바로 출력하기 위한 메소드
    public String getImageURL(){
        try {
            //url을 utf-8로 인코딩하는 과정
            return URLEncoder.encode(path+"/"+uuid+"_"+imgName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
    //thumbNaile
    public String getThumbNaileURL(){
        try {
            //url을 utf-8로 인코딩하는 과정
            return URLEncoder.encode(path+"/"+"s_"+uuid+"_"+imgName, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

}
