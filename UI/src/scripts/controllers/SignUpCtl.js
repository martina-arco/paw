define(['footballManager', 'services/AccountService'], function(footballManager) { 
 
  footballManager.controller('SignUpCtl', function($scope/*, $rootScope*/, $location, AccountService) { 
    /* 
    if(AccountService.getToken()) { 
      $location.url("home")
    } 
    */ 
 
    $scope.username = null; 
    $scope.password = null; 
    $scope.repeatPassword = null; 
 
    $scope.signup = function () {
      $scope.usernameError = ""; 
      $scope.passwordError = ""; 
      $scope.repeatPasswordError = ""; 
      var error = false; 
 
      if (!$scope.username) { 
        $scope.usernameError = /*$scope.home_enterUsername*/ "Enter Username"; 
        error = true; 
      } else if ($scope.username.length < 4 || $scope.username.length > 30) { 
        $scope.usernameError = /*$scope.register_usernameLength*/ "Username length invalid"; 
        error = true; 
      } 
 
      if (!$scope.password) { 
        $scope.passwordError = /*$scope.home_enterPassword*/ "Enter password"; 
        error = true; 
      } else if ($scope.password.length < 4 || $scope.password.length > 30) { 
        $scope.passwordError = /*$scope.register_passwordLength*/ "Password length invalid"; 
        error = true; 
      } 
 
      if ($scope.password !== $scope.repeatPassword) { 
        $scope.repeatPasswordError = /*$scope.register_passwordMatch*/ "Passwords dont match"; 
        error = true; 
      } 
 
      if (!error) { 
        AccountService.createUser($scope.username, $scope.password).then(
          function (response) {
            AccountService.login($scope.username, $scope.password, false).then( 
              function (response){
                $location.url("home");
              } 
            ) 
          }
        )
      } 
    }
  }); 
 
}); 
