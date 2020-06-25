package poly.util;

public class regtest {

    public static void main(String[] args) {
        String testword =  "testword & 테스트 단어 t테스트단어 ^^ ";

        testword = testword.replaceAll("[^가-힣\\s]", "");
        System.out.println("치환 문자 : " + testword);

        testword = testword.replaceAll("[\\s]{2}", "");
        System.out.println("치환 문자 : " + testword);
    }
}
