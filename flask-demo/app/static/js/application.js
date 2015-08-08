window.App = Ember.Application.create({
	ready: function() {
		console.log("Start......");
	},
	// For debugging, log the information as below.
	LOG_TRANSITIONS: true,
	LOG_TRANSITIONS_INTERNAL: true
});



// Router
App.Router.map(function() {
	this.resource('app', {
		path: '/'
	}, function() {
		this.route("systems", {
			path: '/systems'
		}, function() {
			this.route("add")
		});
		this.route("home");
	});

	this.resource('newsystem', {
		path: 'story/new'
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

App.IndexAdapter = DS.RESTAdapter.extend({});

App.AppHomeRoute = Ember.Route.extend({
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

App.AppSystemsRoute = Ember.Route.extend({
	activate: function() {
		console.log("SystemsRoute Active");
	},
	beforeModel: function() {
		console.log('SystemsRoute beforeModel');
	},
	afterModel: function() {
		console.log('SystemsRoute afterModel ');
	},
	model: function() {
		return Ember.$.getJSON('http://127.0.0.1:5000/api/v1/systems').then(function(data) {
			console
			return data.objects;
		});
	}
});

App.AppSystemsAddRoute = Ember.Route.extend({
	activate: function() {
		console.log("AppSystemsAddRoute Active");
	},
	beforeModel: function() {
		console.log('AppSystemsAddRoute beforeModel');
	},
	afterModel: function() {
		console.log('AppSystemsAddRoute afterModel ');
	}
});


// Model
App.System = DS.Model.extend({
	name: DS.attr('string'),
	desc: DS.attr('string')
});


// Controller
App.AppSystemsController = Ember.ArrayController.extend();

App.SystemController = Ember.ObjectController.extend({
	isEditing: false,
	actions: {
		edit: function() {
			this.set('isEditing', true);
		},
		save: function() {
			console.lgo("SystemController save");
			this.content.save();
			this.set('isEditing', false);
		},
		cancel: function() {
			thi.set('isEditing', false);
			this.content.rollback();
		},
		add: function() {
			cnosole.log("1 add");
		}
	}
});

App.AppSystemsAddController = Ember.ObjectController.extend({
	isEditing: false,
	actions: {
		edit: function() {
			this.set('isEditing', true);
		},
		save: function() {
			this.content.save();
			this.set('isEditing', false);
		},
		cancel: function() {
			thi.set('isEditing', false);
			this.content.rollback();
		},
		add: function() {
			console.log("2 add");
			var id = $("#id").val();
			var name = $("#name").val();
			var desc = $("#desc").val();
			var store = this.get('store');
			var system = store.createRecord('system', {
				id: id,
				name: name,
				desc: desc
			});
			system.save();
		}
	}
});


App.SystemAdapter = DS.RESTAdapter.extend({
	bulkCommit: false,
	host: 'http://127.0.0.1:5000',
	namespace: "api/v1"
})