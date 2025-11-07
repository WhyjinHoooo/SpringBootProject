/*DOM : 특정 웹 페이지를 구성하는 태그들이 객체화 되어 서로 트리 형태로 접근이 가능하게끔
부모와 자식간의 트리 구조러 생성되는 것을 의미*/
const container = document.getElementById("container");
const menuAdmin = document.getElementById("menuAdmin");
const menuList = document.getElementById("menuList");

//CSRF 토큰과 헤더이름 가져오기
const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
const csrfGeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');