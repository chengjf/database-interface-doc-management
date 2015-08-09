App = Ember.Application.create();

App.Router.map(function() {
  // put your routes here
  this.resource("systems");
});

App.IndexRoute = Ember.Route.extend({
  model: function() {
    // return ['red', 'yellow', 'blue'];
    return this.store.findAll('system');
  }
});

App.IndexController = Ember.Controller.extend({
  actions: {
    doSomething: function(){
      alert('IndexController');
    }
  }
});



App.SystemsRoute = Ember.Route.extend({
  model: function() {
    return this.store.findAll('system');
  },
  setupController: function(controller,model) {
     controller.set('model',model);
  }
});

App.SystemsController = Ember.Controller.extend({
  actions: {
    doSomething: function(){
      alert('SystemsController');
    },

    add: function(){
      var system = this.store.createRecord('system');
      Em.Logger.info(system.get('controller'));
      system.set('isCreating', 'true');
    }
  }
});


App.SystemController = Ember.ObjectController.extend({
  isEditing: false,
  
  isCreating: Ember.computed('id', 'name', 'desc',function() {
    var flag = this.get('model').get('isCreating')
    return flag === undefined ? false : flag;
  }),

  actions: {
    edit: function() {
      this.set('isEditing', true);
    },
    save: function() {
      Em.Logger.info("SystemController save");
      this.set('isEditing', false);
      this.set('isCreating', false);
      this.get("model").save();
      Em.Logger.info(this.get("model").get('isCreating'));
    },
    delete: function(){
      if (window.confirm('Are you sure you want to delete this record?')) {
        var system = this.get("model");
        system.deleteRecord();
        system.save();
      }
    },
    cancelEdit: function( model ) {
      var system = this.get("model");
      this.set('isEditing', false);
    },
    cancelSave: function( model ) {
      var system = this.get("model");
      system.deleteRecord();
    },
    create: function(){
      var system = this.get("model");

      Em.Logger.info(system.get('id'));
      system.save();
    }
  }
});



App.ItemController = Ember.Controller.extend({
  actions: {
    doSomething: function(){
      alert(this.model);
      return true;
    }
  }
});

App.ApplicationAdapter = DS.RESTAdapter.extend({
  namespace: 'api/v1'
});

// Model
App.System = DS.Model.extend({
  number : DS.attr('string'),
  name: DS.attr('string'),
  desc: DS.attr('string')
});

App.EditSystemView = Ember.TextField.extend({
  didInsertElement: function() {
    this.$().focus();
  }
});

Ember.Handlebars.helper('edit-system', App.EditSystemView);
