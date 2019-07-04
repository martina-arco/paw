define(['footballManager', 'services/AccountService'], function (footballManager) { 
 
  footballManager.controller("LoginCtl", function ($scope, AccountService, $location) { 
 
    if(AccountService.getToken()) { 
      $location.url("home"); 
    } 
 
    $scope.username = null; 
    $scope.password = null; 
    $scope.rememberMe = null; 
 
    $scope.login = function () { 
 
      $scope.usernameError = ""; 
      $scope.passwordError = ""; 
      let error = false; 
 
      if (!$scope.username) { 
        $scope.usernameError = /*$scope.home_enterUsername*/ "Enter Username"; 
        error = true; 
      } 
 
      if (!$scope.password) { 
        $scope.passwordError = /*$scope.home_enterPassword*/ "Enter password"; 
        error = true; 
      } 
 
      if (!error) { 
        AccountService.login($scope.username, $scope.password, $scope.rememberMe); 
        $location.url("home"); 
      } 
    } 
  }); 
 
});