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
        type:"post",
        contentType:'application/x-www-form-urlencoded; charset=UTF-8',
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                var lease=JSON.parse(resp.content);
                 console.log(lease)
                //将后台数据放入页面中
                $("#renterId").text(lease.renterId)
                $("#houseId").text(lease.houseId)
                $("#rental").text(lease.rental)
                $("#leaseTerm").text(lease.leaseTerm)
                $("#contractTerm").text(lease.contractTerm)
                $("#createTime").text(lease.createTime)
                $("#lastModifiedTime").text(lease.lastModifiedTime)

            }

        }



    })



})