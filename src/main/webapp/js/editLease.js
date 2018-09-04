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
        url:"/lease/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                var lease=JSON.parse(resp.content);
                console.log(lease)
                //将后台数据放入页面中
                $("#renterId").val(lease.renterId),
                $("#id").val(lease.id),
                $("#leaseTerm").val(lease.leaseTerm),
                 $("#houseId").val(lease.houseId),
               $("#rental").val(lease.rental),
                    $("#contractTerm").val(lease.contractTerm)
                    $("#createTime").val(lease.createTime)
                    $("#lastModifiedTime").val(lease.lastModifiedTime)


            }

        }



    })

})

function save() {
    $.ajax({
        url:"/lease/update.do",
        dataType:"json",
        scriptCharset:"utf-8",
        data:{
            id:$("#id").val(),
            renterId:$("#renterId").val(),
            houseId:$("#houseId").val(),
            rental:$("#rental").val(),
            leaseTerm:$("#leaseTerm").val(),
            contractTerm:$("#contractTerm").val(),
            createTime:$("#createTime").val(),
            lastModifiedTime:$("#lastModifiedTime").val()
        },
        success:function (resp) {
            if (resp.isSuccess){
                window.location.reload()
                window.location.href="/view/flats/lease.html"
            } else{
                alert("更新失败："+resp.error)
            }

        }

    })

}