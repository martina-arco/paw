define(['footballManager', 'services/AccountService'], function (footballManager) { 
 
  footballManager.controller("LoginCtl", function ($scope, AccountService, $location) {
    AccountService.isLoggedIn().then(function () {
      $location.url("");
    }, function () {});

    $scope.user = {username:'', password:''};
    $scope.rememberMe = null; 
 
    $scope.login = function () {
      $scope.usernameError = "";
      $scope.passwordError = "";
      $scope.errorLogIn = "";
      var error = false;
 
      if (!$scope.user.username) {
        $scope.usernameError = $scope.ENTERUSERNAME;
        error = true; 
      } 
 
      if (!$scope.user.password) {
        $scope.passwordError = $scope.ENTERPASSWORD;
        error = true; 
      } 
 
      if (!error) { 
        AccountService.login($scope.user, $scope.rememberMe).then(function (response) {
          $location.url("home");
        }, function (reason) {
          $scope.errorLogIn = $scope.INVALIDLOGIN;
        });
      }
    } 
  }); 
 
});
