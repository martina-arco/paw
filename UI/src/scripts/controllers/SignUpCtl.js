define(['footballManager', 'services/AccountService'], function(footballManager) {
 
  footballManager.controller('SignUpCtl', function($scope, $location, AccountService) {
    // if(AccountService.getToken()) {
    //   $location.url("home")
    // }

    $scope.user = {username:'', password:'', mail:''};
    $scope.repeatPassword = null;

    $scope.signup = function () {
      $scope.usernameError = "";
      $scope.passwordError = "";
      $scope.repeatPasswordError = "";
      var error = false; 
 
      if (!$scope.user.username) {
        $scope.usernameError = "Enter Username";
        error = true; 
      } else if ($scope.user.username.length < 4 || $scope.user.username.length > 30) {
        $scope.usernameError = "Username length invalid";
        error = true; 
      } 
 
      if (!$scope.user.password) {
        $scope.passwordError = "Enter password";
        error = true; 
      } else if ($scope.user.password.length < 4 || $scope.user.password.length > 30) {
        $scope.passwordError = "Password length invalid";
        error = true; 
      } 
 
      if ($scope.user.password !== $scope.repeatPassword) {
        $scope.repeatPasswordError = "Passwords do not match";
        error = true; 
      } 
 
      if (!error) {
        //Uncomment to test login
        // AccountService.createUser($scope.user).then(function(response){
        //   AccountService.login($scope.user, false).then(function (response) {
        //     $location.url("home");
        //   })
        // });

        //Just signup
        AccountService.createUser($scope.user).then(function(response){
          $location.url("home");
        });
      } 
    }
  });
}); 
