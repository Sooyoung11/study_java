package edu.java.loop03;

public class LoopMain03 {

    public static void main(String[] args) {
        /* 아래와 같은 형식으로 구구단 2단을 출력하세요.
         * 2 x 1 = 2
         * 2 x 2 = 4
         * 2 x 3 = 6
         * ...
         * 2 x 8 = 16
         * 2 x 9 = 18
         */

        for(int i=1; i<10; i++) {
//            System.out.println("2 x "+i+" = "+2*i);
            // Ctrl+/: 현재 커서가 있는 라인을 주석 토글
//            System.out.printf("2   x %d = %d\n", i, (i * 2));
        }
        
        // System.out.printf(): 문자열을 포맷에 맞춰서 출력.
        // %d: 정수, %f: 실수, %s: 문자열
        // \n: new line(줄바꿈), \t: tab(탭)
        
        /* 아래와 같이 출력하세요.
         *   1 2 3 4 5 6 7 8 9 10
         *   11 12 13 .... 
         *                      100
         */
 
        
        for(int i=1; i<=100; i++) {
            System.out.printf("%d \t", i);
            if(i % 10 == 0) {
                System.out.println();
            }   
        }
        
        
        
        
        
    }

}
