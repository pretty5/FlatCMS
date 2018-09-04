$(function () {
    loadData(1);
})
function deleteHouse(id) {
    var flag=confirm("确定要删除吗")
    if (!flag){
        return;
    }
    //请求后台删除
    $.ajax({
        url:"/house/delete.do?id="+id,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //重新加载
                window.location.reload();
            } else{
                alert("删除失败")
            }

        }



    })
    //刷新页面
}
function currentPage() {
    return parseInt($("#currentPage").text())
}
function prePage() {
    var prePage=currentPage()-1>0?currentPage()-1:1;
    loadData(prePage)
}
function nextPage() {
    loadData(currentPage()+1)
}
function loadData(page) {
    $.ajax({
        url:"/house/list.do?page="+page,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //需要将json字符串转json对象
                var jsonObject=JSON.parse(resp.content);
                var table=$("#houseData")
                var html="";
                html+="<tr>\n" +
                    "\t\t\t<th>序号</th>\n" +
                    "\t\t\t<th>所属地区</th>\n" +
                    "\t\t\t<th>所属小区</th>\n" +
                    "\t\t\t<th>单元号</th>\n" +
                    "\t\t\t<th>所属楼层</th>\n" +
                    "\t\t\t<th>房间号</th>\n" +
                    "\t\t\t<th>面积</th>\n" +
                    "\t\t\t<th>朝向</th>\n" +
                    "\t\t\t<th>限住人数</th>\n" +
                    "\t\t\t<th>出租价格(元)</th>\n" +
                    "\t\t\t<th>出租状态</th>\n" +
                    "\t\t\t<th>操作</th>\n" +
                    "\t\t</tr>"
                for (var i = 0; i < jsonObject.length; i++) {
                    var house=jsonObject[i];
                    console.log(house)
                    html+="<tr>"
                    html+="<td>"+house.id+"</td>"
                    html+="<td>"+house.area+"</td>"
                    html+="<td>"+house.community+"</td>"
                    html+="<td>"+house.unitNum+"</td>"
                    html+="<td>"+house.floor+"</td>"
                    html+="<td>"+house.roomNum+"</td>"
                    html+="<td>"+house.space+"</td>"
                    html+="<td>"+house.direction+"</td>"
                    html+="<td>"+house.limit+"</td>"
                    html+="<td>"+house.price+"</td>"
                    html+="<td>"+house.status+"</td>"
                    var detailUrl="detail.html?id="+house.id
                    var editUrl="edit.html?id="+house.id
                    html+="<td>\n" +
                        "\t\t\t\t<a class=\"fa fa-info\" title=\"详情\" href="+detailUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-pencil\" title=\"编辑\" href="+editUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-remove\" title=\"删除\" href=\"#\" onclick=\"deleteHouse("+house.id+")\"></a>\n" +
                        "\t\t\t</td>"
                    html+="</tr>"
                }
                $("#houseListInfo").text("共有"+jsonObject.length+"条记录，当前第"+page+"页")
                $("#currentPage").text(page)
                table.html(html)
            }

        }
        
        
        
    })
}
