package com.sangmeebee.teamfreshproject.domain.util

open class IllegalSignInException : IllegalArgumentException()
class IllegalIdException : IllegalSignInException()
class IllegalPasswordException : IllegalSignInException()

class HttpConnectionException : IllegalStateException()
