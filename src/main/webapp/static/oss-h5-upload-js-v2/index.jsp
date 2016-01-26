<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<title>OSS web直传</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/common/oss-h5-upload-js-v2/style.css"/>
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
</head>
<body>

<h2>OSS web直传---在服务端servlet签名</h2>
<ol>
<li>基于plupload封装 </li>
<li>支持html5,flash,silverlight,html4 等协议上传</li>
<li>可以运行在PC浏览器，手机浏览器，微信</li>
<li>签名在服务端(servlet)完成, 安全可靠, 推荐使用!</li>
<li>可以选择多文件上传</li>
<li>显示上传进度条</li>
<li>可以控制上传文件的大小</li>
<li>注意一点:bucket必须设置了Cors(Post打勾）,不然没有办法上传</li>
<li>注意一点:这里返回的success,是OSS已经回调应用服务器，应用服务已经返回200!</li>
</ol>
<br>

<h4>您所选择的文件列表：</h4>
<div id="ossfile">你的浏览器不支持flash,Silverlight或者HTML5！</div>

<br/>

<div id="container">
	<a id="selectfiles" href="javascript:void(0);" class='btn'>选择文件</a>
	<a id="postfiles" href="javascript:void(0);" class='btn'>开始上传</a>
</div>

<pre id="console"></pre>

<p>&nbsp;</p>

</body>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/oss-h5-upload-js-v2/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/common/oss-h5-upload-js-v2/upload.js"></script>
</html>
