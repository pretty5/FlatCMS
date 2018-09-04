$(function () {
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
                $("#name").val(renter.name)
                $("#id").val(renter.id)
                $("#phone").val(renter.phone)
                $("#sex").val(renter.sex)
                $("#idNum").val(renter.idNum)
                $("#nativePlace").val(renter.nativePlace)

            }

        }



    })

})

function save() {
    $.ajax({
        url:"/renter/update.do",
        dataType:"json",
        scriptCharset:"utf-8",
        data:{
            id:$("#id").val(),
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
            }else {
                alert("更新失败："+resp.error)
            }



        }

    })

}