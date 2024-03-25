package com.teamproject.megabox.service;

import com.teamproject.megabox.entity.MovieImg;
import com.teamproject.megabox.repository.MovieImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieImgServiceImpl implements MovieImgService{

    //겅로 설정
    @Value("${itemImageLocation}")
    private String movieImgLocation ;

    //의존성주입
    private final FileService fileService;
    private final MovieImageRepository movieImageRepository;

    //영화 저장하기
    @Override
    public void saveItemImg(MovieImg movieImg, MultipartFile movieImgFile) throws Exception {
        String oriImgName =  movieImgFile.getOriginalFilename();
        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)){
            //오리진 파일이 있으면 업로드 실행
            String imgName = fileService.uploadFile(movieImgLocation, oriImgName, movieImgFile.getBytes());

            String imgUrl = "/images/img/"+imgName;// --> /images/item/asdasdasd.jpg
            //엔티티 필드값 입력
            movieImg.update(oriImgName,imgName,imgUrl);
            // 엔티티 영속저장(DB저장)
            movieImageRepository.save(movieImg);
        }
    }

    //movieId에 해당하는 이미지 정보 받아오기
    @Override
    public List<MovieImg> findByMovieId(Long movieId) {
        List<MovieImg> movieImgList = movieImageRepository.findByMovieId(movieId);
        return movieImgList;
    }

    //영화정보 수정하기 (admin)
    @Override
    public void updateMovieImg(Long movieImgId, MultipartFile multipartFile) throws Exception {
        //itemImgId로 조회
        //있으면 기존이미지 삭제
        //가지고온 이미지 업로드
        //itemImg업데이트
        Optional<MovieImg> result = movieImageRepository.findById(movieImgId);
        MovieImg movieImg = result.get();
        //이미지 이름이 비어있지 않으면
        if (!StringUtils.isEmpty(movieImg.getImgName())) {
            fileService.deleteFile(movieImgLocation + "/" + movieImg.getImgName());
        }
        //이미지 업로드
        String oriImgName = multipartFile.getOriginalFilename();
        if (!StringUtils.isEmpty(oriImgName)) {
            String imgName = fileService.uploadFile(movieImgLocation, oriImgName, multipartFile.getBytes());
            //상품이미지
            String imgUrl = "/images/img/" + imgName;
            movieImg.update(oriImgName, imgName, imgUrl);
            movieImageRepository.save(movieImg);
        }

    }


}
