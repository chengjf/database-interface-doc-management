Todos.Todo = DS.Model.extend({
  title: DS.attr('string'),
  isCompleted: DS.attr('boolean')
});

Todos.Todo.FIXTURES = [
 {
   id: 1,
   title: '吃饭',
   isCompleted: true
 },
 {
   id: 2,
   title: '睡觉',
   isCompleted: false
 },
 {
   id: 3,
   title: '打豆豆',
   isCompleted: false
 }
];