package com.teamproject.megabox.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class FileService {
    //파일 등록하기
    public String uploadFile(String uploadPath, String originalFile, byte[] fileData) throws Exception {
        //uuid생성 - 중복 방지
        UUID uuid = UUID.randomUUID();
        //확장자 .jpg
        String extension = originalFile.substring(originalFile.lastIndexOf("."));
        //새로운 파읿명
        String saveFileName = uuid.toString() + extension; // 랜덤 문자열.확장자
        //경로와 파일명 더하기
        String fileUploadFullUrl = uploadPath +"/"+saveFileName; //shop/ite/sdadsad.jpg

        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();
        return saveFileName;
    }
    //파일 삭제하기
    public void deleteFile(String filePath){
        //파일 객체 생성
        File deleteFile = new File(filePath);
        //해당 파일이 존재하면 삭제
        if (deleteFile.exists()){
            deleteFile.delete();//File객체가 가지고 있는 Method
        }
    }
}
