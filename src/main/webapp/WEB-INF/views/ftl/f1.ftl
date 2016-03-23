<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>测试</title>
	</head>
	<script src="${ctxStatic}/knockout/build/output/knockout-latest.debug.js"></script>
	<body>
		<h1>你好${username}</h1>
		<input data-bind="value:firstName"/>
		<input data-bind="value:lastName"/>
		<h2 data-bind="text:fullName"></h2>
		<br />
		
		<p>First name: <span data-bind="text: firstName"></span></p>
		<p>Last name: <span data-bind="text: lastName"></span></p>
		<h2>Hello, <input data-bind="value: fullName"/>!</h2>
	</body>
	<script>
		var myViewModel = {
		    personName: ko.observable('Bob'),
    		personAge: ko.observable(123),
    		firstName:ko.observable("he"),
    		lastName:ko.observable("feng")
		};
		/*
		myViewModel.fullName = ko.dependentObservable(function () {
					return this.firstName() + " " + this.lastName();
				}, myViewModel);*/
		myViewModel.fullName=ko.dependentObservable({
			read:function(){
				return this.firstName() + " " + this.lastName();
			},
			write: function (value) {
		        var lastSpacePos = value.lastIndexOf(" ");
		        if (lastSpacePos > 0) { // Ignore values with no space character
		            this.firstName(value.substring(0, lastSpacePos)); // Update "firstName"
		            this.lastName(value.substring(lastSpacePos + 1)); // Update "lastName"
		        }
		    },
		    owner: myViewModel
			
		});
		ko.applyBindings(myViewModel);
	</script>
</html>