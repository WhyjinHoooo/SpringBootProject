document.getElementById("buttonSubmit").addEventListener("click", function(){
	console.log("클릭");
	// 객체
	const formData = {
		memID : document.getElementById("memID").value,
		title : document.getElementById("title").value,
		content : document.getElementById("content").value,
		writer : document.getElementById("writer").value,
	}
	console.log(formData);
	// 공지사항 작성 페이지 index.jsp에서 만든 meta CSRF태그 2개를 js파일로 가져온다
	const csrfToken = document.querySelector("meta[name='_csrf']").getAttribute('content');
	const csrfHeader = document.querySelector("meta[name='_csrf_header']").getAttribute('content');
	
	fetch("/menu/add", {
		method: "POST",
		headers : {
			'Content-Type': 'application/json',
			[csrfHeader] : csrfToken // csrf헤더와 토큰을 동적으로 추가
		}, //HTTP url에 해당하는 header
		body : JSON.stringify(formData) // JSON타입으로 변경
	}).then(response => {
		if(!response.ok){
			throw new Error("공지사항 작성 실패")
		}
		return response.text();
	}).then(_=>{
		console.log("Success");
		window.location.href="/"
		// localhost:0000 로 페이지가 이동합니다.
	}).catch(error => {
		console.log("Error가 발생", error);
	}) // 프론트 -> 백엔드
	
});


