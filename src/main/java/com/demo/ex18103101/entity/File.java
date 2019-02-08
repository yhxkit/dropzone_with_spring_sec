package com.demo.ex18103101.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
public class File {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_idx") //h2 쓸때는 하이버네이트가 알아서 해줬는데...
    int fileIdx;

    @Column(name = "file_name")
    String fileName;
    ROLE role;
    //동일 role을 가진 새 파일이 들어오면 기존의 동일 role 파일들을 모두 삭제할지(하나 업로드할때마다 하나만 삭제하면 되고, 기존에 올린 파일들은 재활용 불가)
    //아니면 newest 필드를 하나 추가해서 true false 로 가장 최신 파일만 true를 주고, 동일 role이고 newest가 true 인 파일을 검색해서 newest 를 false로 바꿀지..?(기존 파일들을 재활용하기 위한 구현 발생..)
    boolean newest = true;

    public enum ROLE{
        BANNER_RIGHT, BANNER_LEFT, BANNER_MAIN, BACKGROUND
    }

    public File(){} //디폴트 생성자 머냐..?

    public File(String  fileName, ROLE role){
        this.fileName = fileName;
        this.role = role;
    }






}
