define(['footballManager', 'services/AccountService'], function (footballManager) { 
 
  footballManager.controller("LoginCtl", function ($scope, AccountService, $location) {
    // if(AccountService.getToken()) {
    //   $location.url("home");
    // }

    $scope.user = {username:'', password:''};
    $scope.rememberMe = null; 
 
    $scope.login = function () {
      $scope.usernameError = "";
      $scope.passwordError = "";
      $scope.errorLogIn = "";
      var error = false;
 
      if (!$scope.user.username) {
        $scope.usernameError = "Enter Username";
        error = true; 
      } 
 
      if (!$scope.user.password) {
        $scope.passwordError = "Enter password";
        error = true; 
      } 
 
      if (!error) { 
        AccountService.login($scope.user, $scope.rememberMe).then(function (response) {
          $location.url("home");
        }, function (reason) {
          $scope.errorLogIn = "Invalid username or password";
        });
      }
    } 
  }); 
 
});
