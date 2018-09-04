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
        url:"/lease/delete.do?id="+id,
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
        url:"/lease/list.do?page="+page,
        dataType:"json",
        success:function (resp) {
            if (resp.isSuccess){
                //需要将json字符串转json对象
                var jsonObject=JSON.parse(resp.content);
                var table=$("#leaseData")
                var html="";
                html+="<tr>\n" +
                    "\t\t\t<th>序号</th>\n" +
                    "\t\t\t<th>租户信息</th>\n" +
                    "\t\t\t<th>房子信息</th>\n" +
                    "\t\t\t<th>房租</th>\n" +
                    "\t\t\t<th>租房时长</th>\n" +
                    "\t\t\t<th>合同时长</th>\n" +
                    "\t\t\t<th>添加时间</th>\n" +
                    "\t\t\t<th>修改时间</th>\n" +
                    "\t\t\t<th>操作</th>\n" +
                    "\t\t</tr>"
                for (var i = 0; i < jsonObject.length; i++) {
                    var lease=jsonObject[i];
                    console.log(lease)
                    html+="<tr>"
                    html+="<td>"+lease.id+"</td>"
                    html+="<td>"+lease.renterId+"</td>"
                    html+="<td>"+lease.houseId+"</td>"
                    html+="<td>"+lease.rental+"</td>"
                    html+="<td>"+lease.leaseTerm+"</td>"
                    html+="<td>"+lease.contractTerm+"</td>"
                    html+="<td>"+lease.createTime+"</td>"
                    html+="<td>"+lease.lastModifiedTime+"</td>"
                    var detailUrl="detailLease.html?id="+lease.id
                    var editUrl="editLease.html?id="+lease.id
                    html+="<td>\n" +
                        "\t\t\t\t<a class=\"fa fa-info\" title=\"详情\" href="+detailUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-pencil\" title=\"编辑\" href="+editUrl+"></a>\n" +
                        "\t\t\t\t&nbsp;&nbsp;\n" +
                        "\t\t\t\t<a class=\"fa fa-remove\" title=\"删除\" href=\"#\" onclick=\"deleteLease("+lease.id+")\"></a>\n" +
                        "\t\t\t</td>"
                    html+="</tr>"
                }
                $("#leaseListInfo").text("共有"+jsonObject.length+"条记录，当前第"+page+"页")
                $("#currentPage").text(page)
                table.html(html)
            }

        }
        
        
        
    })
}
