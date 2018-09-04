// 注意本项目使用了JS和jQuery，但给学生说明JS和jQuery等以后会讲，现在大家先了解即可

$('.menu-title').click(function(){
	$('.menu-title').removeClass('active');
	$(this).addClass('active');
});

$('.sub-menu li').click(function(){
	$('.sub-menu li').removeClass('active');
	$('.sub-menu li .fa-circle').removeClass('fa-circle').addClass('fa-circle-thin');
	$(this).addClass('active');
	$(this).find('i').removeClass('fa-circle-thin').addClass('fa-circle');
})
$(function(){$("#user").text(getCurrentUser())})
function getCurrentUser(){
	var res;
    $.ajax({
		async:false,
        url:"login/currentUser.do",
        dataType:"json",
        success:function (resp) {
        	console.log(resp)
        	if (resp.isSuccess){
        		console.log(resp.content)
                res=resp.content;
			}
        }
    })
	return res;
}
function logout() {
    $.ajax({
        async:false,
        url:"login/logout.do",
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                window.location.href="/"
            }else{
            	alert(resp.error)
			}
        }
    })
}