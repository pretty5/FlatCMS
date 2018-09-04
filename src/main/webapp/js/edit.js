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
        url:"/house/get.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                var house=JSON.parse(resp.content);
                console.log(house)
                //将后台数据放入页面中
                $("#area").val(house.area)
                $("#id").val(house.id)
                $("#unitNum").val(house.unitNum)
                $("#floor").val(house.floor)
                $("#remark").val(house.remark)
                $("#roomNum").val(house.roomNum)
                $("#price").val(house.price)
                $("#space").val(house.space)
                $("#limit").val(house.limit)
                $("#isDoubleAir").val(house.isDoubleAir)
                $("#createTime").val(house.createTime)
                $("#lastModifiedTime").val(house.lastModifiedTime)
                $("#facility").val(house.facility)
                $("#community").val(house.community)
                $("#direction").val(house.direction)
                $("#address").val(house.address)

            }

        }



    })

})

function save() {
    $.ajax({
        url:"/house/update.do",
        dataType:"json",
        scriptCharset:"utf-8",
        data:{
            id:$("#id").val(),
            area:$("#area").val(),
            unitNum:$("#unitNum").val(),
            floor:$("#floor").val(),
            remark:$("#remark").val(),
            roomNum:$("#roomNum").val(),
            price:$("#price").val(),
            space:$("#space").val(),
            limit:$("#limit").val(),
            isDoubleAir:$("#isDoubleAir").val(),
            facility:$("#facility").val(),
            community:$("#community").val(),
            direction:$("#direction").val(),
            address:$("#address").val(),
            fitment:$("#fitment").val(),
            status:$("#status").val()
        },
        success:function (resp) {
            if (resp.isSuccess){
                window.location.reload()
            } else{
                alert("更新失败："+resp.error)
            }

        }

    })

}