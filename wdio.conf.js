// WebdriverIO Configuration (wdio.conf.js)
// This file should be placed in the root directory of your project.

// const { remote } = require('webdriverio');

// exports.config = {
//     runner: 'local',
//     specs: ['./tests/**/*.js'],
//     capabilities: [{
//         maxInstances: 1,
//         browserName: 'chrome',
//         acceptInsecureCerts: true,
//     }],
//     logLevel: 'info',
//     framework: 'mocha',
//     reporters: ['spec'],
//     mochaOpts: {
//         ui: 'bdd',
//         timeout: 60000
//     },
//     services: ['chromedriver'],
//     beforeSession: async function (config, capabilities, specs) {
//         global.browser = await remote({
//             logLevel: 'info',
//             capabilities: capabilities
//         });
//     }
// };
