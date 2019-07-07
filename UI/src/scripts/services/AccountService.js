define(['footballManager', 'services/SettingsService'], function(footballManager) {
 
  footballManager.service('AccountService', function($http, $q, SettingsService) {
    this.url = SettingsService.getUrl();
    this.urlLogin = this.url + 'login';
    this.urlRegister = this.url + 'register';
    this.urlUser = this.url + 'user';
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
        .catch(function (reason) {
          if (reason.status == 401) {
            this.eraseToken();
            $location.url("login");
          }
          throw reason;
        })
    };

    this.get = function (url) {
      return this.authenticated('GET', url);
    };

    this.post = function (url, body) {
      return this.authenticated('POST', url, body);
    }

    this.put = function (url, body) {
      return this.authenticated('PUT', url, body);
    }
 
    this.createUser = function (user) {
      var body = JSON.stringify(user);
      return $http.post(this.urlRegister , body);
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
      return $http({
          method: 'POST',
          url: this.urlLogin,
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
          },
          data: this.formFormat({username: user.username, password: user.password})
      })
        .then(function(response) { 
            that.eraseToken();
            that.saveToken(response.headers('Authorization'), remeberMe);
            return $q.resolve(response);
        });
    }; 
 
    this.logout = function () { 
      this.eraseToken(); 
    };

    this.isLoggedIn = function() {
      return this.get(this.urlUser);
    };
  }); 
});
