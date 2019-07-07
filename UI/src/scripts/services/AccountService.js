define(['footballManager'], function(footballManager) { 
 
  footballManager.service('AccountService', function($http, $q) { 
    this.url = 'http://localhost:8080/users/';
    this.key = 'token-footballManager'; 
 
    this.saveToken = function(token, remeberMe){
      if(remeberMe) { 
        localStorage.setItem(this.key, token);
      } else { 
        sessionStorage.setItem(this.key,token);
      } 
    }; 
 
    this.getToken = function () {
      if(localStorage.getItem(this.key))
        return localStorage.getItem(this.key);
      else if(sessionStorage.getItem(this.key))
        return sessionStorage.getItem(this.key);
      else
        return null;
    }; 
 
    this.eraseToken = function () { 
      localStorage.removeItem(this.key); 
      sessionStorage.removeItem(this.key); 
    }; 
 
    this.authenticated = function(method, url, body) { 
      return $http({ 
        method: method, 
        url: url, 
        headers: {'Authorization': this.getToken()}, 
        data: body 
      }) 
    };
 
    this.createUser = function (user) {
      var body = JSON.stringify(user);
      return $http.post(this.url , body);;
    }; 
 
    this.getUser = function () { 
      return this.authenticated('GET', this.url + "user") 
    }; 
 
    this.formFormat = function(obj){ 
      var str = []; 
      for(var p in obj) 
        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p])); 
      return str.join("&"); 
    }; 
 
    this.login = function (user, remeberMe) {
      var that = this;
      return $http.post(this.url, JSON.stringify(user))
      return $http({
          method: 'POST',
          url: this.url,
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: JSON.stringify(user)
      })
        .then(function(response) { 
            that.eraseToken();
            that.saveToken(response.headers("authorization"), remeberMe);
            return $q.resolve(response);
        });
    }; 
 
    this.logout = function () { 
      this.eraseToken(); 
    }
  }); 
});
