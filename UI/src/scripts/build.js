requirejs.config({
  baseUrl: 'scripts',
  // paths: maps ids with paths (no extension)
  paths: {
    'angular': 'angular.min',
    'angular-route': 'angular-route.min'
  },
  // shim: makes external libraries reachable
  shim: {
    angular: {
      exports : 'angular'
    }
  },
  'angular-route': {
    deps: [
      'angular'
    ]
  },
  packages: [  ]
});

require([
    'angular',
    'footballManager',
    'controllers/IndexCtl'
  ],
  function(angular) {
    angular.bootstrap(document, ['footballManager']);
  }
);
