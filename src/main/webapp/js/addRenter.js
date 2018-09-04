/*$(function () {
    //获取路径上的id
    var url=window.location.href;
    var params=url.split("?")[1];
    var kvs=params.split("&")
    var id;
    for (var i = 0; i < kvs.length; i++) {
        if (kvs[i].indexOf("id")==0){
            var id= kvs[i].split("=")[1]
            break;
        }
    }
    console.log(id)
    //根据id请求后台数据
    if (id==null){
        return
    }
    $.ajax({
        url:"/renter/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                var renter=JSON.parse(resp.content);
                console.log(renter)
                //将后台数据放入页面中
                $("#user").val(renter.user)
                $("#id").val(renter.id)
                $("#phoneNum").val(renter.phoneNum)
                $("#sex").val(renter.sex)
                $("#idNum").val(renter.idNum)
                $("#nativePlace").val(renter.nativePlace)
                $("#createTime").val(renter.createTime)
                $("#lastModifiedTime").val(renter.lastModifiedTime)

            }

        }



    })

})*/

  function addRenter() {
    $.ajax({
        url:"/renter/insert.do",
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        dataType:"json",
        scriptCharset:"utf-8",
        data:{
            name:$("#name").val(),
            phone:$("#phone").val(),
            sex:$("#sex").val(),
            idNum:$("#idNum").val(),
            nativePlace:$("#nativePlace").val()
        },
        success:function (resp) {
            if (resp.isSuccess){
                window.location.reload()
                window.location.href="/view/flats/renter.html"
            } else{
                alert("添加失败："+resp.error)
            }

        }

    })

}