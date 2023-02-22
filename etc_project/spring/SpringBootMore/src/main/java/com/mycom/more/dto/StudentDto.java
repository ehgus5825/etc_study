package com.mycom.more.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// lombok 은 setter나 getter등을 자동으로 생성해주는 library인데
// annotation을 붙여서 이것이 가능하려면 현재 사용하고 있는 IDE (-STS)와 lombok library가 소통해야함.
// Maven library에 lombok을 java로 실행하여 IDE 찾은 후 인스톨 -> 재시작
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentDto {
    
	// 파라미터의 순서는 위에서 부터 순차적으로 내려옴
    private int studentId;
    private String studentNm;
    private String email;
    private String phone;
    
    // setters & getters
    // constructors
    // toString
    // lombok을 통해서 만든 것보다 내가 직접 만든 것이 우선 순위를 가진다. (Overriding 개념)
//    public String getStudentNm() {
//        return studentNm;
//    }
//
}