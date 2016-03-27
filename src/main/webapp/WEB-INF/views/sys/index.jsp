<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#testjson").click(function(){
			var saveDataAry=[];  
	        var data1={"id":1,"username":"test","password":"gz"};  
	        var data2={"id":2,"username":"何锋","password":"gr"};  
	        saveDataAry.push(data1);  
	        saveDataAry.push(data2); 
			$.ajax({
                url: "${ctx}/test/testjson",
                type: "POST",  
                contentType: "application/json",//发送数据到服务器时所使用的内容类型。默认是："application/x-www-form-urlencoded"。
                dataType: "json",//预期的服务器响应的数据类型（返回的data？）
                data:JSON.stringify(saveDataAry),
                success: function(data) {
                    console.log(data);
                },
                error: function() {
                    alert("系统发生异常，请稍候再试！\n\n有任何疑问，请联系系统管理员！");
                }
            });
            /* $.getJSON("test/testjson",function(data){
            	alert(data);
            }); */
			return false;
		});
		
		//$(".media").media({width:1366, height:400});
		
		var setting = {data:{simpleData:{enable:true,idKey:"id",pIdKey:"pId",rootPId:'0'}},
				callback:{onClick:function(event, treeId, treeNode){
						var id = treeNode.id == '0' ? '' :treeNode.id;
						//$('#officeContent').attr("src","${ctx}/sys/user/list?office.id="+id+"&office.name="+treeNode.name);
					}
				}
			};
		var data =[
				{ id:1, pId:0, name:"SO"},
		         { id:11, pId:1, name:"SO-E1"},
		         { id:111, pId:11, name:"SO-E1-S1"},
		         { id:112, pId:11, name:"SO-E1-S2"},
		         { id:12, pId:1, name:"SO-E11"},
		         { id:121, pId:12, name:"SO-E11-S11"},
		         { id:122, pId:12, name:"SO-E11-S22"}
		      ]
		//$.getJSON("${ctx}/sys/office/treeData",function(data){
			//$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
		//});
		
		var uploader = new plupload.Uploader({
			runtimes : 'html5,flash,silverlight,html4',
			browse_button : 'pickfiles', // you can pass in id...
			container: document.getElementById('container'), // ... or DOM Element itself
			url : '${ctx}/test/testplupload',
			flash_swf_url : '${ctxStatic}/oss-h5-upload-js-v2/lib/plupload-2.1.2/js/Moxie.swf',
			silverlight_xap_url : '${ctxStatic}/oss-h5-upload-js-v2/lib/plupload-2.1.2/js/Moxie.xap',
			
			max_file_size : '1024mb',
			chunk_size : '9mb',
			multipart_params: {
				  "one": '1',
				  "tr2":"何锋"
			},

			init: {
				PostInit: function() {
					document.getElementById('filelist').innerHTML = '';

					document.getElementById('uploadfiles').onclick = function() {
						uploader.start();
						return false;
					};
				},

				FilesAdded: function(up, files) {
					plupload.each(files, function(file) {
						document.getElementById('filelist').innerHTML += '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ') <b></b></div>';
					});
				},

				UploadProgress: function(up, file) {
					document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
				},

				Error: function(up, err) {
					document.getElementById('console').innerHTML += "\nError #" + err.code + ": " + err.message;
				}
			}
		});
		uploader.init();
	});
</script>
</head>
<body>
	<h1>这是首页。。。。。。。</h1><a href="${ctx}/sys/logout">退出</a><br>
	<a class="media" href="http://hftest.oss-cn-beijing.aliyuncs.com/upload/file/20151127/1448602013595027188.pdf">pdf</a><br>
	
	<div id="ztree" class="ztree"></div>
	
	<div id="filelist">Your browser doesn't have Flash, Silverlight or HTML5 support.</div>
	<br />
	<div id="container">
	    <a id="pickfiles" href="javascript:;">[Select files]</a> 
	    <a id="uploadfiles" href="javascript:;">[Upload files]</a>
	</div>
	<br />
	<div id="console"></div>
	
	<a href="test/i18n?locale=en_US">英文</a>&nbsp;
	<a href="test/i18n?locale=zh_CH">中文</a>
	<br>
	<form action="test/testFileUpload" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file">
		Desc:<input type="text" name="desc">
		<input type="submit" value="sumit">
	</form>
	<br>
	<a href="${ctx}/test/downloadfile">downloadFile</a>
	<br>
	<a href="test/testResponseEntity">testResponseEntity</a>
	<br>
	<shiro:hasPermission name="sys:view">
	<form action="test/testHttpMessageConverter" method="post" enctype="multipart/form-data">
		File:<input type="file" name="file">
		Desc:<input type="text" name="desc">
		<input type="submit" value="sumit">
	</form>
	<br>
	<a href="#" id="testjson">testjson</a>
	<br>
	<a href="list">list</a>
	<br>
	<a href="testRedirect">testRedirect</a>
	<br>
	<a href="testView">testView</a>
	<br>
	<%String s = session.getId(); //获取session ID号  %>
	<p>你的session对象ID是：</p>
	<%=s %>
	<br />
	<a href="helloworld/hello?username=hf&age=11">点我测试spring mvc</a>
	<br />
	<a href="helloworld/hellopost">点我测试spring mvc post</a>
	<br />

	<a href="helloworld/testPathVariable/121">点我testPathVariable/</a>
	<br />
	<form action="helloworld/hellopost" method="post">
		<input type="submit" value="form post提 交">
	</form>
	<br>
	<br>
	<a href="helloworld/testRestGet/112">点我测试Rest get</a>
	<br />
	<br>
	<br>
	<form action="helloworld/testRestPost" method="post">
		<input type="submit" value="Rest post提 交">
	</form>
	<br>
	<br>
	<form action="helloworld/testRestPut/113" method="post">
		<input type="hidden" name="_method" value="PUT"> <input
			type="submit" value="Rest put提 交">
	</form>
	<br>
	<br>
	<form action="helloworld/testRestDelete/114" method="post">
		<input type="hidden" name="_method" value="DELETE"> <input
			type="submit" value="Rest delete提 交">
	</form>

	<br>
	<br>
	<a href="helloworld/testRequestParam?username=hf&age=18">点我测试RequestParam</a>
	<br />

	<a href="helloworld/testRequestHeaders?username=hf&age=18">点我测试RequestHeaders</a>
	<br />

	<a href="helloworld/testCookieValue?username=hf&age=18">点我测试testCookieValue</a>
	<br />

	<form action="helloworld/testPojo" method="post">
		姓名<input type="text" name="name"><br> 性别（男、女）<input
			type="text" name="sex"><br> car id<input type="text"
			name="cars[0].id"><br> car name<input type="text"
			name="cars[0].name"><br> car password<input type="text"
			name="cars[0].password"><br> car price<input type="text"
			name="cars[0].price"><br> <input type="submit"
			value="提交Pojo">
	</form>
	<br>
	<a href="helloworld/testServletAPI">点我测试testServletAPI</a>
	<br />
	<a href="helloworld/testModelAndView">点我测试ModelAndView</a>
	<br />

	<a href="helloworld/testMap">点我测试map</a>
	<br />

	<a href="helloworld/testSessionAttributes">点我测试SessionAttributes</a>
	<br />

	<form action="helloworld/testModelAttribute" method="post">
		<input type="hidden" name="id" value="11"><br> car name<input
			type="text" name="name" value="奔驰"><br> car price<input
			type="text" name="price" value="1.3f"><br> <input
			type="submit" value="testModelAttribute">
	</form>
	</shiro:hasPermission>
</body>
</html>