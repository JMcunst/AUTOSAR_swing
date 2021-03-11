## 2020-2 Ajou univ, AUTOSAR simulation by JAVA swing

### LDWS 시스템을 JAVA 스윙으로 구현

LDWS : Lane Departure Warning System. 차량이 주행 중 선을 밟으면 운전자에게 알람을 해주는 시스템

조건 1. LDWS 시스템 ON
조건 2. 속도 60 넘어야함
조건 3. 깜빡이가 켜지지 않은 상태
조건 4. 와이퍼 단계가 상(빨간색)이지 않을 때 (폭우시, Lane을 찾기 어렵다고 판단) 
-> 3가지 조건이 맞을 때 알람 트리거 발동

알람 1. 소리
알람 2. 좌석 떨림 (좌, 우)
알람 3. 계기판 표시

우측 하단의 버튼으로 각각의 알람을 조건별로 발동할 수 있음.
색깔은 구별 않함 

- 기능 1
 LDWS 시스템 ON, 속도 60이상, 선을 밟았지만, 깜빡이를 킨 상태이기 때문에 알람 발동 않함. ( 운전자 차선 변경의 의도로 파악 )
![team4_swing_cap_1 (1)](https://user-images.githubusercontent.com/17956765/110782067-07180d00-82aa-11eb-9388-6e0d72725b1d.PNG)

- 기능 2
 LDWS 시스템 ON, 속도 60이상, 선을 밟음. 와이퍼가(상)이 아니기 때문에(현재:노란색(중)) 알람 발동, 시트알람은 OFF 상태이기 때문에, 계기판 알람(좌)와 소리알람 발동
![team4_swing_cap_2 (1)](https://user-images.githubusercontent.com/17956765/110782138-1e56fa80-82aa-11eb-8a81-4b00ada2d663.PNG)


## ERROR
- 쿠키런 오픈소스를 활용하였는데, 일부 comment 및 variable 수정 필요 
