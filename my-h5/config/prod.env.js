'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')

module.exports = merge(prodEnv,{
  NODE_ENV: '"production"',
  H5_DOMAIN:'"http://www.xiaofengzi.top"',
  H5_ROOT:'"/my-h5"',
  API_DOMAIN:'"http://www.xiaofengzi.top/xiaofengzi"',
  API_ROOT:'"/api"',
  CHATYPE:'"1"',
  USERINFO_CHANNEL:'"xiaofengziInfo"',
})

