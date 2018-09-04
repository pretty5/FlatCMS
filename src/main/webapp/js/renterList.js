$(function () {
    loadData(1);
})
function deleteRenter(id) {
    var flag=confirm("确定要删除吗")
    if (!flag){
        return;
    }
    //请求后台删除
    $.ajax({
        url:"/renter/delete.do?id="+id,
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
        url:"/renter/list.do?page="+page,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //需要将json字符串转json对象
                var jsonObject=JSON.parse(resp.content);
                var table=$("#renterData")
                var html="";
                html+="<tr>\n" +
                    "\t\t\t<th>序号</th>\n" +
                    "\t\t\t<th>租户姓名</th>\n" +
                    "\t\t\t<th>手机号码</th>\n" +
                    "\t\t\t<th>性别</th>\n" +
                    "\t\t\t<th>籍贯</th>\n" +
                    "\t\t\t<th>身份证</th>\n" +
                    "\t\t\t<th>添加时间</th>\n" +
                    "\t\t\t<th>修改时间</th>\n" +
                    "\t\t\t<th>操作</th>\n" +
                    "\t\t</tr>"
                for (var i = 0; i < jsonObject.length; i++) {
                    var renter=jsonObject[i];
                    console.log(renter)
                    html+="<tr>"
                    html+="<td>"+renter.id+"</td>"
                    html+="<td>"+renter.name+"</td>"
                    html+="<td>"+renter.phone+"</td>"
                    html+="<td>"+renter.sex+"</td>"
                    html+="<td>"+renter.nativePlace+"</td>"
                    html+="<td>"+renter.idNum+"</td>"
                    html+="<td>"+renter.createTime+"</td>"
                    html+="<td>"+renter.lastModifiedTime+"</td>"
                    var editUrl="editRenter.html?id="+renter.id
                    html+="<td>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-pencil\" title=\"编辑\" href="+editUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-remove\" title=\"删除\" href=\"#\" onclick=\"deleteRenter("+renter.id+")\"></a>\n" +
                        "\t\t\t</td>"
                    html+="</tr>"
                }
                $("#renterListInfo").text("共有"+jsonObject.length+"条记录，当前第"+page+"页")
                $("#currentPage").text(page)
                table.html(html)
            }

        }
        
        
        
    })
}
