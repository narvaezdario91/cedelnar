package com.narvaezjdario

import grails.plugin.springsecurity.annotation.Secured

class ProfileController {

	@Secured(value=["hasRole('ROLE_ADMIN')"])
    def index() { 
		
	}
}
