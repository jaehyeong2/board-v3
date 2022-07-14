package jjfactory.boardtest.business.util;


import jjfactory.boardtest.global.handler.ex.BusinessException;
import jjfactory.boardtest.global.handler.ex.ErrorCode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class signUpValidTest {

    @Test
    @DisplayName("전화번호 길이체크")
    void test(){
        String phone = "01092494441";
        String invalidPhone = "010924944412";

        Assertions.assertThrows(BusinessException.class,() ->{
            phoneLengthCheck(invalidPhone);
        });
    }

    @Test
    @DisplayName("하이픈 포함 체크")
    void test2(){
        String phone = "01092494441";
        String invalidPhone = "0109249-4441";

        Assertions.assertThrows(BusinessException.class,() ->{
            hyphenCheck(invalidPhone);
        });
    }

    void phoneLengthCheck(String phone){
        int length = phone.length();
        if(length != 11) throw new BusinessException(ErrorCode.INVALID_PHONE_LENGTH);
    }

    void hyphenCheck(String phone){
        if(phone.contains("-")) throw new BusinessException(ErrorCode.INVALID_TYPE_VALUE);
    }

    void nameValidate(String username){

    }

    void passwordValidate(String username){

    }

}
