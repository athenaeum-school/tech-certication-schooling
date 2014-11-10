'use strict';

module.exports = function (grunt) {
    require('matchdep').filterDev('grunt-*').forEach(grunt.loadNpmTasks);

    var config = {};
    
    config['watch'] = {
        options: {
            nospawn: true
        },
        compass: {
            files: ['src/styles/{,*/}*.{scss,sass}'],
            tasks: ['compass:server']
        }, jade: {
            files: ['src/templates/{,*/}*.jade'],
            tasks: ['jade:server']
        }
    }
    
    config['compass'] = {
        options: {
            sassDir: 'src/styles/sass',
            cssDir: 'src/styles',
            importPath: 'src/bower_components',
            relativeAssets: false
        },
        dist: {},
        server: {}
    };

    config['jade'] = {
        dist: {
            files: {
                'src/index.html':
                'src/templates/index.jade',
                'src/training.html':
                'src/templates/training.jade',
                'src/products.html':
                'src/templates/products.jade'
            }
        }, server: {
            options: {
                data: {
                    debug: false
                }
            }, files: {
                'src/index.html':
                'src/templates/index.jade',
                'src/training.html':
                'src/templates/training.jade',
                'src/products.html':
                'src/templates/products.jade'
            }
        }
    };

    config['clean'] = {
    build: {
    files: [{
            dot: true,
            src: [
                  'dist/*',
                  '!dist/.git*'
                  ]
            }] }
    };
    
    config['htmlmin'] = {
    dist: {
    options: {
    collapseBooleanAttributes: true,
    removeAttributeQuotes: true,
    removeRedundantAttributes: true,
    removeEmptyAttributes: true
    },
    files: [{
            expand: true,
            cwd: 'src',
            src: '{,*/}*.html',
            dest: 'dist'
            }] }
    };
    
    config['copy'] = {
    dist: {
    files: [{
            expand: true,
            dot: true,
            cwd: 'src',
            dest: 'dist',
            src: [
                  'images/{,*/}*.png'
                  ]
            }] }
    };
    
    config['cssmin'] = {
    dist: {
    files: {
        'dist/styles/main.css': [
                                 'src/styles/{,*/}*.css'
                                 ]
    } }
    };
    
    grunt.initConfig(config);

    var tasks = [];
    
    var tasks = [
                 'clean',
                 'jade:dist',
                 'compass:dist',
                 'htmlmin:dist',
                 'copy',
                 'cssmin'
                 ];

    grunt.registerTask('build', tasks);
};
