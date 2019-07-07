define(['footballManager', 'services/AccountService'], function (footballManager) { 
 
  footballManager.controller("LoginCtl", function ($scope, AccountService, $location) {
    if(AccountService.getToken()) {
      $location.url("home");
    }

    $scope.user = {username:'', password:'', repeatPassword:'', mail:''};
    $scope.rememberMe = null; 
 
    $scope.login = function () {

      var error = false;
 
      if (!$scope.user.username) {
        $scope.user.usernameError = "Enter Username";
        error = true; 
      } 
 
      if (!$scope.user.password) {
        $scope.user.passwordError = "Enter password";
        error = true; 
      } 
 
      if (!error) { 
        AccountService.login($scope.user, $scope.rememberMe).then(function (response) {
          $location.url("home");
        });
      }
    } 
  }); 
 
});
