require.config({
    paths: {
        jquery: "jquery/dist/jquery",
        jquery.form:"jquery-form-plugin/jquery.form"
    },
    shim: {
        "jquery.form" : ["jquery"]
    }
});
 
/*require(['jquery'], function($) {
    alert($().jquery);
    
});*/