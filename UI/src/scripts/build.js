requirejs.config({
  baseUrl: 'scripts',
  // paths: maps ids with paths (no extension)
  paths: {
    'angular': 'angular.min',
    'angular-route': 'angular-route.min',
    'jquery': 'jquery.min'
  },
  // shim: makes external libraries reachable
  shim: {
    angular: {
      exports : 'angular',
      deps: [
        'jquery'
      ]
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
  function() {
    angular.bootstrap(document, ['footballManager']);
  }
);
