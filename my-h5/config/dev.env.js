'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv,{
  NODE_ENV: '"development"',
  H5_DOMAIN:'"http://localhost:8088"',
  H5_ROOT:'""',
  API_DOMAIN:'"http://localhost:8088"',
  API_ROOT:'"/api"',
  CHATYPE:'"1"',
  USERINFO_CHANNEL:'"xiaofengziInfo"',
})

