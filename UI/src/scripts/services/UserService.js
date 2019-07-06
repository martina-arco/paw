define(['footballManager'], function(footballManager) {

  footballManager.service('UserService', function($http) {
    this.url = 'http://localhost:8080/webapp_war_exploded/users/';

    this.createUser = function (user) {
        var body = JSON.stringify(user);
        return $http.post(this.url, body);
    };
  })
});
