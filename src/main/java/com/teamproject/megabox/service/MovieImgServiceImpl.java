package com.teamproject.megabox.service;

import com.teamproject.megabox.entity.MovieImg;
import com.teamproject.megabox.repository.MovieImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class MovieImgServiceImpl implements MovieImgService{
    @Value("${itemImageLocation}")
    private String itemImgLocation ;

    private final FileService fileService;
    private final MovieImageRepository movieImageRepository;

    @Override
    public void saveItemImg(MovieImg movieImg, MultipartFile movieImgFile) throws Exception {
        String oriImgName =  movieImgFile.getOriginalFilename();
        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            //오리진 파일이 있으면 업로드 실행
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, movieImgFile.getBytes());

            String imgUrl = "/images/item/"+imgName;// --> /images/item/asdasdasd.jpg
            //엔티티 필드값 입력
            movieImg.update(oriImgName,imgName,imgUrl);
            // 엔티티 영속저장(DB저장)
            movieImageRepository.save(movieImg);
        }
    }


}
