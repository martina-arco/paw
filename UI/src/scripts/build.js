requirejs.config({
  baseUrl: 'scripts',
  paths: {
    angular: '../../bower_components/angular/angular',
    'angular-route': '../../bower_components/angular-route/angular-route',
    'angular-translate': '../../bower_components/angular-translate/angular-translate',
    jquery: '../../bower_components/jquery/dist/jquery',
    requirejs: '../../bower_components/requirejs/require',
    bootstrap: '../../bower_components/bootstrap/dist/js/bootstrap.bundle',
    collapse: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/collapse',
    plugins: '../../src/assets/js/plugins',
    main: '../../src/assets/js/main',
    json3: '../../bower_components/json3/lib/json3',
    chart: '../../bower_components/chart.js/dist/Chart',
    'angular-chart': '../../bower_components/angular-chart.js/dist/angular-chart',
    ngDialog: '../../bower_components/ng-dialog/js/ngDialog',
    affix: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/affix',
    alert: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/alert',
    button: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/button',
    carousel: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/carousel',
    dropdown: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/dropdown',
    tab: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tab',
    transition: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/transition',
    scrollspy: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/scrollspy',
    modal: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/modal',
    tooltip: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/tooltip',
    popover: '../../bower_components/bootstrap-sass-official/assets/javascripts/bootstrap/popover',
    'ng-dialog': '../../bower_components/ng-dialog/js/ngDialog',
    tslib: '../../bower_components/tslib/tslib'
  },
  shim: {
    angular: {
      exports: 'angular',
      deps: [
        'jquery'
      ]
    },
    'angular-chart': {
      deps: [
        'angular',
        'chart'
      ]
    },
    'angular-route': {
      deps: [
        'angular'
      ]
    },
    bootstrap: {
      deps: [
        'jquery'
      ]
    },
    ngDialog: {
      deps: [
        'angular'
      ]
    },
    collapse: {
      deps: [
        'angular',
        'jquery',
        'bootstrap'
      ]
    },
    'angular-translate': {
      deps: [
        'angular'
      ]
    }
  },
  packages: [

  ]
});

if (paths) {
  require.config({
    paths: paths
  });
}

require([
    'angular',
    'jquery',
    'bootstrap',
    'footballManager',
    'controllers/IndexCtl'
  ],
  function() {
    angular.bootstrap(document, ['footballManager']);
  }
);
