<template>
  <div class="login-container">
    <div class="content-container">
      <div class="text-with-image-login">
        <img src="../assets/custard-logo.png" alt="커스터드 로고">
        <div class="text">로그인</div>
      </div>
      <form id="loginForm" @submit.prevent="login">
        <input type="text" placeholder="이메일" id="email" v-model="loginForm.customerEmail" required>
        <input type="password" placeholder="비밀번호" id="password" v-model="loginForm.customerPwd" required>
        <br><br>
        <button type="submit">로그인</button>
      </form>
      <br>
      <p class="memeber">회원이 아니신가요?</p>
      <a href="./signup">회원가입</a>

    </div>
  </div>
</template>

<script>
import {useCustomerStore} from "@/stores/useCustomerStore";
import axios from "axios";

export default {
  data() {
    return {
      loginForm: {
        customerEmail: '',
        customerPwd: '',
      },
      customerEmail: '',
    };
  },
  methods: {
    reqNotificationPermission(){
      if ('Notification' in window){ //윈도우 창에서 알림
        Notification.requestPermission().then(perm =>{
          if(perm === "granted" && localStorage.getItem('accessToken')) { //로컬 스토리지에 토큰이 있을 때와 권한을 허용했을 때만 알림이 푸시되게 함
            console.log("알림 허용")
            new Notification("오랜만에 접속하셨네요🥹",{
              body: "고객님을 위한 깜짝 쿠폰이 있어요🎁",
              icon: "https://github.com/beyond-sw-camp/be02-fin-CuStard-CRM/assets/122515113/2a07a238-c33b-4913-be49-3aadb1f7b548",
            });
            localStorage.setItem('notified' , 'true');
          }else{
            console.log("알림이 차단됨")
          }
        });
      }
    },
    async login() {
      // const backend = "http://localhost:8080"
      let backend = "http://192.168.0.31:80/api";
      const customerEmail = {
        customerEmail : this.loginForm.customerEmail
      }
      console.log(customerEmail);
      // const customerEmail = toRaw(this.loginForm.customerEmail)
      // axios.get(backend + '/coupon/pushNoti/'+this.customerEmail)
      let couponPush = await axios.post(
          backend + "/coupon/pushnoti",
          customerEmail
      )

      console.log(couponPush)
      const customerStore = useCustomerStore(); // 스토어 직접 사용
      const result = await customerStore.login(this.loginForm);

      // 로그인 응답에서 status 값을 확인
      if (result && result.status !== false) {
        // status가 0이 아니면 로그인 성공 처리
        const customerIdx = sessionStorage.getItem('customerIdx');
        const accessToken = sessionStorage.getItem('atoken');

        localStorage.setItem('customerIdx', customerIdx);
        localStorage.setItem('accessToken', "Bearer " + accessToken);

        console.log(`로그인 성공: ${customerIdx}`);

        this.$router.push("/");

        if(couponPush) {
          this.reqNotificationPermission();
        }

      } else if (result && result.status === false) {
        // status가 0이면 로그인 거부
        alert("이메일 인증을 해주세요.");
      } else {
        // 그 외 경우는 로그인 실패 처리
        alert("ID와 PW를 다시 확인해주세요");
        this.loginForm = {
          customerEmail: '',
          customerPwd: ''
        };
      }
    },
    toggleOrderSearch() {
      var orderSearchContainer = document.querySelector('.order-search-container');
      var isVisible = orderSearchContainer.style.maxHeight !== '0px';

      if (isVisible) {
        orderSearchContainer.style.maxHeight = '0';
        orderSearchContainer.style.opacity = '0';
      } else {
        orderSearchContainer.style.maxHeight = '200px'; // 필요에 따라 높이를 조절하세요.
        orderSearchContainer.style.opacity = '1';
      }
    },
    searchOrder() {
      var orderNumber = document.getElementById('orderNumber').value;
      var orderEmail = document.getElementById('orderEmail').value;
      console.log('주문번호:', orderNumber);
      console.log('이메일:', orderEmail);
    }
  }
};
</script>




<style scoped>
* {
  font-family: "NotoSansKR-Bold", "malgun gothic", AppleGothic, dotum, sans-serif;
}

body {
  width: 100%;
  background-color: #f9f9f9;
  margin: 0;
  padding: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100vh;
}

.login-container {
  background-color: #f9f9f9;
  padding: 20px;
  border-radius: 8px;
  width: 100%;
  text-align: center;
}

.text-with-image-login {
  text-align: center;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.text-with-image-login img {
  width: 100px;
  height: auto;
  border-radius: 20%;
  margin-bottom: 10px;
}

.text {
  font-family: Arial, sans-serif;
  font-size: 17px;
  color: #333;
  text-align: center;
  text-decoration: none;
  margin-bottom: 15px;
  color: #494949;
}


.login-container h2 {
  color: #333;
}

.login-container input {
  width: 100%;
  padding: 10px;
  margin-bottom: 0px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
  margin-bottom: 10px;
}

.login-container button {
  width: 100%;
  padding: 10px;
  background-color: black;
  color: #fff;
  border: 1px solid black;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.5s;
}

.login-container button:hover {
  background-color: #FFFFFF;
  color:black;
}

.content-container {
  text-align: center;
  margin-top: 80px;
  background-color: #fff;
}

.content-container a {
  color: #333;
  text-decoration: none;
  margin: 0 10px;
  font-size: 12px;
}

.sns-buttons {
  margin-top: 20px;
}

.sns-buttons a {
  display: inline-block;
  margin: 0 13px;
  padding: 8px;
  text-decoration: none;
  color: #fff;
  border-radius: 50%;
  cursor: pointer;
}

.sns-buttons .facebook {
  background-color: #3b5998;
}

.sns-buttons .kakao {
  background-color: #fae100;
}

.sns-buttons .naver {
  background-color: #00c63b;
}

.confortLogin {
  font-size: 11px;
  color: #696969;
  margin-top: 10px;
}

.sns-buttons img {
  width: 20px;
  height: 20px;
  margin-right: 4px;
}

.line {
  margin-top: 25px;
  border: none;
  height: 1px;
  background-color: #c2c8cc;
}

.loginError {
  font-size: 10px;
  margin-top: 20px;
  margin-bottom: -5px;
  color: #c2c8cc;
}

.serchOrder {
  font-size: 11px;
  color: #696969;
  margin-top: 20px;
  cursor: pointer;
}

.order-search-container {
  opacity: 0;
  max-height: 0;
  overflow: hidden;
  transition: max-height 0.3s ease-out, opacity 0.3s ease-out;
  margin-top: 10px;
}

.order-search-container input {
  width: 100%;
  padding: 10px;
  margin-bottom: 10px;
  box-sizing: border-box;
  border: 1px solid #ccc;
  border-radius: 4px;
}
.memeber{
  font-weight: normal;
  font-size: 12px;
  color: #a4a4a4;
}
</style>
