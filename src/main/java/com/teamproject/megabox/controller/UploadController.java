package com.teamproject.megabox.controller;
import com.teamproject.megabox.dto.UploadResultDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    //import 주의 org.springframework.beans.factory.annotation.Value;
    @Value("${com.teamproject.upload.path}")// 주소값 -> c:\\upload
    private String uploadPath;
    //파일 저장 : 파일.transferTo(저장 경로(저장이름포함된));

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(@RequestParam(value = "uploadFiles") MultipartFile[] uploadFiles){

        List<UploadResultDTO> resultDTOList = new ArrayList<>(); //이미지 파일 업로드시 바로 받아서 볼수있게 List관련해서 받겠다.

        for (MultipartFile uploadFile : uploadFiles){//여러장의 이미지를 선택했을때 for문을 활용해서 받겠다.
            if(uploadFile.getContentType().startsWith("image")==false){//이미지 파일만 업로드 가능
                log.warn("이미지 파일이 아닙니다."); //이미지 파일이 아닐결우 경고창을 띄워서 알린다.
                //403에러 리턴 : 클라이언트 요청을 이해 했지만 서버가 요청 거부, 요청 리소스에 대한 권한이 없을 때
                return new ResponseEntity<>(HttpStatus.FORBIDDEN); // 이미지 파일이 아닌경우 403에러를 리턴하겠다
            }
            String OriginalName = uploadFile.getOriginalFilename();//업로드된 파일 하나당 파일의 이름을 받겠다.
            log.info("OriginalName : " +OriginalName);//처음으로 받은 파일의 이름은 로그창에 출력
            //받은 파일명을 subsTring을 활용해 \뒤의 파일이름을 사로운 변수에 받겠다.
            String fileName = OriginalName.substring(OriginalName.lastIndexOf("\\")+1);
            log.info("fileName : " + fileName);// 새롭게 받은 파일이름을 로그창에 출력

            //날짜 폴더 생성(중복되는 파일명을 방지하기 위한 목정)
            String folderPath = makeFolder();//아래에 정의된 메소드를 호출에 해당 날짜의 폴더가 없으면 폴더를 생성 그리고 그 경로를 저장
            String uuid = UUID.randomUUID().toString();//UUID생성(파일별로 상이한 값을 주기위한 난수)
            //c://upload\2021\03\11\dfsdfuid_fileName.jpg
            String saveName = uploadPath+File.separator+folderPath+File.separator+uuid+"_"+fileName;//새로 저장될 파일의 이름을 생성
            Path savePath = Paths.get(saveName);// 저장될 경로를 지정
            try {
                uploadFile.transferTo(savePath);// 새로운 파일 저장
                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));// 저장한 파일을 리턴해서 바로 활용(아작스에서 활용)
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }
        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);//선택된 이미지들을 리턴
    }

    private String makeFolder(){//날짜 폴더 생성 method
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));//오늘 날짜 객체를 "2024/03/11" 문자로 str에 할당
        String folderPath = str.replace("/",File.separator);//날짜에 입력된 '/'를 운영체제에 맞는 분리기호로 변경
        File upLoadPathFile = new File(uploadPath,folderPath);//경로 생성
        if(upLoadPathFile.exists()==false){
            upLoadPathFile.mkdirs();// 경로에 폴더가 없으면 날짜별로 폴더를 만들어 주겠다. (관리의 용이를 위한 행위 )
        }
        return folderPath;
    }// makeFolder끝

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFiles (@RequestParam(value = "fileName") String fileName){//src를 누르면 자세히 보여주는 화면을 만들것으로 생각
        ResponseEntity<byte[]> result = null;// 바이트 배열로 진행시키는 것이 메모리도 아끼고 효율이 좋은것으로 생각됨
        String srcfileName = null;//선택할 파일의 이름을 담을 변수 생성
        try {
            srcfileName = URLDecoder.decode(fileName,"utf-8"); //디코더를 이용해서 선택된 파일을 utf-8을 활용 하여 변경 -> 새로운 변수에 할당
            File file = new File(uploadPath+File.separator+srcfileName);// 새로운변수명을 File타입으로 할당
            HttpHeaders headers = new HttpHeaders();//파일은 body에 관련된 정보는 headr에 입력하여 전송 파일에 관한 정보이므로 header에 입력
            //파일의 mime타입을 header에 추가//Files.probeContentType 파일의 마임파입 검사
            headers.add("Content-Type",Files.probeContentType(file.toPath()));//mimetype 체크
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file),headers,HttpStatus.OK); //넘겨줄 정보를 result변수에 할당
        } catch (Exception e) {
            new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 예외가 발생하면 서버에러 메세지를 생성
        }
        return result;// 결과물(바이트 배열)리턴
    }//getFiles끝

    //이미지 삭제하기
    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(@RequestParam(value = "fileName")String fileName){
        String srcfileName = null;
        try {
            srcfileName = URLDecoder.decode(fileName,"UTF-8");
            //파일 삭제
            File file = new File(uploadPath + File.separator + srcfileName);
            boolean result = file.delete();
            //썸네일 삭제
            File thumbNaile = new File(file.getParent(),"s_"+file.getName());
            result = thumbNaile.delete();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}//클래스 끝
