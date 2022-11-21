'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv,{
  NODE_ENV: '"development"',
  H5_DOMAIN:'"localhost:8077"',
  H5_ROOT:'"/my-h5"',
  API_DOMAIN:'"http://localhost:8088/xiaofengzi"',
  API_ROOT:'"/api"',
  CHATYPE:'"1"',
  USERINFO_CHANNEL:'"xiaofengziInfo"',
})

