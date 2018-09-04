function login() {
    //第一步  获取表单数据
    var name=$("#username").val()
    var password=$("#password").val()
    var tip=$("#tip")
    //第二步 校验
    if (name.length==0){
        tip.text("名字不能为空")
        return
    }
    if (password.length<6){
        tip.text("密码长度不能小于6位")
    }
    //发送ajax
    $.ajax({
        url:"login/login.do",
        method:"post",
        data:{
            name:name,
            password:md5(password)
        },
        dataType:"json",
        success:function(resp){
            console.log(resp)
            if (resp['isSuccess']){
                window.location.href="admin.html"
            }else{
                tip.text(resp['error'])
            }

        },
        error:function (resp) {
            console.log(resp)
            tip.text(resp['error'])
        }
    })

}
function md5(password) {
    return hex_md5(password);
}
