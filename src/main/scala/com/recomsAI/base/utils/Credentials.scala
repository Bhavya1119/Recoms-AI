package com.recomsAI.base.utils

case class Credentials(clientID : String, clientSecret : String , credentialType : String)

case class BaseConfig(credentials : Credentials)
