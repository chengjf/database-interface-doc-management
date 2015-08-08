App.System = DS.Model.extend({
	id: DS.attr('int'),
	name: DS.attr('string'),
	desc: DS.attr('string')
});

App.System.FIXTURES = [{
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