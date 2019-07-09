define(['footballManager', 'services/AccountService'], function(footballManager) {
 
  footballManager.controller('SignUpCtl', function($scope, $location, AccountService) {
    // if(AccountService.getToken()) {
    //   $location.url("home")
    // }

    $scope.user = {username:'', password:'', mail:''};
    $scope.repeatPassword = null;

    $scope.signup = function () {
      $scope.usernameError = "";
      $scope.mailError = "";
      $scope.passwordError = "";
      $scope.repeatPasswordError = "";
      $scope.errorLogIn = "";
      var error = false; 
 
      if (!$scope.user.username) {
        $scope.usernameError = $scope.ENTERUSERNAME;
        error = true; 
      } else if ($scope.user.username.length < 4 || $scope.user.username.length > 30) {
        $scope.usernameError = $scope.USERNAMELENGTH;
        error = true; 
      }

      if(!$scope.user.mail) {
        $scope.mailError = $scope.ENTEREMAIL;
        error = true;
      }
 
      if (!$scope.user.password) {
        $scope.passwordError = $scope.ENTERPASSWORD;
        error = true; 
      } else if ($scope.user.password.length < 4 || $scope.user.password.length > 30) {
        $scope.passwordError = $scope.PASSWORDLENGTH;
        error = true; 
      } 
 
      if ($scope.user.password !== $scope.repeatPassword) {
        $scope.repeatPasswordError = $scope.NOTNULLREGISTERFORMREPEATPASSWORD;
        error = true; 
      } 
 
      if (!error) {
        //Uncomment to test login
        AccountService.createUser($scope.user).then(function(response){
          AccountService.login($scope.user, false).then(function (response) {
            $location.url("chooseTeam");
          })
        }, function (response) {
          $scope.errorLogIn = $scope.INVALIDSIGNUP;
        });

        //Just signup
        // AccountService.createUser($scope.user).then(function(response){
        //   $location.url("home");
        // });
      } 
    }
  });
}); 
