require(['jquery','bootstrap'], function($) {
   		alert($().jquery);
   		//id=1;
   		//alert(typeof id);
   		//if (window instanceof Object) alert('Y');else alert('N');
   		
   		var person = {
		    firstName: "John",
		    lastName : "Doe",
		    id       : 5566,
		    fullName : function() {
		       return this.firstName + " " + this.lastName;
		    }
		};

document.getElementById("demo").innerHTML = person.fullName; //访问 person 对象的 fullName 属性，它将作为一个定义函数的字符串返回：
   	document.getElementById("demo1").innerHTML = person.fullName();	
   		//一、外部的为全局，内部的为局部变量。
		//二、加var为局部变量(在方法内)，不加var为全局变量(当方法内有一次使用后)
   		
   		/*var golbe="global"; 
		test(); 
		function test(){ 
		     local="local"; 
		    document.write(golbe); 
		    //document.write(local); 
		}
		document.write(golbe); 
		document.write(local);
		*/
		/*在上面的test方法内，当把local变量的var去掉后，local就变成了全局变量，但是如果在局部不使用local（），则这个local作为全局是无效的。（全局取不到）
		为了验证这点，我把test方法内部唯一使用local变量的这句代码注释掉.发现在外部也打印不出来了。
		总结：全局变量可以不声明var 函数内变量必须声明var，在定义全局变量时加或不加var关键字没什么影响；但在定义局部变量时如果不加var关键字javascript解释程序会将其解释为全局变量。*/
});