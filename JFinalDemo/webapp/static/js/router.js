App.Router.map(function() {
	this.resource('app', {
		path: '/'
	}, function() {
		this.route("system");
		this.route("home");
	});
});

App.Route = Ember.Route.extend({
	model: function() {
		return ['Jackson Huang', 'Ada Li', 'JK Rush'];
	},
	setupController: function(controller) {
		controller.set('title', "My App");
	}
});

App.IndexRoute = Ember.Route.extend({
	model: function() {
		return ['Jackson Huang', 'Ada Li', 'JK Rush'];
	}

});

App.SystemRoute = Ember.Route.extend({
	model: function() {
		return [{
			id: 1,
			name: '吃饭',
			desc: 'eat'
		}, {
			id: 2,
			name: '睡觉',
			desc: 'sleep'
		}, {
			id: 3,
			name: '打豆豆',
			desc: 'beat bean'
		}];
	}
});

App.HomeRoute = Ember.Route.extend({
	model: function() {
		return ['Jackson Huang', 'Ada Li', 'JK Rush'];
	}
});


// App.AppActiveRoute = Ember.Route.extend({
// 	model: function(){
// 		return this.store.filter("todo", function(todo){
// 			return !todo.get("isCompleted");
// 		});
// 	},

// 	renderTemplate:function(controller){
// 		this.render("app/index",{controller:controller});
// 	}
// });

// App.AppCompletedRoute = Ember.Route.extend({
// 	model: function(){
// 		return this.store.filter("todo", function(todo){
// 			return todo.get("isCompleted");
// 		});
// 	},

// 	renderTemplate:function(controller){
// 		this.render("app/index",{controller:controller});
// 	}
// });