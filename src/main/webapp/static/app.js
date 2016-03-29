require.config({
	baseUrl: "../../../static",
    paths: {
        jquery: "jquery/dist/jquery",
        bootstrap: 'bootstrap/dist/js/bootstrap',
    },
    shim: {
    	'bootstrap': {
			deps: ["jquery"]
		}
    }
});
//这种加载方式？为什么不行（baseurl是指当前文件加载路径为参照？）
//<script data-main="../../../static/app" src="../../../static/requirejs/require.js"></script>