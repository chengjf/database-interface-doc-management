window.App = Ember.Application.create({
	ready: function() {
		//alert("Welcome!");
		console.log("Start......");
	}
});

App.ApplicationAdapter = DS.FixtureAdapter.extend();

// Router
App.Router.map(function() {
	this.resource('app', {
		path: '/'
	}, function() {
		this.route("systems", {
			path: '/systems'
		});
		this.route("home");
	});
});

App.Route = Ember.Route.extend({
	activate: function() {
		console.log("Route Active");
	},
	beforeModel: function() {
		console.log('Route beforeModel');
	},
	afterModel: function() {
		console.log('Route afterModel ');
	}
});

App.HomeRoute = Ember.Route.extend({
	activate: function() {
		console.log("HomeRoute Active");
	},
	beforeModel: function() {
		console.log('HomeRoute beforeModel');
	},
	afterModel: function() {
		console.log('HomeRoute afterModel ');
	},
	model: function() {
		return ['Jackson Huang', 'Ada Li', 'JK Rush'];
	},
	setupController: function(controller, model) {
		controller.set('model', model);
	}
});

App.SystemsRoute = Ember.Route.extend({
	activate: function() {
		console.log("SystemRoute Active");
	},
	beforeModel: function() {
		console.log('SystemRoute beforeModel');
	},
	afterModel: function() {
		console.log('SystemRoute afterModel ');
	},
	model: function() {
		return ['Jackson Huang', 'Ada Li', 'JK Rush'];
	}
});

// Model
App.System = DS.Model.extend({
	id: DS.attr('int'),
	name: DS.attr('string'),
	desc: DS.attr('string')
});


// Controller
App.SystemsController = Ember.ArrayController.create({

	content: [],
	init: function() {
		// create an instance of the Song model
		// var system = App.System.create({
		// 	id: 'Son of the Morning',
		// 	name: 'Oh, Sleeper',
		// 	desc: 'Screamo'
		// });

		// console.log(system);
		// this.pushObject(system);
	}
});