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
        url:"/lease/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                var lease=JSON.parse(resp.content);
                console.log(lease)
                //将后台数据放入页面中
                $("#user").val(lease.user)
                $("#id").val(lease.id)
                $("#phoneNum").val(lease.phoneNum)
                $("#sex").val(lease.sex)
                $("#idNum").val(lease.idNum)
                $("#nativePlace").val(lease.nativePlace)
                $("#createTime").val(lease.createTime)
                $("#lastModifiedTime").val(lease.lastModifiedTime)

            }

        }



    })

})*/

  function addLease() {
    $.ajax({
        url:"/lease/insert.do",
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        dataType:"json",
        scriptCharset:"utf-8",
        data:{
            renterId:$("#renterId").val(),
            houseId:$("#houseId").val(),
            rental:$("#rental").val(),
            leaseTerm:$("#leaseTerm").val(),
            contractTerm:$("#contractTerm").val()
        },
        success:function (resp) {
            if (resp.isSuccess){
                window.location.reload()
                window.location.href="/view/flats/lease.html"
            } else{
                alert("添加失败："+resp.error)
            }

        }

    })

}