Todos.TodosController = Ember.ArrayController.extend({
  actions: {
    createTodo: function() {
      // Get the todo title set by the "New Todo" text field
      var title = this.get('newTitle');
      if (!title.trim()) { return; }

      // Create the new Todo model
      console.log("createTodo begin");
      var todo = this.store.createRecord('todo', {
        title: title,
        isCompleted: false
      });

      console.log("createTodo end");

      // Clear the "New Todo" text field
      this.set('newTitle', '');

      console.log("createTodo start save");
      // Save the new model
      todo.save();

      console.log("createTodo end save");
    },

	removeCompeleted : function(){
		var completed = this.filterBy('isCompleted', true);
		completed.invoke('deleteRecord');
		completed.invoke('save');
	}
  },

  remaining : function(){
	return this.filterBy("isCompleted",false).get("length");
  }.property("@each.isCompleted"),

	inflection : function(){
		var remaining = this.get("remaining");
		return remaining === 1?"item":"items";
	}.property("remaining"),

compeleted : function(){
	return this.filterBy("isCompleted",true).get("length");
  }.property("@each.isCompleted"),

  allAreDone: function(key, value) {
      if (value === undefined) {
        return !!this.get('length') && this.isEvery('isCompleted', true);
      } else {
        this.setEach('isCompleted', value);
        this.invoke('save');
        return value;
      }
  }.property('@each.isCompleted')
  
});