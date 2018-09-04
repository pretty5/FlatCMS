$(function () {
    list(1)
})
function list(page) {
    $.ajax({
        url:"/house/list.do",
        dataType:"json",
        success:function (resp) {
            var table=$("#houseList")
            var jsonArray=JSON.parse(resp.content);
            //var jsonArray2=JSON.parse(resp.content);
            console.log(jsonArray)
            console.log(jsonArray.length)
            var html="<tr>\n" +
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
                "\t\t</tr>";
            for (var i = 0; i < jsonArray.length; i++) {
                html+="<tr>"
                html+="<td>"+jsonArray[i].id+"</td>"
                html+="<td>"+jsonArray[i].area+"</td>"
                html+="<td>"+jsonArray[i].community+"</td>"
                html+="<td>"+jsonArray[i].unitNum+"</td>"
                html+="<td>"+jsonArray[i].floor+"</td>"
                html+="<td>"+jsonArray[i].roomNum+"</td>"
                html+="<td>"+jsonArray[i].space+"</td>"
                html+="<td>"+jsonArray[i].direction+"</td>"
               /* html+="<td>"+jsonArray[i].fitment+"</td>"
                html+="<td>"+jsonArray[i].isDoubleAir+"</td>"*/
                html+="<td>"+jsonArray[i].limit+"</td>"
                html+="<td>"+jsonArray[i].price+"</td>"
                html+="<td>"+jsonArray[i].status+"</td>"
                html+="<td>\n" +
                    "\t\t\t\t<a class=\"fa fa-info\" title=\"详情\" href=\"detail.html\"></a>\n" +
                    "\t\t\t\t&nbsp;&nbsp;\n" +
                    "\t\t\t\t<a class=\"fa fa-pencil\" title=\"编辑\" href=\"edit.html\"></a>\n" +
                    "\t\t\t\t&nbsp;&nbsp;\n" +
                    "\t\t\t\t<a class=\"fa fa-remove\" title=\"删除\" href=\"#\" onclick=\"confirmDelete(1)\"></a>\n" +
                    "\t\t\t</td>"
                html+="</tr>"
            }
            table.html(html)
        }


    })
}