<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<%@include file="../../public/back_head.jspf"%>

<style>
	.form-group{
		margin-bottom:15px;
	}
</style>
</head>
<body>
  	<center>
		<form id="ff" method="post">   
    <div class="form-group">   
        <input id="helpTitle"  type="text" name="helpTitle" />   
    </div>   
    <div class="form-group">    
        <input id="helpContent" type="text" name="helpContent"/>   
    </div>  
    <div>
    	<a id="cancel-btn" href="#" onclick="clearForm()" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">重置</a>
    	<a id="submit-btn" href="#" onclick="submitForm()" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>  
    </div>
</form>  
	</center> 
   
</body>
<script type="text/javascript">
var isNew=true;
var helpId = '${_id}' || 0;
if(helpId!="" && helpId!=0){
	isNew=false;
}

//提示
function showDialog(content){
	$.messager.show({
		title:'提示',
		msg:content,
		timeout:3000,
		showType:'slide',
	});
}

function getHelpMsgById(){
	$.ajax({	
    	url:'${pageContext.request.contextPath}/help/getHelpMsgById',
    	type:'POST',
    	data:{
			helpId:helpId
    	},
    	success:function(data){ 
    		if(data.pd===true){
    			$('#helpTitle').textbox("setValue",data.result.helpTitle)	
    			$('#helpContent').textbox("setValue",data.result.helpContent)	
    		}else{
    			showDialog("未查询到该数据，请确定已经登录！")
    		}
    	},
    	error:function(data){  
			alert("请求出错！")	
    	}
    })	
}

$(function() {
	$('#helpTitle').textbox({    
		height:45,
		width:470,
		label: '标题:' ,               
		labelPosition: 'top',
		required:true,
		missingMessage:"不能为空",
		tipPosition:'top',
	})

	$('#helpContent').textbox({    
		height:180,
		width:470,
		label: '内容:' ,               
		labelPosition: 'top',
		multiline:true,
		required:true,
		missingMessage:"不能为空",
		tipPosition:'top',
	})	
	if(isNew===false){//如果修改
		getHelpMsgById();		
	}
	
});


function submitForm(){
	$.messager.progress();	// 显示进度条

	$('#ff').form('submit', {    
	    url:"${pageContext.request.contextPath}/help/saveOrUpdateHelpMsg",  
	    novalidate:true,
	    onSubmit: function(param){   
	    	param.helpId=helpId;
	    	var isValid = $(this).form('validate');			
			if (!isValid){
				$.messager.progress('close');	// 如果表单是无效的则隐藏进度条
			}
	    	// return false to prevent submit; 
			return isValid;   
	          
	    },    
	    success:function(data){  
	    	$.messager.progress('close');	// 如果提交成功则隐藏进度条
	    	var dataJson=eval('(' + data + ')')
	    	if(dataJson.pd===true && dataJson.msg=="ok"){		    	
		    	window.parent.showDialog("操作成功！");
		    	window.parent.reloadData();//重新加载
	    		if(isNew===false){	    			
	    			window.parent.closeWin();
	    			return;
	    		}
		    	clearForm();//重置表单
	    	}else{
	    		window.parent.showDialog("操作失败！");
	    	}

	    }    
	}); 	
}

function clearForm(){
	$('#ff').form('clear')
}
 

</script>
</html>