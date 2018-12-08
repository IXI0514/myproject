/**
 * 验证用户名密码是否为空
 */
function checklogin() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	//alert("username:"+username +"\n" +"password:"+password);
	if (username==null||username==""||password==null||password==""){
	    alert("用户名密码不能为空");
	    return false;
    }
    return true;


}

//全选方法
function checkAll(node) {
    var eles = document.getElementsByName("check");
    for (var a in eles){
        eles[a].checked = node.checked;
    }
}
//批量删除

function deleAll() {
    var array = checkedinput();
    var len =array.length;

    if(len==0){
        alert("未选择行！！");
    }else{
        var pid="";
        for (var i=0;i<len;i++){
        var tr = array[i];
            var text =tr.getElementsByTagName("td")[2].textContent;
            pid=pid+text+'@';
        }
        alert("已删除："+len+"行");
        console.log("已删除："+len+"行");
        var url = "dele?deleno="+pid;
        location.href=url;

    }

}
//删除本行
function deleme(me) {
    var tr = me.parentNode.parentNode;
    //var p = tr.parentNode;
    //p.removeChild(tr);
    var text =tr.getElementsByTagName("td")[2].textContent
    var url = "dele?deleno="+text;
    location.href=url;
}

//结算
function checktotal() {
    var array = checkedinput();
    var total = document.getElementById("total");
    var t=0.0;
    for (var i=0;i<array.length;i++){
        var tr = array[i];
        var price =tr.getElementsByTagName("td")[4].textContent;
        var num =tr.getElementsByTagName("td")[5].textContent;
        t+=price*num;
    }
    total.textContent = t+"￥";
    document.getElementById("gototal").value=t;
}

//付款提交
function goCheckout() {
    var array = checkedinput();
    var len = array.length;
    if(len>0){
        var check ="";
        for (var i=0;i<len;i++){
            var tr = array[i];
            var text =tr.getElementsByTagName("td")[2].textContent;
            check=check+text+'@';
        }
        var checkvalue=document.getElementById("checkvalue");
        checkvalue.value =check;
        return true;
    }else{
        alert("请选择需要结算的商品！！");
    }
    return false;
}

//被选中de
function checkedinput() {
    var array =new Array();
    var tab = document.getElementById("carttable");
    var trs = tab.getElementsByTagName("tr");
    for (var i = 1; i < trs.length - 1; i++) {
        var tr = trs[i];
        var input = tr.getElementsByTagName("input")[0];
        if (input != null && input.checked) {
            array.push(tr);
        }
    }
    return array;
}

//检查信息1
function  checkloc() {
    var reuser = document.getElementById("reuser").value;
    var phone = document.getElementById("phone").value;
    var postcode = document.getElementById("postcode").value;
    var site = document.getElementById("site").value;
    var regx1 =/^[\u4e00-\u9fa5]{1,10}[\u4e00-\u9fa5]$/;
    var regx2 =/^(131|132|133|177|188)\d{8}/;
    var regx3 =/^[0-9]\d{5}$/;
    var regx4 =/.{5,30}/;
   /* var msg="";
    console.log(regx1.test(user));
    msg=regx1.test(user)?"":"用户名 "+regx2.test(phone)?"":"电话 "+regx3.test(postcode)?"":"邮编 "+regx4.test(site)?"":"地址";*/

    if (regx1.test(reuser)&&regx2.test(phone)&&regx3.test(postcode)&&regx4.test(site)){
        return true;
    }
    alert("填写有误！！\n请按要求填写");
    return false;

}

//检查信息2
function  checkpay() {
    var cid = document.getElementById("cardid").value;
    var password = document.getElementById("password").value;
    var regx1 =/\d{16}|\d{18}/;
    var regx2 =/\d{6}/;
    if(cid!=""&&password!=""){
        if (regx1.test(cid)&&regx2.test(password)){
            return true;
        }else {
            alert("卡号或密码填写有误！！");
        }
    }else {
        alert("卡号和密码不能为空！！");
    }
    return false;

}


function  savetableinfo() {
    alert("将要跳转到下载页面");
    console.log(document.getElementById("detailtable").innerHTML);
    document.getElementById("tableinfo").value=document.getElementById("tablespan").innerHTML;
}


